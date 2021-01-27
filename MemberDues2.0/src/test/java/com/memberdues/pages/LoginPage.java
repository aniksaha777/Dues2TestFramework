package com.memberdues.pages;

import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.memberdues.utility.Helper;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[contains(text(),'Member Login')]") WebElement LoginHeader;
	@FindBy(name = "uname") WebElement username;
	@FindBy(name = "psw") WebElement pwd;
	@FindBy(xpath = "//*[@id='btnlogin']") WebElement LoginButton;
	@FindBy(xpath = "//*[@id='userDropdown']") WebElement UserDropdown;
	@FindBy(xpath = "//span[@onclick='logoutService()']/a") WebElement Logout;
	@FindBy(xpath ="//*[@id='uname'and @class='form-control loging-error']") WebElement UnameError;
	@FindBy(xpath ="//*[@id='psw' and @class='form-control loging-error']") WebElement PwError;
	@FindBy(xpath ="//*[@id='token' and @class='form-control loging-error']") WebElement TokenError;
	@FindBy(id ="captcha_image") WebElement CaptchaImg;

	public void EnterUserDetails(String userid,String pw) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Helper.highlightElement(driver, LoginHeader);
		Helper.highlightElement(driver, username);
		username.sendKeys(userid);
		Helper.highlightElement(driver, pwd);
		pwd.sendKeys(pw);

	}
	
	public void HandleCaptcha(String tcname) {
		
		String captchapath = Helper.captureScreenshot(driver, tcname, CaptchaImg);
		System.out.println(captchapath);
		ITesseract image = new Tesseract();
		String imageText;
		try {
			imageText = image.doOCR(new File(captchapath));
			System.out.println(imageText);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Logout_of_Member_Portal() {

		Helper.highlightElement(driver, UserDropdown);
		UserDropdown.click();
		Helper.highlightElement(driver, Logout);
		Logout.click();
		Helper.highlightElement(driver, LoginHeader);

	}

	public void OnlyLoginClick() {
		Helper.highlightElement(driver, LoginButton);
		LoginButton.click();
		if(UnameError.isDisplayed()) {
			System.out.println("Username Empty error is Displayed");
			if(PwError.isDisplayed()) {
				System.out.println("Password Empty Error is Displayed");
				if(TokenError.isDisplayed()) {
					System.out.println("Token Empty Error is Displayed");
				}
				else{

					System.out.println("Token Empty Error is Not Displayed");
					Assert.fail();
				}


			}
			else{

				System.out.println("Password Empty Error is Not Displayed");
				Assert.fail();
			}
		}
		else{

			System.out.println("Username Empty Error is Not Displayed");
			Assert.fail();
		}
	}

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
		Helper.highlightElement(driver, LoginHeader);	
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
	public void clickLogin() {
		Helper.highlightElement(driver, LoginButton);
		LoginButton.click();
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
