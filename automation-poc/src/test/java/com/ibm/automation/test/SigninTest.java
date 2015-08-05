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

@ContextConfiguration(classes=AutomationBootstrap.class) 
@RunWith(SpringJUnit4ClassRunner.class) 
@SuppressWarnings("unused")
public class SigninTest extends AbstractBaseTest{
	
	@Autowired
	private SignInPage signInPage;
	
	//Log4J initialization
	private final Logger logger = Logger.getLogger(SigninTest.class);
	
	@Before
    public void setup() throws Exception {
    }
    
	@Test
	public void testLoginPage() throws InterruptedException{		 
		Assert.assertTrue(signInPage.doLogin()) ;
	}
	
}
