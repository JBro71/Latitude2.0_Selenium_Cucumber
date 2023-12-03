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
		
		public DesktopEmail(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public void email(HashMap<String,String> paramsMap) throws Exception  {
		String account = pageUtils.testMap.get("account");
		Boolean check = false;
		Logging.logToConsole("INFO","DesktopEmail/email/"+account+": start");
		String action = "add";
		//scroll the screen to the section with the Email Panel
		pageUtils.ReturnHome();
		
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {
			lowercaseParamsMap.put(key.toLowerCase(), value);
		});
		
		if(action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("update") ){
			action = "edit";	
			}

		//open the email panel
		pageUtils.Scroll(200);
		driver.findElement(By.xpath("//button[normalize-space()='Available Panels']")).click();
		//Thread.sleep(100);
		//pageUtils.ScrollBy(800);
		//Thread.sleep(500);

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
		//driver.findElement(By.xpath("//a[normalize-space()='Email']")).click();
		/*
		for(int i=0;i<10;i++) {
			try {
			driver.findElement(By.xpath("//a[normalize-space()='Email']")).click();
			}catch(Exception e) {
				//pageUtils.ScrollBy(1000);
				Thread.sleep(100);
			}
		}
*/

		
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
			String value = lowercaseParamsMap.get(i);
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
