package stepDefinitions;

import java.util.HashMap;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopWorkFlowAlerts;
import utils.Context;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;



public class WorkFlowAlertsStepDefs {
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopWorkFlowAlerts workFlowAlerts;
	
	public WorkFlowAlertsStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		workFlowAlerts = context.getWorkFlowAlerts();
	}
	
	

	@Then("I can check for workflow Alerts matching the following details")
	public void i_can_check_for_workflow_alerts_matching_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		
		int expectedCount = 1;
		if(dataMap.containsKey("count")) {
			expectedCount = Integer.parseInt(dataMap.get("count"));
			dataMap.remove("count");
		}
		
		HashMap<String,Integer> resultsMap = workFlowAlerts.findWorkFlowAlerts(dataMap);
		int matchCount = resultsMap.get("numberOfMatches");
		
		String valueString = pageUtils.hashMapToString(dataMap);
		// check if returned values match expected values		
		Assert.assertEquals("WorkFlowAlerts Check. "+pageUtils.testMap.get("account") +
				" entry ("+valueString+ ") count does not match", expectedCount, matchCount);
	}

}

