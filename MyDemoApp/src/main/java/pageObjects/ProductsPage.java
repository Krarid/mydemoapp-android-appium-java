package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NotFoundException;

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
		boolean productFound = false, scrolled = false;
		
		while(!productFound) {
			for( WebElement p : products ) {
				try {
					if( p.findElement(By.xpath("//*[@content-desc='store item text']")).getText().contains(product) ) {
						p.click();
						productFound = true;
						break;
					}
				} catch(NoSuchElementException e) {
					break;
				}
			}
			
			if(productFound)
				break;
			else if(!scrolled) {
				scrollToElement("© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy.");
				scrolled = true;
			} else
				throw new NotFoundException();
		}
		
		return new ProductPage(driver);
	}
}
