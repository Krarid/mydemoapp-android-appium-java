package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
		shipment.enterShipmentDetails();
		
		PaymentPage payment = shipment.toPayment();
		Thread.sleep(500);
		payment.enterPaymentDetails();
		
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
	
	@Test(dataProvider="getData")
	public void DecreaseTheCart(HashMap<String, String> input) throws InterruptedException, IOException
	{
		int items = Integer.parseInt( input.get("quantity") );
		float price, delivery = 5.99f;
		
		final DecimalFormat df = new DecimalFormat("0.00");
		
		ProductPage product = products.chooseProduct(input.get("productName"));
		product.chooseQuantity(5);
		product.addToCart();
		price = product.getPrice();
		
		MyCartPage cart = product.goToCart();
		cart.decrement(items);
		
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
	
	@Test(dataProvider="getData")
	public void AddToCartManyTimes(HashMap<String, String> input)
	{
		final DecimalFormat df = new DecimalFormat("0.00");
		
		int items = Integer.parseInt(input.get("items")), times = Integer.parseInt(input.get("times"));
		float price = 0.0f, total = 0.0f;
		
		ProductPage product = products.chooseProduct(input.get("productName"));
		
		price = product.getPrice();
		total = items * times * price;
		
		product.chooseQuantity(items);
		
		for(int i = 1; i <= times; i++) {
			product.addToCart();
		}
		
		Assert.assertEquals(product.getCartCount(), items * times);
		
		MyCartPage cart = product.goToCart();
		
		int counterAmount = cart.getCounterAmount();
		int totalitems = cart.getTotalNumberOfItems();
		String totalPrice = cart.getTotalPrice();
		
		cart.removeItem();
		cart.goShopping();
		
		Assert.assertEquals(counterAmount, items * times);
		Assert.assertEquals(totalitems, items * times);
		Assert.assertEquals(totalPrice, "$" + df.format(total));
	}
	
	@Test
	public void SubmitReview() throws InterruptedException
	{
		ProductPage product;
		
		for(int i = 0; i < 6; i++) {
			product = products.chooseProduct(i);
			
			for(int j = 0; j < 5; j++) {
				product.submitReview(j);
				Thread.sleep(200);
				Assert.assertEquals(product.getTextModal(), "Thank you for submitting your review!");
				product.closeModal();
			}
			
			products.returnToCatalog();
		}
	}
	
	@Test
	public void SortProductsBy()
	{
		Stack<String> names;
		Stack<Float> prices;
		
		// Name ascending
		products.sortBy("nameAsc");
		names = products.getProductNames();
		
		for( int i = 0; i < names.size() - 1; i++) {

			if( names.get(i).compareToIgnoreCase(names.get(i+1)) <= 0 )
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
		}
		
		// Name descending
		products.sortBy("nameDesc");
		names = products.getProductNames();
		
		for( int i = 0; i < names.size() - 1; i++) {

			if( names.get(i).compareToIgnoreCase(names.get(i+1)) >= 0 )
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
		}
		
		// Price ascending
		products.sortBy("priceAsc");
		prices = products.getPrices();
		
		for( int i = 0; i < prices.size() - 1; i++) {

			if( prices.get(i) <= prices.get(i+1) )
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
		}
		
		// Price descending
		products.sortBy("priceDesc");
		prices = products.getPrices();
		
		for( int i = 0; i < prices.size() - 1; i++) {

			if( prices.get(i) >= prices.get(i+1) )
				Assert.assertTrue(true);
			else
				Assert.assertTrue(false);
		}
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
			
			case "DecreaseTheCart":
				dataFile = "4";
			break;
			
			case "AddToCartManyTimes":
				dataFile = "5";
			break;
		}
		
		List<HashMap<String, String>> data = getJsonData("C:\\Users\\javie\\OneDrive\\Documents\\Github\\mydemoapp-android-appium-java\\MyDemoApp\\src\\test\\java\\data\\mda" + dataFile + ".json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}
