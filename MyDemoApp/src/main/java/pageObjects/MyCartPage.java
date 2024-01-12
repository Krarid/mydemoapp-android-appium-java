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
}
