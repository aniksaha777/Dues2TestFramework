package com.memberdues.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {
	
	//Helps to handle Frames, Alerts, Multiple Windows, takes Screenshot, Hightlight Element,Sync Issues,javascriptexecutor
	
	public static String captureScreenshot(WebDriver driver)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String ScreenshotPath = System.getProperty("user.dir")+"/Screenshots/"+getCurrentDateTime()+".jpeg";
		
		try {
			FileHandler.copy(src, new File(ScreenshotPath));
			System.out.println("Screenshot Captured");
		} catch (Exception e) {
			
			System.out.println("Unable to Capture Screenshot"+e.getMessage());

		}
		
		return ScreenshotPath;
	}
	public void handleFrames()
	{
		
	}
	public void handleAlert()
	{
		
	}
	public static String getCurrentDateTime()
	{
		DateFormat customFormat = new SimpleDateFormat("MMddyyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

}
