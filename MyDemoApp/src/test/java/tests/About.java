package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AboutPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class About extends BaseTest {
	
	@Test
	public void VerifyLastBuild()
	{
		MenuPage menu = new MenuPage(driver);
		AboutPage about = menu.goToAbout();
		
		Assert.assertTrue(about.getBuild().contains("V.1.3.0-build 244"));
	}
	
	@Test
	public void GoToTheSauceLabsExternalSite()
	{
		MenuPage menu = new MenuPage(driver);
		AboutPage about = menu.goToAbout();
		
		about.goToSauceLabs();
		
		String nativeContext = "", browserContext = "";

		for(String contextName : driver.getContextHandles() ) {
			if(contextName.contains("NATIVE"))
				nativeContext = contextName;
			else
				browserContext = contextName;
		}
		
		driver.context(browserContext);
		
		WebElement logo = driver.findElement(By.xpath("//header//img[@alt='Saucelabs']"));
		
		Assert.assertEquals(logo.getAttribute("alt"), "Saucelabs");
		
		driver.context(nativeContext);
		
		driver.navigate().back();
	}
}