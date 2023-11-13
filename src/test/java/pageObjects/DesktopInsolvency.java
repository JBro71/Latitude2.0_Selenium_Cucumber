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
		
		public DesktopInsolvency(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
		}
		
		
		public void Insolvency(HashMap<String,String> paramsMap, String action) throws InterruptedException  {
			Logging.logToConsole("INFO","DesktopInsolvency/AddInsolvency: "+action+" Insolvency");

			//scroll the screen to the section with the DMC
			pageUtils.ReturnHome();
			
			//Click the Debt management button then switch to iframe
			driver.findElement(By.xpath("//button[@class='dropdown-toggle']")).click();
			driver.findElement(By.xpath("//ul[@class='dropdown-menu'] //a[contains(text(),'Bankruptcy & Insolvency')]")).click();
			pageUtils.Scroll(1000);
			driver.switchTo().frame(driver.findElement(By.id("Bankruptcy&Insolvency4")));
			
			

			// order in which the data should be entered
			String[] entryOrder = {"party", "insolvency type","start date","expiration date", "insolvency date", "discharged date", 
					"iva failed date", "dro date","dro schedule received","dro amount","courts","insolvency practitioner","status",
					"court reference number","indefinite discharge","home owner","legal action required","moratorium period expired",
					"comment"};	
			
			//ensure all the keys in the map are lower case	
			HashMap<String,String> lowercaseParamsMap = new HashMap<String, String>();
			paramsMap.forEach((key, value) -> {lowercaseParamsMap.put(key.toLowerCase(), value);});
			

			//iterate through the keys that have a value and execute them in correct entry order
			WebElement inputWebElement; // temp element to hold values in case statement
			for (String i : entryOrder) {
				String value = lowercaseParamsMap.get(i);
				if (value != null )
					{ 
					Logging.logToConsole("DEBUG", "DesktopInsolvency/AddInsolvency: insolvency Key: '" +i+ "' Value: "+ value);	
					switch (i) {
						case "party":
							new Select(driver.findElement(By.xpath("//select[@id='selectDebtor']"))).selectByIndex(Integer.parseInt(value) - 1);
							break;
							
						case "insolvency type":
							Select customerDropdown = new Select(driver.findElement(By.xpath("//select[@id='insolvency-type']")));
							customerDropdown.selectByVisibleText(value);
							break;
							
						case "start date":
							driver.findElement(By.xpath("//button[@id='start-date']")).click(); //click on calendar
							DatePicker(value);
							break;
							
						case "expiration date":
							driver.findElement(By.xpath("//button[@id='expiration-date']")).click(); //click on calendar
							DatePicker(value);
							break;
						case "insolvency date":
							driver.findElement(By.xpath("//button[@id='insolvency-date']")).click(); //click on calendar
							DatePicker(value);
							break;
						case "discharged date":
							driver.findElement(By.xpath("(//button[@id='discharged-date'])[1]")).click(); //click on calendar
							DatePicker(value);
							break;
						case "iva failed date":
							driver.findElement(By.xpath("//button[@id='iva-failed-date']")).click(); //click on calendar
							DatePicker(value);
							break;
						case "dro date":
							driver.findElement(By.xpath("//button[@id='dro-date']")).click(); //click on calendar
							DatePicker(value);
							break;
						case "dro schedule received":
							driver.findElement(By.xpath("//button[@id='dro-schedule-received']")).click(); //click on calendar
							DatePicker(value);
							break;
						case "dro amount":
							inputWebElement=driver.findElement(By.xpath("//input[@id='dro-amount']"));
							inputWebElement.clear();
							inputWebElement.sendKeys(value); 
							break;
						case "courts":
							new Select(driver.findElement(By.xpath("//select[@id='courts']"))).selectByVisibleText(value);
							break;
						case "insolvency practitioner":
							inputWebElement=driver.findElement(By.xpath("//input[@id='insolvency-practioner']"));
							inputWebElement.clear();
							inputWebElement.sendKeys(value); 
							break;
						case "status":
							//driver.findElement(By.xpath("//select[@id='status']")).click();
							new Select(driver.findElement(By.xpath("//select[@id='status']"))).selectByVisibleText(value);
							break;
						case "court reference number":
							inputWebElement=driver.findElement(By.xpath("//select[@id='courts']"));
							inputWebElement.clear();
							inputWebElement.sendKeys(value); 
							break;
						case "indefinite discharge":
							Checkbox(value,"//input[@name='indefinite-discharge'][@type='checkbox']" );
							break;
						case "home owner":
							Checkbox(value,"//input[@name='home-owner'][@type='checkbox']" );
						case "legal action required":
							Checkbox(value,"//input[@name='legal-action-required'][@type='checkbox']" );
						case "moratorium period expired":
							Checkbox(value,"//input[@name='indefinite-discharge'][@type='checkbox']" );
						case "comment":	
							driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys(" New Comment: " + value);
							break;	

						}
					}
				}
		
			
	}
		public void Checkbox (String inputValue, String inputXpath) {
			//if sum of inputValue + currentState = 1 then values are either 1&0 or 0&1 so state needs to change
			WebElement checkbox = driver.findElement(By.xpath(inputXpath));
			int boxChecked = checkbox.isSelected() ? 1 : 0; //sets to 1 if checked and 0 if not
			if(Integer.parseInt(inputValue) + boxChecked == 1) {checkbox.click();}	
		}
		
		
		// select a date in the date picker popup
		public void DatePicker(String targetDate) throws InterruptedException {
			//extract month and year from required date as integers
			String[] dateSplit = targetDate.split("-"); //0=day 1=month 2=year
			int inputMonthInt = Integer.parseInt(dateSplit[1]);// extract day integer
			int inputYearInt = Integer.parseInt(dateSplit[2].trim());// extract day integer
			//get date picker button elements needed to set month and year
			WebElement yearBack = driver.findElement(By.xpath("//button[@title='Previous year']"));
			WebElement yearForward = driver.findElement(By.xpath("//button[@title='Next year']"));
			WebElement monthBack = driver.findElement(By.xpath("//button[@title='Previous month']"));	
			WebElement monthForward = driver.findElement(By.xpath("//button[@title='Next month']"));	
			// date picker element that displays the month and year selected
			WebElement currentMonthYear = driver.findElement(By.xpath("//header[contains(@id,'__calendar-grid-caption_')]"));
		
			String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September","October","November","December"};
			
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
		
		
}
