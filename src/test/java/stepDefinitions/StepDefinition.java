package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import testComponents.StepDefCommonFunctions;
import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.*;
import testComponents.*;
import utils.Context;
import utils.Logging;
import pageObjects.*;


public class StepDefinition <Public> extends BaseTest{
	
	Context context;
	PageUtils pageUtils;
	StepDefCommonFunctions StepDefCF;
	LoginPage loginPage;
	SplashPage splashPage;
	SearchPage searchPage;
	DesktopAnchorPanels desktopAnchorPanels;
	OpenAccount openAccount;
	DesktopArrangements arrangements;
	DesktopVulnerabilites vulnerabilities;
	
	public StepDefinition(Context context)
	{
		this.context = context;
		pageUtils = context.getPageUtils();
		StepDefCF = context.getStepDefCommonFunctions();
		searchPage = context.getSearchPage();
		loginPage = context.getLoginPage();
		splashPage = context.getSplashPage();
		desktopAnchorPanels = context.getDesktopAnchorPanels();
		openAccount = context.getOpenAccount();
		arrangements = context.getDesktopArrangements();
		vulnerabilities = context.getDesktopVulnerabilites();
	}
	
	
	@Then("I can convert field names to a string")
	public void i_can_convert_field_names_to_a_string(io.cucumber.datatable.DataTable dataTable) throws Exception {
		//convert dataTable to Hashmap and convert variables to real values
		List<List<String>> dataList = dataTable.asLists(); //get data table
		String fieldString = "\"";
		String caseString = "";
		for (List<String> keyValuePair : dataList) {
			fieldString = fieldString + keyValuePair.get(0).toLowerCase() +"\",\"";
			caseString = caseString + "case \"" +keyValuePair.get(0).toLowerCase()+"\":\n\n break; \n";
			}	
		System.out.println(fieldString);
		System.out.println(caseString);
	}
	
	@Then("I can convert I and E field names to a string")
	public void i_can_convert_i_and_e_field_names_to_a_string(io.cucumber.datatable.DataTable dataTable) {
		//convert dataTable to Hashmap and convert variables to real values
		List<List<String>> dataList = dataTable.asLists(); //get data table
		String fieldString = "\"";
		String caseString = "";
		for (List<String> itemList : dataList) {
			for (int i=0; i<5; i++) {if(itemList.get(i) == null) {itemList.set(i, "");}} // replace nulls with ""
			String key = itemList.get(0).toLowerCase()+ "/" + itemList.get(1).toLowerCase()+ "/" + itemList.get(2).toLowerCase();
			fieldString = fieldString + key +"\",\"";
			caseString = caseString + "case \"" +key+"\":\n\n break; \n";
		}
		System.out.println(fieldString);
		System.out.println(caseString);	
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
		pageUtils.updateTestMap("account",accountNumber, true);
		pageUtils.updateTestMap("customer1", null, true);
		pageUtils.updateTestMap("customer2", null, true);
		//reset the customer details
		}
		else {
			pageUtils.closeAnchorPanel();
		}
		pageUtils.ReturnHome(); // scroll back to the top
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
