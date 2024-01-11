package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductsPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='store item']")
	private List<WebElement> products;
	
	public ProductsPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public ProductPage chooseProduct(int product)
	{
		products.get(product).click();
		
		return new ProductPage(driver);
	}
	
	public ProductPage chooseProduct(String product)
	{
		scrollToElement("© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.");
		
		for( WebElement p : products ) {
			if( p.findElement(By.xpath("//*[@content-desc='store item text']")).getText().contains(product) ) {
				p.click();
				break;
			}
		}
		
		return new ProductPage(driver);
	}
}
