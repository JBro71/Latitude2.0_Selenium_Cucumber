package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopCommunications;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.TimeDateCalcs;

public class BatchAPIStepDefs {
	Context context;
	DesktopCommunications desktopCommunications;
	PageUtils pageUtils;
	DesktopCustomers desktopCustomers;

	
	public BatchAPIStepDefs(Context context)
	{
		this.context = context;
		desktopCommunications = context.getDesktopCommunications();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
	}
	
	@Then("I can check if a batchAPI entry has been created that matches these details")
	public void i_can_check_if_a_batch_api_entry_has_been_created_that_matches_these_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and 
		HashMap<String,String> dataMap = pageUtils.convertDataTableToMap(dataTable);
		//convert any date variables to actual dates;
		dataMap = pageUtils.calculateVariableDates(dataMap);
		//convert any customer variables to customer names
		dataMap = pageUtils.getVariableCustomers(dataMap);
		/*
		try {
			for (String key : dataMap.keySet()) {
				//iterate over map and split by "," looking for "customer" variables
				String[] valueSplit = dataMap.get(key).split(",");
				if(valueSplit[0].equalsIgnoreCase("$customer")) {
					desktopCustomers.getCustomers();
					dataMap.put(key, pageUtils.testMap.get("customer" + Integer.parseInt(valueSplit[1])));
					}	
		    	}
		}catch (Exception e) {
			//throw new Exception("PageUtils/calculateVariableDates/"+testMap.get("account")+" : date variable invalid");
		}
		*/
		
		String dummy = "";
	}

	
	
	
	
	
	@Then("I can check if the batchAPI entry contains the following JSON values")
	public void i_can_check_if_the_batch_api_entry_contains_the_following_json_values(io.cucumber.datatable.DataTable dataTable) {

	}
	
	
	/*
	
	@Then("^I can check if a communication was sent with the following details$")
	public void I_can_check_if_a_communication_was_sent_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> dataMap = new HashMap<String, String>();
		//HashMap<String,String> customerMap = new HashMap<String, String>();
		//customerMap.put("1", null);
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			dataMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
		}
		
		//if the date entered is a variable then process it and convert into a date
		String[] dates = {"date requested", "date sent"};
		for (String date : dates) {
		if(dataMap.get(date) != null) {
			String dateFormat = "dd/MM/yyyy";
			dataMap.put(date,TimeDateCalcs.CalculateDate(dataMap.get(date), dateFormat));
			}
		}
		
		//if the debtor name is supplied as is a number not a name lookup the name and replace
		String[] customers = {"recipient", "subject"};
		for (String customer : customers) {
			try {
			int debtorNumber = Integer.parseInt(dataMap.get(customer));
			desktopCustomers.getCustomers();
			dataMap.put(customer, pageUtils.testMap.get("customer" + debtorNumber));
			}catch(Exception e) {}
		}
		
		boolean match = desktopCommunications.communicationHistory(dataMap);
		// check if returned values match expected values
		Assert.assertTrue("Communications Issued check: ", match);
		    
			
		
	}
	*/
}