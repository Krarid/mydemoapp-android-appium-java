package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException 
	{
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>() {} );
		
		return data;
	}
	
	public AppiumDriverLocalService startAppiumServer(String address, int port)
	{
		return new AppiumServiceBuilder()
		  .withAppiumJS(new File("C:\\Users\\javie\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
		  .withIPAddress(address).usingPort(port).build();
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
}