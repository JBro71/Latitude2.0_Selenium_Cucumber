package pageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopEmail {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();
	String tablePath = "//table[@lat-table='vm.emailGrid']/tbody/tr/td[";
		
		public DesktopEmail(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	

		public void updateEmailAddress(int row, HashMap<String,String> paramsMap) throws Exception  {

		String logEntryPrefix= "Account: "+pageUtils.testMap.get("account")+" DesktopEmail/Email/update: "; 
		//try {
		Logging.logToConsole("INFO",logEntryPrefix+" Start");
		pageUtils.defaultImplictWait();
		//pageUtils.openLowerPanel("Email");

		//String buttonPath = "(//table[@lat-table='vm.emailGrid']//following-sibling::ul//li[@ng-if='::directionLinks'])[";

		/*
		HashMap<String,String> dataMap = new HashMap<String,String>();	
		dataMap.put("function", "findemailAddress");
		dataMap.put("buttonPrevious", buttonPath+"1]");
		dataMap.put("buttonNext", buttonPath+"2]");
		dataMap.put("buttonDisabledAttributeName", "class");
		dataMap.put("buttonDisabledAttributeText", "page-item ng-scope disabled");
		dataMap.put("tablePath", "//table[@lat-table='vm.emailGrid']/tbody/tr/td[");
		*/
		// open the edit menu
		driver.findElement(By.xpath("("+tablePath+1+"])+["+row+"]/div/a")).click();
		driver.findElement(By.xpath("(//ul[@class='dropdown-menu show'])[1]/li[1]/a")).click();
		
		
		//store element number of each table column in a map
		HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
		tableMap.put("email address", new String[] {"1","text"});
		tableMap.put("primary",new String[] {"2","checkbox"});
		tableMap.put("type",new String[] {"3","text"});
		tableMap.put("consent",new String[] {"4","colour","//i"});
		tableMap.put("correspondence",new String[] {"5","checkbox"});
		tableMap.put("status",new String[] {"6","colour","//i"});
		tableMap.put("contact",new String[] {"7","text"});
		
		HashMap<String,String> fieldMap = new HashMap<String,String>();		
		
		//return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
				
				
		
		

		
		

		public HashMap<String,Integer> findemailAddress(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+" DesktopEmail/email/find: "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.defaultImplictWait();
				pageUtils.openLowerPanel("Email");
				//phone = "((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3])[1]/span[3]/i"
				//      ((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3])[1]//i[@class='icon-ok-sign']
				String buttonPath = "(//table[@lat-table='vm.emailGrid']//following-sibling::ul//li[@ng-if='::directionLinks'])[";

				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findemailAddress");
				dataMap.put("buttonPrevious", buttonPath+"1]");
				dataMap.put("buttonNext", buttonPath+"2]");
				dataMap.put("buttonDisabledAttributeName", "class");
				dataMap.put("buttonDisabledAttributeText", "page-item ng-scope disabled");
				dataMap.put("tablePath", tablePath);
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("email address", new String[] {"1","text"});
				tableMap.put("primary",new String[] {"2","checkbox"});
				tableMap.put("type",new String[] {"3","text"});
				tableMap.put("consent",new String[] {"4","colour","//i"});
				tableMap.put("correspondence",new String[] {"5","checkbox"});
				tableMap.put("status",new String[] {"6","colour","//i"});
				tableMap.put("contact",new String[] {"7","text"});
				
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
				
		
		
	public void email(String action, HashMap<String,String> paramsMap, int row) throws Exception  {
		String account = pageUtils.testMap.get("account");
		Boolean check = false;
		Logging.logToConsole("INFO","DesktopEmail/email/"+account+": start");
		//String action = "add";
		//scroll the screen to the section with the Email Panel
		pageUtils.ReturnHome();


		//open the email panel
		/*
		pageUtils.Scroll(200);
		driver.findElement(By.xpath("//button[normalize-space()='Available Panels']")).click();

		 List<WebElement> menu = driver.findElements(By.xpath("//div[@class='dropdown-menu show']/div/a"));
		 for (WebElement menuItem : menu) {
			 try {
			String menuItemText = menuItem.getText();
			    if (menuItemText.equalsIgnoreCase("email")) {
			    	menuItem.click();
			    }
			 }catch(Exception e) {
				 throw new Exception("DesktopEmail/email/"+account+" :  unable to open Email Panel");
			 }
			 }
		*/
			
		 
		 
		switch (action.toLowerCase()) {
		case "add":
			pageUtils.openLowerPanel("Email");
			driver.findElement(By.xpath("//a[@ng-click='vm.openAddEmailDialog()']")).click();
			break;
		case "update":
			pageUtils.Scroll(300);
			driver.findElement(By.xpath("("+tablePath+"1])["+row+"]/div/a[2]")).click();
			String path = "("+tablePath+"1])["+row+"]/div/a";
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("(//ul[@class='dropdown-menu show'])[1]/li[1]/a")).click();
			break;
		case "check":
			check = true;
			break;
			}
				
		// order in which the data should be entered
		String[] entryOrder = {"email address", "type", "status","customer on account","is primary","is correspondence","consent to email", "obtained from", "method", 
				"comments"};	

		
			//driver.switchTo().frame(driver.findElement(By.id("DebtManagement21")));
				//Thread.sleep(1000);

		//iterate through the keys that have a value and execute them in correct entry order
		Select dropdown;		
		WebElement inputWebElement; // temp element to hold values in case statement
		Boolean isSelected;
		for (String i : entryOrder) {
			String value = paramsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", "DesktopEmail/Email: Key: " +i+ " Value: "+ value);	
			switch (i) {
			
			case "email address":
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys(value);
				break;
			case "type":
				dropdown = new Select(driver.findElement(By.xpath("//select[@name='type']")));
				dropdown.selectByVisibleText(value);
				break;
			case "status":
				dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.emailRecord.statusCd']")));
				dropdown.selectByVisibleText(value);
				break;
			case "customer on account":
				dropdown = new Select(driver.findElement(By.xpath("//select[@name='customer']")));
				dropdown.selectByVisibleText(value);
				break;	
			case "is primary":	
				driver.findElement(By.xpath("//input[@ng-model='vm.emailRecord.primary']")).click();
				pageUtils.handlePopup("OK");
				break;
			case "is correspondence":	;
				driver.findElement(By.xpath("//input[@ng-model='vm.emailRecord.correspondence']")).click();
				pageUtils.handlePopup("OK");
				break;				
			case "consent to email":
				driver.findElement(By.xpath("//input[@name='chkMayEmail']")).click();
				break;
			case "obtained from":	
				if(action.equals("add")) {
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='consentSourceDrop']")));
					}
					else {
						dropdown = new Select(driver.findElement(By.xpath("//select[@name='obtainedfrom']")));	
						}
				dropdown.selectByVisibleText(value);
				break;
			case "method":	
				if(value.equalsIgnoreCase("written")) {
					driver.findElement(By.xpath("//input[@id='consentWritten']")).click();
					}else if (value.equalsIgnoreCase("verbal")){
						driver.findElement(By.xpath("//input[@id='consentVerbal']")).click();					
					}
				break;	
			case "comments":
				driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys(value);
				break;
				}
			}
		}
		
		WebElement buttonSave = driver.findElement(By.xpath("//div[@class='modal-footer lat-form-idle']/button[1]"));
		if(buttonSave.isEnabled() == true) {
			buttonSave.click();
			pageUtils.ReturnHome();
			return;
			}
			else {
			//Click Cancel	
			driver.findElement(By.xpath("//div[@class='modal-footer lat-form-idle']/button[2]")).click();
			pageUtils.ReturnHome();
			throw new Exception("DesktopEmail/email/"+account+" :  cannot save update. button disabled");
			}	
	}	
}
