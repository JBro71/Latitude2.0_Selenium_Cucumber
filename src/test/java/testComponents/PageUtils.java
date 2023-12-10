package testComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logging;

public class PageUtils extends BaseTest {
	WebDriver driver;
	public HashMap<String, String> testMap;
	JavascriptExecutor js;
	
	
	public PageUtils( WebDriver driver, HashMap<String, String> testMap ) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		this.testMap = testMap;
		js = (JavascriptExecutor)driver;

	}
	
	public void updateCheckBox(String set, String xpath) throws Exception {
	//helper function to set or unset a check box
		try {
	WebElement inputWebElement = driver.findElement(By.xpath(xpath));
	Boolean isSelected = inputWebElement.isSelected();
		if((set.equalsIgnoreCase("true") && isSelected == false) || (set.equalsIgnoreCase("false")&& isSelected == true)) {
			inputWebElement.click();
			}
		}catch (Exception e) { 
			throw new Exception("updateCheckBox "+ testMap.get("account")+" :  unable to update " + xpath);}
	}
	
	public String checkCheckBox(String xpath) {		
	if(driver.findElement(By.xpath(xpath)).isSelected()) {return "true";}
	return "false";
	}
	
	
	
	public void handlePopup(String buttonText) {
		try {
			tempImplictWait(0);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//button[contains(text(),'"+buttonText+"')]")).click();
			//driver.findElement(By.xpath("//button[normalize-space()='OK']")).click();
			}catch(Exception e) {
		}
		lastImplictWait();
	}
	
	public void updateTestMap(String key, String value) {
		testMap.put(key,value);
		staticTestMap.put(key,value);
	}
	
	public void defaultImplictWait() {
		String wait = prop.getProperty("implicitWait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(wait)));
		testMap.put("lastImplicitWait",wait);
	}
	
	public void implictWait(int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
		testMap.put("lastImplicitWait",Integer.toString(wait));
	}
	
	public void tempImplictWait(int wait) {
		//change the implicit wait without updating the last value
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}
	
	public void lastImplictWait() {
		//change the implicit wait without updating the last value
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(testMap.get("lastImplicitWait"))));
	}

	public void openAnchorPanel() throws InterruptedException {
	ReturnHome();
	 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']/i"));
	 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-down")) {
		 driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']")).click();
	 }
	 //delay until the panel is definitely open
	 for(int i=0; i<5; i++) {
		 try {
			 driver.findElement(By.xpath("//dt[normalize-space()='Account Overview']")).getText();
			 return;
		 	}catch (Exception e) {Thread.sleep(100);}
	 	}
	 
	}
	
	public void closeAnchorPanel() throws InterruptedException {
		ReturnHome();
		 WebElement toggle = driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']/i"));
		 if(toggle.getAttribute("class").equalsIgnoreCase("icon-chevron-up")) {
			 driver.findElement(By.xpath("//span[@ng-click='vm.toggleAnchor()']")).click();
		 	}
		 //delay until panel is no longer accessible
		 for(int i=0; i<5; i++) {
			 try {
				 driver.findElement(By.xpath("//dt[normalize-space()='Account Overview']")).getText();
				Thread.sleep(100);
			 	}catch (Exception e) {return;}
		 	}
		}
	
	
	public void CloseAccount() {
		//close existing account if one open
	try 
	{
		implictWait(0);		
		ReturnHome();
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		//context.DefaultImplictWait();
	}
	catch(Exception e){
		Logging.logToConsole("DEBUG", "No Existing open account to close");
		}
	defaultImplictWait();
	}
	
	
	public void ReturnHome() throws InterruptedException {
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public void Scroll(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0," + move + ")");						
	}
	
	public void ScrollBy(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0," + move + ")");	
			
	}
	
	public void openLowerPanel(String panelName) throws Exception { 
		//open the named lower panel
		Scroll(200);
		driver.findElement(By.xpath("//button[normalize-space()='Available Panels']")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//div[@class='dropdown-menu show']/div/a"));
		for (WebElement menuItem : menu) {
		try {
			String menuItemText = menuItem.getText();
			if (menuItemText.equalsIgnoreCase(panelName)) {
				menuItem.click();
				return;
				}
				}catch(Exception e) {
						 throw new Exception(
								 "PageUtils/openLowerPanel"+ testMap.get("account")+" :  unable to open "+panelName+" lower panel");
					 }
			}
		 throw new Exception(
				 "PageUtils/openLowerPanel"+ testMap.get("account")+" :  cannot find "+panelName+" lower panel");
	}
	
	
	
	
	
	
	
	public HashMap<String,Integer> findInTable(HashMap<String,String[]> tableMap,HashMap<String,String> fieldMap,HashMap<String,String> dataMap, HashMap<String,String> paramsMap ) throws Exception { 
		String logEntryPrefix= "Account: "+testMap.get("account")+" PageUtils/"+dataMap.get("function")+"/find: "; 
		HashMap<String,Integer> resultsMap = new HashMap<String,Integer>();	
		resultsMap.put("numberOfMatches", 0);
		WebElement buttonPrevious = driver.findElement(By.xpath(dataMap.get("buttonPrevious")));
		WebElement buttonNext = driver.findElement(By.xpath(dataMap.get("buttonNext")));
		// make sure we are at the first page
		while(!buttonPrevious.getAttribute(dataMap.get("buttonDisabledAttributeName")).equals(dataMap.get("buttonDisabledAttributeText"))) {
			buttonPrevious.click();
		}
		
		int page = 1;
		boolean match = false; // boolean to hold the matchstatus
		int matchCount = 0;
		boolean firstPass = true; // two passes around the loop variable to hold which pass we are on
		//search for a match until one is found or all the data is checked
		while (true) {
			Logging.logToConsole("DEBUG",logEntryPrefix+": Page: " + (page));
			// get the number of rows 	
			int size = driver.findElements(By.xpath(dataMap.get("tablePath")+"1]")).size();
			//look through each row in the table on the current page until a match is found or we run out of rows
			for(int row = 1; row <= size; row++)	{
				Logging.logToConsole("DEBUG",logEntryPrefix+": Row: "+ row);	
				//loop through each entry in the input parameters map and see if it matches the current table row being checked
				match = true;
				for (String key : tableMap.keySet()) { //check the table fields for a match
					String tableFieldString = "("+dataMap.get("tablePath")+ tableMap.get(key)[0] +"])["+row+"]";
					WebElement tableField = driver.findElement(By.xpath(tableFieldString));
					//String value = driver.findElement(By.xpath(tableMap.get(key))).getText();
					Logging.logToConsole("INFO",logEntryPrefix+" Key: " + key);
					Logging.logToConsole("INFO",logEntryPrefix+" Table text: " + tableField.getText());
					Logging.logToConsole("INFO",logEntryPrefix+" pramsMap value: " + paramsMap.get(key));
					
					//try {
					if(paramsMap.containsKey(key) == false) {continue;}// don't need to check that value so skip
					switch (tableMap.get(key)[1]) {
					case "checkbox":
						if((paramsMap.get(key).equalsIgnoreCase("true") && tableField.isSelected() == true) || 
								(paramsMap.get(key).equalsIgnoreCase("false")&& tableField.isSelected() == false)) 
								{
								continue;
								}
						break;
					case "text":
						if(paramsMap.get(key).equals(tableField.getText())) {continue;}	
						break;
					case "colour":
						String tableFieldClass = driver.findElement(By.xpath(tableFieldString+tableMap.get(key)[2])).getAttribute("style");
						if (paramsMap.get(key).equalsIgnoreCase("true")&& tableFieldClass.contains("green") ||
								paramsMap.get(key).equalsIgnoreCase("false")&& tableFieldClass.contains("red")) 
						{
						continue;	
						}
						
						
						break;
					}
						//}catch(Exception E) {}
					match = false;
					break;
				}
				
				if(match) {
					for (String key : fieldMap.keySet()) { // check the non table fields for a match
						if(paramsMap.get(key) == null) {continue;}
						String value = driver.findElement(By.xpath(fieldMap.get(key))).getAttribute("value");
						if(paramsMap.get(key).equals(value)) {continue;}// if a match then skip to the next loop/value to check
						match = false;
						break;
						}
					}
				
				if(match) {
					if (!firstPass) { //click the match and exit function
						driver.findElement(By.xpath("("+dataMap.get("tablePath")+ "1])["+row+"]")).click();
						return resultsMap;
						}
					matchCount++;	
					resultsMap.put("numberOfMatches", matchCount);					
					resultsMap.put("matchPage"+page, page);
					resultsMap.put("matchRow"+row, row);
					Logging.logToConsole("DEBUG",logEntryPrefix+": Match Found: "+"page" + page + " Row: "+ row);
					}
				} //row loop	

		if (!buttonNext.getAttribute(dataMap.get("buttonDisabledAttributeName")).equals(dataMap.get("buttonDisabledAttributeText")))
			{
			buttonNext.click();
			page++;
			}
			else if (firstPass ) 
				{//start the second pass reset the pages
				firstPass = false;
				while(!buttonPrevious.getAttribute(dataMap.get("buttonDisabledAttributeName")).equals(dataMap.get("buttonDisabledAttributeText"))) 
					{
					buttonPrevious.click();
					}
				page = 0;
				}
				else {//next button is disabled and we are at the end of the second pass through. time to quit
					return resultsMap;
				}
		
			} //while loop			
		
	}
	
	
	
}
