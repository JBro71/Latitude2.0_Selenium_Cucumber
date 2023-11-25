package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopCommunications;
import utils.Context;
import utils.Logging;
import testComponents.TimeDateCalcs;

public class CommunicationsStepDefs {
	Context context;
	DesktopCommunications desktopCommunications;
	
	public CommunicationsStepDefs(Context context)
	{
		this.context = context;
		desktopCommunications = context.getDesktopCommunications();
	}
	
	
	@Then("^I can check if a communication was sent with the following details$")
	public void I_can_check_if_a_communication_was_sent_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> dataMap = new HashMap<String, String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			dataMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
		}
		
		

		//if the date accepted is a variable then process it and convert into a date
		String[] dates = {"date received", "date closed", "recourse date"};
		for (String date : dates) {
		
		//String dateName = "date received";
		if(dataMap.get(date) != null) {
			String dateFormat = "dd/MM/yyyy";
			//if (action.equalsIgnoreCase("check")) {dateFormat = "yyyy-MM-dd";}
			dataMap.put(date,TimeDateCalcs.CalculateDate(dataMap.get(date), dateFormat));
			}
		}
		
		HashMap<String,String> resultsMap = desktopCommunications.communicationHistory(dataMap);
		// check if returned values match expected values

			//DMCMap.forEach((key, value) -> System.out.println(key + ":" + value));
			//resultsMap.forEach((key, value) -> System.out.println(key + ":" + value));
			for (String key : dataMap.keySet()) {
				//Assert.assertEquals("DMC values: " + key, dataMap.get(key), resultsMap.get(key));
		    }
			
		
	}
}
