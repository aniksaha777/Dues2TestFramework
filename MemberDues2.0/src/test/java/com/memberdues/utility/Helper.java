package com.memberdues.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Helper {
	
	//Helps to handle Frames, Alerts, Multiple Windows, takes Screenshot, Hightlight Element,Sync Issues,javascriptexecutor
	
	public static String captureScreenshot(WebDriver driver,String tcname)
	{
		String Foldername = tcname;
		String folderpath = System.getProperty("user.dir")+"/Screenshots/TestExecutionDate_"+getCurrentDate()+"/"+Foldername;
		File file = new File(folderpath);
		file.mkdirs();
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String ScreenshotPath = folderpath+"/"+getCurrentDateTime()+".jpeg";
		
		try {
			FileHandler.copy(src, new File(ScreenshotPath));
			//System.out.println("Screenshot Captured");
		} catch (Exception e) {
			
			System.out.println("Unable to Capture Screenshot"+e.getMessage());

		}
		
		return ScreenshotPath;
	}
	
	public static void highlightElement(WebDriver driver, WebElement ele) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;  
		js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;');", ele);
		try {
			Thread.sleep(1000);
		} 	
		catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		js.executeScript("arguments[0].style.border=''", ele);
		
	}
	public void handleFrames()
	{
		
	}
	public void handleAlert()
	{
		
	}
	public static String getCurrentDateTime()
	{
		DateFormat customFormat = new SimpleDateFormat("MMddyyyy_HHmmss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
	
	public static String getCurrentDate()
	{
		DateFormat customFormat = new SimpleDateFormat("MMddyyyy");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
	


}
