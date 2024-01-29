package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class APIcallsPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.view.ViewGroup[2]")
	private WebElement eudcButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.view.ViewGroup[3]")
	private WebElement usdcButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.view.ViewGroup[4]")
	private WebElement unauthorizedButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.view.ViewGroup[5]")
	private WebElement notFoundButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private WebElement unauthorized;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='api calls screen']/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private WebElement notFound;
	
	public APIcallsPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void goToEUDC()
	{
		eudcButton.click();
	}
	
	public void goToUSDC()
	{
		usdcButton.click();
	}
	
	public void goToUnauthorized()
	{
		unauthorizedButton.click();
	}
	
	public void goToNotFound()
	{
		notFoundButton.click();
	}
	
	public String getUnauthorizedText()
	{
		return unauthorized.getText();
	}
	
	public String getNotFoundText()
	{
		return notFound.getText();
	}
}
