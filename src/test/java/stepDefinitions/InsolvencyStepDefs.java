package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopInsolvency;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class InsolvencyStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopInsolvency desktopInsolvency;
	
	public InsolvencyStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
		desktopInsolvency = context.getDesktopInsolvency();
	}
	
	
	@Then("I can {string} an Insolvency with the following details")
	public void i_can_a_insolvency_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		action = action.toLowerCase();
		HashMap<String,String> testMap = desktopInsolvency.insolvency(dataMap, action);
		// check if returned values match expected values
		//if(action.equalsIgnoreCase("check")) {
			//for (String key : dataMap.keySet()) {
				//Assert.assertEquals("account" + pageUtils.testMap.get("account")+"DMC values : " + key, dataMap.get(key), resultsMap.get(key));
		    //}
			
		//}
	}
	
	@Then("I can delete an Insolvency for customer {string}")
	public void i_can_delete_an_insolvency_for_customer(String customer) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("customer", customer);
		dataMap = StepDefCF.processVariables(dataMap);
		desktopInsolvency.insolvency(dataMap, "delete");
	}
}
