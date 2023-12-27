package stepDefinitions;

import io.cucumber.java.Before;
import testComponents.BaseTest;
import io.cucumber.java.After;
import utils.Logging;

public class Hooks {
	
	@Before("@tag1")
	public void tag1Setup() {
		Logging.logToConsole("INFO", "***********Tag1 Before Hook***********");
			}
	
	@Before("@tag2")
	public void tag2Setup() {
		Logging.logToConsole("INFO", "***********Tag2 Before Hook***********");
			}
	
	@Before
	public void GenericSetup() {
		Logging.logToConsole("INFO", "***********Generic Before Hook***********");
			}
	
	@After
	public void teardown() {
		Logging.logToConsole("INFO", "***********Generic After Hook***********");
		try {
		if(BaseTest.staticTestMap.get("run").equals("true")) {
			
		testComponents.FileTools.writeTestReport("STAGE FAILED");	
		}
		BaseTest.staticTestMap.put("run", "");
		}catch (Exception e) {Logging.logToConsole("INFO", "Hook Failed");}
	}
	
	
}
