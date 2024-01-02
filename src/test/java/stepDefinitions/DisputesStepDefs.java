package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopDisputes;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class DisputesStepDefs {
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	DesktopDisputes desktopDisputes;
	
	public DisputesStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
		desktopDisputes = context.getDesktopDisputes();
	}
	
	
	@Then("^I can \"([^\"]*)\" a Dispute with the following details$")
	public void if_i_add_a_Dispte_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = StepDefCF.convertDataTableToMap(dataTable);
		dataMap = StepDefCF.processVariables(dataMap);
		action = action.toLowerCase();
		HashMap<String,String> resultsMap = desktopDisputes.disputes(dataMap, action);
		// check if returned values match expected values
		if(action.equalsIgnoreCase("check")) {
			for (String key : dataMap.keySet()) {
				Assert.assertEquals("account" + pageUtils.testMap.get("account")+"DMC values : " + key, dataMap.get(key), resultsMap.get(key));
		    }
			
		}
	}
}
