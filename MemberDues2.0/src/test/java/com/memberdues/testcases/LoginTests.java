package com.memberdues.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.memberdues.pages.BaseClass;
import com.memberdues.pages.FirstResetPasswordPage;
import com.memberdues.pages.LoginPage;
import com.memberdues.utility.Helper;

public class LoginTests extends BaseClass {

	@Test(enabled=false)
	public void Login_to_Member_Portal() {
		
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = excel.GetDataRowNum(testcasename);
		//System.out.println("Data Row is ="+datarow);

		int unamecol =excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");
		
		logger = report.createTest("Login to Member Portal");
				
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver,testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow,unamecol), excel.getStringData(datarow,pwcol), excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Login is Successful");
	}
	
	@Test(enabled=false)
	public void Logout_of_Member_Portal() {
		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = excel.GetDataRowNum(testcasename);
		
		int unamecol =excel.GetDataColNum("Username");
		int pwcol = excel.GetDataColNum("Password");
		int tokencol = excel.GetDataColNum("Token");

		logger = report.createTest("Logout of Member Portal");
				
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow,unamecol), excel.getStringData(datarow,pwcol), excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Logout_of_Member_Portal();
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("Logout is Successful");
		
		
	}
	
	@Test
	public void First_Time_Reset_Password() {

		String testcasename = Thread.currentThread().getStackTrace()[1].getMethodName();
		int datarow = 	excel.GetDataRowNum(testcasename);
		
		int unamecol =	excel.GetDataColNum("Username");
		int pwcol = 	excel.GetDataColNum("Password");
		int tokencol = 	excel.GetDataColNum("Token");
		int npcol= 		excel.GetDataColNum("NewPassword");
		int rhcol = 	excel.GetDataColNum("ResetHeader");
		int Q1Vcol=		excel.GetDataColNum("Q1Val");
		int Q1Acol = 	excel.GetDataColNum("Q1Ans");
		int Q2Vcol	= 	excel.GetDataColNum("Q2Val");
		int Q2Acol = 	excel.GetDataColNum("Q2Ans");
		
		logger = report.createTest("First Reset Password for Member");
				
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		FirstResetPasswordPage FirstResetPasswordPage = PageFactory.initElements(driver, FirstResetPasswordPage.class);
		logger.info("Starting Application");
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Login_to_Member_Portal(excel.getStringData(datarow,unamecol), excel.getStringData(datarow,pwcol), excel.getStringData(datarow, tokencol));
		Helper.captureScreenshot(driver, testcasename);
		String Actual= FirstResetPasswordPage.get_First_Reset_Header();
		String Expected = excel.getStringData( datarow, rhcol);
		Assert.assertEquals(Actual, Expected);
		System.out.println("Reset Password Page Header Matched");
		Helper.captureScreenshot(driver, testcasename);
		String newpass = excel.getStringData(datarow,npcol);
		String oldpass = excel.getStringData(datarow,pwcol);
		String Q1value = excel.getStringData(datarow,Q1Vcol);
		String Q1Ans = excel.getStringData(datarow,Q1Acol);
		String Q2value = excel.getStringData(datarow,Q2Vcol);
		String Q2Ans = excel.getStringData(datarow,Q2Acol);
		
		FirstResetPasswordPage.reset_First_Password(oldpass,newpass, Q1value, Q1Ans, Q2value, Q2Ans);
		Helper.captureScreenshot(driver, testcasename);
		String ModalTextActual = FirstResetPasswordPage.confirm_Text_Modal();
		String ModalTextExpected = "Are you sure to reset the password and set the security questions?";
		Assert.assertEquals(ModalTextActual, ModalTextExpected);
		System.out.println("Confirm Modal Matched");
		Helper.captureScreenshot(driver, testcasename);
		FirstResetPasswordPage.Confirm_Reset();
		
		loginPage.Login_to_Member_Portal(excel.getStringData( datarow, unamecol),newpass, excel.getStringData( datarow,3));
		Helper.captureScreenshot(driver, testcasename);
		loginPage.Logout_of_Member_Portal();
		Helper.captureScreenshot(driver, testcasename);
		logger.pass("First time Reset Password is Successful");
	
		
		
		
	}
	

}
