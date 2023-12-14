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
	
		
		
	public boolean communicationHistory(HashMap<String,String> paramsMap) throws InterruptedException  {
		Logging.logToConsole("INFO","DesktopCommunications/communications: Start");

		//scroll the screen to the section with the comms history
		pageUtils.scrollTo(400);
		pageUtils.defaultImplictWait();
		
		
		//check the comms down arrow button. if closed then click to open it
		WebElement buttonCommunication = driver.findElement(By.xpath("//letters-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span"));
		// if comms menu is closed then open it
		if (buttonCommunication.getAttribute("class").equalsIgnoreCase("icon-caret-right ng-scope")){buttonCommunication.click();}
		driver.findElement(By.xpath("//letters-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span"));
		//click the communication history button
		driver.findElement(By.xpath("//div[normalize-space()='Communication History']")).click();
		//close the menu
		driver.findElement(By.xpath("//letters-menu[@class='ng-scope ng-isolate-scope']/div/ng-switch/span")).click();
		
		// Page button web elements next previous etc
		WebElement buttonPrevious =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Previous']"));
		//WebElement buttonPrevious =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][1]")); //alternative xpath
		//WebElement button1 =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][1]/following-sibling::li[1]"));
		//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='1'] alternative path
		//WebElement buttonNext =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][2]"));
		WebElement buttonNext =  driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Next']"));
		WebElement buttonLast = driver.findElement(By.xpath("//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//li[@ng-if='::directionLinks'][2]/preceding-sibling::li[1]"));
		//div[@ng-show='vm.letterHistoryGrid.pageCount > 1']//a[@class='page-link ng-binding'][normalize-space()='Next']/parent::li/preceding-sibling::li[1]

		
		// go to the last page and then count the pages
		boolean nextEnabled = true;
		int numberOfPages = 1;
		while (nextEnabled) {
			String nextButtonState = buttonNext.getAttribute("disabled");
		if (nextButtonState == null)
			{
			buttonLast.click();
			}
			else {
				nextEnabled = false; 
				}
		}
		// get the page number from the last page button will error if there are no buttons
		try {
			numberOfPages = Integer.parseInt(buttonLast.getText());
			}
			catch (Exception e) {}
		Logging.logToConsole("INFO","DesktopCommunications/communications:Number of pages = "+numberOfPages );
		
		//pageUtils.ReturnHome();
		//pageUtils.Scroll(300);
		
		//store element number of each table column in a map
		String tablePath = "//table[@lat-table='vm.letterHistoryGrid']/tbody//tr/td[";
		HashMap<String,Integer> tableMap = new HashMap<String,Integer>();		
		tableMap.put("method", 2);
		tableMap.put("code",  3);
		tableMap.put("type",4);
		tableMap.put("description", 5);
		tableMap.put("date requested", 6);
		tableMap.put("date sent", 7);
		tableMap.put("sender", 8);
		tableMap.put("recipient", 9);
		tableMap.put("subject",10);
		
		boolean match = false;

		//int page = 0;
		int pageCount = 0;
		//search for a match until one is found or all the pages are checked
		while (!match) {
		Logging.logToConsole("DEBUG","DesktopCommunications/communications: Page: "+ (numberOfPages - pageCount));
		// get the number of rows on the current page 		
		int size = driver.findElements(By.xpath(tablePath+"2]")).size();
		//look through each row in the table on the current page until a match is found or we run out of rows
		for(int row = size-1; row>=0; row--)	{
		//Logging.logToConsole("DEBUG","DesktopCommunications/communications: Row: "+ row);	
		//loop through each entry in the input parameters map and see if it matches the current table row being checked
		for (Map.Entry<String, String> mapEntry :paramsMap.entrySet()) {
			String key = mapEntry.getKey().toLowerCase();
			//Logging.logToConsole("DEBUG","DesktopCommunications/communications: column: "+ key);
			//Logging.logToConsole("DEBUG","DesktopCommunications/communications: value: "+ mapEntry.getValue());
			//Logging.logToConsole("DEBUG","DesktopCommunications/communications: tableMap.get(key): "+ tableMap.get(key));
			List<WebElement> column = driver.findElements(By.xpath(tablePath+ tableMap.get(key) +"]"));
			if(!mapEntry.getValue().equalsIgnoreCase(column.get(row).getText())) {
				//Logging.logToConsole("DEBUG","DesktopCommunications/communications: no match ");
				match = false;
				break;
			}
			match = true;

		}
		if(match) {
					Logging.logToConsole("DEBUG",
							"DesktopCommunications/communications: Match Found: "+ "Page: "+(numberOfPages-pageCount)+" Row: "+(row+1));
					break;
					}
			} //row loop
				//if the previous button is enabled click to go back a page
				if (buttonPrevious.getAttribute("disabled") == null)
					{
						buttonPrevious.click();
						pageCount = pageCount+1;
					}
					//no more pages so break the loop
					else {break;}
			
				} //while loop
		
		pageUtils.ReturnHome();
		return match;
	}	
}
