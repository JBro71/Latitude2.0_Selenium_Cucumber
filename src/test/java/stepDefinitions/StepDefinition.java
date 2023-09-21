package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.*;

import testComponents.BaseTest;
import utils.Context;
import pageObjects.*;



public class StepDefinition <Public> extends BaseTest{
	
	Context context;

	LoginPage loginPage;
	SplashPage splashPage;
	SearchPage searchPage;
	DesktopAnchorPanels desktopAnchorPanels;
	
	public StepDefinition(Context context)
	{
		this.context = context;
		searchPage = context.getSearchPage();
		loginPage = context.getLoginPage();
		splashPage = context.getSplashPage();
		desktopAnchorPanels= context.getDesktopAnchorPanels();
	}
	
	
	@Given("I am logged into the Latitude Desktop")
	public void i_am_logged_into_the_latitude_desktop() throws IOException, InterruptedException {
		//********Launch Browser*************
		//boolean existingSession = initilizeDriver();
		
		//if (!existingSession) { //no existing session
		//testMap.put("account", ""); //Initialise the account value;
		//***********Log in*****************

		loginPage.login();

		//****************** Splash screen*******************
		splashPage.splash("goto desktop");
		//}
	}
	
	
	@Given("^I have account \"([^\"]*)\" open in Latitude$")
	public void i_have_account_open_in_latitude(String accountNumber) throws InterruptedException {
		if (!testMap.get("account").equals(accountNumber)) { //account not open then open
		context.OpenNewAccount(accountNumber);
		testMap.put("account",accountNumber);
		}	
	}

	@Given("the {string} anchor panel will display a {string} value of {string}")
	public void the_anchor_panel_will_display_a_value_of(String panelName, String label, String value) {
		String anchorPanelValue =  desktopAnchorPanels.queryAnchorPanels(panelName, label);
		Assert.assertEquals("Anchor panel: " + panelName + "/" + label, value, anchorPanelValue);
		//Assert. assertTrue(anchorPanelValue.equals(value), "Anchor panel: " + panelName + " label: " + label + " equals: " + value );
	}
	
}
