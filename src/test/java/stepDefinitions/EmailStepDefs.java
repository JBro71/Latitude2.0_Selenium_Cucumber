package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopEmail;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.TimeDateCalcs;

public class EmailStepDefs {
	Context context;
	DesktopEmail desktopEmail;
	DesktopCustomers desktopCustomers;
	PageUtils pageUtils;
	
	public EmailStepDefs(Context context)
	{
		this.context = context;
		desktopEmail = context.getDesktopEmail();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
	}
	
	

	@Then("^I can add and email address for \"([^\"]*)\"$")
	public void i_can_add_and_email_address_for(String customer, io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> dataMap = new HashMap<String, String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			dataMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
		}
		//Add the customer to the dataMap
		dataMap.put("customer on account", customer);
		
		//check if there are variables that need converting to actual customer names
		String[] fields = {"customer on account", "obtained from"};
		for (String field : fields) {
			if(dataMap.get(field).equals("customer1")||dataMap.get(field).equals("customer2")) 
				{
				desktopCustomers.getCustomers(); 
				dataMap.put(field,pageUtils.testMap.get(customer));
				} 
			}
		desktopEmail.email(dataMap);
		}
}
