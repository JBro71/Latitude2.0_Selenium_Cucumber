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
	
	@Then("I can update the Care and Hardship record with the following details")
	public void i_can_update_the_care_and_hardship_record_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		 vulnerabilites.careAndHardship(dataMap, "update");
		// check if returned values match expected values

		
	}
	
	
	
	@Then("I can check the selected Care and Hardship record contains the following details")
	public void i_can_check_the_selected_care_and_hardship_record_contains_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		HashMap<String,String> resultsMap = vulnerabilites.careAndHardship(dataMap, "check");
		// check if returned values match expected values

			for (String key : dataMap.keySet()) {
				

				Assert.assertEquals("Care & Hardship values: " + key, dataMap.get(key), resultsMap.get(key));
		    }
	}
	
	
	
	@Then("I can search for a care record with the following details")
	public void i_can_search_for_a_care_record_with_the_following_details_to_check_that_it_exist(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		//action = action.toLowerCase();
		
		//if a customer is not supplied will default to whatever customer was last used
		if(!(dataMap.get("customer") == null)) {
			boolean foundCustomer = customers.selectCustomer(dataMap.get("customer"));	
			Assert.assertTrue("customer cannot be found" , foundCustomer);
			}
		int careCount = vulnerabilites.findcareAndHardship(dataMap);
		int expectedCount = careCount;
		try {
		expectedCount = Integer.parseInt(dataMap.get("count"));
		}catch (Exception e) {}
		Assert.assertEquals("Number of Matching Care and Hardship Records: ", expectedCount, careCount);
	}
	
	
	@Then("I can add a Care and Hardship record with the following details")
	public void i_can_a_care_and_hardship_record_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);

		dataMap = StepDefCF.processVariables(dataMap);
		//action = action.toLowerCase();
		//if a customer is not supplied will default to whatever customer was last used
		if(!(dataMap.get("customer") == null)) {
		boolean foundCustomer = customers.selectCustomer(dataMap.get("customer"));	
		Assert.assertTrue("customer cannot be found" , foundCustomer);
		}
		vulnerabilites.careAndHardship(dataMap, "add");
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
