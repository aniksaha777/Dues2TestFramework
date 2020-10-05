package com.memberdues.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.memberdues.pages.BaseClass;
import com.memberdues.pages.LoginPage;
import com.memberdues.utility.Helper;

public class LoginTests extends BaseClass {

	@Test
	public void loginMember() {

		logger = report.createTest("Login to Member Portal");
				
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver);
		loginPage.Login_to_Member_Portal(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1), excel.getStringData("Login", 0, 2));
		Helper.captureScreenshot(driver);
		logger.pass("Login is Successful");
	}

}
