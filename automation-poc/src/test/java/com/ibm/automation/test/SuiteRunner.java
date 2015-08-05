package com.ibm.automation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.ibm.automation.demoautomation.pages.uims.portal.AuthenticatePage;


@RunWith(Suite.class)
@SuiteClasses( {    
	//SiteSearchResultsPageTest.class,
	AuthenticatePageTest.class,
	//PaymentsandTransfersPageTest.class,
})
		
public class SuiteRunner {

}
