package com.ibm.testing.mockapplication.view;

import com.ibm.testing.mockapplication.containers.LoginPageContainer;
import com.ibm.testing.utils.BrowserDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by patrick on 8/7/2015.
 */
public class LoginView {
    private static final Logger LOGGER = Logger.getLogger(LoginView.class.getName());
    private static final LoginPageContainer loginContainer = PageFactory.initElements(BrowserDriver.getCurrentDriver(),LoginPageContainer.class);

    public static void isDisplayedCheck(){
        LOGGER.info("Checking load page is displayed");
        BrowserDriver.waitForElement(loginContainer.loginPageDiv);
        loginContainer.loginPageDiv.isDisplayed();
    }


    public static void login(String username,String password){
        LOGGER.info("Logging in with Username:" + username +" Password:"+password);
        loginContainer.usernameInput.sendKeys(username);
        loginContainer.passwordInput.sendKeys(password);
        loginContainer.submitButton.click();
        LOGGER.info("Login Submitted");
        }

    public static void checkLoginSuccess(){
        LOGGER.info("Check Login was successfuly");
        HomeView.isDisplayCheck();
    }

    public static void checkLoginErros(){
        LOGGER.info("Check login errors displayed");
        BrowserDriver.waitForElement(loginContainer.getUsernameValidationDiv());
        BrowserDriver.waitForElement(loginContainer.getPasswordValidationDiv());
    }




}
