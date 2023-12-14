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


public class DesktopAddress {

	HashMap<String,String> resultsMap = new HashMap<String, String>();
	WebDriver driver;
	PageUtils pageUtils;
	JavascriptExecutor js;
	HashMap<String,String> dataMap = new HashMap<String,String>();
	String tablePath = "//tbody/tr/td[";
	String iframeXpathString = "//iframe[contains (@id, 'AddressPanel')]";
	 String checkXpathString;
	 String updateXpathString;	
		
		public DesktopAddress(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			js = (JavascriptExecutor)driver;
		}
	

		public HashMap<String,Integer> findAddress(HashMap<String,String> paramsMap) throws Exception  {
			String account = pageUtils.testMap.get("account");
			String logEntryPrefix= "Account: "+account+" DesktopAddress/address/find: "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.defaultImplictWait();
				pageUtils.openLowerPanel("Address Panel");
				pageUtils.scrollTo(1200);	
				driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathString)));
				String buttonPath = "//div[@id='datapanel']//li";
				List<WebElement> buttonsWebElements = driver.findElements(By.xpath(buttonPath));
				//String nextButtonIndex = buttonsWebElements.size();
				
				HashMap<String,String> dataMap = new HashMap<String,String>();	
				dataMap.put("function", "findphoneNumber");
				dataMap.put("buttonPrevious", buttonPath+"[1]");
				dataMap.put("buttonNext", buttonPath+"["+buttonsWebElements.size()+"]");
				dataMap.put("buttonDisabledAttributeName", "class");
				dataMap.put("buttonDisabledAttributeText", "page-item disabled");
				dataMap.put("tablePath", tablePath);
				
				//store element number of each table column in a map
				HashMap<String,String[]> tableMap = new HashMap<String,String[]>();	
				tableMap.put("party", new String[] {"1","text"});
				tableMap.put("type",new String[] {"2","text"});
				tableMap.put("flags",new String[] {"3","text"});
				tableMap.put("status",new String[] {"4","text"});
				tableMap.put("source",new String[] {"5","text"});
				tableMap.put("confirmation source",new String[] {"6","text"});
				tableMap.put("address line 1",new String[] {"7","text"});
				tableMap.put("address line 2",new String[] {"8","text"});
				tableMap.put("city/town",new String[] {"9","text"});
				tableMap.put("county",new String[] {"10","text"});
				tableMap.put("post code",new String[] {"11","text"});
				tableMap.put("country",new String[] {"12","text"});

				HashMap<String,String> fieldMap = new HashMap<String,String>();		
				
				return pageUtils.findInTable(tableMap, fieldMap, dataMap, paramsMap );
		}
				
		
		public void address(String action, HashMap<String,String> paramsMap, int row) throws Exception  {
			String logEntryPrefix= "Account: "+pageUtils.testMap.get("account")+" DesktopAddress/address: "; 
			WebElement tempWebElement;
			String titleXpathString = "//h5[@id='addeditmodal___BV_modal_title_']";
			Logging.logToConsole("INFO",logEntryPrefix+" start");
			pageUtils.ReturnHome();
			 
			switch (action.toLowerCase()) {
			case "add":
				pageUtils.openLowerPanel("Address Panel");
				driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathString)));
				driver.findElement(By.xpath("(//button[@type='button'])[2]//*[name()='svg']")).click();

				break;
			case "update":
				driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathString)));
				driver.findElement(By.xpath("(//button[@type='button'])[3]//*[name()='svg']")).click();
				break;
				}
			
			driver.switchTo().defaultContent();
			pageUtils.scrollBy(600);	
			driver.switchTo().frame(driver.findElement(By.xpath(iframeXpathString)));
			
		// order in which the data should be entered
		String[] entryOrder = {"address owner","type of address","status of address","source","confirmation","active","primary",
				"correspondence","address line 2","city / town","county","post code","country","address line 1"};
		//iterate through the keys that have a value and execute them in correct entry order
		for (String i : entryOrder) {
			String value = paramsMap.get(i);
			if (value != null )
			{ 
			Logging.logToConsole("DEBUG", "DesktopEmail/Email: Key: " +i+ " Value: "+ value);	
			switch (i) {
			case "address owner":
				driver.findElement(By.xpath("//select[@id='address-owner']")).sendKeys(value);
				 break; 			
			case "type of address":
				driver.findElement(By.xpath("//select[@id='type-of-address']")).sendKeys(value);
				 break; 
				case "status of address":
					driver.findElement(By.xpath("//select[@id='status-of-address']")).sendKeys(value);
				 break; 
				case "source":
					driver.findElement(By.xpath("//select[@id='source']")).sendKeys(value);
				 break; 
				case "confirmation":
					driver.findElement(By.xpath("//select[@id='confirmation']")).sendKeys(value);
				 break; 
				case "active":
					 updateXpathString = "(//label[@for='checkbox-active'])[1]";
					 checkXpathString = "//label[@for='checkbox-active']/following-sibling::div/div/input";	
					 pageUtils.updateStupidCheckBox(value, checkXpathString, updateXpathString);
				 break; 
				case "primary":
					 updateXpathString = "(//label[@for='checkbox-primary'])[1]";
					 checkXpathString = "//label[@for='checkbox-primary']/following-sibling::div/div/input";	
					 pageUtils.updateStupidCheckBox(value, checkXpathString, updateXpathString);
					pageUtils.handleLaggyPopup(titleXpathString, "OK");
				 break; 
				case "correspondence":
					 updateXpathString = "(//label[@for='checkbox-correspondence'])[1]";
					 checkXpathString = "//label[@for='checkbox-correspondence']/following-sibling::div/div/input";	
					 pageUtils.updateStupidCheckBox(value, checkXpathString, updateXpathString);					
					pageUtils.handleLaggyPopup(titleXpathString, "OK");
				 break; 
				case "address line 1":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='address-line-1']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
				case "address line 2":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='address-line-2']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
				case "city / town":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='city--town']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
				case "county":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='county']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
				case "post code":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='post-code']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
				case "country":
					 tempWebElement =driver.findElement(By.xpath("//input[@id='country']")); 
					 tempWebElement.clear();
					 tempWebElement.sendKeys(value);
				 break; 
			
				}
			}
		}
		
		String buttonXpathString = "//footer[@id='addeditmodal___BV_modal_footer_']/div/fieldset/div/button[";
		if(paramsMap.get("address type").equalsIgnoreCase("paf")) {
			//Open the PAF dialogue and select the right address but this does not work yet
			//driver.findElement(By.xpath(buttonXpathString+"1]")).click();	
			//
			//								TBA
			//
			//
			//
			
		}

		if(driver.findElement(By.xpath(buttonXpathString+"2]")).isEnabled() == true) {
			driver.findElement(By.xpath(buttonXpathString+"2]")).click();
			pageUtils.ReturnHome();
			return;
			}
			else {
			//Click Cancel	
				driver.findElement(By.xpath(buttonXpathString+"3]")).click();
				pageUtils.ReturnHome();
				throw new Exception(logEntryPrefix+ "  cannot save update. button disabled");
				}	
		}	
		
		
		

		
}
