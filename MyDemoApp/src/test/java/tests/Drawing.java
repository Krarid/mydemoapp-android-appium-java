package tests;

import org.testng.annotations.Test;

import pageObjects.DrawingPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class Drawing extends BaseTest {
	@Test
	public void performDrawingAction() throws InterruptedException
	{
		MenuPage menu = new MenuPage(driver);
		DrawingPage drawing = menu.goToDrawing();
		
		for( int i = 0; i <= 6; i++ ) {
			drawing.draw(150 + 200 * i, 100, 150 + 200 * i, 2500);
		}
		
		for( int i = 1; i <= 9; i++ ) {
			drawing.draw(100, 200 * i, 1500, 200 * i);
		}
	}
}
