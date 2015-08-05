package com.ibm.automation.test;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty","html:reports/test-report"},
		tags= {"@siteSearchResultsPageTest"},                   
		glue = {"com/ibm/automtion/test"},
        features =  {"com/ibm/automtion/test"}
		)

public class CucumberRunner {


}
