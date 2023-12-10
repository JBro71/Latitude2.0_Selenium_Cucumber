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
		
		public DesktopEmail(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
		

		public int findemailAddress(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+" DesktopEmail/Care&Hardship/find: "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.defaultImplictWait();
				pageUtils.openLowerPanel("Email");
				//phone = "((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3])[1]/span[3]/i"
				//      ((//table[@class='table table-striped table-hover table-bordered'])[2]/tbody/tr/td[3])[1]//i[@class='icon-ok-sign']
				String buttonPath = "(//table[@lat-table='vm.emailGrid']//following-sibling::ul//li[@ng-if='::directionLinks'])[";
				//WebElement buttonPrevious = driver.findElement(By.xpath(buttonPath+"1]"));
				//WebElement buttonNext = driver.findElement(By.xpath(buttonPath+"2]"));
				//String buttonDisabled = "page-item ng-scope disabled";
				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findemailAddress");
				dataMap.put("buttonPrevious", buttonPath+"1]");
				dataMap.put("buttonNext", buttonPath+"2]");
				dataMap.put("buttonDisabledAttributeName", "class");
				dataMap.put("buttonDisabledAttributeText", "page-item ng-scope disabled");
				dataMap.put("tablePath", "//table[@lat-table='vm.emailGrid']/tbody/tr[3]/td[");
				
				//store element number of each table column in a map
				//String tablePath = "//table[@lat-table='vm.emailGrid']/tbody/tr/td[";
				//[class='icon-headphones']
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("email address", new String[] {"1","text"});
				tableMap.put("primary",new String[] {"2","checkbox"});
				tableMap.put("type",new String[] {"3","text"});
				tableMap.put("consent",new String[] {"4","colour","//i"});
				tableMap.put("correspondence",new String[] {"5","checkbox"});
				tableMap.put("status",new String[] {"6","colour","//i"});
				tableMap.put("contact",new String[] {"7","text"});
				
				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				HashMap<String,Integer> resultsMap = pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
				
				return resultsMap.get("numberOfMatches");
				/*
				// make sure we are at the first page
				while(!buttonPrevious.getAttribute("class").equals(buttonDisabled)) {
					buttonPrevious.click();
				}
						
				int pageCount = 0;
				boolean match = false; // boolean to hold the matchstatus
				int matchCount = 0;
				boolean firstPass = true; // two passes around the loop variable to hold which pass we are on
				//search for a match until one is found or all the data is checked
				while (true) {
					Logging.logToConsole("DEBUG",logEntryPrefix+": Page: " + (pageCount+1));
					// get the number of rows 	
					int size = driver.findElements(By.xpath(tablePath+"1]")).size();
					//look through each row in the table on the current page until a match is found or we run out of rows
					for(int row = 1; row <= size; row++)	{
						Logging.logToConsole("DEBUG",logEntryPrefix+": Row: "+ row);	
						//loop through each entry in the input parameters map and see if it matches the current table row being checked
						match = true;
						for (String key : tableMap.keySet()) { //check the table fields for a match
							WebElement tableField = driver.findElement(By.xpath("("+tablePath+ tableMap.get(key) +"])["+row+"]"));
							//String value = driver.findElement(By.xpath(tableMap.get(key))).getText();
							//Logging.logToConsole("INFO",logEntryPrefix+" Key: " + key);
							//Logging.logToConsole("INFO",logEntryPrefix+" Table text: " + tableField.getText());
							//Logging.logToConsole("INFO",logEntryPrefix+" pramsMap value: " + paramsMap.get(key));
							
							try {
							//String temp = paramsMap.get(key);
							if(paramsMap.containsKey(key) == false) {continue;}// don't need to check that value so skip
							if(paramsMap.get(key).equals(tableField.getText())) {continue;}// if a match or no value then skip to the next loop/value to check
							}catch(Exception E) {}
							match = false;
							break;
							}
						/*
						if(match) {
							for (String key : careMap.keySet()) { // check the non table fields for a match
								if(paramsMap.get(key) == null) {continue;}
								String value = driver.findElement(By.xpath(careMap.get(key))).getAttribute("value");
								if(paramsMap.get(key).equals(value)) {continue;}// if a match then skip to the next loop/value to check
								match = false;
								break;
								}
							}
						
						if(match) {
							if (!firstPass) { //click the match and exit function
								driver.findElement(By.xpath("("+tablePath+ "1])["+row+"]")).click();
								return matchCount;
								}
							matchCount++;
							Logging.logToConsole("DEBUG",
									"DesktopBatchApi/getBatchApi/"+account+": Match Found: "+ " Row: "+(row));
							}
						} //row loop	

				if (!buttonNext.getAttribute("class").equals(buttonDisabled))
					{
					buttonNext.click();
					pageCount = pageCount+1;
					}
					else if (firstPass ) 
						{//start the second pass reset the pages
						firstPass = false;
						while(!buttonPrevious.getAttribute("class").equals(buttonDisabled)) 
							{
							buttonPrevious.click();
							}
						pageCount = 0;
						}
						else {//next button is disabled and we are at the end of the second pass through. time to quit
							return matchCount;
						}
				
					} //while loop	
				*/
		}
				
		
		
		
		
		
		
		
		
	
	public void email(HashMap<String,String> paramsMap) throws Exception  {
		String account = pageUtils.testMap.get("account");
		Boolean check = false;
		Logging.logToConsole("INFO","DesktopEmail/email/"+account+": start");
		String action = "add";
		//scroll the screen to the section with the Email Panel
		pageUtils.ReturnHome();

		if(action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("update") ){
			action = "edit";	
			}

		//open the email panel
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
		
		switch (action.toLowerCase()) {
		case "add":
			driver.findElement(By.xpath("//a[@ng-click='vm.openAddEmailDialog()']")).click();
			break;
		case "edit":
			
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
				if (check){				

				}
				else {
					driver.findElement(By.xpath("//input[@name='email']")).sendKeys(value);
				}
				break;
			case "type":
				if (check){
					//dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					//resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='type']")));
							dropdown.selectByVisibleText(value);
							}
				break;
			case "status":
				if (check){
					//dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					//resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.emailRecord.statusCd']")));
							dropdown.selectByVisibleText(value);
							}
				break;
			case "customer on account":
				if (check){
					//dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					//resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='customer']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "is primary":	
				if (check){				
				}
				else {
					driver.findElement(By.xpath("//input[@ng-model='vm.emailRecord.primary']")).click();
					pageUtils.handlePopup("OK");
				}
				break;
			case "is correspondence":	;
				if (check){				
				}
				else {
					driver.findElement(By.xpath("//input[@ng-model='vm.emailRecord.correspondence']")).click();
					pageUtils.handlePopup("OK");
				}
				break;				
			case "consent to email":
				if (check){				
				}
				else {
					driver.findElement(By.xpath("//input[@name='chkMayEmail']")).click();
				}
				break;	
			case "obtained from":	
				if (check){
					//dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					//resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='consentSourceDrop']")));
							dropdown.selectByVisibleText(value);
							}
				break;
			case "method":	
				if(value.equalsIgnoreCase("written")) {
					driver.findElement(By.xpath("//input[@id='consentWritten']")).click();
					}else if (value.equalsIgnoreCase("verbal")){
						inputWebElement = driver.findElement(By.xpath("//input[@id='consentVerbal']"));					
					}
				break;	
			case "comments":
				if (check){

						}
						else {
							driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys(value);
							}
				break;
			case "insufficient proof received":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.insufficientProofReceived']"));
					resultsMap.put(i,"false");							
					if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
					}
					else {
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm."+action+"Record.insufficientProofReceived']"));
						isSelected = inputWebElement.isSelected();
						if((value.equalsIgnoreCase("true") && isSelected == false) || (value.equalsIgnoreCase("false")&& isSelected == true)) {
						inputWebElement.click();
						}
					}
				break;
			case "justified":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeJustified']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.justified']")));
							dropdown.selectByVisibleText(value);
									}
				break;	
			case "outcome":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeOutcome']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.outcome']")));
							dropdown.selectByVisibleText(value);
									}
				break;	
			case "proof received":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.proofReceived']"));
					resultsMap.put(i,"false");							
					if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
					}
					else {
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm."+action+"Record.proofReceived']"));
						isSelected = inputWebElement.isSelected();
						if((value.equalsIgnoreCase("true") && isSelected == false) || (value.equalsIgnoreCase("false")&& isSelected == true)) {
						inputWebElement.click();
						}
					}
				break;
			case "proof requested":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.proofRequested']"));
					resultsMap.put(i,"false");							
					if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
					}
					else {							
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm."+action+"Record.proofRequested']"));
						isSelected = inputWebElement.isSelected();
						if((value.equalsIgnoreCase("true") && isSelected == false) || (value.equalsIgnoreCase("false")&& isSelected == true)) {
						inputWebElement.click();
								}
						}
				break;
			case "proof required":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentDisputeType.proofRequired']"));
					resultsMap.put(i,"false");							
					if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
					}
				break;
			case "recourse date":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.recourseDate']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.recourseDate']"));
						inputWebElement.clear();
						inputWebElement.sendKeys(value);
						}
				break;	
				
			case "referred by":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeReferredBy']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
						dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.referredBy']")));
						dropdown.selectByVisibleText(value);
								}
				break;
				}
			}
		}
		
		WebElement buttonSave = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
		if(buttonSave.isEnabled() == true) {
			buttonSave.click();
			pageUtils.ReturnHome();
			return;
			}
			else {
			//Click Cancel	
			driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();
			pageUtils.ReturnHome();
			throw new Exception("DesktopEmail/email/"+account+" :  cannot save update. button disabled");
			}	
	}	
}
