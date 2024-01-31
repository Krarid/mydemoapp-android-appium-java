package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import pageObjects.MenuPage;
import utils.BaseTest;

public class Reset extends BaseTest {

	@Test(groups= {"Smoke"})
	public void ResetAppState()
	{
		MenuPage menu = new MenuPage(driver);
		
		menu.resetAppState(true);
		
		Assert.assertEquals(menu.getResetTitle(), "App State has been reset.");
		
		menu.ok();
	}
	
	@Test
	public void CancelResetAppState() throws InterruptedException
	{
		boolean reset = true;
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		MenuPage menu = new MenuPage(driver);
		
		menu.resetAppState(false);
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[contains(@text, 'App State has been reset.')]")));
		} catch(Exception e) {
			Assert.assertTrue(true);
			reset = false;
		}
		
		if(reset) {
			menu.ok();
			driver.navigate().back();
			Assert.assertTrue(false);
		}
	}
}
