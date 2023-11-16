package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopDMC;
import utils.Context;
import utils.Logging;
import testComponents.TimeDateCalcs;



public class DMCStepDefs {
	
	Context context;
	DesktopDMC dmc;
	
	public DMCStepDefs(Context context)
	{
		this.context = context;
		dmc = context.getDesktopDMC();
	}
	
	@Then("^I can \"([^\"]*)\" a DMC with the following details$")
	public void if_i_add_a_DMC_with_the_following_details(String action, io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> DMCMap = new HashMap<String, String>();
	
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			DMCMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
		}
		//if the date accepted is a variable then process it and convert into a date
		if(DMCMap.get("Date Accepted") != null) {
		DMCMap.put("Date Accepted",TimeDateCalcs.CalculateDate(DMCMap.get("Date Accepted"), "ddMMyyyy"));
		}
		HashMap<String,String> resultsMap = dmc.dmc(DMCMap, action);
		
		if(action.equalsIgnoreCase("check")) {
			DMCMap.forEach((key, value) -> System.out.println(key + ":" + value));
			resultsMap.forEach((key, value) -> System.out.println(key + ":" + value));
			
			for (String key : DMCMap.keySet()) {
				Assert.assertEquals("DMC values: " + key, DMCMap.get(key), resultsMap.get(key));
		    }
			
		}
	}
}

