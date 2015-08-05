package com.ibm.automation.demoautomation.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.automation.demoautomation.utils.CommonAutoUtils;


@Component
public class WebDriverWrapper {
	
	final Logger logger = LoggerFactory.getLogger(WebDriverWrapper.class);
			
	private EventFiringWebDriver eventFiringWebDriver = null;
	private JavascriptExecutor jsExecutor=null;
	private final static String WEBDRIVER_PATH = System.getProperty("user.dir")+"//src//main//resources//" ;
	private final static String IE_WEB_DRIVER="IEDriverServer.exe";
	private final static String CHROME_WEB_DRIVER="chromedriver.exe";

	
	@Value("${browser}")
	private String browser ;

	@Value("${testURL}")
	private String webUrl ;
	
	@Value("${hsbc.screenshotpath}")
	private String screenShotPath;

	
	@PostConstruct
	public void initialize() throws IOException{
		logger.info("Browser=" + browser + " URL=" + webUrl );
		WebDriver webDriver = null ;
		//Initialize the web driver and EventFiringWebDriver
		if("Firefox".equals(browser)){
//			setEnvironmentForFASTEclipse();
			webDriver = new FirefoxDriver();
		}else if("IE".equals(browser)){
			System.setProperty("webdriver.ie.driver", WEBDRIVER_PATH + IE_WEB_DRIVER);
			webDriver = new InternetExplorerDriver();
		}else if("Chrome".equals(browser) ){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			System.setProperty("webdriver.chrome.driver", WEBDRIVER_PATH + CHROME_WEB_DRIVER);
			webDriver = new ChromeDriver(options);			
		}else {
			webDriver = new HtmlUnitDriver();	
			
			
		}	
		webDriver.get(webUrl) ;		
		this.eventFiringWebDriver = new EventFiringWebDriver(webDriver);
		this.jsExecutor = (JavascriptExecutor) webDriver;
	}

	/**
	 * Return web element Object by xpath
	 * @param xpathKey
	 * @return
	 * @throws InterruptedException 
	 */
	
	// judege whether the element exists or not based on the xpath
	public boolean doesWebElementExistByXpath(String xpathKey)
	 { 
	        try { 
	        	eventFiringWebDriver.findElement(By.xpath(xpathKey)); 
	               return true; 
	        } catch (NoSuchElementException e) { 
	            return false; 
	        } 
	 } 
	//judege whether the element exists or not based on the id element
	public boolean doesWebElementExistByID(String id)
	 { 
	        try { 
	        	eventFiringWebDriver.findElement(By.id(id)); 
	               return true; 
	        } catch (NoSuchElementException e) { 
	            return false; 
	        } 
	 } 
	
	
	public WebElement getObject(String xpathKey) throws InterruptedException {
        while (doesWebElementExistByXpath(xpathKey)==false)
        {
        	System.out.print("Looping when the following xpath does not show up:"+xpathKey);
        	Thread.sleep(100);
        }
        	return eventFiringWebDriver.findElement(By.xpath(xpathKey));
	}
	
	//Return web element Object by Id
	public WebElement getObjectById(String id) throws InterruptedException {
		while (doesWebElementExistByID(id)==false)
		{
			System.out.print("Looping when the following id does not show up:"+id);
			Thread.sleep(100);
		}
		return eventFiringWebDriver.findElement(By.id(id));
	}
	
	//Return web element Object by name
	public WebElement getObjectByName(String name) {
		return eventFiringWebDriver.findElement(By.name(name) );
	}
	
	//Return web element Object by link
	public WebElement getObjectByLink(String linkText) {
		return eventFiringWebDriver.findElement(By.linkText(linkText) );
	}
	
	//Return web element Object by partial link
	public WebElement getObjectByPartialLink(String linkText) {
		return eventFiringWebDriver.findElement(By.partialLinkText(linkText) );
	}
	//Return web element Object by class Name e.g. <span class="pagetitle">
	public WebElement getObjectByClass(String className) {
		return eventFiringWebDriver.findElement(By.className(className) );
	}
	
	public boolean isWindowTitle(String webElement){
		return eventFiringWebDriver.getTitle().contains(webElement) ;
	}
	
	//Return web element text 
	public String getText(String element) throws Exception{		
		if(eventFiringWebDriver.findElement(By.xpath(element)).isDisplayed()){
			String fieldValue = eventFiringWebDriver.findElement(By.xpath(element)).getText();
			logger.info("Successfully grabbed field value for "+ element );
			return fieldValue;	
		}else{
			logger.error("Could not grab field value for " + element ) ;
			throw new Exception("Could not grab field value for " + element);			
		}
	}
	
	//Input element value
	public void sendKey(String element, String input) throws Exception {
		if( element.contains( "//")){
			if (eventFiringWebDriver.findElement(By.xpath(element)).isDisplayed()) {
				getObject(element).clear();
				getObject(element).sendKeys(input);
			} else {
				throw new Exception("Could not send keys to " + element);
			}
		}else{
			if (eventFiringWebDriver.findElement( By.id(element) ).isDisplayed()) {
				getObject(element).clear();
				getObject(element).sendKeys(input);
			} else {
				throw new Exception("Could not send keys to " + element);
			}	
		}
	}
	
	//Click element
	public void click(String element) throws InterruptedException{
		if(eventFiringWebDriver.findElement(By.xpath(element)).isDisplayed()){
			getObject(element).click();		
			logger.info("Successfully clicked " + element);
		}else{
			logger.error("Could not click " + element);
		}		
	}
	
