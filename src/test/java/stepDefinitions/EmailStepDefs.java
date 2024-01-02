package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopEmail;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class EmailStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopEmail desktopEmail;
	DesktopCustomers desktopCustomers;
	HashMap<String,Integer> resultsMap;

	
	public EmailStepDefs(Context context)
	{
		this.context = context;
		desktopEmail = context.getDesktopEmail();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
	}
	
	
	@Then("I can update the email address above with following details")
	public void i_can_update_the_email_address_above_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
	if(!stepDefCF.run()) {return;}// if run is false do not run
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
	dataMap = stepDefCF.processVariables(dataMap);
	// get the email address identified in email search step error if not present
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	desktopEmail.email("update",dataMap,row);
	}

	
	@Then("I can update email address {string} belonging to {string}")
	public void i_can_update_email_address_belonging_to(String emailAddress, String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
	if(!stepDefCF.run()) {return;}// if run is false do not run
	//convert dataTable to Hashmap and convert variables to real values
	HashMap<String,String> dataMapFind = new HashMap<String,String>();
	dataMapFind.put("email address", emailAddress);
	dataMapFind.put("contact", customer);
	dataMapFind = stepDefCF.processVariables(dataMapFind);
	resultsMap= desktopEmail.findemailAddress(dataMapFind);	
	
	HashMap<String,String> dataMapUpdate = stepDefCF.convertDataTableToMap(dataTable);
	dataMapUpdate = stepDefCF.processVariables(dataMapUpdate);
	
	int row = 0;
	try {
		row = resultsMap.get("matchRow1");
		}
		catch (Exception e) {throw new Exception(
			"updateEmail "+ pageUtils.testMap.get("account")+" :  no email address identified to update");
			}	
	desktopEmail.email("update",dataMapUpdate,row);
	}
	
	
	
	
	@Then("I can search for an email address with following details")
	public void i_can_search_for_an_email_adresss_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		
		resultsMap= desktopEmail.findemailAddress(dataMap);	
		
		int actualCount = resultsMap.get("numberOfMatches");
		int expectedCount = actualCount;
		try {
		expectedCount = Integer.parseInt(dataMap.get("count"));
		}catch (Exception e) {}
		Assert.assertEquals("Number of Matching Email Address's : ", expectedCount, actualCount);
	}
	

	@Then("^I can add an email address for \"([^\"]*)\"$")
	public void i_can_add_and_email_address_for(String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		//Add the customer to the dataMap
		dataMap.put("customer on account", customer);
		dataMap = stepDefCF.processVariables(dataMap);
		desktopEmail.email("add",dataMap,0);
	
		}
		
}
