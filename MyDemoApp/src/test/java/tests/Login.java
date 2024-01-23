package tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import pageObjects.LoginPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class Login extends BaseTest {

	@Test
	public void LoginValidCredentials()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		MenuPage menu = new MenuPage(driver);
		LoginPage login = menu.goToLogin();
		
		login.login("bob@example.com", "10203040");
		
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='container header']/android.widget.TextView"), "Products"));
		} catch(Exception e) {
			Assert.assertTrue(false);
		}

		Assert.assertTrue(true);
	}
}
