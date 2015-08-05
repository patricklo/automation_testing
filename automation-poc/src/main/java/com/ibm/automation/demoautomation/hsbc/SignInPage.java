package com.ibm.automation.demoautomation.hsbc;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.automation.demoautomation.core.AbstractPage;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;

@Component
public class SignInPage extends AbstractPage {
	
	private final Logger logger = LoggerFactory.getLogger(WebDriverWrapper.class);
	
	@Value("${hsbc.password}")
	private String username;
	
	@Value("${hsbc.username}")
	private String password;
	
	@Autowired
	private WebDriverWrapper webDriver;
	
	private boolean userLoggedIn;
		
	public boolean isUserLoggedIn(){
		return this.userLoggedIn ;
	}
	public boolean doLogin() throws InterruptedException{
		
     
     /*If user is already logged in just return loggedIn = true, since we don't have the valid test account yet, the commented out ones are just assumptions.		
		try{
			if((webDriver.getObjectByClass("pagetitle").getText().contains("Welcome to HSBC") ) ){
				userLoggedIn = true;
				logger.info("Login successful for Username: " + username);
				return userLoggedIn ;
			}
		}catch(NoSuchElementException exception){
			logger.info("User is not logged in" ) ;
		}
	  */	
		
		//Click the log on button; <a id="authenticate" class="redBtn" href="/1/2/3/personal/online-services/personal-internet-banking/view-accounts/view-accounts-post-registration-email" title="Log on to Personal Internet Banking"><span>Log on</span></a>
		webDriver.getObjectById("authenticate").click();
		//Enter the user name; <input id="userID" name="userid" type="text" value="" />
		webDriver.getObjectById("userID").sendKeys(username);
		//Click the log on button; <input id="enter" class="enter" type="submit" value="Log on" title="Log on to Personal Internet Banking" />
		webDriver.getObjectById("enter").submit();;
		
		
//		webDriver.getObjectById("password").sendKeys(password);

		Thread.sleep(2000);
		//check if log out link is displayed to assert that you have loggen in
		if(webDriver.getObjectByClass("pagetitle").getText().contains("Welcome to HSBC") ){
			userLoggedIn= true;
			logger.info("Login successful for Username: " + username);
		}else{
			userLoggedIn=false;
			logger.error("Login failed for Username: " + username);
		}
		logger.info("Completed Login Execution");
		return userLoggedIn ;
	}
	
	//logout after all operation
	/*
	public void logout(){
		if(userLoggedIn){
			webDriver.waitForAction(15);
			webDriver.getObject("//a[@href='/pesdoxpath']").click();
			userLoggedIn=false;
			webDriver.closeDriver();
		}		
	}
	*/

}
