package com.ibm.testing.mockapplication.containers;

import com.ibm.testing.utils.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import javax.xml.ws.WebEndpoint;

/**
 * Created by patrick on 8/7/2015.
 */
public class LoginPageContainer {
    private String validateionContainerCSS = "div.dijitValidationContainer";

    @FindBy(how= How.ID,using="LoginPage")
    public WebElement loginPageDiv;

    @FindBy(how=How.CSS,using="#LoginPage input[name=username]")
    public WebElement usernameInput;

    public WebElement getUsernameValidationDiv(){
        WebElement parent = BrowserDriver.getParent(BrowserDriver.getParent(usernameInput));
        return parent.findElement(By.cssSelector(validateionContainerCSS));
    }

    @FindBy(how=How.CSS,using="#LoginPage input[name=password]")
    public WebElement passwordInput;

    public WebElement getPasswordValidationDiv(){
        WebElement parent = BrowserDriver.getParent(BrowserDriver.getParent(passwordInput));
        return parent.findElement(By.cssSelector(validateionContainerCSS));
    }

    @FindBy(how=How.CSS,using="#LoginPage span[role='button'")
    public WebElement submitButton;

}
