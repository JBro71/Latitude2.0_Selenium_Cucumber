package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopDMC;
import utils.Context;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;



public class DMCStepDefs {
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopDMC dmc;
	
	public DMCStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
		dmc = context.getDesktopDMC();
	}
	
	
	
	@Then("^I can \"([^\"]*)\" a DMC with the following details$")
	public void if_i_add_a_DMC_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		action = action.toLowerCase();
		
		HashMap<String,String> resultsMap = dmc.dmc(dataMap, action);
		// check if returned values match expected values
		if(action.equalsIgnoreCase("check")) {
			for (String key : dataMap.keySet()) {
				Assert.assertEquals("DMC values: " + key, dataMap.get(key), resultsMap.get(key));
		    }
		}
	}
	
	
	@Then("^I can \"([^\"]*)\" a Debt Management Company with the following details$")
	public void if_i_add_a_Debt_Management_Company_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		action = action.toLowerCase();
		dmc.dmcCompany(dataMap, action);

	}
}

