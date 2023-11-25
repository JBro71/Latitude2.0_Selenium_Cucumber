package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopCommunications {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopCommunications(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
		
		
	public HashMap<String, String> communicationHistory(HashMap<String,String> paramsMap) throws InterruptedException  {
		Logging.logToConsole("INFO","DesktopCommunications/communications: Start");

		//scroll the screen to the section with the DMC
		pageUtils.Scroll(400);
		
		// don't know if the menu is already open or not so just in case it is not
		for (int i = 0; i<4;i++) {
			//Click the communications button
			try {
			driver.findElement(By.xpath("(//ng-switch[@on='vm.menuOpen'])[2]")).click();
			pageUtils.ImplictWait(0);
			driver.findElement(By.xpath("//div[normalize-space()='Communication History']")).click();
			}
			catch (Exception E) {Thread.sleep(500);}
			
		}
		pageUtils.DefaultImplictWait();
		

		
		// Pages
		
		WebElement buttonPrevious =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Previous']"));
		//WebElement buttonPrevious =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][1]")); //alternative xpath
		WebElement button1 =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][1]/following-sibling::li[1]"));
		
		//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='1'] alternative path
		//WebElement buttonNext =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][2]"));
		WebElement buttonNext =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Next']"));
		WebElement buttonLast = driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][2]/preceding-sibling::li[1]"));
				//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Next']/parent::li/preceding-sibling::li[1]
		
		
		//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']   //pages table
		//li[@ng-if='::directionLinks'] 
		
		// go to the last page
		boolean nextEnabled = true;
		int numberOfPages = 1;
		while (nextEnabled) {
			String nextButtonState = buttonNext.getAttribute("disabled");
		if (nextButtonState == null){
			buttonLast.click();
			}
		else {
			nextEnabled = false; 
		}
		numberOfPages = Integer.parseInt(buttonLast.getText());
		Logging.logToConsole("INFO","DesktopCommunications/communications:Number of pages = "+numberOfPages );
		}
		
		
		
		pageUtils.ImplictWait(0);
		
		Thread.sleep(1);
		String tablePath = "//table[@lat-table='vm.letterHistoryGrid']/tbody//tr/td[";
		/*
		HashMap<String,List<WebElement>> tableMap = new HashMap<String,List<WebElement>>();		
		//all the table elements
		
		tableMap.put("method", driver.findElements(By.xpath(tablePath+"2]")));
		tableMap.put("type",  driver.findElements(By.xpath(tablePath+"3]")));
		tableMap.put("code",driver.findElements(By.xpath(tablePath+"4]")));
		tableMap.put("description", driver.findElements(By.xpath(tablePath+"5]")));
		tableMap.put("date requested", driver.findElements(By.xpath(tablePath+"6]")));
		tableMap.put("date sent", driver.findElements(By.xpath(tablePath+"7]")));
		tableMap.put("sender", driver.findElements(By.xpath(tablePath+"8]")));
		tableMap.put("recipent", driver.findElements(By.xpath(tablePath+"9]")));
		tableMap.put("subject d",driver.findElements(By.xpath(tablePath+"10]")));
		 */
		
		
		/*
		String[] tableHeadings = {"method","type","code", "description", "date requested", "date sent", "date sent", "sender", "recipent", "subject d"};
		// iterate through all of the table headings i.e. columns
		for( int i = 0; i < tableHeadings.length - 1; i++)
		{
			// for each column get the list of web elements for the current page and store in the table Map
		    tableMap.put(tableHeadings[i],driver.findElements(By.xpath(tablePath+ i+2+"]"))); 
			//Logging.logToConsole("DEBUG","DesktopCommunications/communications: Page: " + tableHeadings[i]);
		}
		 List<WebElement> a = driver.findElements(By.xpath(tablePath+ 2+"]"));
		 WebElement b = a.get(1);
		 b.click();
		 String c = b.getText();
		 //String b = a.get(0).getText();
		 */
		HashMap<String,Integer> tableMap = new HashMap<String,Integer>();		
		tableMap.put("method", 2);
		tableMap.put("code",  3);
		tableMap.put("type",4);
		tableMap.put("description", 5);
		tableMap.put("date requested", 6);
		tableMap.put("date sent", 7);
		tableMap.put("sender", 8);
		tableMap.put("recipent", 9);
		tableMap.put("subject",10);
		
		Logging.logToConsole("DEBUG","DesktopCommunications/communications: Page: tablepath complete");
		
		
		boolean match = false;

				int page = 0;
				int j = 0;
				while (!match) {
				Logging.logToConsole("DEBUG","DesktopCommunications/communications: Page: last-"+ Integer.toString(j));
				int size = driver.findElements(By.xpath(tablePath+"2]")).size();
				// go through each row in the table on the current page until a match is found or we run out of rows
				for(int row = size-1; row>=0; row--)	{
				Logging.logToConsole("DEBUG","DesktopCommunications/communications: Row: "+ row);
				//loop through each entry in the input parameters map and see if it matches the current table row selected
				for (Map.Entry<String, String> mapEntry :paramsMap.entrySet()) {
					String key = mapEntry.getKey().toLowerCase();
					Logging.logToConsole("DEBUG","DesktopCommunications/communications: column: "+ key);
					Logging.logToConsole("DEBUG","DesktopCommunications/communications: column input value: "+ mapEntry.getValue());
					Logging.logToConsole("DEBUG","DesktopCommunications/communications: tableMap.get(key): "+ tableMap.get(key));
					
					int x = tableMap.get(key);
					List<WebElement> a = driver.findElements(By.xpath(tablePath+ x +"]"));
					WebElement b = a.get(row);
					String c = b.getText();
					
					if(!mapEntry.getValue().equalsIgnoreCase(c)) {
						match = false;
						break;
					}
					match = true;

					Logging.logToConsole("DEBUG","DesktopCommunications/communications: Match Found");
				}
				if(match) {
					
					break;
					}
			} //row loop
			
				//if the previous button is enabled click to go back a page
				if (buttonPrevious.getAttribute("disabled") == null)
					{
						buttonPrevious.click();
						// refresh the map of elements in the table (not all pages the same size
						//for( int i = 0; i < tableHeadings.length - 1; i++)
							//{
								// for each column get the list of web elements for the current page and store in the table Map
							   // tableMap.put(tableHeadings[i],driver.findElements(By.xpath(tablePath+ i+2+"]"))); 
							//}
					}
					//no more pages so break the loop
					else {break;}
			
				} //while loop

		
		
		
		
		
		
		// order in which the data should be entered
		
		/*
		
					String communicationKey = lowercaseParamsMap.get("communication key").toLowerCase();
					int communicationKeyNumber = 0;
					if(communicationKey != null) {// there is a communication key value
						try {
							// if the communication key value is a number then shift to a new integer variable
						 communicationKeyNumber = Integer.parseInt(communicationKey);
						 communicationKey = "number";
						}catch(Exception e) {}
						
						switch (communicationKey) {
						
						case "first":	
							//get the first item in the list and click it
							//communicationIDs.get(0).click();
							break;
						case "last":	
							//get the last item in the list and click it
							//communicationIDs.get(communicationIDs.size()-1).click();
							break;							

											}
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
			
			case "category":
				if (check){				
				dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationCategory']")));	
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
			case "communication against":
				
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "communication key":	
				//dummy value just needs returning to avoid assertion failure
				resultsMap.put(i,value);
				break;
			case "communication relates to":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationAgainst']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.against']")));
							dropdown.selectByVisibleText(value);
							}
				break;	
			case "communication details":	
				if (check){
					inputWebElement = driver.findElement(By.xpath("//textarea[@ng-model='vm.selectedRow.details']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							driver.findElement(By.xpath("//textarea[@ng-model='vm."+action+"Record.details']")).sendKeys(value);
									}
				break;
			case "communication id":	
				inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.selectedRow.communicationId']"));
				resultsMap.put(i,inputWebElement.getAttribute("value"));  
				break;	
			case "communication type":
				if (check){
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentCommunicationType.description']"));
					resultsMap.put(i,inputWebElement.getAttribute("value"));  
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationType']")));
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
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationJustified']")));	
					resultsMap.put(i,dropdown.getFirstSelectedOption().getText());
						}
						else {
							dropdown = new Select(driver.findElement(By.xpath("//select[@ng-model='vm."+action+"Record.justified']")));
							dropdown.selectByVisibleText(value);
									}
				break;	
			case "outcome":
				if (check){
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationOutcome']")));	
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
					inputWebElement = driver.findElement(By.xpath("//input[@ng-model='vm.currentCommunicationType.proofRequired']"));
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
					dropdown = new Select(driver.findElement(By.xpath("//select[@name='communicationReferredBy']")));	
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
			Logging.logToConsole("INFO","DesktopCommunication/communication: New communication submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/AddCommunication: unable to add Communication: " + action + " button disabled");}
			break;
		case "edit":
			button = driver.findElement(By.xpath("//button[@type='button'][normalize-space()='Save']"));
			if (button.isEnabled() == true) {button.click();
			Logging.logToConsole("INFO","DesktopCommunication/communication: updated communication submitted");}
				else { Logging.logToConsole("WARNING","DesktopDMC/Communication: unable to update Communication: " + action + " button disabled");}
			break;
		case "close":
			driver.findElement(By.xpath("//button[normalize-space()='Close Communication']")).click();
			
			break;
		case "reopen":
			driver.findElement(By.xpath("//button[normalize-space()='ReOpen']")).click();
			break;	
			}
		
		//driver.switchTo().defaultContent(); //switch out of iframe
		pageUtils.ReturnHome();
		
		*/
		return resultsMap;
		
		
	}	
	
	
	
	
	

}
