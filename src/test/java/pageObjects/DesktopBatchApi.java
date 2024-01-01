package pageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testComponents.PageUtils;
import utils.Logging;


public class DesktopBatchApi {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
		
		public DesktopBatchApi(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	
	
	public HashMap<String,String> getBatchApi(HashMap<String,String> paramsMap) throws Exception  {
		String account = pageUtils.testMap.get("account");
		Logging.logToConsole("INFO","DesktopBatchApi/getBatchApi/"+account+": start");
		pageUtils.ReturnHome();
		
		//open the batchAPI panel
		pageUtils.openLowerPanel("Batch API Submissions");
		pageUtils.ScrollBy(1000);
		
		//store element number of each table column in a map
		String tablePath = "//table[@class='k-grid-table']/tbody//tr/td[";
		HashMap<String,Integer> tableMap = new HashMap<String,Integer>();		
		tableMap.put("date created", 1);
		tableMap.put("event", 2);
		tableMap.put("value", 5);
		tableMap.put("payload", 7);
		tableMap.put("debtor id", 9);

		// Page button web elements next previous etc
		//WebElement buttonFirst =  driver.findElement(By.xpath("//span[@title='Go to the first page']"));
		WebElement buttonNext =  driver.findElement(By.xpath("//span[@title='Go to the next page']"));
		//WebElement buttonLast = driver.findElement(By.xpath("//span[@title='Go to the next page']"));
				
		int pageCount = 0;
		boolean match = false;
		int matchCount = 0;
		HashMap<String,String> resultsMap = new HashMap<String,String>();
		//search for a match until one is found or all the data is checked
		while (!match) {
		Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": Page: " + pageCount);
		// get the number of rows 	
		int size = driver.findElements(By.xpath(tablePath+"2]")).size();
		//look through each row in the table on the current page until a match is found or we run out of rows
		for(int row = 0; row < size; row++)	{
		Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": Row: "+ (row+1));	
		//loop through each entry in the input parameters map and see if it matches the current table row being checked
		for (Map.Entry<String, String> mapEntry :paramsMap.entrySet()) {
			String key = mapEntry.getKey().toLowerCase();
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": column: "+ key);
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+" input search value: "+ mapEntry.getValue());
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": tableMap.get(key): "+ tableMap.get(key));
			List<WebElement> column = driver.findElements(By.xpath(tablePath+ tableMap.get(key) +"]"));
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": actual table value: " + column.get(row).getText());
			if(!mapEntry.getValue().equalsIgnoreCase(column.get(row).getText())) {
				//Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": no match ");
				match = false;
				break;
				}
			Logging.logToConsole("DEBUG","DesktopBatchApi/getBatchApi/"+account+": match field ");
			match = true;
		 }
		if(match) {
			Logging.logToConsole("DEBUG",
							"DesktopBatchApi/getBatchApi/"+account+": Match Found: "+ " Row: "+(row+1));
			matchCount++;
			match = false;
			//for the first match store the JSON
			if(matchCount == 1) {
				resultsMap.put("matchedJsonString1", driver.findElement(By.xpath("("+tablePath+ tableMap.get("payload")+"])["+(row+1)+"]")).getText()); 
			}
			//return driver.findElement(By.xpath("("+tablePath+ tableMap.get("payload")+"])["+(row+1)+"]")).getText();
				}
		} //row loop
		if (!buttonNext.getAttribute("class").equalsIgnoreCase("k-link k-pager-nav k-disabled"))
			{
			buttonNext.click();
			pageCount = pageCount+1;
			}
			//no more pages so break the loop
			else {break;}
		
			} //while loop
				
		resultsMap.put("matchCount", Integer.toString(matchCount));	
		return resultsMap;
	}	
}
