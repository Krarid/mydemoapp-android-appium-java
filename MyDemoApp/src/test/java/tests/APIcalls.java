package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pageObjects.MenuPage;
import utils.BaseTest;

public class APIcalls extends BaseTest {

	@Test
	public void ListOfDevicesInEU_DC()
	{
		MenuPage menu = new MenuPage(driver);
		menu.goToApiCalls();
		
		String response = RestAssured.given().header("Content-Type", "application/json").
		when().get("https://api.eu-central-1.saucelabs.com/v1/rdc-devices").
		then().assertThat().statusCode(200).extract().response().asString();
		
		Assert.assertTrue(response.contains("iPhone"));
	}
}
