package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopCommunications;
import pageObjects.DesktopCustomers;
import utils.Context;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;


public class CommunicationsStepDefs {
	Context context;
	PageUtils pageUtils;	
	StepDefCommonFunctions stepDefCF;
	DesktopCommunications desktopCommunications;
	DesktopCustomers desktopCustomers;

	
	public CommunicationsStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		desktopCommunications = context.getDesktopCommunications();
		desktopCustomers = context.getDesktopCustomers();
	}
	
	@Then("^I can check if a communication was sent with the following details$")
	public void I_can_check_if_a_communication_was_sent_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		int expectedCount = 1;
		if(dataMap.containsKey("count")) {
			expectedCount = Integer.parseInt(dataMap.get("count"));
			dataMap.remove("count");
		}
		// check if returned values match expected values			
		int matchCount = desktopCommunications.communicationHistory(dataMap);
		String valueString = pageUtils.hashMapToString(dataMap);
		Assert.assertEquals("Communications Issued check. "+pageUtils.testMap.get("account") +
				" requested coms ("+valueString+ ") count does not match", expectedCount, matchCount);
	}
}
