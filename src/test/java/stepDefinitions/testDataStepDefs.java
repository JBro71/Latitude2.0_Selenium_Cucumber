package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.DesktopAddress;
import pageObjects.DesktopCustomers;
import utils.Context;
import utils.Logging;
import testComponents.PageUtils;
import testComponents.FileTools;
import testComponents.OpenAccount;
import testComponents.StepDefCommonFunctions;
import testComponents.TimeDateCalcs;

public class testDataStepDefs {
	Context context;
	PageUtils pageUtils;
	FileTools fileTools;
	StepDefCommonFunctions stepDefCF;
	HashMap<String,Integer> resultsMap;
	String testId;
	OpenAccount openAccount;

	
	public testDataStepDefs(Context context)
	{
		this.context = context;
		//desktopCustomers = context.getDesktopCustomers();
		pageUtils = context.getPageUtils();
		fileTools = context.getFileTools();
		stepDefCF = context.getStepDefCommonFunctions();
		openAccount = context.getOpenAccount();
	}

	
	@Given("that I am running test {string}")
	public void that_i_am_running_test(String testId) throws Exception {
		this.testId = testId;
		pageUtils.testMap.put("testId", testId);
		fileTools.getTestFile(testId);

	}
	

	@Then("I wait to run stage {string} until {string} {string} after the {string} stage")
	public void testStage(String stage, String delayString, String delayUnitsString, String delayStartParameter) throws Exception {
		String logEntryPrefix= "testDataStepDefs/testStage/test: " +testId+ ": " ; 
		Logging.logToConsole("DEBUG", logEntryPrefix +"TestStage: " + stage);
		int lastStage = Integer.parseInt(pageUtils.testMap.get("lastStage"));
		
		if (Integer.parseInt(stage) == lastStage+1) {
			pageUtils.testMap.put("run","false");
			String referenceDateTimeString;
			if(delayStartParameter.equalsIgnoreCase("first")) {
				referenceDateTimeString = pageUtils.testMap.get("testStartDateTime");
				}else if(delayStartParameter.equalsIgnoreCase("last")){
					referenceDateTimeString = pageUtils.testMap.get("lastStageDateTime");	
					}else {
					throw new Exception(" " + "test stage specifed not first or last");
					}
			boolean runNow = TimeDateCalcs.stageRunDateReached(referenceDateTimeString, delayString, delayUnitsString);
			Logging.logToConsole("DEBUG", logEntryPrefix +"Next Test Stage: " + stage+" run now? " + runNow);
			if(runNow) {
				pageUtils.testMap.put("run","true");
				fileTools.writeTestFileLine(testId, "INPROGRESS");
				
				//////put run code here
				}
				
			}else if(Integer.parseInt(stage) == lastStage+2 && pageUtils.testMap.get("run").equals("true")) {// test has got here without failing so previous stage complete
				fileTools.writeTestFileLine(testId, "PASS");
				pageUtils.testMap.put("run","false");
			}
		}
		
	@Then("Test is complete")
	public void test_is_complete() throws IOException {
		if(pageUtils.testMap.get("run").equals("true")) {// test has got here without failing so previous stage complete and it is the last Stage
			fileTools.writeTestFileLine(testId, "COMPLETE");
			pageUtils.testMap.put("run","false");
		}
	}

	
	
	
	@Given("that I have an account open in Latitude with the following details")
	public void that_i_have_an_account_open_in_latitude_with_the_following_details(io.cucumber.datatable.DataTable dataTable) throws Exception {
		if(!stepDefCF.run()) {return;}// if run is false do not run
		//convert dataTable to Hashmap and convert variables to real values
		HashMap<String,String> dataMap = stepDefCF.convertDataTableToMap(dataTable);
		dataMap = stepDefCF.processVariables(dataMap);
		String fileNameString = "";
		String[] entryOrder = {"client","stage","household size",};
		for (String i : entryOrder) {
			String value = dataMap.get(i).toLowerCase();
			if (value != null )
				{ 
				fileNameString = fileNameString + value;	
				}
			}	
			fileTools.loadTestDataFromFile(fileNameString);
			String accountNumber = stepDefCF.getTestDataItem(fileNameString);
			
			openAccount.OpenNewAccount(accountNumber);
			
		
	}
	
}
