package pageObjects;

import java.util.HashMap;
//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopEvents {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();

		
		public DesktopEvents(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	

		public HashMap<String,Integer> findEvents(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+"DesktopEvents/findevents: ";
			Logging.logToConsole("INFO",logEntryPrefix+" start");
			
			pageUtils.openLowerPanel("Events");	
			//Thread.sleep(2000);
			pageUtils.defaultImplictWait();
			//String buttonPath = "(//ul[@items-per-page='vm.notesConfig.pageSize']/li/a)";
				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("tablePath", "//table[@class='k-grid-table']/tbody/tr/td[");
				dataMap.put("staleElementCheckColumn", "4");// column that normally has different data so the code knows when the page data has updated
				dataMap.put("pages", "false"); // there are no page buttons
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("date", new String[] {"1","datetime"});
				tableMap.put("user",new String[] {"2","text"});
				tableMap.put("event",new String[] {"3","text"});
				tableMap.put("details",new String[] {"4","text"});
				
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
			
		
		
		
}
