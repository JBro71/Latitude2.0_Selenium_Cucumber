package cucumberOptions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(  
	    features = {"src/test/java/features"},
	    glue={"stepDefinitions"},
	    stepNotifications = true, 
	    monochrome=true,
	   	tags = "@insolvencyAdd",
	    plugin= {"pretty","html:target/cucumberReports/testReport.html"}) //,"json:target/cucumber.json","junit:target/cukes.xml"})



public class JunitTestRunnerTest {
	// A method with annotation @BeforeClass runs at test class initialization time, i.e. before the whole bunch of all tests

	@BeforeClass
	  public static void setupClass() {
	    System.out.println("JUnit BeforeClass hook started");
	  }
	
	// A method with annotation @AfterClass runs at test class completion time, i.e. after the whole bunch of all tests
	  @AfterClass
	  public static void teardownClass() {
	    System.out.println("JUnit AfterClass hook started");
	  }
	
}
