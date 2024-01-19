package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class DrawingPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(accessibility = "Clear button")
	private WebElement clearButton;
	
	@AndroidFindBy(accessibility = "Save button")
	private WebElement saveButton;
	
	@AndroidFindBy(id = "signature-pad")
	public WebElement signaturePad;
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private WebElement allowButton;
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
	private WebElement denyButton;
	
	@AndroidFindBy(id = "android:id/message")
	private WebElement saveDrawingMessage;
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement okButton;
	
	public DrawingPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void draw(int startX, int startY, int endX, int endY)
	{
		super.dragFromElement(driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc='drawing screen']/android.view.ViewGroup[2]")), startX, startY, endX, endY);
	}
	
	public void clearDrawing()
	{
		clearButton.click();
	}
	
	public void saveDrawing()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		saveButton.click();
		
		try {
			wait.until(ExpectedConditions.invisibilityOf(allowButton));
		} catch(Exception e) {
			allowButton.click();
		}
	}
	
	public String getSuccessMessage()
	{
		return saveDrawingMessage.getText();
	}
	
	public void ok()
	{
		okButton.click();
	}
}
