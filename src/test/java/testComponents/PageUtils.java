package testComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

public class PageUtils extends BaseTest {
	WebDriver driver;
	public HashMap<String, String> testMap;
	JavascriptExecutor js;
	
	
	public PageUtils( WebDriver driver, HashMap<String, String> testMap ) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.testMap = testMap;
		js = (JavascriptExecutor)driver;

	}
	
/*
	public HashMap<String, String> processVariables(HashMap<String, String> dataMap) throws Exception {
		
		for (String key : dataMap.keySet()) {
			//iterate over map and split by "," looking for "customer" variables
			String[] valueSplit = dataMap.get(key).split(",");
			
			switch (valueSplit[0]) {
			case "$customerName":
				try {
					desktopCustomers.getCustomers();
					dataMap.put(key, testMap.get("customer" + valueSplit[1]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+testMap.get("account")+" : unable to get customer name field:" + key);
						}
				break;
			case "$date":
				try {
					dataMap.put(key,TimeDateCalcs.ReturnDate(valueSplit[1], valueSplit[2]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+testMap.get("account")+" : date variable invalid");
						}
				break;	
			case "$customerId":
				try {
					dataMap.put(key, getCustomerId(valueSplit[1]));
					}catch (Exception e) {
						throw new Exception("PageUtils/processVariables/"+testMap.get("account")+" : unable to get customer Id field:" + key);
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
					dataMap.put(key, testMap.get("customer" + Integer.parseInt(valueSplit[1])));
					}	
		    	}
		}catch (Exception e) {
			//throw new Exception("PageUtils/calculateVariableDates/"+testMap.get("account")+" : date variable invalid");
		}
		return dataMap;
	}
	
	
	*/
	
	
	
	/*
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
			throw new Exception("PageUtils/calculateVariableDates/"+testMap.get("account")+" : date variable invalid");
		}
		return dataMap;
	}
	
	
	public String getCustomerId(String customerIdString ) throws Exception {
		//get the debtorID for debtor1 or 2
		if (testMap.get("customerId1") == null) {
		testMap.put("customerId1", fileNumbersMap.get(testMap.get("account"))[1]);
		testMap.put("customerId1", fileNumbersMap.get(testMap.get("account"))[2]);
		}
		String customerId = testMap.get("customerId"+customerIdString);
		if(customerId.equals("NULL")){
			throw new Exception("pageUtils/getCustomerId/"+testMap.get("account")+" :  cannot get customerID customer "+customerIdString+" does not exist");
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
			throw new Exception("pageUtils/convertDataTableToMap/"+testMap.get("account")+" :  cannot convert dataTable to Map");
		}
		return dataMap;
	}
	
	*/
	
	
	public void updateTestMap(String key, String value) {
		testMap.put(key,value);
		staticTestMap.put(key,value);
	}
	
	public void DefaultImplictWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
	}
	
	public void ImplictWait(int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}

	public void OpenAnchorPanel() {
	 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']"));
	 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
		 toggle.click();
	 }
	}
	
	public void CloseAnchorPanel() {
		 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']"));
		 if(!toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
			 toggle.click();
		 }
		}
	
	
	public void CloseAccount() {
		//close existing account if one open
	try 
	{
		ImplictWait(0);		
		ReturnHome();
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		//context.DefaultImplictWait();
	}
	catch(Exception e){
		Logging.logToConsole("DEBUG", "No Existing open account to close");
		}
	DefaultImplictWait();
	}
	
	
	public void ReturnHome() throws InterruptedException {
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public void Scroll(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0," + move + ")");						
	}
	
	
	// use JS to get all the atributes of a web element
	//Object attrs = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",inputWebElement);
	//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + "Attributes" +" "+ attrs);	
}
