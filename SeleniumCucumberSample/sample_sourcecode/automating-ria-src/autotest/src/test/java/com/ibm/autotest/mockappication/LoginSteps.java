package com.ibm.autotest.mockappication;

import java.util.logging.Logger;

import com.ibm.autotest.mockapplication.NaviagtorFactory;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

public class LoginSteps {
	private static final Logger LOGGER = Logger.getLogger(LoginSteps.class.getName());
	
	@Given("^I navigate to the mock application$")
	public void given_I_navigate_to_the_mock_application(){
		LOGGER.info("Entering: I navigate to the mock application");
		NaviagtorFactory.getNavigator().given_I_navigate_to_the_mock_application();
	}
	
	@When("^I try to login with '(.+)' credentials$")
	public void when_I_try_to_login(String credentialsType){
		LOGGER.info("Entering: I try to login with " + credentialsType + " credentials");
		NaviagtorFactory.getNavigator().when_I_try_to_login(credentialsType);
	}
	
	@Then("^I should see that I logged in '(.+)'$")
	public void then_I_login(String outcome){
		LOGGER.info("Entering: I should see that I logged in " + outcome);
		NaviagtorFactory.getNavigator().then_I_login(outcome);
	}
}
