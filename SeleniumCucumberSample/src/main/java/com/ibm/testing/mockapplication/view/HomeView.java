package com.ibm.testing.mockapplication.view;

import com.ibm.testing.mockapplication.containers.HomePageContainer;
import com.ibm.testing.utils.BrowserDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

/**
 * Created by patrick on 8/7/2015.
 */
public class HomeView {
    private static final Logger LOGGER = Logger.getLogger(HomeView.class.getName());
    private static final HomePageContainer homePageContainer = PageFactory.initElements(BrowserDriver.getCurrentDriver(), HomePageContainer.class);


    public static void isDisplayCheck(){
        LOGGER.info("Checking login page is displayed");
        BrowserDriver.waitForElement(homePageContainer.homePageTitle);
        homePageContainer.homePageTitle.isDisplayed();
    }
}
