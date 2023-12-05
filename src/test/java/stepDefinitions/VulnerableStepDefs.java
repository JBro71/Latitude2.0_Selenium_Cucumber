package stepDefinitions;


import java.util.HashMap;

import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopVulnerabilites;
import pageObjects.DesktopCustomers;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import utils.Context;



public class VulnerableStepDefs {//<Public> extends BaseTest {
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopVulnerabilites vulnerabilites;
	DesktopCustomers customers;
	
	public VulnerableStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
		vulnerabilites = context.getDesktopVulnerabilites();
		customers = context.getDesktopCustomers();
	}
	
	
	@Then("^I can \"([^\"]*)\" a Care and Hardship record with the following details$")
	public void i_can_a_care_and_hardship_record_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);

		dataMap = StepDefCF.processVariables(dataMap);
		action = action.toLowerCase();
		//if a customer is not supplied will default to whatever customer was last used
		if(!(dataMap.get("customer") == null)) {
		boolean foundCustomer = customers.selectCustomer(dataMap.get("customer"));	
		Assert.assertTrue("customer cannot be found" , foundCustomer);
		}
		HashMap<String,String> resultsMap = vulnerabilites.careAndHardship(dataMap, action);
		// check if returned values match expected values
		if(action.equalsIgnoreCase("check")) {
			for (String key : dataMap.keySet()) {
				Assert.assertEquals("Care & Hardship values: " + key, dataMap.get(key), resultsMap.get(key));
		    }
		}
		
	}
	

	@When("^I add a new C&H record with care type of \"([^\"]*)\" and hardship of \"([^\"]*)\"$")
	public void i_add_a_new_c_h_record_with_care_type_of_physical_difficulty_and_hardship_of_impact_pay(String careType, String hardshipType) throws Exception {
		vulnerabilites.OpenCareAndHardshipPanel();
		vulnerabilites.NewCareAndHardship(careType, hardshipType);
	}

	@When("^the default hold days of \"([^\"]*)\" are set to \"([^\"]*)\"$")
	public void the_default_hold_days_of_are_set_to_default(String defaultHoldDays, String SetHoldDays) throws Exception {
		String presetCareAndHardshipHoldDays = vulnerabilites.CareHoldDays(SetHoldDays);
		Assert.assertEquals("Hold Days"+ pageUtils.testMap.get("account"), defaultHoldDays, presetCareAndHardshipHoldDays);  
	
	}

	@Then("when I submit the C&H record it is stored successfully")
	public void when_i_submit_the_c_h_record_it_is_stored_successfully() throws InterruptedException  {
		boolean submitCareRecord = vulnerabilites.SubmitCareAndHardshipRecord();
		Assert.assertTrue("care record submitted successfully", submitCareRecord);
	}
	
	
	
	
}