	//Select Drop Down
	public void dropDownSelect (String element, String input){
		if( element.contains( "//")){
			if(eventFiringWebDriver.findElement(By.xpath(element)).isDisplayed()){
				Select dropdown = new Select(eventFiringWebDriver.findElement( By.xpath(element) )); 
				dropdown.selectByVisibleText(input); 
			} else {
				logger.error("Could not select " + input + "from dropdown list");
			}
		}else{
			if(eventFiringWebDriver.findElement(By.id(element)).isDisplayed()){
				Select dropdown = new Select(eventFiringWebDriver.findElement( By.id(element) )); 
				dropdown.selectByVisibleText(input); 
			} else {
				logger.error("Could not select " + input + "from dropdown list");
			}
		}
	}
	
	//Take Screen shot and Store it.
     public void takeScreenShot(String fileName) {
		File scrFile = ((TakesScreenshot)eventFiringWebDriver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(screenShotPath+fileName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void waitForAction(long seconds){
		eventFiringWebDriver.manage().timeouts().implicitlyWait(seconds, java.util.concurrent.TimeUnit.SECONDS) ;
	}
	
	
	//Check if element is display on web page.
	public boolean isDisplayed(String element){
//		WebElement e = eventFiringWebDriver.findElement(By.xpath(element));
//		return e.isDisplayed();		
		return eventFiringWebDriver.getPageSource().contains(element);
	}
	
	//Check if element is display on web page.
	public boolean isDisplayed( HashMap<String, Object> headerElements ){
		headerElements = (HashMap<String, Object>)CommonAutoUtils.prepareXpathElement(headerElements);
		for ( String eachKey : headerElements.keySet() ){
			if( !isDisplayed(headerElements.get(eachKey).toString()) ){
				return false ;  
			}
		}
		return true ;				
	}
	
	//Check if current Page is displayed with input content .
	public boolean isWebPageDisplayed(String element){
		return eventFiringWebDriver.getPageSource().contains(element);		
	}
	/**
	 * Returns the heading(1st row of the table) of each column in the table
	 * 
	 * @param tableDataXpath
	 * @return List of columns
	 * @throws Exception
	 */
	public List<String> getTableHeading(String tableXpath ) throws Exception{
		List<String> columnHeaders = new ArrayList<String>();
		try {
			List<WebElement> tableElements = eventFiringWebDriver.findElements(By.xpath(tableXpath));
			for (WebElement webElement : tableElements) {
				String text = webElement.getText().trim();
				if ((text.length() > 0) && (webElement.getLocation().getX() > 1)) {
					columnHeaders.add(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnHeaders ; 
        
	}

	public List<String> getColumnData(String tableColumnXPath) {
		List<String> columnData = new ArrayList<String>();
		try {
			List<WebElement> tabledata = eventFiringWebDriver.findElements(By.xpath(tableColumnXPath));			
			for (WebElement webElement : tabledata) {
				String text = webElement.getText().trim();
				if ((text.length() > 0) && (webElement.getLocation().getX() > 1)) {
					columnData.add(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columnData ;
	}

	/**
	 * Parse the search result table column Header and each row values into a HashMap
	 * Column name is key and value is comma separated list of rows under column
	 * @param tableDataXpath
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<String>> getTableData(String tableDataXpath ) throws Exception{
		
        List<String> columnNames = getTableHeading(tableDataXpath);
        CommonAutoUtils.printList( columnNames ) ;
        
    	Map<String, List<String>> map = new HashMap<String, List<String>>();
    	
        for (String column : columnNames){
        	int columnIndex = 1 + columnNames.indexOf(column);
        	String tableRowDataXpath = tableDataXpath + "/td[" + columnIndex + "]" ;
        	map.put(column.trim(), getColumnData(tableRowDataXpath));      	
        } 
        return map;
	}
	
	public String openPopupWindow(String popUpWindowXPath) throws InterruptedException{
		// Store the current window handle
		String parentWindowHandle = eventFiringWebDriver.getWindowHandle();
		
		try{
			//Wait for 2 seconds to display original window so that pop window link is visible
			Thread.sleep(2000);
		}catch(InterruptedException interruptedException){
			logger.error("Exception occured:",interruptedException) ;
		}

		// Perform the click operation that opens new window
		click(popUpWindowXPath);
					
		// Switch to new window opened
		for (String winHandle : eventFiringWebDriver.getWindowHandles()) {
			eventFiringWebDriver.switchTo().window(winHandle);
		}
		return parentWindowHandle ;
	}
	
	public void closePopUp(String popUpWindowCloseButtonXPath, String parentWindowHandle ) throws InterruptedException{
		click(popUpWindowCloseButtonXPath);
		eventFiringWebDriver.switchTo().window(parentWindowHandle);
	}

	
	//Close the current window, quitting the browser if it's the last window currently open.
	public void closeDriver(){
		eventFiringWebDriver.close();
	}
	//Quits this driver, closing every associated window.
	public void quitDriver(){
		eventFiringWebDriver.quit();
	}
	
	public EventFiringWebDriver getEventFiringWebDriver() {
		return eventFiringWebDriver;
	}

	public void setEventFiringWebDriver(EventFiringWebDriver eventFiringWebDriver) {
		this.eventFiringWebDriver = eventFiringWebDriver;
	}	
	
}
