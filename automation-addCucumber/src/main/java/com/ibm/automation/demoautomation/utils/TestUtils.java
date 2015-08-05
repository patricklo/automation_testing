package com.ibm.automation.demoautomation.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class TestUtils {
	
	private Xls_Reader excelTestData = null;
	
	@PostConstruct
	public void initialize() {
		
		File file = FileUtils.getFile("src","test", "resources", "TestData.xlsx");
		if(excelTestData == null){
			// load the TestData sheet
			
			excelTestData = new Xls_Reader(file.getAbsolutePath());	
		}	
		
	}
	
	public Xls_Reader getExcelData() {
		return excelTestData; 
	}
		
	/**
	 * @param testCase
	 * @return
	 */
	
	public boolean isSkip(String testCase){		
		for(int rowNum = 2; rowNum <= excelTestData.getRowCount("Test Cases"); rowNum++){
			if(testCase.equals(excelTestData.getCellData("Test Cases", "TCID", rowNum))){
				if(excelTestData.getCellData("Test Cases", "Runmode", rowNum).equals("Y")){
					return false;

				}else{
					return true;
				}
			}
		}
		return false;
	}
	
	//get the data from xls file
	public Object[][] getData(String testName){

		File file = FileUtils.getFile("src","test", "resources", "TestData.xlsx");
		if(excelTestData == null){
			// load the TestData sheet
			excelTestData = new Xls_Reader(file.getAbsolutePath());	
		}
		
		int rows=excelTestData.getRowCount(testName)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;
		}
	    rows = excelTestData.getRowCount(testName); 
		int cols = excelTestData.getColumnCount(testName);
		System.out.println("Test Name -- "+testName);
		System.out.println("total rows -- "+ rows);
		System.out.println("total cols -- "+cols);
		Object data[][] = new Object[rows-1][1];
			
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
			Map<String, String> inputParams = new HashMap<String, String>() ;
			for(int colNum=0 ; colNum< cols; colNum++){
				String paramKey = excelTestData.getCellData(testName, colNum, 1) ;
				String paramValue	=excelTestData.getCellData(testName, colNum, rowNum);
				inputParams.put(paramKey, paramValue) ;
			}
			data[rowNum-2][0] = inputParams ;
		}
		return data;
	}
}
