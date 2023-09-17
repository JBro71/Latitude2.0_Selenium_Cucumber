package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(  
	    features = {"src/test/java/features"},
	    glue={"stepDefinitions"},
	    stepNotifications = true, 
	    monochrome=true,
	    plugin= {"pretty","html:target/cucumberReports"}) //,"json:target/cucumber.json","junit:target/cukes.xml"})



public class newTestRunner {

}
