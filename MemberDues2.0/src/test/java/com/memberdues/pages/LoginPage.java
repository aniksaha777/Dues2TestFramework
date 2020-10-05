package com.memberdues.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(name = "uname")
	WebElement username;

	@FindBy(name = "psw")
	WebElement pwd;

	@FindBy(name = "token")
	WebElement token;

	@FindBy(xpath = "//*[@id='btnlogin']")
	WebElement LoginButton;

	@FindBy(xpath = "//div[@class='login-form']//div[2]")
	WebElement LoginHeader;

	public void Login_to_Member_Portal(String userid,String pw, String xt) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		username.sendKeys(userid);
		pwd.sendKeys(pw);
		token.sendKeys(xt);
		LoginButton.click();

	}

}
