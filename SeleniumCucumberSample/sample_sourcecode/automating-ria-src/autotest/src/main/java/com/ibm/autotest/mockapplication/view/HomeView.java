package com.ibm.autotest.mockapplication.view;

import java.util.logging.Logger;

import org.openqa.selenium.support.PageFactory;

import com.ibm.autotest.mockapplication.containers.HomePageContainer;
import com.ibm.autotest.utils.BrowserDriver;

public class HomeView {
	private static final Logger LOGGER = Logger.getLogger(HomeView.class.getName());
	private static final HomePageContainer homePageContainer = PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePageContainer.class);
	
	public static void isDisplayedCheck(){
		LOGGER.info("Checking login page is displayed");
		BrowserDriver.waitForElement(homePageContainer.homePageTitle);
		homePageContainer.homePageTitle.isDisplayed();
	}
}
