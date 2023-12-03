package testComponents;


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
	DesktopCustomers desktopCustomers;
	String accountNumber;
	//JavascriptExecutor js;
	
	
	public StepDefCommonFunctions(WebDriver driver, PageUtils pageUtils,DesktopCustomers desktopCustomers ) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
		//js = (JavascriptExecutor)driver;
		this.desktopCustomers = desktopCustomers;
		accountNumber = pageUtils.testMap.get("account");
	}


	public HashMap<String, String> processVariables(HashMap<String, String> dataMap) throws Exception {
		
		for (String key : dataMap.keySet()) {
			// check the value is not empty
			if (dataMap.get(key)== null){
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
					dataMap.put(key,TimeDateCalcs.ReturnDate(valueSplit[1], valueSplit[2]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : date variable invalid");
						}
				break;	
			case "$customerId":
				try {
					dataMap.put(key, getCustomerId(valueSplit[1]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+accountNumber+" : unable to get customer Id field:" + key);
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
		//check the map for any variable dates and update the values to date strings in the specified format
		try {
			for (String key : dataMap.keySet()) {
				//iterate over map and split by , looking for date variables
				String[] valueSplit = dataMap.get(key).split(",");
				if(valueSplit[0].equalsIgnoreCase("$date")) {
					dataMap.put(key,TimeDateCalcs.ReturnDate(valueSplit[1], valueSplit[2]));
					}	
		    	}
		}catch (Exception e) {
			throw new Exception("PageUtils/calculateVariableDates/"+accountNumber+" : date variable invalid");
		}
		return dataMap;
	}
	
	
	public String getCustomerId(String customerIdString ) throws Exception {
		//get the debtorID for debtor1 or 2
		if (pageUtils.testMap.get("customerId1") == null) {
			pageUtils.testMap.put("customerId1", fileNumbersMap.get(accountNumber)[1]);
			pageUtils.testMap.put("customerId2", fileNumbersMap.get(accountNumber)[2]);
		}
		
		String[] temp = fileNumbersMap.get("A2EE80657316496ABC");
		String temp2 = fileNumbersMap.get("A2EE80657316496ABC")[2];
		String customerId = pageUtils.testMap.get("customerId"+customerIdString);
		if(customerId.equals("NULL")){
			throw new Exception("pageUtils/getCustomerId/"+accountNumber+" :  cannot get customerID customer "+customerIdString+" does not exist");
		}
		return customerId;
	}

	
	public HashMap<String,String> convertDataTableToMap(io.cucumber.datatable.DataTable dataTable ) throws Exception {
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
	
	
	
	


}
