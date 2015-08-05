package com.ibm.automation.demoautomation.pages;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.automation.demoautomation.core.AbstractPage;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;

@Component
public class SiteSearchResultsPage extends AbstractPage {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(SiteSearchResultsPage.class);
	private static final String PAGE_ACTION_FLOW_INPUT_FILE="SiteSearchResultsPageActionFlow.json";
	
	@Autowired
	private WebDriverWrapper webDriver;
		

	@SuppressWarnings("unchecked")
	public boolean searchKeyWord(Map<String, String> inputParameters)throws Exception{
		Map<String, Object> parsedPageFlowData = getParsedPageFlowData(PAGE_ACTION_FLOW_INPUT_FILE);		
		Map<String, Object> siteSearchResultsPage = (HashMap<String, Object>) parsedPageFlowData.get("SiteSearchResultsPage"); 
		executePageAction(siteSearchResultsPage, inputParameters);
		return true;
	}

}
