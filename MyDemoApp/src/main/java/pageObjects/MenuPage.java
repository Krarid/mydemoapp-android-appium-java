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
	
	@AndroidFindBy(accessibility = "menu item drawing")
	private WebElement drawing;
	
	@AndroidFindBy(accessibility = "menu item about")
	private WebElement about;
	
	@AndroidFindBy(accessibility = "menu item reset app")
	private WebElement resetApp;
	
	@AndroidFindBy(accessibility = "menu item log in")
	private WebElement login;
	
	@AndroidFindBy(accessibility = "menu item log out")
	private WebElement logout;
	
	@AndroidFindBy(accessibility = "menu item api calls")
	private WebElement apiCalls;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[contains(@text, 'LOG OUT')]")
	private WebElement logoutYes;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[contains(@text, 'OK')]")
	private WebElement logoutOK;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[contains(@text, 'You are successfully logged out.')]")
	private WebElement logoutMessage;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[contains(@text, 'RESET APP')]")
	private WebElement resetAppYes;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[contains(@text, 'CANCEL')]")
	private WebElement resetAppNo;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[contains(@text, 'App State has been reset.')]")
	private WebElement resetAppAlert;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[contains(@text, 'OK')]")
	private WebElement resetAppOK;
	
	public MenuPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void goToCatalog()
	{
		hamburgerIcon.click();
		catalog.click();
	}
	
	public WebviewPage goToWebview()
	{
		hamburgerIcon.click();
		webview.click();
		
		return new WebviewPage(driver);
	}
	
	public DrawingPage goToDrawing()
	{
		hamburgerIcon.click();
		drawing.click();
		
		return new DrawingPage(driver);
	}
	
	public AboutPage goToAbout()
	{
		hamburgerIcon.click();
		about.click();
		
		return new AboutPage(driver);
	}
	
	public LoginPage goToLogin()
	{
		hamburgerIcon.click();
		login.click();
		
		return new LoginPage(driver);
	}
	
	public String goToLogout()
	{
		hamburgerIcon.click();
		logout.click();
		logoutYes.click();
		String logoutMessage = this.logoutMessage.getText();
		logoutOK.click();
		
		return logoutMessage;
	}
	
	public APIcallsPage goToApiCalls()
	{
		hamburgerIcon.click();
		apiCalls.click();
		
		return new APIcallsPage(driver);
	}
	
	public void resetAppState(boolean option)
	{
		hamburgerIcon.click();
		resetApp.click();
		
		if(option) {
			resetAppYes.click();
		} else {
			resetAppNo.click();
		}
	}
	
	public String getResetTitle()
	{
		return resetAppAlert.getText();
	}
	
	public void ok()
	{
		resetAppOK.click();
	}
}
