package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testComponents.PageUtils;

import utils.Logging;

public class DesktopVulnerabilites {
	
WebDriver driver;
PageUtils pageUtils;
String logEntryPrefix;
	
	public DesktopVulnerabilites(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
	}
	
	

	public int findcareAndHardship(HashMap<String,String> paramsMap) throws Exception  {
		String account = pageUtils.testMap.get("account");
		logEntryPrefix= "Account: "+account+" DesktopVulnerabilites/Care&Hardship/find: "; 
		//try {
			Logging.logToConsole("INFO",logEntryPrefix+" Start");
			pageUtils.defaultImplictWait();
			OpenCareAndHardshipPanel();
			
			WebElement buttonPrevious = driver.findElement(By.xpath("(//a[@class='page-link ng-binding'][normalize-space()='Previous'])[3]//parent::li"));
			WebElement buttonNext = driver.findElement(By.xpath("(//a[@class='page-link ng-binding'][normalize-space()='Next'])[3]//parent::li"));
			String buttonDisabled = "page-item ng-scope disabled";
			// make sure we are at the first page
			while(!buttonPrevious.getAttribute("class").equals(buttonDisabled)) {
				buttonPrevious.click();
			}
			
			
			//store element number of each table column in a map
			String tablePath = "//table[@lat-table='vm.cfhGrid']/tbody/tr/td[";
			HashMap<String,String> tableMap = new HashMap<String,String>();		
			tableMap.put("date open", "1");
			tableMap.put("status", "2");
			tableMap.put("date closed", "3");
			
			HashMap<String,String> careMap = new HashMap<String,String>();	
			careMap.put("care type", "//input[@name='careTypeCode']");
			careMap.put("financial hardship", "//input[@name='hardshipType']");
			
					
			int pageCount = 0;
			boolean match = false; // boolean to hold the matchstatus
			//boolean loop = true; 
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
					driver.findElement(By.xpath("("+tablePath+ "1])["+row+"]")).click();
					for (String key : tableMap.keySet()) { //check the table fields for a match
						WebElement tableField = driver.findElement(By.xpath("("+tablePath+ tableMap.get(key) +"])["+row+"]"));
						Logging.logToConsole("INFO",logEntryPrefix+" Key: " + key);
						Logging.logToConsole("INFO",logEntryPrefix+" Table text: " + tableField.getText());
						Logging.logToConsole("INFO",logEntryPrefix+" pramsMap value: " + paramsMap.get(key));
						
						try {
						//String temp = paramsMap.get(key);
						if(paramsMap.containsKey(key) == false) {continue;}// don't need to check that value so skip
						if(paramsMap.get(key).equals(tableField.getText())) {continue;}// if a match or no value then skip to the next loop/value to check
						}catch(Exception E) {}
						match = false;
						break;
						}
					
					if(match) {
						for (String key : careMap.keySet()) { // check the non table fields for a match
							if(paramsMap.get(key) == null) {continue;}
							String value = driver.findElement(By.xpath(careMap.get(key))).getAttribute("value");
							Logging.logToConsole("INFO",logEntryPrefix+" Key: " + key);
							Logging.logToConsole("INFO",logEntryPrefix+" Field text: " + value);
							Logging.logToConsole("INFO",logEntryPrefix+" pramsMap value: " + paramsMap.get(key));
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
	}
	
	
	public HashMap<String, String> careAndHardship(HashMap<String,String> paramsMap, String action) throws Exception  {
			String account = pageUtils.testMap.get("account");
			logEntryPrefix= "Account: "+account+" DesktopVulnerabilites/Care&Hardship/"+action+": "; 
			try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start"+ action);
				pageUtils.defaultImplictWait();
				//Select dropdown;

				OpenCareAndHardshipPanel();
				WebElement inputWebElement; // temp element to hold values in case statement
				String holdDaysXpath = "//input[@name='holdDays']";
				if (action.equals("add")) {
					//add button may be disabled and the care and hardship panel does not always open correctly first time so the mess below id to handle that 
					pageUtils.implictWait(0);
					int sleep = 300;
					for(int i=0;i<3 ;i++) {
						careAndHardshipAddButton(logEntryPrefix);
						try {
						// panel sometimes does not initilise properly
						Thread.sleep(sleep);
						inputWebElement = driver.findElement(By.xpath(holdDaysXpath));
						inputWebElement.clear();
						inputWebElement.sendKeys("0");
						break;
						}
						catch (Exception e){
							Logging.logToConsole("ERROR",logEntryPrefix + ":  unable to open Add care record panel ERROR retry");
							try {
								driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
								}catch (Exception f){}
							sleep = sleep + 200;
							}
						}
				}
				pageUtils.implictWait(1);
				
				// order in which the data should be entered
				String[] entryOrder = {"have consent","care type","financial hardship","confirmed care","times in care","care proof required",
						"care proof requested","care proof received","financial proof required","financial proof requested",
						"financial proof received","hold days","hold days approved","expiration date","status","closed date","comments","braile",
						"large type","audio file","prison name","prison number","sentence date","release date","prison informant"};	
				
				//iterate through the keys that have a value and execute them in correct entry order

				WebElement button = driver.findElement(By.xpath("//input[@name='careTypeCode']"));
				String xpathVar = "newCare";
				if(action.equals("add")){
					button = driver.findElement(By.xpath("//button[@ng-click='vm.add()']"));}
					else if (action.equals("check")){}
						else {//update
							driver.findElement(By.xpath("//button[@ng-click='vm.editCareRecord()']")).click();
							button = driver.findElement(By.xpath("//button[@ng-click='vm.update()']"));
							xpathVar = "toUpdate";
							}
				HashMap<String,String> resultsMap = new HashMap<String, String>();
				//Boolean isSelected;
				for (String i : entryOrder) {
					String value = paramsMap.get(i);
					if (value != null )
					{ 
					//Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value);	
					switch (i) {
						case "have consent":
							if(action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@name='consent']"));	
							}else {
								pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".consent']");
								}
						 break; 
						case "care type":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@name='careTypeCode']")).getAttribute("value"));
							}else {//ok
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break; 
						case "financial hardship":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@name='hardshipType']")).getAttribute("value"));								
							}else {
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='hardshipType']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break; 
						case "confirmed care":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.confirmed']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".confirmed']");
									}
						 break; 
						case "times in care":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@ng-model='vm.timesInCare']")).getAttribute("value"));	
								}
						 break; 
						case "care proof required":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.currentCareType.proofRequired']"));		
							}
						 break;						 
						case "care proof requested":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.careProofRequested']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".careProofRequested']");
									}
						 break; 							
						case "care proof received":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.careProofRequested']"));	
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".careProofReceived']");
									}
						 break; 
						case "financial proof required":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.careProofRequested']"));		
							}
						 
						 break;
						case "financial proof requested":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.careProofRequested']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".hardshipProofRequested']");
									}
						 break; 
						case "financial proof received":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.careProofReceived']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".hardshipProofReceived']");
									}
						 break; 
						case "hold days":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath(
										"(//div[@ng-show='vm.selectedRow.confirmed']//div[@class='controls']/input)[1]")).getAttribute("value"));		
							}else {//ok
								inputWebElement =driver.findElement(By.xpath(holdDaysXpath));
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
									}
						 break; 
						case "hold days approved":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox(
										"(//div[@ng-show='vm.selectedRow.confirmed']//div[@class='controls']/input)[2]"));		
							}
						 break; 
						case "expiration date":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.holdExpirationDate']")).getAttribute("value"));		
							}
						 break; 
						case "status":
							if(action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.status']")).getAttribute("value"));		
							}else {//OK
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='Status']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break;
						case "closed date":
							if (action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.closedDate']")).getAttribute("value"));		
							}
						 break; 
						case "comments":
							if(action.equals("check")) {
								resultsMap.put(i,driver.findElement(By.xpath("//textarea[@ng-model='vm.selectedRow.comment']")).getAttribute("value"));		
							}else if (action.equals("add")) {
								driver.findElement(By.xpath("//textarea[@ng-model='vm."+xpathVar+".Comment']")).sendKeys(value);
								}else {//f**k up by Genysis. "Comment" in the add box but "comment" in the update box :o(
									driver.findElement(By.xpath("//textarea[@ng-model='vm."+xpathVar+".comment']")).sendKeys("\n "+ value);
									}
						 break; 
						case "braile":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.braille']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".braille']");
									}
						 break; 
						case "large type":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.largePrint']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".largePrint']");
									}
						 break; 
						case "audio file":
							if (action.equals("check")) {
								resultsMap.put(i,pageUtils.checkCheckBox("//input[@ng-model='vm.selectedRow.audioFiles']"));		
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".audioFiles']");
									}
						 break; 
						case "prison name":
							inputWebElement = driver.findElement(By.xpath("(//div[@id='prison-section']/div/div/div/input)[1]"));
							if(action.equals("check")) {
								resultsMap.put(i,inputWebElement.getAttribute("value"));		
							}else {
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
								}
						 break; 
						case "prison number":
							inputWebElement = driver.findElement(By.xpath("(//div[@id='prison-section']/div/div/div/input)[2]"));
							if(action.equals("check")) {
								resultsMap.put(i,inputWebElement.getAttribute("value"));		
							}else {
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
								}
						 break; 
						case "sentence date":
							inputWebElement = driver.findElement(By.xpath("(//div[@id='prison-section']/div/div/div/input)[3]"));
							if(action.equals("check")) {
								resultsMap.put(i,inputWebElement.getAttribute("value"));		
							}else {
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
								}
						 break; 
						case "release date":
							inputWebElement = driver.findElement(By.xpath("(//div[@id='prison-section']/div/div/div/input)[4]"));
							if(action.equals("check")) {
								resultsMap.put(i,inputWebElement.getAttribute("value"));		
							}else {
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
								}
						 break; 
						case "prison informant":
							inputWebElement = driver.findElement(By.xpath("(//div[@id='prison-section']/div/div/div/input)[5]"));
							if(action.equals("check")) {
								resultsMap.put(i,inputWebElement.getAttribute("value"));		
							}else {
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
								}
						 break; 

					
						}
					}
				}

							
				if (!action.equals("check")) {
						button.click();
						Logging.logToConsole("INFO",logEntryPrefix+" care record "+action+"ed");
					}
				
				driver.switchTo().defaultContent();
				return resultsMap;
				
			}catch (Exception e) { 
				driver.findElement(By.xpath("//button[@ng-click='vm.close()']")).click();
				driver.switchTo().defaultContent();
				throw new Exception(logEntryPrefix+" unable to "+action+" care record."+ "\n" + e );
				
				}	
		}
		

	
	public void careAndHardshipAddButton(String logEntryPrefix) throws Exception {
		for(int i=0; i<5 ;i++) {
			try {
				driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
				return;
				}catch (Exception e){
					Logging.logToConsole("ERROR",logEntryPrefix + ":  unable to click add button retrying");
					Thread.sleep(200);}
			}
		throw new Exception(logEntryPrefix + ":  ERROR <add> button disabled");
	}
	
	
	public void OpenCareAndHardshipPanel() throws InterruptedException {
		Logging.logToConsole("INFO","DesktopVulnerabilites/OpenCareAndHardshipPanel: Start");
		pageUtils.closeAnchorPanel();
		pageUtils.ReturnHome();
		driver.findElement(By.xpath("//div[contains(text(),'Care-Financial Hardship')]")).click();//open arrangement panel

	}
	
	public void NewCareAndHardship(String careType, String harshipType) throws Exception {
		Logging.logToConsole("INFO","DesktopVulnerabilites/NewCareAndHardshipPanel: Start");
		pageUtils.closeAnchorPanel();
		// dialogue does not always initialise the first time so the loop is to work around that
		for(int i=1;i<3 ;i++) {
		 WebElement buttonAdd = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
			if(!buttonAdd.isEnabled()) {
				throw new Exception("DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+":  ERROR <add> button disabled");
			}
		try {
		buttonAdd.click();//open arrangement panel
		driver.findElement(By.xpath("//input[@ng-model='vm.newCare.consent']")).click();//check consent check box
		Thread.sleep(300);
		Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
		careTypeDropdown.selectByVisibleText(careType);
		Select hardshipDropdown =  new Select(driver.findElement(By.xpath("//select[@name='hardshipType']")));
		hardshipDropdown.selectByVisibleText(harshipType);
		driver.findElement(By.xpath("//input[@name='vm.newCare.confirmed']")).click();//check confirmed care check box
		break;
		}
		catch (Exception e){
			Logging.logToConsole("ERROR","DesktopVulnerabilites/NewCareAndHardshipPanel:  ERROR retry");
			driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
			Thread.sleep(500);
		}
		}
		
	}
	
	public String CareHoldDays(String SetHoldDays) throws Exception {		
		Logging.logToConsole("INFO","DesktopVulnerabilites/CareHoldDays: Start");
		try {
			String actualHoldDays = driver.findElement(By.xpath("//input[@name='holdDays']")).getAttribute("value");//hold days
			//det the actual hold days to the required value
			if (!SetHoldDays.equalsIgnoreCase("default")) {
				driver.findElement(By.xpath("//input[@name='holdDays']")).clear();
				driver.findElement(By.xpath("//input[@name='holdDays']")).sendKeys(SetHoldDays);
			}
			return actualHoldDays;
		}
		catch (Exception e){
			throw new Exception("DesktopVulnerabilites/CareHoldDays/"+pageUtils.testMap.get("account")+": hold days ERROR (possible invalid care type");
		}
	}
	
	public boolean SubmitCareAndHardshipRecord() throws InterruptedException {		
		Logging.logToConsole("INFO","DesktopVulnerabilites/SubmitCareAndHardshipRecord:Start");
		WebElement addbutton = driver.findElement(By.xpath("//div[@class='modal-footer lat-form-idle']//button[contains(text(),'Add')]"));
		boolean editEnabled = driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).isEnabled();
		for(int i=0; i<10; i++) {
			try {
				addbutton.click();	
				}catch(Exception e) {
					Logging.logToConsole("INFO",
						"DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+": add new Errored on attempt "+ (i+1) );
					}
			try {
				if (editEnabled ) {return true;}
				}catch(Exception e) {
					Logging.logToConsole("INFO",
					"DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+": submit new Errored on attempt "+ (i+1) );
				}
			Thread.sleep(100);
		}
		return false;
		
	
	}
	

}
