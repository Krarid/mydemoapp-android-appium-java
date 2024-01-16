package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.ProductsPage;

public class BaseTest extends AppiumUtils {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public ProductsPage products;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException
	{
		Properties property = new Properties();
		FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		
		property.load(propertyFile);
		
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : property.getProperty("ipAddress");
		String port = System.getProperty("port") != null ? System.getProperty("port") : property.getProperty("port");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		  
		service.start();
		
		String deviceName = System.getProperty("deviceName") != null ? System.getProperty("deviceName") : property.getProperty("deviceName");
		  
		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		// options.setCapability(MobileCapabilityType.APP, "C:\\Users\\javie\\Documents\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setCapability(MobileCapabilityType.APP, "C:\\Users\\javie\\OneDrive\\Documents\\Github\\mydemoapp-android-appium-java\\MyDemoApp\\src\\test\\java\\resources\\Android-MyDemoAppRN.1.3.0.build-244.apk");
		options.setChromedriverExecutable("C:\\Users\\javie\\OneDrive\\Documents\\Github\\mydemoapp-android-appium-java\\MyDemoApp\\src\\test\\java\\resources\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		products = new ProductsPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}
}