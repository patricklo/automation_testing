package com.ibm.autotest.mockappication;

import org.junit.runner.RunWith;
import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(

        features = "MockApp",//path to the features
        format = {"json:target/integration_cucumber.json"},//what formatters to use
        tags = {"@login"})//what tags to incluse(@)/exclude(@~)
public class LoginTest {
}
