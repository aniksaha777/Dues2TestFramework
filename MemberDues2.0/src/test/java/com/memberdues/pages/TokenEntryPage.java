package com.memberdues.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.memberdues.utility.Helper;

public class TokenEntryPage {

	WebDriver driver;

	public TokenEntryPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='login-form']//div[2]") WebElement CommonLoginHeader;
	@FindBy(name = "token") WebElement token;
	@FindBy(xpath ="//input[@value='Enter Portal']") WebElement EnterPortal;
	@FindBy(xpath = "//*[@id='userDropdown']") WebElement UserDropdown;
	@FindBy(xpath = "//span[@onclick='logoutService()']/a") WebElement Logout;
	@FindBy(xpath ="//*[@id='token' and @class='form-control loging-error']") WebElement TokenError;


	public void Enter_token(String xt) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Helper.highlightElement(driver, CommonLoginHeader);
		Helper.highlightElement(driver, token);
		token.sendKeys(xt);
		Helper.highlightElement(driver, EnterPortal);
		EnterPortal.click();
		try {
		Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		e.printStackTrace();
		}
	}

	public void Logout_of_Member_Portal() {

		Helper.highlightElement(driver, UserDropdown);
		UserDropdown.click();
		Helper.highlightElement(driver, Logout);
		Logout.click();
		Helper.highlightElement(driver, CommonLoginHeader);

	}

//	public void OnlyLoginClick() {
//		Helper.highlightElement(driver, LoginButton);
//		LoginButton.click();
//		if(UnameError.isDisplayed()) {
//			System.out.println("Username Empty error is Displayed");
//			if(PwError.isDisplayed()) {
//				System.out.println("Password Empty Error is Displayed");
//				if(TokenError.isDisplayed()) {
//					System.out.println("Token Empty Error is Displayed");
//				}
//				else{
//
//					System.out.println("Token Empty Error is Not Displayed");
//					Assert.fail();
//				}
//
//
//			}
//			else{
//
//				System.out.println("Password Empty Error is Not Displayed");
//				Assert.fail();
//			}
//		}
//		else{
//
//			System.out.println("Username Empty Error is Not Displayed");
//			Assert.fail();
//		}
//	}

	public String getAlertText() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		Alert alert = driver.switchTo().alert();
		String alerttext =alert.getText();
		//System.out.println("Alertext "+alerttext);
		return alerttext;

	}

	public void handleAlert() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Alert is Handled");
	}
	
	public void verifyLoginPageHeader() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.getMessage();
		}
		Helper.highlightElement(driver, CommonLoginHeader);	
	}
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (Exception e) 
	    { 
	    	e.getMessage();
	        return false; 
	    }
	} 
}
