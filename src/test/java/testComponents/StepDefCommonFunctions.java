package testComponents;


import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import testComponents.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;

public class StepDefCommonFunctions extends BaseTest {
	//Context  context;
	WebDriver driver;
	PageUtils pageUtils;
	FileTools fileTools;
	DesktopCustomers desktopCustomers;
	//JavascriptExecutor js;
	
	
	public StepDefCommonFunctions(WebDriver driver, PageUtils pageUtils, FileTools fileTools, DesktopCustomers desktopCustomers ) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
		this.fileTools = fileTools;
		//js = (JavascriptExecutor)driver;
		this.desktopCustomers = desktopCustomers;
		
	}


	public HashMap<String, String> processVariables(HashMap<String, String> dataMap) throws Exception {
		String accountNumber = pageUtils.testMap.get("account");
		for (String key : dataMap.keySet()) {
			// check the value is not empty
			if (dataMap.get(key)== null){
				dataMap.put(key,"");
				continue;
			}
			//iterate over map and split by "," looking for "customer" variables
			String[] valueSplit = dataMap.get(key).split(",");

			switch (valueSplit[0]) {
			case "$customerName":
				try {
					desktopCustomers.getCustomers();
					dataMap.put(key, pageUtils.testMap.get("customer" + valueSplit[1]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : unable to get customer name field:" + key);
						}
				break;
			case "$date":
				try {
					dataMap.put(key,TimeDateCalcs.ReturnDate("",valueSplit[1], valueSplit[2]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : date variable invalid");
						}
				break;	
			case "$startDate":
				try {
					String dateString = "";
					if (pageUtils.testMap.containsKey("testStartDateTime")){
						dateString = pageUtils.testMap.get("testStartDateTime").substring(0, 10);
					}
					dataMap.put(key,TimeDateCalcs.ReturnDate(dateString,valueSplit[1], valueSplit[2]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" :startDate variable invalid" + e);
						}
				break;	
			case "$lastDate":
				try {
					String dateString = "";
					if (pageUtils.testMap.containsKey("lastStageDateTime")){
						dateString = pageUtils.testMap.get("lastStageDateTime").substring(0, 10);
					}
					dataMap.put(key,TimeDateCalcs.ReturnDate(dateString,valueSplit[1], valueSplit[2]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : lastDate variable invalid");
						}
				break;	
			case "$customerId":
				try {
					dataMap.put(key, getCustomerId(valueSplit[1]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : unable to get customer Id field:" + key);
						}
				break;
			case "$defaultClient":
				try {
					dataMap.put(key,prop.getProperty("defaultClient"));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : date variable invalid");
						}
				break;	
			}
		}
		
		
		return dataMap;
	}
	
	
	public HashMap<String, String> getVariableCustomers(HashMap<String, String> dataMap) throws Exception {
		//check the map for any variable dates and update the values to date strings in the specified format
		try {
			for (String key : dataMap.keySet()) {
				//iterate over map and split by "," looking for "customer" variables
				String[] valueSplit = dataMap.get(key).split(",");
				if(valueSplit[0].equalsIgnoreCase("$customerName")) {
					desktopCustomers.getCustomers();
					dataMap.put(key, pageUtils.testMap.get("customer" + Integer.parseInt(valueSplit[1])));
					}	
		    	}
		}catch (Exception e) {
			//throw new Exception("PageUtils/calculateVariableDates/"+testMap.get("account")+" : date variable invalid");
		}
		return dataMap;
	}
	
	
	public HashMap<String, String> calculateVariableDates(HashMap<String, String> dataMap) throws Exception {
		String accountNumber = pageUtils.testMap.get("account");
		//check the map for any variable dates and update the values to date strings in the specified format
		try {
			for (String key : dataMap.keySet()) {
				//iterate over map and split by , looking for date variables
				String[] valueSplit = dataMap.get(key).split(",");
				if(valueSplit[0].equalsIgnoreCase("$date")) {
					dataMap.put(key,TimeDateCalcs.ReturnDate("", valueSplit[1], valueSplit[2]));
					}	
		    	}
		}catch (Exception e) {
			throw new Exception("PageUtils/calculateVariableDates/"+accountNumber+" : date variable invalid");
		}
		return dataMap;
	}
	
	
	public String getCustomerId(String customerIdString ) throws Exception {
		String accountNumber = pageUtils.testMap.get("account");
		//get the debtorID for debtor1 or 2
		if (pageUtils.testMap.get("customerId1") == null) {
			pageUtils.updateTestMap("customerId1", fileNumbersMap.get(accountNumber)[1], false);
			pageUtils.updateTestMap("customerId2", fileNumbersMap.get(accountNumber)[2], false);
		}
		
		//String[] temp = fileNumbersMap.get("A2EE80657316496ABC");
		//String temp2 = fileNumbersMap.get("A2EE80657316496ABC")[2];
		String customerId = pageUtils.testMap.get("customerId"+customerIdString);
		if(customerId.equals("NULL")){
			throw new Exception("pageUtils/getCustomerId/"+accountNumber+" :  cannot get customerID customer "+customerIdString+" does not exist");
		}
		return customerId;
	}

	
	public HashMap<String,String> convertDataTableToMap(io.cucumber.datatable.DataTable dataTable ) throws Exception {
		String accountNumber = pageUtils.testMap.get("account");
		HashMap<String,String> dataMap = new HashMap<String,String>();
		try {	
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			dataMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
			}	
		}catch (Exception e) {
			throw new Exception("pageUtils/convertDataTableToMap/"+accountNumber+" :  cannot convert dataTable to Map");
		}
		return dataMap;
	}
	

	public String getTestDataItem(String fileNameString ) throws IOException{
		String testDataItemStr = BaseTest.staticTestDataMap.get(fileNameString).remove(0);
		String[] testDataItemArr = testDataItemStr.split(",");
		String customer2 = "";
		if (testDataItemArr.length > 2) {// may not be a customer2
			customer2 = testDataItemArr[2];
		}
		fileTools.caseStart();
		pageUtils.updateTestMap("account",testDataItemArr[0], true);
		pageUtils.updateTestMap("customer1", testDataItemArr[1],true);
		pageUtils.updateTestMap("customer2", customer2, true);
		return testDataItemArr[0];
	}
	
	public Boolean run(){
		try {
			if(pageUtils.testMap.get("run").equals("true")){
				return true;
				}
			return false;
			}catch (Exception e) {
				return true;
				}
	}

}
