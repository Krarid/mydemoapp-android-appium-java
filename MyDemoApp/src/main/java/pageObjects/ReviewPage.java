package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ReviewPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "product label")
	private WebElement productLabel;
	
	@AndroidFindBy(accessibility = "product price")
	private WebElement productPrice;
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'circle')]")
	private WebElement color;
	
	@AndroidFindBy(accessibility = "total price")
	private WebElement totalPrice;
	
	@AndroidFindBy(accessibility = "Place Order button")
	private WebElement placeOrderButton;
	
	public ReviewPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getProduct()
	{
		return productLabel.getText();
	}
	
	public String getPrice()
	{
		return productPrice.getText();
	}
	
	public String getColor()
	{
		return color.getAttribute("content-desc");
	}
	
	public String getTotalPrice()
	{
		return totalPrice.getText();
	}
	
	public CompletePage placeOrder()
	{
		placeOrderButton.click();
		return new CompletePage(driver);
	}
}
