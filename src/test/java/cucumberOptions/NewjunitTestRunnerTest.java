package cucumberOptions;

/*
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

*/

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.IncludeTags;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions")
@IncludeTags("check")
public class NewjunitTestRunnerTest {
	

}
