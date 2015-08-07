package com.ibm.testing.mockapplication;

import com.ibm.testing.constants.CredentialsType;
import com.ibm.testing.constants.Outcome;
import com.ibm.testing.mockapplication.view.LoginView;
import com.ibm.testing.utils.BrowserDriver;

/**
 * Created by patrick on 8/6/2015.
 */
public class Navigation {
    private User user;

    public void given_I_navigate_to_the_mock_application(){
        BrowserDriver.loadPage("http://localhost:8080/mockwebapp/html/MockApplication.html");
        LoginView.isDisplayedCheck();
    }

    public void when_I_try_to_login(String credentialsType){
        CredentialsType ct = CredentialsType.credentialsTypeForName(credentialsType);
        switch (ct){
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
        switch (outcome){
            case SUCCESS:
                LoginView.checkLoginSuccess();
                break;
            case FAILURE:
                LoginView.checkLoginErros();
                break;
        }
    }


}
