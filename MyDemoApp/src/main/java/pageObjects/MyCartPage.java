package pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class MyCartPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Proceed To Checkout button")
	private WebElement proceedButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='remove item']")
	private WebElement removeButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='container header']/android.widget.TextView")
	private WebElement noItems;
	
	@AndroidFindBy(accessibility = "Go Shopping button")
	private WebElement goShoppingButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='counter plus button']")
	private WebElement counterPlusButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='counter minus button']")
	private WebElement counterMinusButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='counter amount']/android.widget.TextView")
	private WebElement counterAmount;
	
	@AndroidFindBy(accessibility = "total number")
	private WebElement totalNumber;
	
	@AndroidFindBy(accessibility = "total price")
	private WebElement totalPrice;
	
	public MyCartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public ShipmentPage proceedToCheckout() throws IOException
	{
		Properties property = new Properties();
		FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		
		property.load(propertyFile);
		
		proceedButton.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		boolean loggedIn = false;
		
		try {
			wait.until(ExpectedConditions.textToBePresentInElementLocated(AppiumBy.xpath("//*[@content-desc='container header']/android.widget.TextView"), "Login"));
		} catch(Exception e) {
			loggedIn = true;
		}
		
		if(!loggedIn)
		{
			LoginPage login = new LoginPage(driver);
			login.login(property.getProperty("username"), property.getProperty("password"));
		}
		
		return new ShipmentPage(driver);
	}
	
	public void removeItem()
	{
		removeButton.click();
	}
	
	public void goShopping()
	{
		goShoppingButton.click();
		scrollToElement("Products");
	}
	
	public String getNoItemsText()
	{
		return noItems.getText();
	}
	
	public void increment(int items)
	{
		for(int i = 1; i < items; i++) {
			counterPlusButton.click();
		}
	}
	
	public void decrement(int items)
	{
		 while(getCounterAmount() > 0 && getCounterAmount() != items) {
			 counterMinusButton.click();
		 }
	}
	
	public int getCounterAmount()
	{
		System.out.println(counterAmount.getText());
		return Integer.parseInt(counterAmount.getText());
	}
	
	public String getTotalPrice()
	{
		return totalPrice.getText();
	}
	
	public int getTotalNumberOfItems()
	{
		return Integer.parseInt(totalNumber.getText().split(" ")[0]);
	}
}
