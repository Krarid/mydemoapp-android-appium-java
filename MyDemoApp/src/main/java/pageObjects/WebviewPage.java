package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class WebviewPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "URL input field")
	private WebElement urlField;
	
	@AndroidFindBy(accessibility = "Go To Site button")
	private WebElement goToSiteButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='URL-error-message']/android.widget.TextView")
	private WebElement urlErrorMessage;
	
	public WebviewPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void enterUrl(String url)
	{
		urlField.sendKeys(url);
	}
	
	public void goToSite()
	{
		goToSiteButton.click();
	}
	
	public String getUrlErrorMessage()
	{
		return urlErrorMessage.getText();
	}
}
