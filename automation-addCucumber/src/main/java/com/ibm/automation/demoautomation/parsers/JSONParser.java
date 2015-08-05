package com.ibm.automation.demoautomation.parsers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component("jsonParser")
public class JSONParser implements Parser{
	
	public Map<String, Object> parseData(File jsonData) throws JsonParseException, JsonMappingException, IOException {
		@SuppressWarnings("unchecked")
		Map<String,Object> result = new ObjectMapper().readValue(jsonData, HashMap.class);
	//	System.out.print("jsonParsed used to parse"+jsonData.toString());
		return result;
	}
	
	public String getparserName(){
		
		return "this is jsonParser";
	}
		
}
