package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.en.*;
import testComponents.BaseTest;
import pageObjects.*;



public class StepDefinition <Public> extends BaseTest{

	
	
	@Given("I am logged into the Latitude Desktop")
	public void i_am_logged_into_the_latitude_desktop() throws IOException, InterruptedException {
		//********Launch Browser*************
		boolean existingSession = initilizeDriver();
		
		if (!existingSession) { //no existing session
		testMap.put("account", ""); //Initialise the account value;
		//***********Log in*****************
		LoginPage loginPage = new LoginPage();
		loginPage.login(driver);

		//****************** Splash screen*******************
		SplashPage splashPage = new SplashPage(driver);
		splashPage.splash("goto desktop");
		}
	}
	
	
	@Given("^I have account \"([^\"]*)\" open in Latitude$")
	public void i_have_account_open_in_latitude(String accountNumber) throws InterruptedException {
		if (!testMap.get("account").equals(accountNumber)) { //account not open then open
		OpenNewAccount(accountNumber);
		testMap.put("account",accountNumber);
		}	
	}

	@Given("the {string} anchor panel will display a {string} value of {string}")
	public void the_anchor_panel_will_display_a_value_of(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
