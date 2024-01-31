package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import pageObjects.APIcallsPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class APIcalls extends BaseTest {

	@Test(groups= {"Smoke"})
	public void ListOfDevicesInEU_DC()
	{
		MenuPage menu = new MenuPage(driver);
		menu.goToApiCalls();
		
		String response = RestAssured.given().header("Content-Type", "application/json").
		when().get("https://api.eu-central-1.saucelabs.com/v1/rdc-devices").
		then().assertThat().statusCode(200).extract().response().asString();
		
		Assert.assertTrue(response.contains("iPhone"));
	}
	
	@Test
	public void ListOfDevicesInUS_DC()
	{
		MenuPage menu = new MenuPage(driver);
		APIcallsPage apiCalls = menu.goToApiCalls();
		
		apiCalls.goToUSDC();
		
		String response = RestAssured.given().header("Content-Type", "application/json").
		when().get("https://api.us-west-1.saucelabs.com/v1/rdc-devices").
		then().assertThat().statusCode(200).extract().response().asString();
		
		Assert.assertTrue(response.contains("iPhone"));
	}
	
	@Test
	public void UnauthorizedErrorResponse()
	{
		MenuPage menu = new MenuPage(driver);
		APIcallsPage apiCalls = menu.goToApiCalls();
		
		apiCalls.goToUnauthorized();
		
		RestAssured.given().header("Content-Type", "application/json").
				when().get("https://api.eu-central-1.saucelabs.com/v1/rdc-devices-2").
				then().assertThat().statusCode(401).extract().response().asString();
		
		Assert.assertEquals(apiCalls.getUnauthorizedText(), "Unauthorized");
	}
	
	@Test
	public void NotFoundErrorResponse()
	{
		MenuPage menu = new MenuPage(driver);
		APIcallsPage apiCalls = menu.goToApiCalls();
		
		apiCalls.goToNotFound();
		
		RestAssured.given().header("Content-Type", "application/json").
				when().get("https://api.eu-central-1.saucelabs.com/v1/").
				then().assertThat().statusCode(404).extract().response().asString();
		
		Assert.assertEquals(apiCalls.getNotFoundText(), "Not found");
	}
}
