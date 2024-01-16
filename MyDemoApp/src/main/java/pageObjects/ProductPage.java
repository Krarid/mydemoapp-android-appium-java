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
	
	@AndroidFindBy(accessibility = "product price")
	private WebElement price;
	
	@AndroidFindBy(xpath = "//*[@content-desc='cart badge']/android.widget.TextView")
	private WebElement cartCount;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'review star')]" )
	private List<WebElement> reviewStar;
	
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	private WebElement textModal;
	
	@AndroidFindBy(accessibility = "Close Modal button")
	private WebElement closeModalButton;
	
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
	
	public void submitReview(int star)
	{
		reviewStar.get(star).click();
	}
	
	public void closeModal()
	{
		closeModalButton.click();
	}
	
	public String getTextModal()
	{
		return textModal.getText();
	}
	
	public void addToCart()
	{
		addToCartButton.click();
	}
	
	public float getPrice()
	{
		return super.getPrice(price.getText());
	}
	
	public int getCartCount()
	{
		return Integer.parseInt(cartCount.getText());
	}
	
	public MyCartPage goToCart()
	{
		cart.click();
		return new MyCartPage(driver);
	}
}
