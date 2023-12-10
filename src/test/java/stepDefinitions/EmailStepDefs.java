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
	StepDefCommonFunctions StepDefCF;
	DesktopEmail desktopEmail;
	DesktopCustomers desktopCustomers;

	
	public EmailStepDefs(Context context)
	{
		this.context = context;
		desktopEmail = context.getDesktopEmail();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
	}
	
	
	@Then("I can search for an Email adresss with following details")
	public void i_can_search_for_an_email_adresss_with_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
	
		int actualCount = desktopEmail.findemailAddress(dataMap);
		int expectedCount = actualCount;
		try {
		expectedCount = Integer.parseInt(dataMap.get("count"));
		}catch (Exception e) {}
		Assert.assertEquals("Number of Matching Email Address's : ", expectedCount, actualCount);
		
	}
	

	@Then("^I can add an email address for \"([^\"]*)\"$")
	public void i_can_add_and_email_address_for(String customer, io.cucumber.datatable.DataTable dataTable) throws Exception {
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		//Add the customer to the dataMap
		dataMap.put("customer on account", customer);
		dataMap = StepDefCF.processVariables(dataMap);
		desktopEmail.email(dataMap);
	
		}
		
}
