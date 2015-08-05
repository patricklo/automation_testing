package com.ibm.automation.demoautomation.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonAutoUtils {
	public static Map<String, Object>  prepareXpathElement( Map<String, Object>  elements){
		
		Map<String, Object> elementsWithXpathTocken = new HashMap<String, Object>();		
		for ( String key : elements.keySet() ){
			if( key.contains("_link")){
				elementsWithXpathTocken.put(key, "//a[@href='" + elements.get(key) + "']" )  ;
			}else if (key.contains("_img")){
				elementsWithXpathTocken.put(key, "//img[contains(@src,'" + elements.get(key) + "')]" )  ;
			}else{
				elementsWithXpathTocken.put(key, elements.get(key));
			}
		}		
		return elementsWithXpathTocken ;		
	}
	
	public static String getXpathElement(String parameterType, String plainTextParam){
		String xPathString = plainTextParam ;
		if( "link".equalsIgnoreCase(parameterType)){
			xPathString = "//a[@href='" + plainTextParam + "']" ;
		}else if ("id".equalsIgnoreCase(parameterType)){
			xPathString = "//*[@id='" + plainTextParam + "']" ;
		}else if ("name".equalsIgnoreCase(parameterType)){
			xPathString = "//*[@name=" + plainTextParam + "]" ;
		}else if ("value".equalsIgnoreCase(parameterType)){
			xPathString = "//*[@value='" + plainTextParam + "']" ;
		}else if ("img".equalsIgnoreCase(parameterType) ){
			xPathString = "//img[contains(@src,'" + plainTextParam + "')]"  ;
		}
		return xPathString ;
	}
	
	public static void printList(List<String> inputList){
		StringBuffer printTextBuffer = new StringBuffer() ;
		printTextBuffer.append("[ ") ;
		for ( String eachField :inputList ){
			printTextBuffer.append(eachField).append(",") ;
		}		
		printTextBuffer.append("]") ;
		System.out.println(printTextBuffer.toString());
	}

	
	
	  public static void setClipboardData(String string) {
		    StringSelection stringSelection = new StringSelection(string);
		    Toolkit.getDefaultToolkit().getSystemClipboard()
		        .setContents(stringSelection, null);
		  }

	
}
