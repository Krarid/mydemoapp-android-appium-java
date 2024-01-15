package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import pageObjects.CompletePage;
import pageObjects.MyCartPage;
import pageObjects.PaymentPage;
import pageObjects.ProductPage;
import pageObjects.ReviewPage;
import pageObjects.ShipmentPage;
import utils.BaseTest;

public class Catalog extends BaseTest {
	
	@Test(dataProvider="getData", groups= {"Smoke"})
	public void BuyProduct(HashMap<String, String> input) throws InterruptedException, IOException
	{
		ProductPage product = products.chooseProduct(input.get("productName"));
		product.chooseColor(input.get("color"));
		product.chooseQuantity(Integer.parseInt(input.get("quantity")));
		product.addToCart();
		MyCartPage cart = product.goToCart();
		
		ShipmentPage shipment = cart.proceedToCheckout();
		shipment.enterFullName(input.get("fullName"));
		shipment.enterAddress(input.get("address"));
		shipment.enterCity(input.get("city"));
		shipment.enterState(input.get("state"));
		shipment.enterZipCode(input.get("zipCode"));
		shipment.enterCountry(input.get("country"));
		
		PaymentPage payment = shipment.toPayment();
		Thread.sleep(500);
		payment.enterFullName(input.get("fullName"));
		payment.enterCardNumber(input.get("cardNumber"));
		payment.enterExpiration(input.get("expiration"));
		payment.enterSecurityCode(input.get("securityCode"));
		
		ReviewPage review = payment.reviewOrder();
		Assert.assertTrue( review.getProduct().contains(input.get("productName")) );
		Assert.assertTrue( review.getColor().contains(input.get("color")) );
		
		CompletePage complete = review.placeOrder();
		complete.continueShopping();
	}
	
	@Test(dataProvider="getData")
	public void RemoveProduct(HashMap<String, String> input)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		ProductPage product = products.chooseProduct(input.get("productName"));
		product.chooseColor(input.get("color"));
		product.chooseQuantity(Integer.parseInt(input.get("quantity")));
		product.addToCart();
		
		MyCartPage cart = product.goToCart();
		cart.removeItem();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(AppiumBy.xpath("//*[@content-desc='container header']/android.widget.TextView"), "No Items"));
		
		Assert.assertEquals(cart.getNoItemsText(), "No Items");
		cart.goShopping();
	}
	
	@Test(dataProvider="getData")
	public void IncreaseTheCart(HashMap<String, String> input) throws InterruptedException, IOException
	{
		int items = Integer.parseInt( input.get("quantity") );
		float price, delivery = 5.99f;
		
		final DecimalFormat df = new DecimalFormat("0.00");
		
		ProductPage product = products.chooseProduct(input.get("productName"));
		product.addToCart();
		price = product.getPrice();
		
		MyCartPage cart = product.goToCart();
		cart.increment(items);
		
		Thread.sleep(500);
		
		Assert.assertEquals(cart.getTotalPrice(), "$" + df.format(price * items) );
		
		ShipmentPage shipment = cart.proceedToCheckout();
		shipment.enterShipmentDetails();
		
		PaymentPage payment = shipment.toPayment();
		Thread.sleep(500);
		payment.enterPaymentDetails();
		
		ReviewPage review = payment.reviewOrder();
		Assert.assertEquals(review.getTotalPrice(), "$" + df.format(price * items + delivery) );
		
		CompletePage complete = review.placeOrder();
		complete.continueShopping();
	}
	
	@DataProvider
	public Object[][] getData(Method m) throws IOException
	{
		String dataFile = "1";
		
		switch( m.getName() ) {
		
			case "BuyProduct":
				dataFile = "1";
			break;
		
			case "RemoveProduct":
				dataFile = "2";
			break;
			
			case "IncreaseTheCart":
				dataFile = "3";
			break;
		}
		
		List<HashMap<String, String>> data = getJsonData("C:\\Users\\javie\\OneDrive\\Documents\\Github\\mydemoapp-android-appium-java\\MyDemoApp\\src\\test\\java\\data\\mda" + dataFile + ".json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}
