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

public class DesktopDMC {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	String logEntryPrefix;
		
		public DesktopDMC(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
		
		public void dmcCompany(HashMap<String,String> paramsMap, String action) throws Exception  {
			String account = pageUtils.testMap.get("account");
			logEntryPrefix= "Account: "+account+"DesktopDmc/dmcCompany/"+action+": "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.closeAnchorPanel();
				pageUtils.ReturnHome();
				pageUtils.implictWait(1);
				//Click the Debt management button then switch to iframe
				driver.findElement(By.xpath("//div[contains(text(),'Debt Management')]")).click();
				WebElement iframe = driver.findElement(By.id("DebtManagement21"));
				//open the dialogue to add a company and switch to it
				driver.switchTo().frame(iframe);
				
				//select the right customer
				Select dropdown;
				dropdown = new Select(driver.findElement(By.xpath("//select[@id='customer']")));
				dropdown.selectByVisibleText(paramsMap.get("customer"));
				
				pageUtils.implictWait(0);
				try {
					if(action.equals("add")) {
						driver.findElement(By.xpath("//button[@id='addNewButton']")).click();
						}else {// action is edit/update
							driver.findElement(By.xpath("//button[@id='editButton']")).click();
							}
					}catch(Exception E) {
						throw new Exception(logEntryPrefix+" unable to click "+ action + " button check whether there is an existing DMC?");
						}
				pageUtils.implictWait(1);
				driver.switchTo().activeElement();
				
				// order in which the data should be entered
				String[] entryOrder = {"company name", "street 1","street 2","city", "county", "country", "post code", "phone",
						"fax", "third party type","revoked", "acts as insolvency practitioner"};	
				
				//iterate through the keys that have a value and execute them in correct entry order
				WebElement inputWebElement; // temp element to hold values in case statement

				Boolean isSelected;
				for (String i : entryOrder) {
					String value = paramsMap.get(i);
					if (value != null )
					{ 
					Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value);	
					switch (i) {
					case "company name":
						driver.findElement(By.xpath("//input[@id='company-name']")).sendKeys(value);
						break;
					case "street 1":
						driver.findElement(By.xpath("//input[@id='street-1']")).sendKeys(value);
						break;
					case "street 2":
						driver.findElement(By.xpath("//input[@id='street-2']")).sendKeys(value);
						break;
					case "city":
						driver.findElement(By.xpath("//input[@id='city']")).sendKeys(value);
						break;
					case "county":
						driver.findElement(By.xpath("//input[@id='county']")).sendKeys(value);
						break;	
					case "country":
						driver.findElement(By.xpath("//input[@id='country']")).sendKeys(value);
						break;
					case "post code":
						driver.findElement(By.xpath("//input[@id='post-code']")).sendKeys(value);
						break;	
					case "phone":
						driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(value);
						break;
					case "fax":
						driver.findElement(By.xpath("//input[@id='fax']")).sendKeys(value);
						break;
					case "third party type":
						dropdown = new Select(driver.findElement(By.xpath("//select[@id='third-party-type']")));
						dropdown.selectByVisibleText(value);
						break;
					case "revoked":
						inputWebElement = driver.findElement(By.xpath("(//label[@for='checkbox-revoked'])[1]"));
						isSelected = inputWebElement.isSelected();	
						if((value.equalsIgnoreCase("true") && isSelected == false) || (value.equalsIgnoreCase("false")&& isSelected == true)) {
						inputWebElement.click();
								}
						break;
					case "acts as insolvency practitioner":
						inputWebElement = driver.findElement(
								By.xpath("(//label[@for='checkbox-acts-as insolvency practitioner'])[1]"));
						isSelected = inputWebElement.isSelected();
						if((value.equalsIgnoreCase("true") && isSelected == false) || (value.equalsIgnoreCase("false")&& isSelected == true)) {
						inputWebElement.click();
								}
						break;
						}
					}
				}
				
				//Close the dialogue and exit the iframe
				WebElement button = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
				if (button.isEnabled() == true) {
					button.click();
					Logging.logToConsole("INFO",logEntryPrefix+" DMC company "+action+"ed");
					}
					else {
						driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
						driver.switchTo().defaultContent();
						throw new Exception(logEntryPrefix+" unable to complete" + action + " button not enabled");
						}//to ensure that we switch out of the iframe at the end regardless
					
				driver.switchTo().defaultContent();
			}
			
		
		public HashMap<String, String> dmc(HashMap<String,String> paramsMap, String action) throws Exception  {
			String account = pageUtils.testMap.get("account");
			logEntryPrefix= "Account: "+account+"DesktopDmc/dmc/"+action+": "; 		
			try {

				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.closeAnchorPanel();
				pageUtils.ReturnHome();
				
				
				//Click the Debt management button then switch to iframe
				
				driver.findElement(By.xpath("//div[contains(text(),'Debt Management')]")).click();
				WebElement iframe = driver.findElement(By.id("DebtManagement21"));
				driver.switchTo().frame(iframe);
								
				// order in which the data should be entered
				String[] entryOrder = {"customer", "company","contact","client id", "creditor id", "date accepted", "amount accepted", "comment"};	
				
				
				//iterate through the keys that have a value and execute them in correct entry order
				WebElement inputWebElement; // temp element to hold values in case statement
				Select dropdown;
				for (String i : entryOrder) {
					String value = paramsMap.get(i);
					if (value != null )
					{ 
					Logging.logToConsole("DEBUG", logEntryPrefix+" DMC Key: " +i+ " Value: "+ value);	
					switch (i) {
					case "company":
						if (action.equalsIgnoreCase("check")) {
							inputWebElement =  driver.findElement(By.xpath("//span[@class='vs__selected']"));
							//Object attrs = js.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",inputWebElement);
							//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + "Attributes" +" "+ attrs);	
							Logging.logToConsole("DEBUG",logEntryPrefix+ "/CheckDMC: " + i +" "+ inputWebElement.getText());	
							resultsMap.put(i,inputWebElement.getText());  
							}else {
								driver.findElement(By.xpath("//input[@type='search']")).sendKeys(value);
								driver.findElement(By.xpath("//li[@id='vs1__option-0']")).click();
								}
						
						break;
					case "contact":
						inputWebElement=driver.findElement(By.xpath("//input[@id='contact']"));
						implementAction(action,inputWebElement,value,i);
						break;
					case "client id":
						inputWebElement=driver.findElement(By.xpath("//input[@id='client-id']"));
						implementAction(action,inputWebElement,value,i);
						break;
					case "creditor id":
						inputWebElement=driver.findElement(By.xpath("//input[@id='creditor-id']"));
						implementAction(action,inputWebElement,value,i);
						break;
					case "date accepted":
						Logging.logToConsole("DEBUG",logEntryPrefix + "AddDMC: " + "Date accepted" +" "+ value);	
						inputWebElement=driver.findElement(By.xpath("//input[@id='date-accepted']"));
						implementAction(action,inputWebElement,value,i); 
						break;	
					case "amount accepted":
						inputWebElement=driver.findElement(By.xpath("//input[@id='amount-accepted']"));
						implementAction(action,inputWebElement,value,i); 
						break;
					case "comment":
						inputWebElement=driver.findElement(By.xpath("//textarea[@id='comment']"));
						implementAction(action,inputWebElement,value,i); 
						break;	
					case "customer":
						dropdown = new Select(driver.findElement(By.xpath("//select[@id='customer']")));
						dropdown.selectByVisibleText(value);
						break;	
					}
					}
				}
				WebElement button = driver.findElement(By.xpath("//button[@id='deleteButton']"));
				switch (action.toLowerCase()) {
				case "add":
					button = driver.findElement(By.xpath("//button[@id='submitButton']"));
					if (button.isEnabled() == true) {button.click();
					Logging.logToConsole("INFO",logEntryPrefix+" New DMC submitted");}
						else { Logging.logToConsole("WARNING",logEntryPrefix+" unable to click DMC: " + action + " button disabled");}
					break;
				case "update":
					button = driver.findElement(By.xpath("//button[@id='submitButton']"));
					if (button.isEnabled() == true) {button.click();
					Logging.logToConsole("INFO","DesktopDMC/dmc: DMC updated");}
						else { Logging.logToConsole("WARNING",logEntryPrefix+" unable to click DMC: " + action + " button disabled");}
					break;
				case "delete":
					driver.findElement(By.xpath("//button[@id='deleteButton']")).click();
					for (int i=1; i<10;i++) {
						try {
							pageUtils.handlePopup("Yes");
							driver.switchTo().frame(iframe);
							if(button.getAttribute("disabled").equals("disabled")) {
								break;
								}
						}catch(Exception e) {Thread.sleep(200);}	
					}
					break;
				case "cancel":
					button = driver.findElement(By.xpath("//button[@id='cancelButton']"));
					if (button.isEnabled() == true) {button.click();}
						else { Logging.logToConsole("WARNING",logEntryPrefix+" unable to click DMC: " + action + " button disabled");}
					break;	
					}
			
			}catch(Exception e) {//to ensure that we switch out of the iframe at the end regardless
				driver.switchTo().defaultContent(); //switch out of iframe
				 throw new Exception(logEntryPrefix+" unable to complete" + action + " an error has occured");}//to ensure that we switch out of the iframe at the end regardless
			
			driver.switchTo().defaultContent(); //switch out of iframe
			pageUtils.ReturnHome();
			return resultsMap;	
	}	
	
	
	
	public void implementAction(String action, WebElement inputWebElement, String value, String attribute ) throws InterruptedException {
		if (action.equalsIgnoreCase("check")) {
			//get the value and store in the results map
			try {
			//Logging.logToConsole("DEBUG","DesktopDMC/CheckDMC: " + attribute +" "+ inputWebElement.getAttribute("value"));	
			resultsMap.put(attribute,inputWebElement.getAttribute("value"));  
			}catch (Exception e){resultsMap.put(attribute,"No Value"); }
		}
		else {
			inputWebElement.clear(); //don't clear the comments add to them	
			inputWebElement.sendKeys(value); 
			}
		}

}
