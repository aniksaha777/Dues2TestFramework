package com.memberdues.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.memberdues.utility.Helper;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath = "//div[@class='login-form']//div[2]") WebElement LoginHeader;
	@FindBy(name = "uname") WebElement username;
	@FindBy(name = "psw") WebElement pwd;
	@FindBy(name = "token") WebElement token;
	@FindBy(xpath = "//*[@id='btnlogin']") WebElement LoginButton;
	@FindBy(xpath = "//*[@id='userDropdown']") WebElement UserDropdown;
	@FindBy(xpath = "//span[@onclick='logoutService()']/a") WebElement Logout;
	
	
	

	public void Login_to_Member_Portal(String userid,String pw, String xt) {

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
		Helper.highlightElement(driver, token);
		token.sendKeys(xt);
		Helper.highlightElement(driver, LoginButton);
		LoginButton.click();
		
		
	}
	
	public void Logout_of_Member_Portal() {
		
		Helper.highlightElement(driver, UserDropdown);
		UserDropdown.click();
		Helper.highlightElement(driver, Logout);
		Logout.click();
		Helper.highlightElement(driver, LoginHeader);
		

	}

}
