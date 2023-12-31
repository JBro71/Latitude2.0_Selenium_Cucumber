package stepDefinitions;

import java.util.HashMap;
import org.junit.Assert;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import io.cucumber.java.en.*;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import pageObjects.DesktopBatchApi;

public class BatchAPIStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopCustomers desktopCustomers;
	DesktopBatchApi desktopBatchApi;
	HashMap<String,String> payloadMap = new HashMap<String,String>();


	
	public BatchAPIStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		desktopBatchApi = context.getDesktopBatchApi();
		desktopCustomers = context.getDesktopCustomers();

	}
	
	@Then("I can check if a batchAPI entry has been created that matches these details")
	public void i_can_check_if_a_batch_api_entry_has_been_created_that_matches_these_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		
		int expectedCount = 1;
		if(dataMap.containsKey("count")) {
			expectedCount = Integer.parseInt(dataMap.get("count"));
			dataMap.remove("count");
		}
		
		HashMap<String,String> resultsMap = desktopBatchApi.getBatchApi(dataMap);
		int matchCount = Integer.parseInt(resultsMap.get("matchCount"));
		
		String valueString = pageUtils.hashMapToString(dataMap);
		// check if returned values match expected values		
		Assert.assertEquals("BatchAPI entries Check. "+pageUtils.testMap.get("account") +
				" entry ("+valueString+ ") count does not match", expectedCount, matchCount);

		if(matchCount > 0) {// store the JSON in payloadMap for later use
			String batchApiPayload = resultsMap.get("matchedJsonString1");
			//Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi: "+ batchApiPayload);
			Gson gson = new Gson();
			HashMap<String,String> tempPayloadMap = gson.fromJson(batchApiPayload, new TypeToken<HashMap<String, String>>(){
				private static final long serialVersionUID = 1L;}.getType());
			tempPayloadMap.forEach((key, value) -> {payloadMap.put(key.toLowerCase(), value);});
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi: "+ payloadMap);
		}
	}

	
	
	@Then("I can check if the batchAPI entry contains the following JSON values")
	public void i_can_check_if_the_batch_api_entry_contains_the_following_json_values(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap 
		//requires i_can_check_if_a_batch_api_entry_has_been_created_that_matches_these_details to be run first
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		//convert any variables to actual values
		dataMap = stepDefCF.processVariables(dataMap);
		//convert all the keys in the map are lower case
		/*
		HashMap<String,String> lowercasePayloadMap = new HashMap<String, String>();
		for (String key : payloadMap.keySet()) {
			Logging.logToConsole("DEBUG","DesktopBatchApi/key: "+ key);
			Logging.logToConsole("DEBUG","DesktopBatchApi/value: "+ payloadMap.get(key));
			
		}
		payloadMap.forEach((key, value) -> {lowercasePayloadMap.put(key.toLowerCase(), value);});
		*/
		dataMap.forEach((key, value) -> {
			String payloadValue = payloadMap.get(key);
			Assert.assertEquals("checkBatchAPI/" + pageUtils.testMap.get("account")+" field: " + key, value, payloadValue);
		});
	}
	
}
