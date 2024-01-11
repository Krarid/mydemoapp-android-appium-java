package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class MyCartPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Proceed To Checkout button")
	private WebElement proceedButton;
	
	public MyCartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public LoginPage proceedToCheckout()
	{
		proceedButton.click();
		return new LoginPage(driver);
	}
}
