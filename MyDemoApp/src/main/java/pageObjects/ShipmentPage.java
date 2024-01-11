package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ShipmentPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Full Name* input field")
	private WebElement fullName;
	
	@AndroidFindBy(accessibility = "Address Line 1* input field")
	private WebElement address;
	
	@AndroidFindBy(accessibility = "City* input field")
	private WebElement city;
	
	@AndroidFindBy(accessibility = "State/Region input field")
	private WebElement state;
	
	@AndroidFindBy(accessibility = "Zip Code* input field")
	private WebElement zipCode;
	
	@AndroidFindBy(accessibility = "Country* input field")
	private WebElement country;
	
	@AndroidFindBy(accessibility = "To Payment button")
	private WebElement paymentButton;
	
	public ShipmentPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void enterFullName(String fullName)
	{
		this.fullName.sendKeys(fullName);
	}
	
	public void enterAddress(String address)
	{
		this.address.sendKeys(address);
	}
	
	public void enterCity(String city)
	{
		this.city.sendKeys(city);
	}
	
	public void enterState(String state)
	{
		this.state.sendKeys(state);
	}
	
	public void enterZipCode(String zipCode)
	{
		this.zipCode.sendKeys(zipCode);
	}
	
	public void enterCountry(String country)
	{
		this.country.sendKeys(country);
	}
	
	public PaymentPage toPayment()
	{
		paymentButton.click();
		return new PaymentPage(driver);
	}
}
