package com.ibm.automation.test;

import java.io.File;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Assume;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.ibm.automation.demoautomation.pages.uims.portal.AuthenticatePage;
import com.ibm.automation.demoautomation.parsers.Parser;
//import com.ibm.automation.demoautomation.hsbc.SignInPage;
import com.ibm.automation.demoautomation.utils.TestUtils;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;


public abstract class AbstractBaseTest {

	private Map<String, Object> parsedData;
	
	@Autowired
	@Qualifier("jsonParser")
	private Parser parser;
	
	@Autowired
	private TestUtils testUtils;

	@Autowired
	private AuthenticatePage authenticatePage;
	
	@Autowired
	public WebDriverWrapper webDriver;
	
	@Value("${browser}")
	private String browser ;
	
	public Map<String, Object> getParsedData(String fileName) throws Exception {
		if( parsedData == null ){
			
			File file = FileUtils.getFile("src", "test",  "resources", fileName);

			parsedData = parser.parseData(file);
		}
		return parsedData;
	}
	
     public void  setUpChecks(String testName) throws Exception {
		// Check Run mode
	    if (testUtils==null){
	    	System.out.print("failed to intiatie testUtils bean");
	    	
	    }
		if (testUtils.isSkip(testName)) {
			Assume.assumeTrue(false);
		}
//				if(!authenticatePage.isUserLoggedIn()) {
//		            authenticatePage.doLogin() ;
//		}
		
	}
	
	public void signOut() {
//		authenticatePage.logout();
	}
	
	public void closeWebDriver() {
		
		webDriver.quitDriver();
	}
	
	public void takeScreenShot(String screenShotName){
       //Cannot take screenshot if the webdriver is a instance of HtmlUnitDriver , supports IE,Chrome,Firefox currently
		if(browser.equalsIgnoreCase("IE")||browser.equalsIgnoreCase("Chrome")||browser.equalsIgnoreCase("Firefox"))
		webDriver.takeScreenShot(screenShotName ) ;
	
	
	}

}
