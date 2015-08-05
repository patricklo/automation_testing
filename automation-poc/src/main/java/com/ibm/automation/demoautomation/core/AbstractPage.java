package com.ibm.automation.demoautomation.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.automation.demoautomation.parsers.Parser;
import com.ibm.automation.demoautomation.utils.CommonAutoUtils;
import com.ibm.automation.demoautomation.wrappers.WebDriverWrapper;

@SuppressWarnings("unchecked")
public abstract class  AbstractPage {
	
	@Autowired
	public WebDriverWrapper webDriver;
	
	private Map<String, Object> parsedSequenceAction;
	
	@Autowired
	@Qualifier("jsonParser")
	private Parser parser;
	
	public Map<String, Object> getParsedPageFlowData(String fileName) throws Exception {
		if( parsedSequenceAction == null ){
			
			File file = FileUtils.getFile("src","main","resources", fileName);
			parsedSequenceAction = parser.parseData(file);
		}
		return parsedSequenceAction;
	}
	
		
	public boolean validateHeader( Map<String, Object> parsedData, String link) throws InterruptedException{
		if(link != null) {
			webDriver.click(link) ;
		}
		return  webDriver.isDisplayed( (HashMap<String, Object>)parsedData.get("page_header") ) ;
	}
		
	public boolean validateFooter(Map<String, Object> parsedData){
		return webDriver.isDisplayed((HashMap<String, Object>)parsedData.get("page_footer") ); 
	}

	public boolean validateTitle(Map<String, Object> parsedData){
		return webDriver.isDisplayed((HashMap<String, Object>)parsedData.get("page_title") ); 
	}
	
	public boolean isMessageDisplayed(String messageText){
		return webDriver.isWebPageDisplayed( messageText ) ;
	}
	
	public void executePageAction(Map<String, Object> pageAction, Map<String, String> inputParameterMap){
		
		ArrayList<HashMap<String, Object>> actionSequence = (ArrayList<HashMap<String, Object>>)pageAction.get("ActionSequence");
		System.out.println("Action Sequence" + actionSequence);
		
		for ( HashMap<String, Object> eachAction : actionSequence ){
				Object webElement = eachAction.get("WebElement") ;
			if( webElement instanceof String){
				System.out.println("WebElement=" + eachAction.get("WebElement"));
				String parameters[] = new String[]{webElement.toString()};
				invokeWebDriverAction(eachAction.get("Action").toString(), 
						eachAction.get("WebElementType").toString(), parameters) ;
			}else if (webElement instanceof Map ){
				HashMap<String, String> webElementValueMap =(HashMap<String, String>) webElement ;
				String parameters[] = new String[2];
				parameters = getWebElementParameter(webElementValueMap, inputParameterMap);
				invokeWebDriverAction(eachAction.get("Action").toString(), 
						eachAction.get("WebElementType").toString(), parameters) ;
			}
		}
	}
	
	private String[] getWebElementParameter(Map<String, String> webElementValueMap, Map<String, String> inputParamValueMap){
		String parameters[] = new String[2];
		for ( String key : webElementValueMap.keySet() ) {
			parameters[0] = key ;
			
			if("?".equals(webElementValueMap.get(key).trim() ) && inputParamValueMap != null ){
				parameters[1] = inputParamValueMap.get( key ) !=null ? inputParamValueMap.get( key ) : "" ;
			}else{
				parameters[1] = webElementValueMap.get(key) ;
			}
		}
		
		return parameters ;
	}

	@SuppressWarnings("rawtypes")
	private void invokeWebDriverAction(String action, String webElementType, String[] parameter){
		try{
			Class<?> clazz = Class.forName("com.ibm.automation.demoautomation.wrappers.WebDriverWrapper");
	        Class[] paramString = new Class[parameter.length];
	        
	        for(int i=0; i < paramString.length; ){
	        	paramString[i++] = String.class;
	        }
	        
	        Method method =clazz.getDeclaredMethod(action, paramString);
	        parameter[0] = CommonAutoUtils.getXpathElement( webElementType, parameter[0]) ;
	        if(parameter.length == 1){
	        	method.invoke(webDriver, parameter[0]) ;
	        }else if ( parameter.length == 2){
	        	method.invoke(webDriver, parameter[0], parameter[1]) ;
	        }
	        
		}catch(Exception exception){
			exception.printStackTrace() ;
		}
	}
	
	public void closePage(){
		webDriver.closeDriver() ;
	}
}
