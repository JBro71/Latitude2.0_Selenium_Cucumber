package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopDisputes;
import utils.Context;
import utils.Logging;
import testComponents.TimeDateCalcs;

public class DisputesStepDefs {
	Context context;
	DesktopDisputes desktopDisputes;
	
	public DisputesStepDefs(Context context)
	{
		this.context = context;
		desktopDisputes = context.getDesktopDisputes();
	}
	
	
	@Then("^I can \"([^\"]*)\" a Dispute with the following details$")
	public void if_i_add_a_Dispte_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
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
		
		HashMap<String,String> resultsMap = desktopDisputes.disputes(dataMap, action);
		// check if returned values match expected values
		if(action.equalsIgnoreCase("check")) {
			//DMCMap.forEach((key, value) -> System.out.println(key + ":" + value));
			//resultsMap.forEach((key, value) -> System.out.println(key + ":" + value));
			for (String key : dataMap.keySet()) {
				Assert.assertEquals("DMC values: " + key, dataMap.get(key), resultsMap.get(key));
		    }
			
		}
	}
}
