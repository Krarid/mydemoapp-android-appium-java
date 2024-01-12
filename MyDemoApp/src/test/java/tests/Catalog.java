package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonData("C:\\Users\\javie\\OneDrive\\Documents\\Github\\mydemoapp-android-appium-java\\MyDemoApp\\src\\test\\java\\data\\data.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
}
