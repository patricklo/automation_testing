package com.ibm.automation.demoautomation.pages.uims.portal;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.automation.demoautomation.core.AbstractPage;
import com.ibm.automation.demoautomation.utils.CommonAutoUtils;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;


@Component
public class AuthenticatePage extends AbstractPage {
	
	private final Logger logger = LoggerFactory.getLogger(WebDriverWrapper.class);
	
	@Value("${hsbc.username}")
	private String username;
	
	@Value("${hsbc.password}")
	private String password;	
	
	@Value("${hsbc.mem}")
	private String mem;
	
	@Autowired
	private WebDriverWrapper webDriver;
	
	private boolean userLoggedIn;
	
	private String firstCharPassword="";
	
	private String secondCharPassword="";
	
	private String thirdCharPassword="";
			
	public boolean isUserLoggedIn(){
		return this.userLoggedIn ;
	}
	
	

	public boolean doLogin() throws Exception{
		
     
     /*If user is already logged in just return loggedIn = true, to be finished once got the logged page's info.		
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
	
		//<li class="last">Capture Username</li>
        while(!webDriver.getObjectByClass("last").getText().contains("Capture Username")){
        	logger.info("Authenticate Page does not shown up yet for inputing the username");
        	Thread.sleep(1000);       	
        }
		logger.info("Authenticate Page is shown up!");
        
		//Enter the user name;<input name="userid" type="text" autocomplete="off" class="focusOnLoad" value="" id="userid"/></span
		webDriver.getObjectById("userid").sendKeys(username);
		
		//Try to use clipboard
	    Robot robot = null;
	    try {
	      robot = new Robot();
	    } catch (AWTException e1) {
	      e1.printStackTrace();
	    }
		
		
		
//	    CommonAutoUtils.setClipboardData(username);
//	    webDriver.getObjectById("userid").clear();
//	    robot.keyPress(KeyEvent.VK_CONTROL);
//	    robot.keyPress(KeyEvent.VK_V);
//	    robot.keyRelease(KeyEvent.VK_V);
//	    robot.keyRelease(KeyEvent.VK_CONTROL);
		
		//Click the Continue button; <input name="action" type="hidden" value="continue"/>
		webDriver.getObjectByName("action").submit();

	//	Thread.sleep(100);
		//check if user has already moved into the "Capture Security Credentials" Page.
		//<li class="last">Capture Security Credentials</li>
		while(!webDriver.getObjectByClass("last").getText().contains("Capture Security Credentials") ){
			logger.info("Authentication Page does not show up yet for Username: " + username);
			Thread.sleep(1000);
		}
			logger.info("Moved into Authentication Page successfully for: " + username);

		//Fill the memorable answer for the Step 1 in AuthenticationPage.	
		webDriver.getObjectById("memorableAnswer").sendKeys(mem);	
	
		fillPassword();
		
		/*
		    <span class="stacked">
           	            		<label for="FIRSTCHAR">1st</label>
                        		<input name="FIRSTCHAR" size="1" maxlength="1" type="password" onkeyup="javascript:tabToNextField(this,1,&#39;SECONDCHAR&#39;,event);" id="FIRSTCHAR"/>
           	</span>
           	<span class="stacked">
           	            		<label for="SECONDCHAR">2nd to last</label>
                        		<input name="SECONDCHAR" size="1" maxlength="1" type="password" onkeyup="javascript:tabToNextField(this,1,&#39;THIRDCHAR&#39;,event);" id="SECONDCHAR"/>
           	</span>
           	<span class="stacked">
           	            		<label for="THIRDCHAR">Last</label>
                        		<input name="THIRDCHAR" size="1" maxlength="1" type="password" onkeyup="javascript:tabToNextField(this,1,&#39;Continue&#39;,event);" id="THIRDCHAR"/>
           	</span>
		</span> 
		  
		  		  
		 */
		
	//  Disable the  onkeyup="javascript:tabToNextField(this,1,&#39;Continue&#39;,event);"
    //	WebElement div = webDriver.getObjectById("FIRSTCHAR");
    //	JavascriptExecutor jse = (JavascriptExecutor) webDriver;
    //	jse.executeScript("arguments[0].setAttribute('onkeyup', arguments[1])", div, "");

    //	WebElement div2 = webDriver.getObjectById("SECONDCHAR");
    //	jse.executeScript("arguments[0].setAttribute('onkeyup', arguments[1])", div2, "");
	
    //	WebElement div3 = webDriver.getObjectById("SECONDCHAR");
    //	jse.executeScript("arguments[0].setAttribute('onkeyup', arguments[1])", div3, "");
		
		if(firstCharPassword.length()==1&&secondCharPassword.length()==1)
		{	
	    webDriver.getObjectById("FIRSTCHAR").sendKeys(firstCharPassword);
	    webDriver.getObjectById("SECONDCHAR").sendKeys(secondCharPassword);
		}
		else
			
		{
		    CommonAutoUtils.setClipboardData(firstCharPassword);
			webDriver.getObjectById("FIRSTCHAR").clear();
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);		    
		    CommonAutoUtils.setClipboardData(secondCharPassword);
			webDriver.getObjectById("SECONDCHAR").clear();;
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);        
		}	
	    webDriver.getObjectById("THIRDCHAR").sendKeys(thirdCharPassword);	    
	    //	change to use clipBoard;
