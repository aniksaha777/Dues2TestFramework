package com.memberdues.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties pro;
	
	public ConfigDataProvider() 
	{
		
		File src = new File("./config/Config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} catch (Exception e) {
			
			System.out.println("Not able to load Config file"+e.getMessage()	);
		} 

	}
	
	
	public String getDataFromConfig(String keytoSearch) {
		
		return pro.getProperty(keytoSearch);
	

		
	}
	
	public String getBrowser() {
		
		return pro.getProperty("browser");	
	}
	
	public String getTestURL() {
		
		return pro.getProperty("testURL");
		
	}

}
