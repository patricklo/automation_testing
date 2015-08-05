package com.ibm.automation.test;

import static org.junit.Assert.assertTrue;

import com.ibm.automation.demoautomation.bootstrap.AutomationBootstrap;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.Arrays;
import java.util.Collection;
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


@ContextConfiguration(classes=AutomationBootstrap.class) 
@RunWith(Parameterized.class)
public class SiteSearchResultsPageTest extends AbstractBaseTest {
	
	//Log4J initialization
	private final Logger logger = LoggerFactory.getLogger(SiteSearchResultsPageTest.class);
 
	@Autowired
	private SiteSearchResultsPage siteSearchResultsPage ;
	private final String testExpectedResultsDataFileName = "SiteSearchResultsPage.json";
	
	private Map<String, String> inputParams;
	
	public SiteSearchResultsPageTest(Map<String, String> inputParams){
		this.inputParams = inputParams;
	}

	@Before
	public void beforeTest() throws Exception {
		
		logger.info("Begining the SiteSearchResults page test...");
		TestContextManager testContextManager = new TestContextManager(getClass() );
		testContextManager.prepareTestInstance(this);
		setUpChecks("SiteSearchResultsPageTest");
	}
    
	@Test
    public void searchFunctionTestCase() throws Exception {
    	logger.info("Checking the search function ...");

    	Assert.assertTrue(siteSearchResultsPage.searchKeyWord(inputParams));   	
    }
	
    @Test
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
		Assert.assertTrue(siteSearchResultsPage.isMessageDisplayed(expectedSearchResult.get(inputParams.get(key.trim()))));
        }
	}
	
	@Test
	public void titleValidationTestCase() throws Exception {
		// verify title contents
//		while(!siteSearchResultsPage.isMessageDisplayed("Your search"))
//		    {
//		      	Thread.sleep(1000);		    
//		    }
		logger.info("Checking and validating for page titile of search sumary page ...");	
	   	Assert.assertTrue(siteSearchResultsPage.validateHeader(( getParsedData(testExpectedResultsDataFileName)) ,null)) ;				
	}	
	
	@Test
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
    
    @Parameters
	public static Collection<Object[]> getInputParameters(){
		TestUtils testUtil = new TestUtils() ;
		Object[][] data = testUtil.getData("SiteSearchResultsPageTest");
		return Arrays.asList(data);   
	}
}
