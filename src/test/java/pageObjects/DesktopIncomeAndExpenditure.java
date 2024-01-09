package pageObjects;

import java.util.HashMap;
import java.util.List;

//import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testComponents.PageUtils;
import utils.Logging;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class DesktopIncomeAndExpenditure {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();
	String currentTab = "";
	String iframeXpathStr = "//custom-reference-panel-with-url[@class='ng-scope']/iframe";

		
		public DesktopIncomeAndExpenditure(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
		
		public void incomeAndExpenditure(HashMap<String,String[]> paramsMap, String status) throws Exception  {
		String account = pageUtils.testMap.get("account");
		String logEntryPrefix= "Account: "+account+"DesktopI&E/I&E/add: ";
		Logging.logToConsole("INFO",logEntryPrefix+" start");
		pageUtils.implictWait(1);
		currentTab = "summary"; // set the value of the starting tab
		Select dropdown;
		//List<WebElement> options;
		boolean tabError = false;
		
		for (int j=0;j<10;j++) { // i&E does not always load correctly this will loop until it does
		tabError = false;
		Logging.logToConsole("INFO",logEntryPrefix+" open lower panel attempt: "+ (j+1));
		driver.switchTo().defaultContent();
		pageUtils.openLowerPanel("SFS Income and Expenditure");	
		currentTab = "summary"; // set the value of the starting tab
		int sleepTime = 100;
		for (int k=0; k<5 ; k++) {
			Thread.sleep(sleepTime);
			try {
				scrollToBottomPanel();
				driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathStr)));
				driver.findElement(By.xpath("//button[@id='add-button']")).click();  //add i&E
			}catch (Exception e) {
				Logging.logToConsole("INFO",logEntryPrefix+" add button error attempt: "+ (k+1));
				sleepTime = sleepTime +100;
				driver.switchTo().defaultContent();
				continue;
				}
			break;
		}
		
		//check summary panel to ensure it has loaded properly
		if(!tabLoadedCheck("summary","//select[@id='customers-employment-status']")) {continue;}

		
		// order in which the data should be entered
		String[] entryOrder = {"summary/status/customers employment status", "income/benefits/jobseekers allowance (contribution based)",
				"expenditure (fixed)/pensions and insurances/mortgage payment protection",
				"expenditure (flexible)/communications and leisure/newspapers, magazines, stationary, postage",
				"expenditure (flexible)/food and housekeeping/school meals and meals at work",
				"expenditure (flexible)/personal costs/clothing and footwear","savings/savings/monthly savings amount",
				"debts/monthly non priority debts/store card arrears","notes//"};

		//iterate through the keys that have a value and execute them in correct entry order
		
		String xpathStr;
		WebElement element;
		for (String i : entryOrder) {
			String[] value = paramsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value[1]);
			selectTab(i.split("/")[0]); // switch to the correct tab for the value being entered
			switch (i) {
			
			case "summary/status/customers employment status":	
				dropdown =  new Select(driver.findElement(By.xpath("//select[@id='customers-employment-status']")));
				dropdown.selectByVisibleText(value[1]);
				break;
			case "income/benefits/jobseekers allowance (contribution based)":
				tabError = updateFrequencyValueField("[@id='jobseekers-allowance-contribution-based']",value);
				break;
			case "expenditure (fixed)/pensions and insurances/mortgage payment protection":
				tabError = updateFrequencyValueField("[@id='mortgage-payment-protection']",value);
				break;
			case "expenditure (flexible)/communications and leisure/newspapers, magazines, stationary, postage":
				tabError = updateFrequencyValueField("[@id='newspapers-magazines-stationary-postage']",value);
				 break; 
			case "expenditure (flexible)/food and housekeeping/school meals and meals at work":
				tabError = updateFrequencyValueField("[@id='school-meals-and-meals-at-work']",value);	
				 break; 
			case "expenditure (flexible)/personal costs/clothing and footwear":
				tabError = updateFrequencyValueField("[@id='clothing-and-footwear']",value);	
				 break; 
			case "savings/savings/monthly savings amount":
				element = driver.findElement(By.xpath("//input[@id='monthly-savings-amount']"));
				element.clear();
				element.sendKeys(value[1]);	
				 break; 
			case "debts/monthly non priority debts/store card arrears":
				xpathStr = "[@id='store-card-arrears']";
				dropdown =  new Select(driver.findElement(By.xpath("//select" + xpathStr)));
				dropdown.selectByVisibleText(value[0]);
				driver.findElement(By.xpath("//input" + xpathStr+"[1]")).clear();
				driver.findElement(By.xpath("//input" + xpathStr+"[1]")).sendKeys(value[1].split("/")[0]);
				driver.findElement(By.xpath("//input" + xpathStr+"[2]")).clear();
				driver.findElement(By.xpath("//input" + xpathStr+"[2]")).sendKeys(value[1].split("/")[1]);	
				 break; 
			case "notes//":
				driver.findElement(By.xpath("//fieldset[@id='-group']//div[@class='form-row']/div/textarea")).sendKeys(value[1]);
				 break; 
				}
			}
			if(tabError) {break;}
		}//for entryOrder Loop
		 if(!tabError) {break;}
		} //  load error loop i.e. for (int i=0;i<10;i++)
		
		selectTab("summary");
		driver.findElement(By.xpath("//select[@id='ie-status']")).sendKeys(status);	
		driver.findElement(By.xpath("//div[@class='btn-group']/button[2]")).click();
	}
		
		
		
		// function to select the correct tab
		public void selectTab(String requiredTab) throws InterruptedException {
			Logging.logToConsole("INFO","select panel: "+ requiredTab+" Existing Panel: "+currentTab  );
			if(currentTab.equals(requiredTab)) {return;} // already on required Tab
			
			String xpathStr = "";
			switch (requiredTab) {
			case "summary":	
				xpathStr = "//a[@id='summary-tab___BV_tab_button__']";
				break;
			case "income":	
				xpathStr = "//a[@id='income-tab___BV_tab_button__']";
				break;
			case "expenditure (fixed)":	
				xpathStr = "//a[@id='exp-fixed-tab___BV_tab_button__']";
				break;
			case "expenditure (flexible)":	
				xpathStr = "//a[@id='exp-flex-tab___BV_tab_button__']";
				break;
			case "savings":	
				xpathStr = "//a[@id='savings-tab___BV_tab_button__']";
				break;
			case "debts":	
				xpathStr = "//a[@id='debts-tab___BV_tab_button__']";
				break;
			case "notes":	
				xpathStr = "//a[@id='notes-tab___BV_tab_button__']";
				break;
			}
			int sleepTime = 0;
			for (int k=0; k<5 ; k++) {
				Thread.sleep(sleepTime);
				try {
					scrollToBottomPanel();
					driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathStr)));
					driver.findElement(By.xpath(xpathStr)).click();
					currentTab = requiredTab; // update the value of the current tab
				}catch (Exception e) {
					Logging.logToConsole("INFO","click " +requiredTab+ "  tab button error attempt: "+ (k+1));
					sleepTime = sleepTime + 50;
					driver.switchTo().defaultContent();
					continue;
					}
				break;
			}
		}
		
		
		public void scrollToBottomPanel() throws InterruptedException  {
			driver.switchTo().defaultContent();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			//js.executeScript("window.scrollBy(0,2000)");
			for (int j=0;j<10;j++) {
				try {
				driver.findElement(By.xpath("//div[@class='custom-panel-menu']//ul[@class='nav nav-tabs']")).click();
				break;
				}catch(Exception e) {
					js.executeScript("window.scrollBy(0,-40)");
					Thread.sleep(25);
				}
			}
		}
		
		
		//check if a tab has loaded correctly
		public boolean tabLoadedCheck(String panel, String xpathStr ) throws Exception  {
			selectTab(panel);
			Select dropdown = new Select(driver.findElement(By.xpath(xpathStr)));
			List<WebElement> options = dropdown.getOptions();
			if(options.size() < 2) { //page did not load correctly
				cancelAndCloseIAndEPanel();
				return false;
				}
			return true;
			}
		
		//Cancel and close panel
		public void cancelAndCloseIAndEPanel() throws Exception  {
				driver.findElement(By.xpath("//button[@id='cancel-button']")).click();
				Thread.sleep(25);
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath("//a[normalize-space()='SFS Income and Expenditure']//i[@class='icon-remove']")).click();	
			}
		
		
		//update a field that has a frequency and a text value
		public boolean updateFrequencyValueField(String xpathStr, String[] value) throws Exception {	
		Select dropdown = new Select(driver.findElement(By.xpath("//select" + xpathStr)));
		List<WebElement> options = dropdown.getOptions();
		if(options.size() < 2) {// select values have not been loaded correctly
			cancelAndCloseIAndEPanel();
			return true;
			}
		dropdown.selectByVisibleText(value[0]);
		driver.findElement(By.xpath("//input" + xpathStr)).clear();
		driver.findElement(By.xpath("//input" + xpathStr)).sendKeys(value[1]);
		return false;
		}
}
