package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DrawingPage;
import pageObjects.MenuPage;
import utils.BaseTest;

public class Drawing extends BaseTest {
	@Test
	public void PerformDrawingAction()
	{
		MenuPage menu = new MenuPage(driver);
		DrawingPage drawing = menu.goToDrawing();
		
		for( int i = 0; i <= 6; i++ ) {
			drawing.draw(150 + 200 * i, 100, 150 + 200 * i, 2500);
		}
		
		for( int i = 1; i <= 9; i++ ) {
			drawing.draw(100, 200 * i, 1500, 200 * i);
		}
		
		drawing.clearDrawing();
	}
	
	@Test
	public void ClearDrawing()
	{
		MenuPage menu = new MenuPage(driver);
		DrawingPage drawing = menu.goToDrawing();
		
		drawing.draw(700, 100, 700, 2500);
		drawing.draw(100, 1000, 1500, 1500);
		
		drawing.clearDrawing();
	}
	
	@Test
	public void SaveDrawing()
	{
		MenuPage menu = new MenuPage(driver);
		DrawingPage drawing = menu.goToDrawing();
		
		drawing.draw(700, 100, 700, 2500);
		drawing.draw(100, 1000, 1500, 1500);
		
		drawing.saveDrawing();
		
		Assert.assertEquals(drawing.getSuccessMessage(), "Drawing saved successfully to gallery");
		
		drawing.ok();
		
		drawing.clearDrawing();
	}
}
