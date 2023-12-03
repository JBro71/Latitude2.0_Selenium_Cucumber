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


public class DesktopDisputes {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopDisputes(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public HashMap<String, String> disputes(HashMap<String,String> paramsMap, String action) throws Exception  {
		String account = pageUtils.testMap.get("account");
		String logEntryPrefix= "Account: "+account+"DesktopDisputes/disputes/"+action+": "; 
		Logging.logToConsole("INFO",logEntryPrefix+" Start");
		pageUtils.closeAnchorPanel();
		pageUtils.ReturnHome();
		//Click the disputes button
		driver.findElement(By.xpath("//div[contains(text(),'Disputes')]")).click();
		
		// order in which the data should be entered
		String[] entryOrder = {"dispute id", "document", "dispute type","date received","date closed","dispute against", "dispute relates to", "category", 
				"referred by", "justified","dispute details","outcome", "proof required", "proof requested","insufficient proof received", "proof received" };	

		Boolean check = false;
		if (action.equalsIgnoreCase("check")) {check = true;};
		
				//Thread.sleep(1000);

				if(action.equalsIgnoreCase("add"))	{	
					//sometimes the dispute opens too quickly before the application is ready
					int sleepTime = 0;
					pageUtils.tempImplictWait(200);
					for(int i=1;i<4 ;i++) {
						 WebElement buttonAdd = driver.findElement(By.xpath("//button[@class='btn ng-binding'][normalize-space()='Add']"));
						if(!buttonAdd.isEnabled()) {
							throw new Exception(logEntryPrefix+"  ERROR <add> button disabled");
						}
						try {
							buttonAdd.click();//open add dispute panel
							pageUtils.tempImplictWait(sleepTime);
							//Thread.sleep(sleepTime);
							Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeType']")));
							dropdown.selectByVisibleText("Unknown");
							break;
							}
							catch (Exception e){
								Logging.logToConsole("ERROR",logEntryPrefix+"  ERROR add dialogue open error attempt:" +i);
								driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
								sleepTime = sleepTime + 200;
								}
							}
					pageUtils.lastImplictWait();
				}
				else {
					//WebElements disputes = driver.findElements(By.xpath("//table[@lat-table='vm.disputesGrid']/tbody/tr/td[1]"));
					List<WebElement> disputeIDs = driver.findElements(By.xpath("//table[@lat-table='vm.disputesGrid']/tbody/tr/td[1]"));
					//List<WebElement> closed = driver.findElements(By.xpath("//table[@lat-table='vm.disputesGrid']/tbody/tr/td[1]"));
					List<WebElement> categories = driver.findElements(By.xpath("//table[@lat-table='vm.disputesGrid']/tbody/tr/td[5]"));
					String disputeKey = paramsMap.get("dispute key").toLowerCase();
					int disputeKeyNumber = 0;
					if(disputeKey != null) {// there is a dispute key value
						try {
							// if the dispute key value is a number then shift to a new integer variable
						 disputeKeyNumber = Integer.parseInt(disputeKey);
						 disputeKey = "number";
						}catch(Exception e) {}
						
						switch (disputeKey) {
						
						case "first":	
							//get the first item in the list and click it
							disputeIDs.get(0).click();
							break;
						case "last":	
							//get the last item in the list and click it
							disputeIDs.get(disputeIDs.size()-1).click();
							break;							
						case "number":	
							//get numbered item in the list starting from the top
							disputeIDs.get(disputeKeyNumber -1).click();
							break;
						case "dispute id":	
							//get the item in the list of Dispute ID's with a dispute ID that matches the one input 
							String disputeID = paramsMap.get("dispute id");
							for (int i = 0;i<disputeIDs.size();i++) {
								if (disputeID.equals(disputeIDs.get(i).getText())) {
									disputeIDs.get(i).click();
									}
								}
							break;	
						case "category":	
							//get the item in the list of Disputes with a category matching the one input
							String category = paramsMap.get("category");
							for (int i = 0;i<categories.size();i++) {
								if (category.equals(categories.get(i).getText())) {
									categories.get(i).click();
									}
								}
							break;	
							}
						}
					}
				
				if(action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("update") ){
					action = "edit";
					driver.findElement(By.xpath("//button[@class='btn ng-binding'][normalize-space()='Edit']")).click();		
					}
		
		
		//iterate through the keys that have a value and execute them in correct entry order
		WebElement inputWebElement; // temp element to hold values in case statement
		Select dropdown;
		Boolean isSelected;
		for (String i : entryOrder) {
			String value = paramsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value);	
			switch (i) {
			
			case "category":
				if (check){				
				dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeCategory']")));	
				resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
				}
				else {
				dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.category']")));
				dropdown.selectByVisibleText(value);
				}
				break;
			case "date received":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.dateReceived']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm."+action+"Record.dateReceived']"));
						inputWebElement.clear();
						inputWebElement.sendKeys(value);
						}
				break;
			case "date closed":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.dateClosed']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}	
				break;
			case "dispute against":
				
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "dispute key":	
				//dummy value just needs returning to avoid assertion failure
				resultsMap.put(i,value);
				break;
			case "dispute relates to":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "dispute details":	
				if (check){
					inputWebElement = driver.findElement(By.xpath("//textarea[@ng-model='vm.selectedRow.details']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							driver.findElement(By.xpath("//textarea[@ng-model='vm."+action+"Record.details']")).sendKeys(value);
									}
				break;
			case "dispute id":	
				inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.disputeId']"));
				resultsMap.put(i,inputWebElement.getAttribute("value"));  
				break;	
			case "dispute type":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentDisputeType.description']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeType']")));
							dropdown.selectByVisibleText(value);
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
		
		WebElement button;
		try {
		switch (action.toLowerCase()) {
		case "add":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO",logEntryPrefix+" New dispute submitted");}
				else { Logging.logToConsole("WARNING",logEntryPrefix+" unable to add Dispute: " + action + " button disabled");}
			break;
		case "edit":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO",logEntryPrefix+" updated dispute submitted");}
				else { Logging.logToConsole("WARNING",logEntryPrefix+" unable to update Dispute: " + action + " button disabled");}
			break;
		case "close":
			driver.findElement(By.xpath("//button[normalize-space()='Close Dispute']")).click();
			
			break;
		case "reopen":
			driver.findElement(By.xpath("//button[normalize-space()='ReOpen']")).click();
			break;	
			}
		}catch(Exception e) {
			throw new Exception(
					 "DesktopDisputes/disputes"+ account+":  cannot " +action+ " dispute");
		}
		//driver.switchTo().defaultContent(); //switch out of iframe
		pageUtils.ReturnHome();
		
		return resultsMap;
	}	
	
	public void disputeDelete() throws InterruptedException {
		
		WebElement button = driver.findElement(By.xpath("//button[@id='deleteButton']"));
		if (button.isEnabled() == true) 
			{
			button.click();	
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
			Logging.logToConsole("INFO","DesktopDispute/dispute: Existing DMC deleted");
			Thread.sleep(2000);
			//driver.switchTo().frame(driver.findElement(By.id("DebtManagement29")));
			}
			
	}
	
	
	
	
	

}
