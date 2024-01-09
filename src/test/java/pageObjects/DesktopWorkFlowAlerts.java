package pageObjects;

import java.util.HashMap;
import java.util.List;

//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopWorkFlowAlerts {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;

	
	public DesktopWorkFlowAlerts(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
		js = (JavascriptExecutor)driver;
	}
	

		public HashMap<String,Integer> findWorkFlowAlerts(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+"DesktopWorkFlowAlerts/findWorkFlowAlerts: ";
			Logging.logToConsole("INFO",logEntryPrefix+" start");
			
			pageUtils.openLowerPanel("Workflow Alerts");	
			pageUtils.defaultImplictWait();
			
				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findWorkFlowAlerts");
				dataMap.put("buttonPrevious", "//span[@title='Go to the previous page']");
				dataMap.put("buttonNext", "//span[@title='Go to the next page']");
				dataMap.put("buttonDisabledAttributeName", "class");
				dataMap.put("buttonDisabledAttributeText", "k-link k-pager-nav k-disabled");
				dataMap.put("tablePath", "//table[@class='k-grid-table']/tbody/tr/td[");
				dataMap.put("staleElementCheckColumn", "1");// column that normally has different data so the code knows when the page data has updated
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("id", new String[] {"1","text"});
				tableMap.put("alert date",new String[] {"2","datetime"});
				tableMap.put("business area",new String[] {"3","text"});
				tableMap.put("workflow name",new String[] {"4","text"});
				tableMap.put("alert description",new String[] {"5","text"});
				tableMap.put("action required",new String[] {"6","text"});
		
				
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}

}
