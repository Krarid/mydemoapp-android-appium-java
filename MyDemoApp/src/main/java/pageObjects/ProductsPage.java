package pageObjects;

import java.util.List;
import java.util.Stack;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NotFoundException;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductsPage extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='store item']")
	private List<WebElement> products;
	
	@AndroidFindBy(xpath = "//*[@content-desc='open menu']/android.widget.ImageView")
	private WebElement hamburgerIcon;
	
	@AndroidFindBy(accessibility = "sort button")
	private WebElement sortButton;
	
	@AndroidFindBy(accessibility = "menu item catalog")
	private WebElement catalog;
	
	@AndroidFindBy(accessibility = "nameAsc")
	private WebElement nameAsc;
	
	@AndroidFindBy(accessibility = "nameDesc")
	private WebElement nameDesc;
	
	@AndroidFindBy(accessibility = "priceAsc")
	private WebElement priceAsc;
	
	@AndroidFindBy(accessibility = "priceDesc")
	private WebElement priceDesc;
	
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
	
	public void returnToCatalog()
	{
		hamburgerIcon.click();
		catalog.click();
	}
	
	public Stack<String> getProductNames()
	{
		Stack<String> names = new Stack<String>();
		
		for( WebElement product : products ) { 
			try {
				String name = product.findElement(AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item text'])")).getText();
				names.add(name);
			} catch(Exception e) {
				break;
			}
		}
		
		return names;
	}
	
	public Stack<Float> getPrices()
	{
		Stack<Float> prices = new Stack<Float>();
		
		for( WebElement product : products ) { 
			try {
				String price = product.findElement(AppiumBy.xpath("(//android.widget.TextView[@content-desc='store item price'])")).getText();
				prices.add(getPrice(price));
			} catch(Exception e) {
				break;
			}
		}
		
		return prices;
	}
	
	public void sortBy( String typeAsc )
	{
		sortButton.click();
		
		switch(typeAsc) {
			case "nameAsc":
				nameAsc.click();
			break;
			
			case "nameDesc":
				nameDesc.click();
			break;
			
			case "priceAsc":
				priceAsc.click();
			break;
			
			case "priceDesc":
				priceDesc.click();
			break;
		}
	}
}
