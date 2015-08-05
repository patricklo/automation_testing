package com.ibm.automation.demoautomation.parsers;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Component("xmlParser")
public class XMLParser implements Parser {

	public Map<String, Object> parseData(File xmlData) throws Exception {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias("map", java.util.Map.class);

		@SuppressWarnings("unchecked")
		//Map<String,Object> result = (Map<String,Object>) xStream.fromXML(xmlData);
		Map<String,Object> result = (Map<String,Object>) xStream.fromXML(xmlData.toString());
		return result;
	}
	
	public String getparserName(){
		
		return "this is xmlParser";
	}
		

}
