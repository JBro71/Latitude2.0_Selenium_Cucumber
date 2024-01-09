package pageObjects;

import java.util.HashMap;
//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopHouseholdNotes {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();

		
		public DesktopHouseholdNotes(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	

		public HashMap<String,Integer> findHouseholdNotes(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+"DesktopHouseholdNotes/householdNotes/find: ";
			Logging.logToConsole("INFO",logEntryPrefix+" start");
			
			pageUtils.openLowerPanel("Notes");	
			pageUtils.defaultImplictWait();
			String buttonPath = "(//ul[@items-per-page='vm.notesConfig.pageSize']/li/a)";
			int buttonCountInt = driver.findElements(By.xpath(buttonPath)).size();
				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findemailAddress");
				dataMap.put("buttonPrevious", buttonPath+"[1]");
				dataMap.put("buttonNext", buttonPath+"["+buttonCountInt+"]");
				dataMap.put("buttonDisabledAttributeName", "disabled");
				dataMap.put("buttonDisabledAttributeText", "true");
				dataMap.put("tablePath", "//table[@lat-table='vm.notesConfig']/tbody/tr/td[");
				dataMap.put("staleElementCheckColumn", "6");// column that normally has different data so the code knows when the page data has updated
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("date", new String[] {"1","datetime"});
				tableMap.put("account",new String[] {"2","text"});
				tableMap.put("user",new String[] {"3","text"});
				tableMap.put("action",new String[] {"4","text"});
				tableMap.put("result",new String[] {"5","text"});
				tableMap.put("comments",new String[] {"6","text"});
				tableMap.put("private?",new String[] {"7","checkbox"});
				
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
			
		
		
		
		public void householdNotes(HashMap<String,String> paramsMap) throws Exception  {
		String account = pageUtils.testMap.get("account");
		String logEntryPrefix= "Account: "+account+"DesktopHouseholdNotes/householdNotes/add: ";
		Logging.logToConsole("INFO",logEntryPrefix+" start");
		
		pageUtils.openLowerPanel("Notes");	
		driver.findElement(By.xpath("//a[@ng-click='vm.openAddNote()']")).click();
		// order in which the data should be entered
		String[] entryOrder = {"action code", "result code", "comments","private"};

		//iterate through the keys that have a value and execute them in correct entry order
		//List<WebElement> menu;
		
		for (String i : entryOrder) {
			String value = paramsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value);	
			switch (i) {
			
			case "action code":	
				driver.findElement(By.xpath("//form[@name='vm.addNoteDialog']/div[2]/div[1]/div[2]/div")).click();
				driver.findElement(By.xpath("(//input[@aria-label='Select box'])[1]")).sendKeys(value);
				driver.findElement(By.xpath("//li[@role='option']")).click();
				break;
			case "result code":
				driver.findElement(By.xpath("//form[@name='vm.addNoteDialog']/div[2]/div[2]/div[2]/div")).click();
				driver.findElement(By.xpath("(//input[@aria-label='Select box'])[2]")).sendKeys(value);
				driver.findElement(By.xpath("//li[@role='option']")).click();
				break;
			case "comments":
				driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys(value);
				break;
			case "private":
				pageUtils.updateCheckBox(value,"//input[@ng-model='vm.newNote.isPrivate']");
				break;	
				}
			}
		}
		
		WebElement buttonSave = driver.findElement(By.xpath("//button[@ng-click='vm.saveNote()']"));
		if(buttonSave.isEnabled() == true) {
			buttonSave.click();
			pageUtils.ReturnHome();
			return;
			}
			else {
			//Click Cancel	
			driver.findElement(By.xpath("//button[@ng-click='vm.saveNote()']/following-sibling::button")).click();
			pageUtils.ReturnHome();
			throw new Exception(logEntryPrefix + " :  cannot save update. button disabled");
			}	
	}	
}
