package stepDefinitions;

import java.util.HashMap;
import org.junit.Assert;
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
		//convert dataTable to Hashmap and convert variables to real values
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
		//convert dataTable to Hashmap 
		//requires i_can_check_if_a_batch_api_entry_has_been_created_that_matches_these_details to be run first
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		//convert any variables to actual values
		dataMap = StepDefCF.processVariables(dataMap);
		//convert all the keys in the map are lower case	
		HashMap<String,String> lowercasePayloadMap = new HashMap<String, String>();
		payloadMap.forEach((key, value) -> {lowercasePayloadMap.put(key.toLowerCase(), value);});
		dataMap.forEach((key, value) -> {
			String payloadValue = lowercasePayloadMap.get(key.toLowerCase());
			Assert.assertEquals("checkBatchAPI/" + pageUtils.testMap.get("account")+" field: " + key, value, payloadValue);
		});
	}
	
}
