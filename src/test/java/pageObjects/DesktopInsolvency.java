package pageObjects;


import java.util.Arrays;
import java.util.HashMap;
//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import testComponents.PageUtils;
import utils.Logging;

public class DesktopInsolvency {
		
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	String[] months = {"January", "February", "March", "April", "May", "June", 
			"July", "August", "September","October","November","December"};
		
		public DesktopInsolvency(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
		
		
		public HashMap<String,String> insolvency(HashMap<String,String> paramsMap, String action) throws Exception  {
			
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+"DesktopInsolvency/Insolvency/"+action+": ";
		
			Logging.logToConsole("INFO",logEntryPrefix + "start");

			pageUtils.ReturnHome();
			pageUtils.openLowerPanel("Bankruptcy & Insolvency");
			boolean check = false;
			if (action.equalsIgnoreCase("check")){check = true;}
			
			try {//catch an error while in the iframe so that we are not left stuck in it which will screw up the next test.
				driver.switchTo().frame(driver.findElement(By.xpath("//custom-reference-panel-with-url[@class='ng-scope']/iframe")));				// scroll to the bottom of the page
				for (int i=0;i<20;i++) {
					try {	
						pageUtils.implictWait(0);
						Logging.logToConsole("DEBUG",logEntryPrefix + "scroll 200");
						js.executeScript("window.scrollBy(0,200)");
						driver.switchTo().frame(driver.findElement(By.xpath("//custom-reference-panel-with-url[@class='ng-scope']/iframe")));
						driver.findElement(By.xpath("//textarea[@id='comment']")).click();
						//driver.findElement(By.xpath(buttonSaveStr+"/parent::div")).click();
						driver.switchTo().defaultContent();
						js.executeScript("window.scrollBy(0,200)");
						break;
					}catch (Exception e){
						Logging.logToConsole("DEBUG",logEntryPrefix + "element not found" + e);
						Thread.sleep(50);
						driver.switchTo().defaultContent();}
				}

				pageUtils.defaultImplictWait();
				driver.switchTo().frame(driver.findElement(By.xpath("//custom-reference-panel-with-url[@class='ng-scope']/iframe")));
				
				// order in which the data should be entered
				String[] entryOrder = {"party", "insolvency type","start date","expiration date", "insolvency date", "indefinite discharge",
						"discharged date", "iva failed date", "dro date","dro schedule received","dro amount","courts",
						"insolvency practitioner","status","court reference number","home owner","legal action required",
						"moratorium period expired","comment"};	
		
				//iterate through the keys that have a value and execute them in correct entry order
				WebElement inputWebElement; // temp element to hold values in case statement
				Select dropdown ;
				String xpathStr;
				HashMap<String,String> resultsMap = new HashMap<String, String>();
				for (String i : entryOrder) {
					String value = paramsMap.get(i);
					if (value != null )
						{ 
						Logging.logToConsole("DEBUG", logEntryPrefix+ " insolvency Key: '" +i+ "' Value: "+ value);	
						switch (i) {
							case "party":
								inputWebElement = driver.findElement(By.xpath("//select[@id='selectDebtor']"));
								if (inputWebElement.isDisplayed()) {
									if(check) {
									resultsMap.put(i,new Select(inputWebElement).getFirstSelectedOption().getText());	
										}else {
											new Select(inputWebElement).selectByVisibleText(value);
											}
								}
								break;
								
							case "insolvency type":
								dropdown = new Select(driver.findElement(By.xpath("//select[@id='insolvency-type']")));
								if(check) {
									resultsMap.put(i,dropdown.getFirstSelectedOption().getText());	
									}else {
										dropdown.selectByVisibleText(value);
										}
								break;
								
							case "start date":
								inputWebElement = driver.findElement(By.xpath("//label[@id='start-date__value_']"));
								if(check) {
									String dateString = dateUnpicker(inputWebElement.getText());
									resultsMap.put(i,dateString); //getAttribute("value"));	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "expiration date":
								inputWebElement = driver.findElement(By.xpath("//button[@id='expiration-date']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "insolvency date":
								inputWebElement = driver.findElement(By.xpath("//button[@id='insolvency-date']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "discharged date":
								inputWebElement = driver.findElement(By.xpath("(//button[@id='discharged-date'])[1]"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "iva failed date":
								inputWebElement = driver.findElement(By.xpath("//button[@id='iva-failed-date']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "dro date":
								inputWebElement = driver.findElement(By.xpath("//button[@id='dro-date']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "dro schedule received":
								inputWebElement = driver.findElement(By.xpath("//button[@id='dro-schedule-received']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.click(); //click on calendar
										datePicker(value);									
										}
								break;
								
							case "dro amount":
								inputWebElement=driver.findElement(By.xpath("//input[@id='dro-amount']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.clear();
										inputWebElement.sendKeys(value); 								
										}
								break;
								
							case "courts":
								dropdown = new Select(driver.findElement(By.xpath("//select[@id='courts']")));
								if(check) {
									resultsMap.put(i,dropdown.getFirstSelectedOption().getText());	
									}else {
										dropdown.selectByVisibleText(value);
										}
								break;
							case "insolvency practitioner":
								dropdown = new Select(driver.findElement(By.xpath("//select[@id='insolvency-practioner']")));
								if(check) {
									resultsMap.put(i,dropdown.getFirstSelectedOption().getText());	
									}else {
										dropdown.selectByVisibleText(value);
										}
								break;
							case "status":
								dropdown = new Select(driver.findElement(By.xpath("//select[@id='status']")));
								if(check) {
									resultsMap.put(i,dropdown.getFirstSelectedOption().getText());	
									}else {
										dropdown.selectByVisibleText(value);
										}
								break;
							case "court reference number":
								inputWebElement=driver.findElement(By.xpath("//input[@id='court-reference-number']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.clear();
										inputWebElement.sendKeys(value); 								
										}
								break;
							case "indefinite discharge":
								xpathStr = "//input[@name='indefinite-discharge'][@type='checkbox']";
								if(check) {
									inputWebElement = driver.findElement(By.xpath(xpathStr));
									resultsMap.put(i,"false");							
									if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
									}else {
										pageUtils.updateCheckBox(value,xpathStr);	
										}
								break;
							case "home owner":
								xpathStr = "//input[@name='home-owner'][@type='checkbox']";
								if(check) {
									inputWebElement = driver.findElement(By.xpath(xpathStr));
									resultsMap.put(i,"false");							
									if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
									}else {
										pageUtils.updateCheckBox(value,xpathStr);	
										}
								break;
							case "legal action required":
								xpathStr = "//input[@name='legal-action-required'][@type='checkbox']";
								if(check) {
									inputWebElement = driver.findElement(By.xpath(xpathStr));
									resultsMap.put(i,"false");							
									if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
									}else {
										pageUtils.updateCheckBox(value,xpathStr);	
										}
								break;
							case "moratorium period expired":
								//input[@name='moratorium-period-expired']//following-sibling::label
								xpathStr = "//input[@name='moratorium-period-expired'][@type='checkbox']";
								if(check) {
									inputWebElement = driver.findElement(By.xpath(xpathStr));
									resultsMap.put(i,"false");							
									if(inputWebElement.isSelected()) {resultsMap.put(i,"true");} 
									}else {
										pageUtils.updateCheckBox(value,xpathStr);	
										}								
								break;
							case "comment":	
								inputWebElement=driver.findElement(By.xpath("//textarea[@id='comment']"));
								if(check) {
									resultsMap.put(i,inputWebElement.getText());	
									}else {
										inputWebElement.sendKeys(value); 								
										}
								break;	
	
							}
						}
					}
				//String buttonSaveStr = "//button[normalize-space()='Save']";
				String buttonSaveStr = "//button[@class='btn btn-primary']";
				
				switch (action.toLowerCase()) {
				case "add":
					driver.findElement(By.xpath(buttonSaveStr)).click();
					break;
				case "update":
					driver.findElement(By.xpath(buttonSaveStr)).click();
					break;
				case "check":
					//no action required
					break;
				case "delete":
					driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
					break;
					}
				
				driver.switchTo().defaultContent();
				return resultsMap;	
			}catch (Exception e) {// if there is an error switch out of the iframe to prevent screwing other tests
				driver.switchTo().defaultContent();	
				throw new Exception(logEntryPrefix + " ERROR \n"  + e);

			}
			
			
	}

		
		// select a date in the date picker popup
		public void datePicker(String targetDate) throws InterruptedException {
			//extract month and year from required date as integers
			String[] dateSplit = targetDate.split("/"); //0=day 1=month 2=year
			int inputMonthInt = Integer.parseInt(dateSplit[1]);// extract day integer
			int inputYearInt = Integer.parseInt(dateSplit[2].trim());// extract day integer
			//get date picker button elements needed to set month and year
			WebElement yearBack = driver.findElement(By.xpath("//button[@title='Previous year']"));
			WebElement yearForward = driver.findElement(By.xpath("//button[@title='Next year']"));
			WebElement monthBack = driver.findElement(By.xpath("//button[@title='Previous month']"));	
			WebElement monthForward = driver.findElement(By.xpath("//button[@title='Next month']"));	
			// date picker element that displays the month and year selected
			WebElement currentMonthYear = driver.findElement(By.xpath("//header[contains(@id,'__calendar-grid-caption_')]"));
		
			// click the year+ or year- buttons until the selected year matches the required year
			for (int x=0; x<25;x++) { //25 is a fail safe to avoid locking 
				String[] currentMonthYearString = currentMonthYear.getText().split(" ");
				int currentYearInt = Integer.parseInt(currentMonthYearString[1].trim()); // extract day integer	
				if (inputYearInt > currentYearInt) { yearForward.click();}
				else if (inputYearInt < currentYearInt) {yearBack.click();}
				else {break;}
				//Thread.sleep(200);	
			}
			
			// click the month+ or month- buttons until the selected month matches the required month
			for (int x=0; x<12;x++) {
				String[] currentMonthYearString = currentMonthYear.getText().split(" ");
				String currentMonthString = currentMonthYearString[0].trim(); 
				int currentMonthInt = Arrays.asList(months).indexOf(currentMonthString) + 1; //convert month name to a number 1-12
				if (inputMonthInt > currentMonthInt) { monthForward.click();}
				else if (inputMonthInt < currentMonthInt) {monthBack.click();}
				else {break;}
				//Thread.sleep(200);
			}
			
			//click the required day button once month and year selected
			String dateXpath = "//div[contains(@id,'__cell-"+dateSplit[2]+"-"+dateSplit[1]+"-"+dateSplit[0]+"_')]";
			driver.findElement(By.xpath(dateXpath)).click();
		}
		
		
		public String dateUnpicker(String longDateStr) throws InterruptedException {
			try { //incase string is not a valid date
			String[] longDateArray = longDateStr.split(" ");
			int currentMonthInt = Arrays.asList(months).indexOf(longDateArray[2]) + 1; //convert month name to a number 1-12
			return longDateArray[1]+"/"+ currentMonthInt +"/" + longDateArray[3];
			}catch (Exception e) {return "";}
			
		}
		
}
