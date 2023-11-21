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


public class DesktopComplaints {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopComplaints(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public HashMap<String, String> complaints(HashMap<String,String> paramsMap, String action) throws InterruptedException  {
		Logging.logToConsole("INFO","DesktopComplaints/complaints: "+action+" Complaint");

		//scroll the screen to the section with the DMC
		pageUtils.Scroll(300);
		
		//Click the Complaints button
		driver.findElement(By.xpath("//complaints-menu[@class='ng-scope ng-isolate-scope']/div")).click();
		
		
		// order in which the data should be entered
		String[] entryOrder = {"id", "document", "owner","status","category","type", "disatisfaction", "grievances", 
				"date received", "priority","sla days remaining","sla days", "date processed", "referred By","Complaint Details", 
				"complaint against type", "complaint against", "investigation to date", 
				"complaint outcome", "root cause", "conclusion", "complaint justified", "recourse date", "compensation amount" };	
		
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {
			lowercaseParamsMap.put(key.toLowerCase(), value);
		});
		Boolean check = false;
		if (action.equalsIgnoreCase("check")) {check = true;};
		
		//driver.switchTo().frame(driver.findElement(By.id("DebtManagement21")));
				Thread.sleep(1000);

				if(action.equalsIgnoreCase("add"))	{	
					//Thread.sleep(1000);
					driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();	
					action = "newComplaint";
				}
				else {
					
					List<WebElement> complaintIDs = driver.findElements(By.xpath("//table[@lat-table='vm.ComplaintsGrid']/tbody/tr/td[1]"));
					List<WebElement> closed = driver.findElements(By.xpath("//table[@lat-table='vm.ComplaintsGrid']/tbody/tr/td[1]"));
					List<WebElement> categories = driver.findElements(By.xpath("//table[@lat-table='vm.ComplaintsGrid']/tbody/tr/td[5]"));
					String complaintKey = lowercaseParamsMap.get("Complaint key").toLowerCase();
					int complaintKeyNumber = 0;
					if(complaintKey != null) {// there is a Complaint key value
						try {
							// if the Complaint key value is a number then shift to a new integer variable
						 complaintKeyNumber = Integer.parseInt(complaintKey);
						 complaintKey = "number";
						}catch(Exception e) {}
						
						switch (complaintKey) {
						
						case "first":	
							//get the first item in the list and click it
							complaintIDs.get(0).click();
							break;
						case "last":	
							//get the last item in the list and click it
							complaintIDs.get(complaintIDs.size()-1).click();
							break;							
						case "number":	
							//get numbered item in the list starting from the top
							complaintIDs.get(complaintKeyNumber -1).click();
							break;
						case "complaint id":	
							//get the item in the list of Complaint ID's with a Complaint ID that matches the one input 
							String complaintID = lowercaseParamsMap.get("complaint id");
							for (int i = 0;i<complaintIDs.size();i++) {
								//String tempid = ComplaintIDs.get(i).getText();
								if (complaintID.equals(complaintIDs.get(i).getText())) {
									complaintIDs.get(i).click();
									}
								}
							break;	
						case "category":	
							//get the item in the list of Complaints with a category matching the one input
							String category = lowercaseParamsMap.get("category");
							for (int i = 0;i<categories.size();i++) {
								//String tempid = ComplaintIDs.get(i).getText();
								if (category.equals(categories.get(i).getText())) {
									categories.get(i).click();
									}
								}
							break;	
											}
							}
					
					}
				
				if(action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("update") ){
					action = "editRecord";
					driver.findElement(By.xpath("//button[@class='btn ng-binding'][normalize-space()='Edit']")).click();		
					}
		
		
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
			
			//String[] entryOrder = {"id", "document", "owner","status","category","type", "disatisfaction", "grievances", 
					//"date received", "priority","sla days remaining","sla days", "date processed", "referred By","Complaint Details", 
					//"complaint against type", "complaint against", "investigation to date", 
					//"complaint outcome", "root cause", "conclusion", "complaint justified", "recourse date", "compensation amount" };
			
			case "id":
				if (check){				
				inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.complaintId']"));
				resultsMap.put(i,inputWebElement.getAttribute("value"));  
				}

				break;
			case "document":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='documentSelect']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+".documentId']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "owner":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.owner']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='owner']")));
							dropdown.selectByVisibleText(value);
							}
				break;
			case "status":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='complaintStatus']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='complaintStatus']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "Complaint key":	
				//dummy value just needs returning to avoid assertion failure
				resultsMap.put(i,value);
				break;
			case "Complaint relates to":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='ComplaintAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "Complaint details":	
				if (check){
					inputWebElement = driver.findElement(By.xpath("//textarea[@ng-model='vm.selectedRow.details']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							driver.findElement(By.xpath("//textarea[@ng-model='vm."+action+"Record.details']")).sendKeys(value);
									}
				break;
			case "Complaint id":	
				inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.ComplaintId']"));
				resultsMap.put(i,inputWebElement.getAttribute("value"));  
				break;	
			case "Complaint type":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentComplaintType.description']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='ComplaintType']")));
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
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='ComplaintJustified']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.justified']")));
							dropdown.selectByVisibleText(value);
									}
				break;	
			case "outcome":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='ComplaintOutcome']")));	
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
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentComplaintType.proofRequired']"));
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
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='ComplaintReferredBy']")));	
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
		switch (action.toLowerCase()) {
		case "add":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopComplaint/Complaint: New Complaint submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddComplaint: unable to add Complaint: " + action + " button disabled");}
			break;
		case "edit":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopComplaint/Complaint: updated Complaint submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/Complaint: unable to update Complaint: " + action + " button disabled");}
			break;
		case "close":
			driver.findElement(By.xpath("//button[normalize-space()='Close Complaint']")).click();
			
			break;
		case "reopen":
			driver.findElement(By.xpath("//button[normalize-space()='ReOpen']")).click();
			break;	
			}
		
		//driver.switchTo().defaultContent(); //switch out of iframe
		pageUtils.ReturnHome();
		
		return resultsMap;
	}	
	
	
	
	
	
	
	

}
