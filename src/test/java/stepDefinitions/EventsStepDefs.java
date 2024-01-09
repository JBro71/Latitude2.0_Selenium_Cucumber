package stepDefinitions;

import java.util.HashMap;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopEvents;
import utils.Context;
import testComponents.PageUtils;
import testComponents.StepDefCommonFunctions;



public class EventsStepDefs {
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions stepDefCF;
	DesktopEvents events;
	
	public EventsStepDefs(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		stepDefCF = context.getStepDefCommonFunctions();
		events = context.getEvents();
	}
	
	

	@Then("I can check for events matching the following details")
	public void i_can_check_for_events_matching_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		
		int expectedCount = 1;
		if(dataMap.containsKey("count")) {
			expectedCount = Integer.parseInt(dataMap.get("count"));
			dataMap.remove("count");
		}
		
		HashMap<String,Integer> resultsMap = events.findEvents(dataMap);
		int matchCount = resultsMap.get("numberOfMatches");
		
		String valueString = pageUtils.hashMapToString(dataMap);
		// check if returned values match expected values		
		Assert.assertEquals("Event Check. "+pageUtils.testMap.get("account") +
				" entry ("+valueString+ ") count does not match", expectedCount, matchCount);
	}

}

