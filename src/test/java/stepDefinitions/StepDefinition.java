package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import io.cucumber.java.en.*;
import testComponents.*;
import utils.Context;
import utils.Logging;
import pageObjects.*;


public class StepDefinition <Public> extends BaseTest{
	
	Context context;
	LoginPage loginPage;
	SplashPage splashPage;
	SearchPage searchPage;
	DesktopAnchorPanels desktopAnchorPanels;
	PageUtils pageUtils;
	OpenAccount openAccount;
	DesktopArrangements arrangements;
	DesktopVulnerabilites vulnerabilities;
	
	public StepDefinition(Context context)
	{
		this.context = context;
		searchPage = context.getSearchPage();
		loginPage = context.getLoginPage();
		splashPage = context.getSplashPage();
		desktopAnchorPanels = context.getDesktopAnchorPanels();
		pageUtils = context.getPageUtils();
		openAccount = context.getOpenAccount();
		arrangements = context.getDesktopArrangements();
		vulnerabilities = context.getDesktopVulnerabilites();
	}
	
	
	@Given("I am logged into the Latitude Desktop")
	public void i_am_logged_into_the_latitude_desktop() throws IOException, InterruptedException {
		//if this is the first test launch browser otherwise re-use existing instance
		if (firstTest) {
		loginPage.login();
		splashPage.splash("goto desktop");
		}
	}
	
	
	@Given("^I have account \"([^\"]*)\" open in Latitude$")
	public void i_have_account_open_in_latitude(String accountNumber) throws Exception  {
		
		if (!pageUtils.testMap.get("account").equals(accountNumber)) { //no account open or a different account open to that needed
		//pageUtils.CloseAccount(); // close an existing account if one open
		openAccount.OpenNewAccount(accountNumber);
		pageUtils.updateTestMap("account",accountNumber);
		//reset the customer details

		}
		pageUtils.ReturnHome(); // scroll back to the top
	}

	@Given("the {string} anchor panel will display a {string} value of {string}")
	public void the_anchor_panel_will_display_a_value_of(String panelName, String label, String value) {
		String anchorPanelValue =  desktopAnchorPanels.queryAnchorPanels(panelName, label);
		Assert.assertEquals("Anchor panel: " + panelName + "/" + label, value, anchorPanelValue);
		//Assert. assertTrue(anchorPanelValue.equals(value), "Anchor panel: " + panelName + " label: " + label + " equals: " + value );
	}
	
	@Then("the anchor panels will display the following values")
	public void the_anchor_panels_will_display_the_following_values(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> dataList = dataTable.asLists(); //get data table
		for (List<String> row : dataList) {
			String anchorPanelValue =  desktopAnchorPanels.queryAnchorPanels(row.get(0), row.get(1));
			Assert.assertEquals("Anchor panel: " + row.get(0) + "/" + row.get(1), row.get(2), anchorPanelValue);
			Logging.logToConsole("INFO","DesktopComplaints/complaints:t");
		}	
		
	
	}
	
	
	

	@Then("if I add a payment card with the following details")
	public void if_i_add_a_payment_card_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
		HashMap<String,String> paymentCardMap = new HashMap<String, String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		 for (String keyValuePair : dataList.get(0)) { 
			   //Logging.logToConsole("Debug","payment card: "+ keyValuePair);
			    String[] dataPairArray = keyValuePair.split(":");
			    String key = dataPairArray[0];
			    String  value = dataPairArray[1].replaceAll("^\\s+", "");
			    if (key.equals("expiry")) {
			    	String[] expiryMonthYear = value.split("/");
			    	paymentCardMap.put("card expiry month", expiryMonthYear[0]);
			    	paymentCardMap.put("card expiry year", expiryMonthYear[1]);
			    	}
				    else {
				    	paymentCardMap.put(key,value);
				    }	
			    }
	    	arrangements.AddPaymentCard(paymentCardMap);
		}

	
	@Then("I can add a card arrangement using the following details")
	public void i_can_add_a_card_arrangement_using_the_following_details(io.cucumber.datatable.DataTable dataTable) throws InterruptedException, IOException {
		HashMap<String,String> dataMap = new HashMap<String, String>();
		List<List<String>> dataList = dataTable.asLists(); //get data table
		 for (String keyValuePair : dataList.get(0)) { 
			   //Logging.logToConsole("Debug","payment card: "+ keyValuePair);
			    String[] dataPairArray = keyValuePair.split(":");
			    String key = dataPairArray[0];
			    String  value = dataPairArray[1]. replaceAll("^\\s+", "");
			    if (key.equals("expiry")) {
			    	String[] expiryMonthYear = value.split("/");
			    	dataMap.put("card expiry month", expiryMonthYear[0]);
			    	dataMap.put("card expiry year", expiryMonthYear[1]);
			    	}
				    else {
				    	dataMap.put(key,value);
				    }	
			    }
	    	arrangements.AddArrangment(dataMap);
	    			}

	
	
}
