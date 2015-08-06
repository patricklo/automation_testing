package com.ibm.autotest.mockapplication.view;

import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;

import com.ibm.autotest.mockapplication.containers.LoginPageContainer;
import com.ibm.autotest.utils.BrowserDriver;

public class LoginView {
	private static final Logger LOGGER = Logger.getLogger(LoginView.class.getName());
	private static final LoginPageContainer loginContainer = PageFactory.initElements(BrowserDriver.getCurrentDriver(), LoginPageContainer.class);
	
	public static void isDisplayedCheck(){
		LOGGER.info("Checking login page is displayed");
		BrowserDriver.waitForElement(loginContainer.loginPageDiv);
		loginContainer.loginPageDiv.isDisplayed();
	}
	
	public static void login(String username, String password){
		LOGGER.info("Logging in with username:" + username + " password:" + password);
		loginContainer.usernameInput.sendKeys(username);
		loginContainer.passwordInput.sendKeys(password);
		loginContainer.submitButton.click();
		LOGGER.info("Login submitted");
	}
	
	public static void checkLoginSuccess(){
		LOGGER.info("Check login was successful");
		HomeView.isDisplayedCheck();	
	}
	
	public static void checkLoginErrors(){
		LOGGER.info("Check login errors displayed");
		BrowserDriver.waitForElement(loginContainer.getUsernameValidationDiv());
		BrowserDriver.waitForElement(loginContainer.getPasswordValidationDiv());
	}
}
