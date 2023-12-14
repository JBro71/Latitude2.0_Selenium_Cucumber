package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopAddress;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class AddressStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopAddress desktopAddress;
	//DesktopCustomers desktopCustomers;
	HashMap<String,Integer> resultsMap;

	
	public AddressStepDefs(Context context)
	{
		this.context = context;
		desktopAddress = context.getDesktopAddress();
		//desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
	}

	   
		
	@Then("I can update the address above with following {string} values")
	public void i_can_update_the_address_above_with_following_details(String addressType, io.cucumber.datatable.DataTable dataTable) throws Exception {
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
	dataMap.put("address type", addressType);
	dataMap = StepDefCF.processVariables(dataMap);
	// get the phone number identified in phone number search step error if not present
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	desktopAddress.address("update",dataMap,row);
	}
		
		
	@Then("I can update address {string} belonging to {string} with the following {string} details")
	public void i_can_update_address_belonging_to_with_the_following_details(String addressLine1, String customer, String addressType, io.cucumber.datatable.DataTable dataTable) throws Exception {
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMapFind = new HashMap<String,String>();
	
	dataMapFind.put("address line 1", addressLine1);
	dataMapFind.put("party", customer);
	dataMapFind = StepDefCF.processVariables(dataMapFind);
	resultsMap= desktopAddress.findAddress(dataMapFind);	
	
	HashMap<String,String> dataMapUpdate = StepDefCF.convertDataTableToMap(dataTable);
	dataMapUpdate.put("address type", addressType);
	dataMapUpdate = StepDefCF.processVariables(dataMapUpdate);

	
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	desktopAddress.address("update",dataMapUpdate,row);
	}
	

	
	@Then("I can search for an address with following details")
	public void i_can_search_for_a_address_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		
		resultsMap= desktopAddress.findAddress(dataMap);	
		
		int actualCount = resultsMap.get("numberOfMatches");
		int expectedCount = actualCount;
		try {
		expectedCount = Integer.parseInt(dataMap.get("count"));
		}catch (Exception e) {}
		Assert.assertEquals("Number of Matching Phone Numbers : ", expectedCount, actualCount);
	}
	
	
	
	@Then("I can add a {string} address for {string}")
	public void i_can_add_an_phone_number_for(String addressType, String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap.put("address owner", customer);
		dataMap.put("address type", addressType);
		dataMap = StepDefCF.processVariables(dataMap);
		desktopAddress.address("add",dataMap,0);
	
		}
		
}
