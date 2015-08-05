package com.ibm.automation.test;

import static org.junit.Assert.assertTrue;

import com.ibm.automation.demoautomation.bootstrap.AutomationBootstrap;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.automation.demoautomation.pages.SiteSearchResultsPage;
import com.ibm.automation.demoautomation.utils.TestUtils;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.table.TableConverter;


@ContextConfiguration(classes=AutomationBootstrap.class) 
@RunWith(Parameterized.class)
public class SiteSearchResultsPageTest extends AbstractBaseTest {
	
	//Log4J initialization
	private final Logger logger = LoggerFactory.getLogger(SiteSearchResultsPageTest.class);
 
	@Autowired
	private SiteSearchResultsPage siteSearchResultsPage ;
	private final String testExpectedResultsDataFileName = "SiteSearchResultsPage.json";
	
	private Map<String, String> inputParams;
	
	public SiteSearchResultsPageTest(){
//		this.inputParams = new HashMap();
//		inputParams.put("q", "Credit");
	}

	@Given("^Test suites are ready$")
	public void beforeTest() throws Exception {
		
		logger.info("Begining the SiteSearchResults page test...");
		TestContextManager testContextManager = new TestContextManager(getClass() );
		testContextManager.prepareTestInstance(this);
		setUpChecks("SiteSearchResultsPageTest");
	}
    
	@When("^user try to search the keywords$")
    public void searchFunctionTestCase(DataTable datatable) throws Exception {
    	logger.info("Checking the search function ...");

    	inputParams=datatable.asMaps().get(0);

//    	TableConverter tableConverter=datatable.getTableConverter();
//    	System.out.print("tostring**"+datatable.toString());
//    	System.out.print("topCells"+datatable.topCells());
//    	System.out.print("list"+datatable.raw());
    	Assert.assertTrue(siteSearchResultsPage.searchKeyWord(inputParams));   	
    }
	
    @Then("^the related results should be pop up properly$")
	public void searchedContentValidationTestCase() throws Exception {
		
		logger.info("Checking and validating for the content of search sumary page ...");
		
		@SuppressWarnings("unchecked")
		Map<String, Object> siteSearchResultsPageQueryResultMap = (Map<String, Object>)getParsedData(testExpectedResultsDataFileName).get("SiteSearchResultsPageTest") ;
		@SuppressWarnings("unchecked")
		Map<String, String> expectedSearchResult = (Map<String, String>)siteSearchResultsPageQueryResultMap.get("ExpectedSearchResultsPageResults") ;
		System.out.println("Expected:" + expectedSearchResult);
		// Add the logic for contents verification
        for (String key:inputParams.keySet()){
        	System.out.println("Contents being verified:" + inputParams.get(key.trim()));	
		Assert.assertTrue(siteSearchResultsPage.isMessageDisplayed(expectedSearchResult.get(inputParams.get(key))));
        }
	}
	
    @Then("^the title should contain global infos$")
	public void titleValidationTestCase() throws Exception {
		// verify title contents
//		while(!siteSearchResultsPage.isMessageDisplayed("Your search"))
//		    {
//		      	Thread.sleep(1000);		    
//		    }
		logger.info("Checking and validating for page titile of search sumary page ...");	
	   	Assert.assertTrue(siteSearchResultsPage.validateHeader(( getParsedData(testExpectedResultsDataFileName)) ,null)) ;				
	}	
	
    @Then("^the footer should contain the global infos$")
	public void headerfooterValidationTestCase() throws Exception {
		// verify general header elements
//		while(!siteSearchResultsPage.isMessageDisplayed("Your search"))
//	       {
//	      	Thread.sleep(1000);		    
//	       }
		logger.info("Checking and validating for footer elements ...");
		// verify general footer elements
	   try {	
		Assert.assertTrue(siteSearchResultsPage.validateFooter( getParsedData(testExpectedResultsDataFileName) ));
	   }
	   catch (AssertionError e){		   
		  logger.error(e.toString());
		  takeScreenShot("headerfooter");	
		  throw e;
	   }	   
	   
	}
	
//	@After
//	public void tearDown(){
//		closeWebDriver();
//	}
//	
    
//    @Parameters
//	public static Collection<Object[]> getInputParameters(){
//		TestUtils testUtil = new TestUtils() ;
//		Object[][] data = testUtil.getData("SiteSearchResultsPageTest");
//		return Arrays.asList(data);   
//	}
}
