package com.memberdues.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.memberdues.pages.BaseClass;
import com.memberdues.pages.FirstResetPasswordPage;
import com.memberdues.pages.LoginPage;
import com.memberdues.utility.Helper;

public class LoginTests extends BaseClass {

	@Test(enabled = false)
	public void Login_to_Member_Portal() {

		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = excel.GetDataRowNum(testcasename);
		// System.out.println("Data Row is ="+datarow);

		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");

		logger = report.createTest("Login to Member Portal");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow, unamecol), excel.getStringData(datarow, pwcol),
				excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Login is Successful");
	}

	@Test(enabled = false)
	public void Logout_of_Member_Portal() {
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = excel.GetDataRowNum(testcasename);

		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");

		logger = report.createTest("Logout of Member Portal");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow, unamecol), excel.getStringData(datarow, pwcol),
				excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Logout_of_Member_Portal();
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Logout is Successful");

	}

	@Test(enabled = false)
	public void First_Time_Reset_Password() {

		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = excel.GetDataRowNum(testcasename);

		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int npcol = excel.GetDataColNum("NewPassword");
		int rhcol = excel.GetDataColNum("ResetHeader");
		int Q1Vcol = excel.GetDataColNum("Q1Val");
		int Q1Acol = excel.GetDataColNum("Q1Ans");
		int Q2Vcol = excel.GetDataColNum("Q2Val");
		int Q2Acol = excel.GetDataColNum("Q2Ans");

		logger = report.createTest("First Reset Password for Member");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		FirstResetPasswordPage FirstResetPasswordPage = PageFactory.initElements(driver, FirstResetPasswordPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow, unamecol), excel.getStringData(datarow, pwcol),
				excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		String Actual = FirstResetPasswordPage.get_First_Reset_Header();
		String Expected = excel.getStringData(datarow, rhcol);
		Assert.assertEquals(Actual, Expected);
		System.out.println("Reset Password Page Header Matched");
		Helper.captureScreenshot(driver, testcasename);
		String newpass = excel.getStringData(datarow, npcol);
		String oldpass = excel.getStringData(datarow, pwcol);
		String Q1value = excel.getStringData(datarow, Q1Vcol);
		String Q1Ans = excel.getStringData(datarow, Q1Acol);
		String Q2value = excel.getStringData(datarow, Q2Vcol);
		String Q2Ans = excel.getStringData(datarow, Q2Acol);

		FirstResetPasswordPage.reset_First_Password(oldpass, newpass, Q1value, Q1Ans, Q2value, Q2Ans);
		Helper.captureScreenshot(driver, testcasename);
		String ModalTextActual = FirstResetPasswordPage.confirm_Text_Modal();
		String ModalTextExpected = "Are you sure to reset the password and set the security questions?";
		Assert.assertEquals(ModalTextActual, ModalTextExpected);
		System.out.println("Confirm Modal Matched");
		Helper.captureScreenshot(driver, testcasename);
		FirstResetPasswordPage.Confirm_Reset();

		loginPage.Login_to_Member_Portal(excel.getStringData(datarow, unamecol), newpass,
				excel.getStringData(datarow, 3));
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Logout_of_Member_Portal();
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("First time Reset Password is Successful");

	}

	@Test(enabled = false)
	public void Login_Error_On_No_Data() {

		logger = report.createTest("Login Error on Clicking Submit with no Data");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		logger.info("Starting Application");
		// int datarow = excel.GetDataRowNum(testcasename);
		// System.out.println("Data Row is ="+datarow);
		// int unamecol =excel.GetDataColNum("Username");
		// int pwcol = excel.GetDataColNum("Password");
		// int tokencol = excel.GetDataColNum("Token");

		loginPage.OnlyLoginClick();
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Login Errors with no Data Shown successfully");

	}

	@Test
	public void Login_Error_On_Wrong_Userid() {

		logger = report.createTest("Login Error on Wrong Username");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		//Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedError");

		// Fetch Test Data Excel
		String uname = excel.getStringData(datarow, unamecol);
		String pw = excel.getStringData(datarow, pwcol);
		String token = excel.getStringData(datarow, tokencol);
		String ExpectedError = excel.getStringData(datarow, errorcol);

		// Start Application
		logger.info("Starting Application");

		loginPage.Login_to_Member_Portal(uname, pw, token);
		logger.pass("Login Details Entered.Login Button Clicked");
		if (loginPage.isAlertPresent()) {
			System.out.println("Alert is present");
			logger.pass("Alert is present");
			String ActualError = loginPage.getAlertText();
			System.out.println("Expected Error: " + ExpectedError);
			System.out.println("Actual Error Text: " + ActualError);
			loginPage.handleAlert();
			logger.info("Alert is Accepted");
			Helper.captureScreenshot(driver, testcasename);
			if (ActualError.equals(ExpectedError)) {				
				System.out.println("Alert text for Incorrect Credentials Matched");
				logger.pass("Alert text for Incorrect Credentials Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}
				
			}
			else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");
				
			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}

	@Test
	public void Login_Error_On_Wrong_Password() {
		
		logger = report.createTest("Login Error on Wrong Password");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		//Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedError");

		// Fetch Test Data Excel
		String uname = excel.getStringData(datarow, unamecol);
		String pw = excel.getStringData(datarow, pwcol);
		String token = excel.getStringData(datarow, tokencol);
		String ExpectedError = excel.getStringData(datarow, errorcol);

		// Start Application
		logger.info("Starting Application");

		loginPage.Login_to_Member_Portal(uname, pw, token);
		logger.pass("Login Details Entered.Login Button Clicked");
		if (loginPage.isAlertPresent()) {
			System.out.println("Alert is present");
			logger.pass("Alert is present");
			String ActualError = loginPage.getAlertText();
			System.out.println("Expected Error: " + ExpectedError);
			System.out.println("Actual Error Text: " + ActualError);
			loginPage.handleAlert();
			logger.info("Alert is Accepted");
			Helper.captureScreenshot(driver, testcasename);
			if (ActualError.equals(ExpectedError)) {				
				System.out.println("Alert text for Incorrect Credentials Matched");
				logger.pass("Alert text for Incorrect Credentials Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}
				
			}
			else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");
				
			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}

	@Test
	public void Login_Error_On_Wrong_Token() {

		logger = report.createTest("Login Error on Wrong Token");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		//Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedError");

		// Fetch Test Data Excel
		String uname = excel.getStringData(datarow, unamecol);
		String pw = excel.getStringData(datarow, pwcol);
		String token = excel.getStringData(datarow, tokencol);
		String ExpectedError = excel.getStringData(datarow, errorcol);

		// Start Application
		logger.info("Starting Application");

		loginPage.Login_to_Member_Portal(uname, pw, token);
		logger.pass("Login Details Entered.Login Button Clicked");
		if (loginPage.isAlertPresent()) {
			System.out.println("Alert is present");
			logger.pass("Alert is present");
			String ActualError = loginPage.getAlertText();
			System.out.println("Expected Error: " + ExpectedError);
			System.out.println("Actual Error: " + ActualError);
			loginPage.handleAlert();
			logger.info("Alert is Accepted");
			Helper.captureScreenshot(driver, testcasename);
			if (ActualError.equals(ExpectedError)) {				
				System.out.println("Alert text for Incorrect Token Matched");
				logger.pass("Alert text for Incorrect Token Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}
				
			}
			else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");
				
			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}
}
