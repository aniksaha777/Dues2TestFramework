package com.memberdues.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.memberdues.utility.BrowserFactory;
import com.memberdues.utility.ConfigDataProvider;
import com.memberdues.utility.ExcelDataProvider;
import com.memberdues.utility.Helper;

public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;



	@BeforeSuite
	public void setupSuite()
	{
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();


		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/MemberPortal_"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

	}

	@BeforeClass
	public void setup() {

		driver = BrowserFactory.startApplication(driver, config.getBrowser(),config.getTestURL());

	}

	@AfterClass
	public void tearDown() {

		BrowserFactory.quitBrowser(driver);
	}
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {

		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.fail("Test Case Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}

		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Case Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}

		//		else if(result.getStatus()==ITestResult.SKIP)
		//		{
		//			logger.skip("Test Case Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		//		}

		report.flush();
	}

}
