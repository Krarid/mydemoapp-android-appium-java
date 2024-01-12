package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductPage extends AndroidActions {
	
	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc, 'circle')]")
	private List<WebElement> colors;
	
	@AndroidFindBy(xpath = "//*[@content-desc='counter plus button']")
	private WebElement plusButton;
	
	@AndroidFindBy(xpath = "//*[@content-desc='counter amount']/android.widget.TextView")
	private WebElement counter;
	
	@AndroidFindBy(accessibility = "Add To Cart button")
	private WebElement addToCartButton;
	
	@AndroidFindBy(accessibility = "cart badge")
	private WebElement cart;
	
	public ProductPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void chooseColor(int color)
	{
		colors.get(color).click();
	}
	
	public void chooseColor(String color)
	{
		for(WebElement c : colors) {
			if( c.getAttribute("content-desc").contains(color) ) {
				c.click();
				break;
			}
		}
	}
	
	public void chooseQuantity(int quantity)
	{
		while( Integer.parseInt(counter.getText()) < quantity ) {
			plusButton.click();
		}
	}
	
	public void addToCart()
	{
		addToCartButton.click();
	}
	
	public MyCartPage goToCart()
	{
		cart.click();
		return new MyCartPage(driver);
	}
}
