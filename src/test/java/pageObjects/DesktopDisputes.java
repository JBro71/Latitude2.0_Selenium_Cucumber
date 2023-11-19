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
	
	
	public HashMap<String, String> disputes(HashMap<String,String> paramsMap, String action) throws InterruptedException  {
		Logging.logToConsole("INFO","DesktopDisputes/disputes: "+action+" Dispute");

		//scroll the screen to the section with the DMC
		pageUtils.Scroll(300);
		
		//Click the disputes button
		driver.findElement(By.xpath("//div[contains(text(),'Disputes')]")).click();
		
		//driver.switchTo().frame(driver.findElement(By.id("DebtManagement21")));
		Thread.sleep(1000);
		if(action.equalsIgnoreCase("add"))	{	
			//Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@class='btn ng-binding'][normalize-space()='Add']")).click();	
		}
		// order in which the data should be entered
		String[] entryOrder = {"dispute id", "document", "dispute type","date received","date closed","dispute against", "dispute relates to", "category", 
				"referred by", "justified","dispute details","outcome", "proof required", "proof requested","insufficient proof received", "proof received" };	
		
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {
			lowercaseParamsMap.put(key.toLowerCase(), value);
		});
		Boolean check = false;
		if (action.equalsIgnoreCase("check")) {check = true;};
		
		//iterate through the keys that have a value and execute them in correct entry order
		WebElement inputWebElement; // temp element to hold values in case statement
		Select dropdown;
		Boolean isSelected;
		for (String i : entryOrder) {
			String value = lowercaseParamsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", "DesktopDMC/DMC: DMC Key: " +i+ " Value: "+ value);	
			switch (i) {
			
			case "category":
				if (check){				
				dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeCategory']")));	
				resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
				}
				else {
				dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.category']")));
				dropdown.selectByVisibleText(value);
				}
				break;
			case "date received":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.dateReceived']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.addRecord.dateReceived']"));
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
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "dispute relates to":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "dispute details":	
				if (check){
					inputWebElement = driver.findElement(By.xpath("//textarea[@ng-model='vm.selectedRow.details']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							driver.findElement(By.xpath("//textarea[@ng-disabled='!vm.getPermissions().editDisputeDetails']")).sendKeys(value);
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
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.addRecord.insufficientProofReceived']"));
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
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.justified']")));
							dropdown.selectByVisibleText(value);
									}
				break;	
			case "outcome":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='disputeOutcome']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.outcome']")));
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
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.addRecord.proofReceived']"));
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
						inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.addRecord.proofRequested']"));
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
						dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm.addRecord.referredBy']")));
						dropdown.selectByVisibleText(value);
								}
				break;
				}
			}
		}
		
		WebElement button;
		switch (action.toLowerCase()) {
		case "add":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDispute/dispute: New dispute submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddDispute: unable to add Dispute: " + action + " button disabled");}
			break;
		case "update":
			button = driver.findElement(By.xpath("//button[@id='submitButton']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopDispute/Dispute: Dispute updated");}
				else { Logging.logToConsole("WARNING","DesktopDispute/Dispute: unable to update Dispute: " + action + " button disabled");}
			break;
		case "delete":
			disputeDelete();
			break;
		case "cancel":
			button = driver.findElement(By.xpath("//button[@id='cancelButton']"));
			if (button.isEnabled() == true) {button.click();}
				else { Logging.logToConsole("WARNING","DesktopDMC/dmc: unable to click DMC: " + action + " button disabled");}
			break;	
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
			//driver.switchTo().defaultContent(); //switch out of iframe
			//driver.switchTo().frame(driver.findElement(By.id("deleteModal___BV_modal_content_")));
			driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
			Logging.logToConsole("INFO","DesktopDMC/AddDMC: Existing DMC deleted");
			Thread.sleep(2000);
			//driver.switchTo().frame(driver.findElement(By.id("DebtManagement29")));
			}
			
	}
	
	
	
	
	

}
