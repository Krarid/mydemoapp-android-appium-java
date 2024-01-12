package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class PaymentPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Full Name* input field")
	private WebElement fullName;
	
	@AndroidFindBy(accessibility = "Card Number* input field")
	private WebElement cardNumber;
	
	@AndroidFindBy(accessibility = "Expiration Date* input field")
	private WebElement expiration;
	
	@AndroidFindBy(accessibility = "Security Code* input field")
	private WebElement securityCode;
	
	@AndroidFindBy(accessibility = "Review Order button")
	private WebElement reviewOrderButton;
	
	public PaymentPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void enterFullName(String fullName)
	{
		this.fullName.sendKeys(fullName);
	}
	
	public void enterCardNumber(String cardNumber)
	{
		this.cardNumber.sendKeys(cardNumber);
	}
	
	public void enterExpiration(String expiration)
	{
		this.expiration.sendKeys(expiration);
	}
	
	public void enterSecurityCode(String securityCode)
	{
		this.securityCode.sendKeys(securityCode);
	}
	
	public ReviewPage reviewOrder()
	{
		reviewOrderButton.click();
		return new ReviewPage(driver);
	}
}
