package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyCartPage;
import pageObjects.PaymentPage;
import pageObjects.ProductPage;
import pageObjects.ReviewPage;
import pageObjects.ShipmentPage;
import utils.BaseTest;

public class Catalog extends BaseTest {
	
	private String productName;
	private String color;
	private int quantity;
	
	private String username;
	private String password;
	private String fullName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	
	private String country;
	private String cardNumber;
	private String expiration;
	private String securityCode;
	
	public Catalog()
	{
		productName = "Onesie";
		color = "black";
		quantity = 3;
		
		username = "bob@example.com";
		password = "10203040";
		fullName = "Rebecca Winter";
		address = "Mandorley 112";
		city = "Truro";
		state = "Cornwall";
		zipCode = "89750";
		
		country = "United Kingdom";
		cardNumber = "325812657568789";
		expiration = "0325";
		securityCode = "123";
	}
	
	@Test
	public void BuyProduct() throws InterruptedException
	{
		ProductPage product = products.chooseProduct(productName);
		product.chooseColor(color);
		product.chooseQuantity(quantity);
		product.addToCart();
		MyCartPage cart = product.goToCart();
		
		LoginPage login = cart.proceedToCheckout();
		login.enterUsername(username);
		login.enterPassword(password);
		
		ShipmentPage shipment = login.login();
		shipment.enterFullName(fullName);
		shipment.enterAddress(address);
		shipment.enterCity(city);
		shipment.enterState(state);
		shipment.enterZipCode(zipCode);
		shipment.enterCountry(country);
		
		PaymentPage payment = shipment.toPayment();
		Thread.sleep(500);
		payment.enterFullName(fullName);
		payment.enterCardNumber(cardNumber);
		payment.enterExpiration(expiration);
		payment.enterSecurityCode(securityCode);
		
		ReviewPage review = payment.reviewOrder();
		Assert.assertTrue( review.getProduct().contains(productName) );
		Assert.assertTrue( review.getColor().contains(color) );
		
		review.placeOrder();
		
		Thread.sleep(1500);
	}
}
