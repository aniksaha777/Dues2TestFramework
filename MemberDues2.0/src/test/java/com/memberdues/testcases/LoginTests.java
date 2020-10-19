package com.memberdues.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.memberdues.pages.BaseClass;
import com.memberdues.pages.FirstResetPasswordPage;
import com.memberdues.pages.LoginPage;
import com.memberdues.utility.Helper;

public class LoginTests extends BaseClass {

	@Test(priority = 0)
	public void Login_to_Member_Portal() {

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");

		// Fetch Test Data Excel
		String uname = excel.getStringData(datarow, unamecol);
		String pw = excel.getStringData(datarow, pwcol);
		String token = excel.getStringData(datarow, tokencol);

		logger = report.createTest("Login to Member Portal");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		// Start Application
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);

		loginPage.Login_to_Member_Portal(uname, pw, token);
		logger.pass("Login Details Entered. Login button Clicked.");
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Login is Successful");
	}

	@Test(priority=1)
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

	@Test(priority=9)
	public void First_Time_Reset_Password() {

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
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
		int Modaltextcol = excel.GetDataColNum("ExpectedString");

		// Fetch Test Data from Excel
		String uname = excel.getStringData(datarow, unamecol);
		String pw = excel.getStringData(datarow, pwcol);
		String token = excel.getStringData(datarow, tokencol);
		String newpass = excel.getStringData(datarow, npcol);
		String oldpass = excel.getStringData(datarow, pwcol);
		String Q1value = excel.getStringData(datarow, Q1Vcol);
		String Q1Ans = excel.getStringData(datarow, Q1Acol);
		String Q2value = excel.getStringData(datarow, Q2Vcol);
		String Q2Ans = excel.getStringData(datarow, Q2Acol);
		String ModalTextExpected = excel.getStringData(datarow, Modaltextcol);

		logger = report.createTest("First Reset Password for Member");

		// Initializing Factory Pages
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		FirstResetPasswordPage FirstResetPasswordPage = PageFactory.initElements(driver, FirstResetPasswordPage.class);

		// Starting Application
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(uname, pw, token);
		logger.pass("Login Details Entered. Login Button Clicked");
		Helper.captureScreenshot(driver, testcasename);
		String Actual = FirstResetPasswordPage.get_First_Reset_Header();
		String Expected = excel.getStringData(datarow, rhcol);
		if (FirstResetPasswordPage.isResetHeaderPresent()) {
			if (Actual.equals(Expected)) {
				System.out.println("First Reset Password Page Header Matched");
				logger.pass("First Reset Password Page Header Matched");
				Helper.captureScreenshot(driver, testcasename);
				FirstResetPasswordPage.reset_Details(oldpass, newpass, Q1value, Q1Ans, Q2value, Q2Ans);
				Helper.captureScreenshot(driver, testcasename);
				logger.pass("Reset Details Entered");
				System.out.println("Reset Details Entered");
				FirstResetPasswordPage.click_Submit();
				Helper.captureScreenshot(driver, testcasename);
				logger.pass("Reset Password Button Clicked");
				System.out.println("Reset Password Button Clicked");

				String ModalTextActual = FirstResetPasswordPage.confirm_Text_Modal();
				if (ModalTextExpected.equals(ModalTextActual)) {

					System.out.println("Confirm Reset Password Modal text Matched");
					logger.pass("Confirm Reset Password Modal text Matched");
					Helper.captureScreenshot(driver, testcasename);
					FirstResetPasswordPage.Confirm_Reset();

					System.out.println("Confirm Reset Password Modal Accepted");
					logger.pass("Confirm Reset Password Modal Accepted");

					loginPage.Login_to_Member_Portal(uname, newpass, token);
					System.out.println("Login Details Entered. Login Button Clicked");
					logger.pass("Login Details Entered. Login Button Clicked");
					Helper.captureScreenshot(driver, testcasename);

					loginPage.Logout_of_Member_Portal();
					Helper.captureScreenshot(driver, testcasename);
					logger.pass("Login and Logout Successful with new Password");

				}

				else {

					logger.fail("Confirm Reset Password Modal text Not Matched");
					Assert.fail("Confirm Reset Password Modal text Not Matched");

				}
			}

			else {
				logger.fail("User Not Redirected to First Time Reset Password Page.");
				Assert.fail("User Not Redirected to First Time Reset Password Page.");
			}
		}

		else {
			logger.fail("User Not Redirected to First Time Reset Password Page.");
			Assert.fail("User Not Redirected to First Time Reset Password Page.");

		}

	}

	@Test(priority=2)
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

	@Test(priority=3)
	public void Login_Error_On_Wrong_Userid() {

		logger = report.createTest("Login Error on Wrong Username");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}

	@Test(priority=4)
	public void Login_Error_On_Wrong_Password() {

		logger = report.createTest("Login Error on Wrong Password");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}

	@Test(priority=5)
	public void Login_Error_On_Wrong_Token() {

		logger = report.createTest("Login Error on Wrong Token");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}
	}

	@Test(priority=6)
	public void Login_Error_On_AppAccess_Restricted() {

		logger = report.createTest("Login Error when Member Application Access Restricted");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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
				System.out.println("Alert text for Member App Access Restricted Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}

	}

	@Test(priority=7)
	public void Login_Error_On_Member_LoginRestricted() {

		logger = report.createTest("Login Error when Member Login Access Restricted");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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
				System.out.println("Alert text for Member Login Access Restricted Matched");
				logger.pass("Alert text for Member Login Access Restricted Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}

	}

	@Test(priority=8)
	public void Login_Error_On_Member_Deleted() {

		logger = report.createTest("Login Error when Member is Deleted");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Get Methodname . Methodname = testcasename
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();

		// Fetch Data Row and Columm number from Test Data
		int datarow = excel.GetDataRowNum(testcasename);
		int unamecol = excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		int errorcol = excel.GetDataColNum("ExpectedString");

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
				System.out.println("Alert text for Member is Deleted Matched");
				logger.pass("Alert text for Member is Deleted Matched");

				try {
					loginPage.verifyLoginPageHeader();
					logger.pass("Login Page Redirection Verified");
				} catch (Exception e) {
					logger.fail("User Not redirected to Login Page");
					Assert.fail("User Not redirected to Login Page");
					e.getMessage();
				}

			} else {
				logger.fail("Expected Alert is not Matched.");
				Assert.fail("Expected Alert is not Matched.");

			}

		} else {
			logger.fail("Expected Alert is not displayed.");
			Assert.fail("Expected Alert is not displayed.");
		}

	}

	
	
}