//	    CommonAutoUtils.setClipboardData(firstCharPassword);
//		webDriver.getObjectById("FIRSTCHAR").clear();
//	    robot.keyPress(KeyEvent.VK_CONTROL);
//	    robot.keyPress(KeyEvent.VK_V);
//	    robot.keyRelease(KeyEvent.VK_V);
//	    robot.keyRelease(KeyEvent.VK_CONTROL);       
//	    CommonAutoUtils.setClipboardData("");
//	    CommonAutoUtils.setClipboardData(secondCharPassword);
//		webDriver.getObjectById("SECONDCHAR").clear();;
//	    robot.keyPress(KeyEvent.VK_CONTROL);
//	    robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);        
//        CommonAutoUtils.setClipboardData("");
//	    CommonAutoUtils.setClipboardData(thirdCharPassword);
//		webDriver.getObjectById("THIRDCHAR").clear();
//	    robot.keyPress(KeyEvent.VK_CONTROL);
//	    robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
	    
	    
	    
	//<a href="javascript:beforeSubmit(); document.forms[&#39;P_15110cam10To30Form&#39;].submit();" id="Continue">Continue</a>
	//    webDriver.getObjectById("Continue").click();		
		Thread.sleep(1000);
		

	
	if(webDriver.getObject("//title").getText().contains("Webroot SecureAnywhere"))
		{
	    Thread.sleep(500);
		//if logged in page does not shown up.
		/*
	<ul class="action">
		<li><a href="/uims/portal/WSAPromotionalDownloader;jsessionid=0000cAsDH2XSHFE1uo93W6GR3_6:14ak8djk8?.pp=WSAPromotional">Download now</a></li>
		<li class="second"><a href="javascript:submitForm(&#39;RML&#39;)">Remind me later</a></li>	
		<li class="second"><a href="javascript:submitForm(&#39;NT&#39;)">No thanks</a></li>
	</ul>
		 */

		webDriver.getObjectByLink("javascript:submitForm(&#39;RML&#39;)").click();
		webDriver.getObjectByLink("javascript:P_27160submitForm(&#39;RMLF&#39;);").click();		
		webDriver.getObjectByLink("javascript:HSBCnetUI.Util.closeTab()").click();
		}

          //<a class="infoTip myprofile" href="/uims/portal/EditMyProfile;jsessionid=0000T14C23wvk9m9mqLG2zH5E8-:14ak8djk8" target="myprofile"><strong>R36SB2&nbsp;R36SB2</strong>		
		webDriver.getObject("//a[@target='myprofile']").getText().contains(username);
		logger.info("Completed Login Execution");	
		userLoggedIn=true;
		return userLoggedIn ;
	}
	
	//logout after all operation

	public void logout(){
		if(userLoggedIn){
			webDriver.waitForAction(15);
			webDriver.getObjectByClass("logoff").click();
			userLoggedIn=false;
			webDriver.closeDriver();
		}		
	}

	
	private void fillPassword() throws Exception{
		
		//Fetch the 1st, 2nd , 3rd password char based on password prompt info.		
		HashMap<String, Integer> passWordHashMap= new HashMap<String, Integer>();
		int firstCharPasswordPOS, secondCharPasswordPOS, thirdCharPasswordPOS;	
		passWordHashMap.put("1st", 1);
		passWordHashMap.put("2nd", 2);
		passWordHashMap.put("3rd", 3);
		passWordHashMap.put("4th", 4);
		passWordHashMap.put("5th", 5);
		passWordHashMap.put("6th", 6);
		passWordHashMap.put("7th", 7);
		passWordHashMap.put("8th", 8);
		passWordHashMap.put("9th", 9);
		passWordHashMap.put("2nd to last", 12);
		passWordHashMap.put("3rd to last", 13);
		passWordHashMap.put("4th to last", 14);
		passWordHashMap.put("5th to last", 15);
		passWordHashMap.put("6th to last", 16);
		passWordHashMap.put("7th to last", 17);
		passWordHashMap.put("8th to last", 18);
		passWordHashMap.put("9th to last", 19);
		passWordHashMap.put("Last", 21);
     

		String firstcharLocator=webDriver.getText("//label[@for='FIRSTCHAR']");
		String secondcharLocator=webDriver.getText("//label[@for='SECONDCHAR']");
		String thirdcharLocator=webDriver.getText("//label[@for='THIRDCHAR']");
		
		System.out.print("Webdriver has found the first one is:"+firstcharLocator+"second:"+secondcharLocator+"third:"+thirdcharLocator);
		
		if(passWordHashMap.get(firstcharLocator)==null||passWordHashMap.get(secondcharLocator)==null||passWordHashMap.get(thirdcharLocator)==null)
		   System.out.print("Failed to fetch the password locator");
		firstCharPasswordPOS=(int) passWordHashMap.get(firstcharLocator);
		secondCharPasswordPOS=(int) passWordHashMap.get(secondcharLocator);
		thirdCharPasswordPOS=(int) passWordHashMap.get(thirdcharLocator);
		if(firstCharPasswordPOS<10)
		  firstCharPassword=password.substring(firstCharPasswordPOS-1, firstCharPasswordPOS);
		else if (firstCharPasswordPOS<20)
		  firstCharPassword=password.substring((firstCharPasswordPOS)-11);
		else
		  firstCharPassword=password.substring(password.length()-1);
		if(secondCharPasswordPOS<10)
		  secondCharPassword=password.substring (secondCharPasswordPOS-1, secondCharPasswordPOS);
		else if (secondCharPasswordPOS<20)
		  secondCharPassword=password.substring((secondCharPasswordPOS)-11);
		else
			secondCharPassword=password.substring(password.length()-1);
		if(thirdCharPasswordPOS<10)
		  thirdCharPassword=password.substring(thirdCharPasswordPOS-1, thirdCharPasswordPOS);
		else if (thirdCharPasswordPOS<20)
			thirdCharPassword=password.substring((thirdCharPasswordPOS)-11);
		else
			thirdCharPassword=password.substring(password.length()-1);		
		System.out.print(firstcharLocator+":"+firstCharPassword+"."+secondcharLocator+":"+secondCharPassword+"."+thirdcharLocator+":"+thirdCharPassword);
	}
	
	
	
}
