package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MenuPage;
import pageObjects.WebviewPage;
import utils.BaseTest;

public class Webview extends BaseTest {
	@Test
	public void GoToExternalSite()
	{
		MenuPage menu = new MenuPage(driver);
		WebviewPage webview = menu.goToWebview();
		webview.enterUrl("https://www.javimele.com");
		webview.goToSite();
		
		String nativeContext = "", browserContext = "";

		for(String contextName : driver.getContextHandles() ) {
			if(contextName.contains("NATIVE"))
				nativeContext = contextName;
			else
				browserContext = contextName;
		}
		
		driver.context(browserContext);
		
		WebElement greeting = driver.findElement(By.className("greeting"));
		Assert.assertTrue(greeting.getText().contains("Javier Meléndez Zacarías"));
		
		driver.context(nativeContext);
	}
	
	@Test
	public void errorMessageWithIncorrectURL()
	{
		MenuPage menu = new MenuPage(driver);
		WebviewPage webview = menu.goToWebview();
		webview.enterUrl("test");
		webview.goToSite();
		
		Assert.assertEquals(webview.getUrlErrorMessage(), "Please provide a correct https url.");
	}
}