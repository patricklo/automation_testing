package com.ibm.autotest.mockapplication.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageContainer {

	@FindBy(how = How.CSS, using = "#HomePage h1")
    public WebElement homePageTitle;
}
