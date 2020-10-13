package com.memberdues.pages;
import javax.swing.text.Highlighter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.memberdues.utility.Helper;

public class FirstResetPasswordPage{

	WebDriver driver;

	public FirstResetPasswordPage(WebDriver driver) {
		this.driver= driver;

	}

	@FindBy(xpath = "//*[@id='container-wrapper']//h1") WebElement ResetPwHeader;
	@FindBy(xpath = "//*[@id='oldPassword']") WebElement OldPassword;
	@FindBy(xpath = "//*[@id='newPassword']") WebElement NewPassword;
	@FindBy(xpath = "//*[@id='confirmPassword']") WebElement ConfirmPassword;
	@FindBy(xpath = "//*[@id='question1']") WebElement Question1;
	@FindBy(xpath = "//*[@id='answer1']") WebElement Answer1;
	@FindBy(xpath = "//*[@id='question2']") WebElement Question2;
	@FindBy(xpath = "//*[@id='answer2']") WebElement Answer2;
	@FindBy(xpath = "//button[contains(text(),'Submit')]") WebElement Submit;
	@FindBy(xpath = "//*[@id='confirmationModal']//h5") WebElement ConfirmModal;
	@FindBy(xpath = "//*[@id='confirmationModalBody']/h6") WebElement ConfirmTextOnModal;
	@FindBy(xpath = "//*[@id='confirmationModalCloseBtn']") WebElement ModalClose;
	@FindBy(xpath = "//*[@id='confirmationModalYesBtn']") WebElement ModalYes;
	
	

	public String get_First_Reset_Header() {

		Helper.highlightElement(driver, ResetPwHeader);
		String Actual = ResetPwHeader.getText();
		return Actual;
	}


	public void reset_First_Password(String OldPw, String newpw, String Q1Val, String Ans1, String Q2Val, String Ans2) {
		
		Helper.highlightElement(driver, OldPassword);
		OldPassword.sendKeys(OldPw);
		
		Helper.highlightElement(driver, NewPassword);
		NewPassword.sendKeys(newpw);
		
		Helper.highlightElement(driver, ConfirmPassword);
		ConfirmPassword.sendKeys(newpw);
		
		Helper.highlightElement(driver, Question1);
		Select Ques1 = new Select(Question1);
		Ques1.selectByValue(Q1Val);
		
		Helper.highlightElement(driver, Answer1);
		Answer1.sendKeys(Ans1);
		
		Select Ques2 = new Select(Question2);
		Ques2.selectByValue(Q2Val);
		
		Helper.highlightElement(driver, Answer2);
		Answer2.sendKeys(Ans2);
		
		Helper.highlightElement(driver, Submit);
		Submit.click();
		
	}
	
	public String confirm_Text_Modal() {
		Helper.highlightElement(driver, ConfirmModal);
		Helper.highlightElement(driver, ConfirmTextOnModal);
		String Modal_text = ConfirmTextOnModal.getText();
		return Modal_text;
	}
	
	public void Confirm_Reset() {
		
		Helper.highlightElement(driver, ModalClose);
		Helper.highlightElement(driver, ModalYes);
		ModalYes.click();
		
	}


}