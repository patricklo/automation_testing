package com.ibm.autotest.mockapplication;

import com.ibm.autotest.constants.CredentialsType;
import com.ibm.autotest.constants.Outcome;
import com.ibm.autotest.mockapplication.view.LoginView;
import com.ibm.autotest.utils.BrowserDriver;

public class Navigation {
	private User user;

	public void given_I_navigate_to_the_mock_application(){
		BrowserDriver.loadPage("http://localhost/MockApplication/html/MockApplication.html");
		LoginView.isDisplayedCheck();
	}

	public void when_I_try_to_login(String credentialsType) {
		CredentialsType ct = CredentialsType.credentialsTypeForName(credentialsType);
		switch(ct){
			case VALID:
				user = Users.createValidUser();
			break;
			case INVALID:
				user = Users.createInvalidUser();
			break;
		}
		LoginView.login(user.getUsername(), user.getPassword());
	}
	
	public void then_I_login(String outcomeString){
		Outcome outcome = Outcome.outcomeForName(outcomeString);
		switch(outcome){
			case SUCCESS:
				LoginView.checkLoginSuccess();
			break;
			case FAILURE:
				LoginView.checkLoginErrors();
			break;
		}
	}
}
