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
		boolean match = desktopCommunications.communicationHistory(dataMap);
		// check if returned values match expected values
		String valueString = "";
		String separator = "";
		for (String value : dataMap.values()) {
			 valueString = valueString +separator + value; 
			separator = "/";
		}
		Assert.assertTrue("Communications Issued check. "+pageUtils.testMap.get("account") +" requested coms ("+valueString+ ") does not exist", match);	    
	}
}
