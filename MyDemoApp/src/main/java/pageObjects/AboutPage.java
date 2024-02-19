package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class AboutPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='about screen']//android.widget.TextView[contains(@text,'build')]")
	private WebElement build;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='about screen']//android.widget.TextView[contains(@text,'Go to the Sauce Labs website.')]")
	private WebElement sauceLabsWebsite;
	
	public AboutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getBuild()
	{
		return build.getText();
	}
	
	public void goToSauceLabs()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		sauceLabsWebsite.click();
		
		wait.until(ExpectedConditions.invisibilityOf(sauceLabsWebsite));
	}
}
