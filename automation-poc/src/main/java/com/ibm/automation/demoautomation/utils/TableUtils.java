package com.ibm.automation.demoautomation.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TableUtils {

	private String headingXpath = null;
	private String dataXpath = null;
	private WebDriver driver = null;
	private List<String> columnNames = null;
	int rowCount = 0;



	public TableUtils(WebDriver driver, String dataXpath, String headingXpath ) {
		this.driver = driver;
		this.columnNames = new ArrayList<String>();
		this.dataXpath =dataXpath;
		this.headingXpath = headingXpath;
		populateColumnNames();
	}

	private void populateColumnNames() {
		try {
			List<WebElement> tabledata = driver.findElements(By.xpath(this.headingXpath));			
			this.rowCount = driver.findElements(By.xpath(this.dataXpath)).size();
			//System.out.println("rows :"+rows);
			for (WebElement entry : tabledata) {
				String text = entry.getText();
				int visible = entry.getLocation().getX();
				if ((text.trim().length() > 0) && (visible > 1)) {
					this.columnNames.add(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("List Columns :+columns");
	}

	public List<String> getColumnNames() {
		return this.columnNames;
	}
	
	public int getRowCount() {
		return this.rowCount;
	}

	public List<String> getColumnData(String columnName, String dataXpath) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			int columnIndex = 1 + this.columnNames.indexOf(columnName);
			List<WebElement> tabledata = driver.findElements(By.xpath(dataXpath
					+ "/td[" + columnIndex + "]"));
			for (WebElement entry : tabledata) {
				String text = entry.getText();
				int visible = entry.getLocation().getX();
				if ((text.trim().length() > 0) && (visible > 1)) {
					if(text != null && !getColumnNames().contains(text)) {
						datas.add(text);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
		
	}
}
