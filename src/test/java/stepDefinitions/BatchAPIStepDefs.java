package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.google.gson.Gson;

import io.cucumber.java.en.*;
import pageObjects.DesktopCommunications;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;
import pageObjects.DesktopBatchApi;

public class BatchAPIStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopCustomers desktopCustomers;
	DesktopBatchApi desktopBatchApi;
	HashMap<String,String> payloadMap;


	
	public BatchAPIStepDefs(Context context)
	{
		this.context = context;
		desktopBatchApi = context.getDesktopBatchApi();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
	}
	
	@Then("I can check if a batchAPI entry has been created that matches these details")
	public void i_can_check_if_a_batch_api_entry_has_been_created_that_matches_these_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and 
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		String batchApiPayload = desktopBatchApi.getBatchApi(dataMap);
		//Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi: "+ batchApiPayload);
		Assert.assertNotEquals("getBatchAPI: no payload returned for account:" + pageUtils.testMap.get("account") , batchApiPayload, "");
		Gson gson = new Gson();
		payloadMap = gson.fromJson(batchApiPayload, HashMap.class);
		Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi: "+ payloadMap);
	}

	
	
	@Then("I can check if the batchAPI entry contains the following JSON values")
	public void i_can_check_if_the_batch_api_entry_contains_the_following_json_values(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and 
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		//convert any variables to actual values
		dataMap = StepDefCF.processVariables(dataMap);
		//conver all the keys in the map are lower case	
		HashMap<String,String> lowercasePayloadMap = new HashMap<String, String>();
		payloadMap.forEach((key, value) -> {lowercasePayloadMap.put(key.toLowerCase(), value);});
		dataMap.forEach((key, value) -> {
			String payloadValue = lowercasePayloadMap.get(key.toLowerCase());
			Assert.assertEquals("checkBatchAPI/" + pageUtils.testMap.get("account")+" field: " + key, value, payloadValue);
		});
		
		
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
