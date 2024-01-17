package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class MenuPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//*[@content-desc='open menu']/android.widget.ImageView")
	private WebElement hamburgerIcon;
	
	@AndroidFindBy(accessibility = "menu item catalog")
	private WebElement catalog;
	
	@AndroidFindBy(accessibility = "menu item webview")
	private WebElement webview;
	
	public MenuPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public WebviewPage goToWebview()
	{
		hamburgerIcon.click();
		webview.click();
		
		return new WebviewPage(driver);
	}
}
