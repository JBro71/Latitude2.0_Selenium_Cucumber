package stepDefinitions;


import org.junit.Assert;
import io.cucumber.java.en.*;
import testComponents.BaseTest;
import pageObjects.*;

public class VulnerableStepDefs <Public> extends BaseTest {
	DesktopVulnerabilites vulnerabilites;

	@When("^I add a new C&H record with care type of \"([^\"]*)\" and hardship of \"([^\"]*)\"$")
	public void i_add_a_new_c_h_record_with_care_type_of_physical_difficulty_and_hardship_of_impact_pay(String careType, String hardshipType) throws InterruptedException {
		vulnerabilites = new DesktopVulnerabilites(driver);
		vulnerabilites.OpenCareAndHardshipPanel();
		vulnerabilites.NewCareAndHardship(careType, hardshipType);
	}

	@When("^the default hold days of \"([^\"]*)\" are set to \"([^\"]*)\"$")
	public void the_default_hold_days_of_are_set_to_default(String defaultHoldDays, String SetHoldDays) throws InterruptedException {
		String presetCareAndHardshipHoldDays = vulnerabilites.CareHoldDays(SetHoldDays);
		Assert.assertEquals("Hold Days", presetCareAndHardshipHoldDays, defaultHoldDays); 
	
	}

	@Then("when I submit the C&H record it is stored successfully")
	public void when_i_submit_the_c_h_record_it_is_stored_successfully() throws InterruptedException  {
		boolean submitCareRecord = vulnerabilites.SubmitCareAndHardshipRecord();
		Assert.assertTrue("care record submitted successfully", submitCareRecord);
	}
	
	
	
	
}
