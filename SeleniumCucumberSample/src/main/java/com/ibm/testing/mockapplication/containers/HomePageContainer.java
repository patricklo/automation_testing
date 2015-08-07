package com.ibm.testing.mockapplication.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by patrick on 8/7/2015.
 */
public class HomePageContainer {

    @FindBy(how= How.CSS,using="#HomePage h1")
    public WebElement homePageTitle;
}
