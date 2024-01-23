package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class LoginPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Username input field")
	private WebElement username;
	
	@AndroidFindBy(accessibility = "Password input field")
	private WebElement password;
	
	@AndroidFindBy(accessibility = "Login button")
	private WebElement loginButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='generic-error-message']/android.widget.TextView")
	private WebElement errorMessage;
	
	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void enterUsername(String username)
	{
		this.username.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		this.password.sendKeys(password);
	}
	
	public void clear()
	{
		username.clear();
		password.clear();
	}
	
	public ShipmentPage login()
	{
		loginButton.click();
		return new ShipmentPage(driver);
	}
	
	public void login(String username, String password)
	{
		clear();
		enterUsername(username);
		enterPassword(password);
		loginButton.click();
	}
	
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}
}
