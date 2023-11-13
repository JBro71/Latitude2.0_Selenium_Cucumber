package stepDefinitions;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopDMC;
import pageObjects.DesktopVulnerabilites;
import utils.Context;
import utils.Logging;


public class DMCStepDefs {
	
	Context context;
	DesktopDMC dmc;
	
	public DMCStepDefs(Context context)
	{
		this.context = context;
		dmc = context.getDesktopDMC();
	}
	
	@Then("I can Add a DMC with the following details")
	public void if_i_add_a_DMC_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> DMCMap = new HashMap<String, String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			DMCMap.put(keyValuePair.get(0), keyValuePair.get(1));
		}
		
		dmc.addDMC(DMCMap, "add");
		}
	
}

