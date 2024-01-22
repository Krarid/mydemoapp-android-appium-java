package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AboutPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class About extends BaseTest {
	
	@Test
	public void VerifyLastBuild()
	{
		MenuPage menu = new MenuPage(driver);
		AboutPage about = menu.goToAbout();
		
		Assert.assertTrue(about.getBuild().contains("V.1.3.0-build 244"));
	}
}