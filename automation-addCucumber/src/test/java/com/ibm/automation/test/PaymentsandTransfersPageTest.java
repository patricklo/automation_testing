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
import com.ibm.automation.demoautomation.pages.uims.portal.PaymentsandTransfersPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@ContextConfiguration(classes=AutomationBootstrap.class) 
@RunWith(SpringJUnit4ClassRunner.class) 
@SuppressWarnings("unused")
public class PaymentsandTransfersPageTest extends AbstractBaseTest{

	@Autowired
	private PaymentsandTransfersPage paymentsAndTransftersPage;
	
	//Log4J initialization
	private final Logger logger = Logger.getLogger(PaymentsandTransfersPageTest.class);
	
	@Given("^Given user is already logged into hsbcnet$")
    public void setup() throws Exception {
		setUpChecks("paymentsAndTransftersPage");
    }
    
	@When("^When user try to create priority payment$")
	public void testLoginPage() throws Exception{		 
		Assert.assertTrue(paymentsAndTransftersPage.createPaymentInstruction()) ;
	}
	
	@Then("^Then priority payment should be created properly")
	public void verifyPriorityPaymentSumaryPage(){
		System.out.print("Should be created properly");
	}
	
	
	
}
