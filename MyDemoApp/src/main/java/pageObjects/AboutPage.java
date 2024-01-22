package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class AboutPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='about screen']//android.widget.TextView[contains(@text,'build')]")
	private WebElement build;
	
	public AboutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public String getBuild()
	{
		return build.getText();
	}
}
