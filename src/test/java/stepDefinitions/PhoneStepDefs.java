package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopPhone;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class PhoneStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopPhone desktopPhone;
	//DesktopCustomers desktopCustomers;
	HashMap<String,Integer> resultsMap;

	
	public PhoneStepDefs(Context context)
	{
		this.context = context;
		desktopPhone = context.getDesktopPhone();
		//desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
	}
	
	/*
	@Then("I can update the email address above with following details")
	public void i_can_update_the_email_address_above_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
	dataMap = StepDefCF.processVariables(dataMap);
	// get the email address identified in email search step error if not present
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	//desktopEmail.email("update",dataMap,row);
	}

	
	@Then("I can update email address {string} belonging to {string}")
	public void i_can_update_email_address_belonging_to(String emailAddress, String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMapFind = new HashMap<String,String>();
	dataMapFind.put("email address", emailAddress);
	dataMapFind.put("contact", customer);
	dataMapFind = StepDefCF.processVariables(dataMapFind);
	//resultsMap= desktopEmail.findemailAddress(dataMapFind);	
	
	HashMap<String,String> dataMapUpdate = StepDefCF.convertDataTableToMap(dataTable);
	dataMapUpdate = StepDefCF.processVariables(dataMapUpdate);
	
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	//desktopEmail.email("update",dataMapUpdate,row);
	}
	
	
	
	*/
	@Then("I can search for a phone number with following details")
	public void i_can_search_for_a_phone_number_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		
		resultsMap= desktopPhone.findPhoneNumber(dataMap);	
		
		int actualCount = resultsMap.get("numberOfMatches");
		int expectedCount = actualCount;
		try {
		expectedCount = Integer.parseInt(dataMap.get("count"));
		}catch (Exception e) {}
		Assert.assertEquals("Number of Matching Phone Numbers : ", expectedCount, actualCount);
	}
	
	
	@Then("^I can add an phone number for \"([^\"]*)\"$")
	public void i_can_add_an_phone_number_for(String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		//Add the customer to the dataMap
		dataMap.put("customer on account", customer);
		dataMap = StepDefCF.processVariables(dataMap);
		desktopPhone.phone("add",dataMap,0);
	
		}
		
}
