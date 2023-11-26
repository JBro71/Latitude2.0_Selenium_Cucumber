package stepDefinitions;

import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopCommunications;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.TimeDateCalcs;

public class CommunicationsStepDefs {
	Context context;
	DesktopCommunications desktopCommunications;
	PageUtils pageUtils;
	DesktopCustomers desktopCustomers;

	
	public CommunicationsStepDefs(Context context)
	{
		this.context = context;
		desktopCommunications = context.getDesktopCommunications();
		desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
	}
	
	@Then("^I can check if a communication was sent with the following details$")
	public void I_can_check_if_a_communication_was_sent_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> dataMap = new HashMap<String, String>();
		//HashMap<String,String> customerMap = new HashMap<String, String>();
		//customerMap.put("1", null);
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> keyValuePair : dataList) {
			dataMap.put(keyValuePair.get(0).toLowerCase(), keyValuePair.get(1));
		}
		
		//if the date entered is a variable then process it and convert into a date
		String[] dates = {"date requested", "date sent"};
		for (String date : dates) {
		if(dataMap.get(date) != null) {
			String dateFormat = "dd/MM/yyyy";
			dataMap.put(date,TimeDateCalcs.CalculateDate(dataMap.get(date), dateFormat));
			}
		}
		
		//if the debtor name is supplied as is a number not a name lookup the name and replace
		String[] customers = {"recipient", "subject"};
		for (String customer : customers) {
			try {
			int debtorNumber = Integer.parseInt(dataMap.get(customer));
			desktopCustomers.getCustomers();
			dataMap.put(customer, pageUtils.testMap.get("customer" + debtorNumber));
			}catch(Exception e) {}
		}
		
		boolean match = desktopCommunications.communicationHistory(dataMap);
		// check if returned values match expected values
		Assert.assertTrue("Communications Issued check: ", match);
		    
			
		
	}
}
