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


public class DesktopPhone {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();
	String tablePath = "//phones//table//tbody/tr/td[";
		
		public DesktopPhone(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
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
				

		public HashMap<String,Integer> findPhoneNumber(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+" DesktopPhone/phone/find: "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.defaultImplictWait();
				pageUtils.openLowerPanel("Phones");
				//phone = "((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3 ])[1]/span[3]/i"
				//      ((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3])[1]//i[@class='icon-ok-sign']
				//tbody[2]/tr[1]/td[1]
				String buttonPath = "//ul[@total-items='vm.pagination.count']//li[@ng-if='::directionLinks'][";

				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findphoneNumber");
				dataMap.put("buttonPrevious", buttonPath+"1]");
				dataMap.put("buttonNext", buttonPath+"2]");
				dataMap.put("buttonDisabledAttributeName", "class");
				dataMap.put("buttonDisabledAttributeText", "page-item ng-scope disabled");
				dataMap.put("tablePath", tablePath);
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("number", new String[] {"1","text"});
				tableMap.put("type",new String[] {"2","text"});
				tableMap.put("consent to call",new String[] {"3","colour","/span[1]/i"});
				tableMap.put("consent to auto dial",new String[] {"3","colour","/span[2]/i"});
				tableMap.put("consent to fax",new String[] {"3","colour","/span[3]/i"});
				tableMap.put("consent to sms",new String[] {"3","colour","/span[4]/i"});
				tableMap.put("status",new String[] {"4","colour","/i"});
				tableMap.put("customer",new String[] {"5","text"});
				tableMap.put("attempts",new String[] {"6","text"});
				tableMap.put("last attempt",new String[] {"7","text"});
				tableMap.put("name",new String[] {"8","text"});
				tableMap.put("correspondence",new String[] {"9","checkbox"});
				tableMap.put("on hold",new String[] {"10","text"});
				tableMap.put("hold expiration date",new String[] {"11","text"});
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
				
		
		
		public void phone(String action, HashMap<String,String> paramsMap, int row) throws Exception  {
		String logEntryPrefix= "Account: "+pageUtils.testMap.get("account")+" DesktopPhone/phone: "; 
		Boolean check = false;
		
		Logging.logToConsole("INFO",logEntryPrefix+" start");
		pageUtils.ReturnHome();
		 
		switch (action.toLowerCase()) {
		case "add":
			pageUtils.openLowerPanel("Phones");
			driver.findElement(By.xpath("//a[@ng-click='vm.openAddPhone(vm.phoneTableData)']")).click();
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
		String[] entryOrder = {"phone number","phone extension","type","status","customer on account","name","on hold",
				"hold expiration date","is correspondence","consent to call","consent to auto dial","consent to sms",
				"consent to fax","obtained from","method","comments"};

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
				case "phone number":
					driver.findElement(By.xpath("//input[@ng-model='vm.newPhone.number']")).sendKeys(value);
					break; 
				case "phone extension":
					driver.findElement(By.xpath("//input[@ng-model='vm.newPhone.extension']")).sendKeys(value);
					
					break; 
				case "type":
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.typeId']")).click();
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.typeId']//input[@ng-model='$select.search']")).sendKeys(value);
					driver.findElement(By.xpath("(//li[@class='ui-select-choices-group'])[1]/ul/li[1]")).click();
					break;
				case "status":
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.statusId']")).click();
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.statusId']//input[@ng-model='$select.search']")).sendKeys(value);
					driver.findElement(By.xpath("(//li[@class='ui-select-choices-group'])[2]/ul/li[1]")).click();
					break;
				case "customer on account":
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.partyId']")).click();
					driver.findElement(By.xpath("//div[@ng-model='vm.newPhone.partyId']//input[@ng-model='$select.search']")).sendKeys(value);
					driver.findElement(By.xpath("(//li[@class='ui-select-choices-group'])[3]/ul/li[1]")).click();
					break;	
				case "name":
					driver.findElement(By.xpath("//input[@name='name']")).sendKeys(value);
					break; 
				case "on hold":
					pageUtils.updateCheckBox(value, "//div[@ng-model='vm.newPhone.partyId']");
					break; 
				case "hold expiration date":
					driver.findElement(By.xpath("//input[@id='dateHoldExpires']")).sendKeys(value);
					break; 
				case "is correspondence":
					pageUtils.updateCheckBox(value, "//input[@name='chkCorrespondence']");
					pageUtils.handlePopup("OK");
					break;				
				case "consent to call":
					pageUtils.updateCheckBox(value, "//input[@name='chkMayCall']");
					break;
				case "consent to auto dial":
					pageUtils.updateCheckBox(value, "//input[@name='chkMayCallViaDialer']");
					break; 
				case "consent to sms":
					pageUtils.updateCheckBox(value, "//input[@name='chkMaySMS']");
					break; 
				case "consent to fax":
					pageUtils.updateCheckBox(value, "//input[@name='chkMayFAX']");
					break; 
				case "obtained from":	
					driver.findElement(By.xpath("//div[@name='obtainedFrom']")).click();
					driver.findElement(By.xpath("//div[@name='obtainedFrom']//input[@ng-model='$select.search']")).sendKeys(value);
					driver.findElement(By.xpath("(//li[@class='ui-select-choices-group'])[4]/ul/li[1]")).click();

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
			throw new Exception(logEntryPrefix+ "  cannot save update. button disabled");
			}	
	}	
}
