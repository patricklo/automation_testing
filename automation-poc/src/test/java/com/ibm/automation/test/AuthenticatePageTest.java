package com.ibm.automation.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.automation.demoautomation.bootstrap.AutomationBootstrap;
import com.ibm.automation.demoautomation.hsbc.SignInPage;
import com.ibm.automation.demoautomation.pages.uims.portal.AuthenticatePage;

@ContextConfiguration(classes=AutomationBootstrap.class) 
@RunWith(SpringJUnit4ClassRunner.class) 
@SuppressWarnings("unused")
public class AuthenticatePageTest extends AbstractBaseTest {

	
	@Autowired
	private AuthenticatePage authenticatePage;
	
	//Log4J initialization
	private final Logger logger = Logger.getLogger(AuthenticatePageTest.class);
	
	@Before
    public void setup() throws Exception {
    }
    
	@Test
	public void testLoginPage() throws Exception{		 
		Assert.assertTrue(authenticatePage.doLogin()) ;
	}
	
	
}
