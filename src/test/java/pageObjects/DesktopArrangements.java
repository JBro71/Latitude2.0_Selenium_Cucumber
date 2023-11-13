package pageObjects;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testComponents.PageUtils;
import utils.Logging;


public class DesktopArrangements {

	
	WebDriver driver;
	PageUtils pageUtils;
		
		public DesktopArrangements(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
		}
	
	public void AddBankDetails(HashMap<String,String> paramsMap) throws InterruptedException {
		Logging.logToConsole("INFO","**********************Add Bank Details *************************");
		Logging.logToConsole("DEBUG", "values: "+ paramsMap);
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lcParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {lcParamsMap.put(key.toLowerCase(), value);});
		pageUtils.Scroll(300); //open the arrangement panel and scroll to it
		driver.findElement(By.xpath("//arrangements-menu[@class='ng-scope ng-isolate-scope']")).click();//open arrangement panel
		driver.findElement(By.xpath("//a[normalize-space()='+']")).click();//add payment method
		driver.findElement(By.xpath("//a[@ng-click=\"vm.openModal('add')\"]")).click(); // add
		driver.findElement(By.xpath("//li[@ng-class=\"{ active: vm.addMode == 'DirectDebit' }\"]")).click(); //add DD
		driver.findElement(By.xpath("//input[@title='Must be 8 digits']")).sendKeys(lcParamsMap.get("bank account number"));
		driver.findElement(By.xpath("//input[@title='Must be 6 digits']")).sendKeys(lcParamsMap.get("bank sort code"));
		driver.findElement(By.xpath("//button[@ng-click='vm.ddValidation()']")).click();
		driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='OK']")).click();
		Logging.logToConsole("INFO","DesktopArrangements/AddBankDetails: Added");
		
	}
	
	
	public void AddPaymentCard(HashMap<String,String> paramsMap) throws InterruptedException {
		Logging.logToConsole("INFO","DesktopArrangements/Add Payment Card: Start");
		Logging.logToConsole("DEBUG", "DesktopArrangements/Add Payment Card: values: "+ paramsMap);
		pageUtils.Scroll(300); //open the arrangement panel and scroll to it
		driver.findElement(By.xpath("//arrangements-menu[@class='ng-scope ng-isolate-scope']")).click();//open arrangement panel
		
		//ensure all the keys in the map are lower case	
		HashMap<String,String> lcParamsMap = new HashMap<String, String>();
		paramsMap.forEach((key, value) -> {lcParamsMap.put(key.toLowerCase(), value);});
		
		driver.findElement(By.xpath("//a[normalize-space()='+']")).click();//add payment method
		driver.findElement(By.xpath("//a[@ng-click=\"vm.openModal('add')\"]")).click(); // add
		
		//Enter the card details
		driver.findElement(By.xpath("//input[@id='entry']")).sendKeys(paramsMap.get("card number")); // enter card number
		driver.findElement(By.xpath("//td[normalize-space()='Name:']/following-sibling::td//input")).sendKeys(lcParamsMap.get("card name")); //enter card name
		
		driver.findElement(By.xpath("//a[@placeholder='Month']//b")).click(); // Click month picker
		driver.findElement(By.xpath("(//input[@type='search'])[1]")).sendKeys(lcParamsMap.get("card expiry month")); //enter month
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@ng-bind-html='item.name']")));
		driver.findElement(By.xpath("//span[@ng-bind-html='item.name']")).click(); // click month
		
		
		driver.findElement(By.xpath("//input[@placeholder='Year']")).clear(); // clear year
		driver.findElement(By.xpath("//input[@placeholder='Year']")).sendKeys(lcParamsMap.get("card expiry year")); // add year
		
		driver.findElement(By.xpath("//input[@ng-model='vm.wallet.cardSecurityCode']")).sendKeys(lcParamsMap.get("card cv2")); // add security code
		
		driver.findElement(By.xpath("//input[@ng-model='vm.wallet.useDebtorAddress']")).click(); //check box
		driver.findElement(By.xpath("//input[@ng-model='vm.wallet.shouldSave']")).click(); //check box
		//driver.findElement(By.xpath("//input[@ng-model='vm.wallet.replaceExisting']")).click(); //check box
		driver.findElement(By.xpath("//button[@ng-click='vm.validateCards()']")).click(); //add
		driver.findElement(By.xpath("//button[@class='btn'][normalize-space()='OK']")).click(); //ok
		Logging.logToConsole("INFO","DesktopArrangements/Add Payment Card: Payment Card Added");
		
		}


	public void AddArrangment(HashMap<String,String> paramsMap) throws InterruptedException {
		Logging.logToConsole("INFO","DesktopArrangements/AddArrangment: Start");
		Logging.logToConsole("DEBUG", "DesktopArrangements/AddArrangment: values: "+ paramsMap);
		pageUtils.Scroll(300); //open the arrangement panel and scroll to it
		driver.findElement(By.xpath("//arrangements-menu[@class='ng-scope ng-isolate-scope']")).click();//open arrangement panel
		for(int i=1;i<20 ;i++) { //wait until this element is populated to move on otherwise will fail
			Thread.sleep(500);
			Logging.logToConsole("DEBUG", "DesktopArrangements/AddArrangment: waiting for arrangement panel");
			if (driver.findElement(By.xpath("//input[@name='negotiated']")).getAttribute("value") != "") 
			{Logging.logToConsole("INFO", "waited " + i*500 + " miliseconds for arrangement panel");
			break;}
		}
				
			Thread.sleep(1000);
			// order in which the data should be entered
			String[] entryOrder = {"method", "start date","amount", "first amount", "frequency", 
			"surcharge amount", "payments","total","surcharge total","gross total","negotiated","settlement"};	
			
			//ensure all the keys in the map are lower case	
			HashMap<String,String> lcParamsMap = new HashMap<String, String>();
			paramsMap.forEach((key, value) -> {lcParamsMap.put(key.toLowerCase(), value);});
			
			for (String i : entryOrder) { //iterate through the values in the entry order array
				String value = lcParamsMap.get(i); // pull matching values from the Map and process them in order
				if (value != null )
					{ 
					Logging.logToConsole("DEBUG", "DesktopArrangements/AddArrangment: Arrangment Key: '" +i+ "' Value: "+ value);	
					//Thread.sleep(5000);
					switch (i) {
						case "method":
							Select paymentMethod =  new Select(driver.findElement(By.xpath("//select[@ng-model='vm.model.walletId']")));
							paymentMethod.selectByVisibleText(value);
							//Logging.logToConsole("DEBUG", "payment method selected");
						break;
						case "start date":
							
						break;
						case "amount":
						String amount = paymentAmount(lcParamsMap.get("amount"));
							if (amount!="0") { //zero = full balance no need to set
								driver.findElement(By.xpath("//input[@name='amount']")).clear();	
								driver.findElement(By.xpath("//input[@name='amount']")).sendKeys(amount);	
							}
						break;
						case "first amount":
							
							break;
						case "frequency":
							
							break;
						case "surcharge amount":
							
							break;
						case "payments":
							driver.findElement(By.xpath("//span[@ng-hide='vm.paymentCountEditable']")).click(); //click to enable field
							driver.findElement(By.xpath("//input[@name='count']")).clear();	
							driver.findElement(By.xpath("//input[@name='count']")).sendKeys(lcParamsMap.get("payments"));	
							break;
						case "total":
							pageUtils.Scroll(300);  //open the arrangement panel and scroll to it							
							amount = paymentAmount(lcParamsMap.get("total"));
							driver.findElement(By.xpath("//span[@ng-show='vm.amountEditable'][normalize-space()='ON']")).click();
							driver.findElement(By.xpath("//span[@ng-show='vm.totalEditable']/following-sibling::span")).click();	
							if (amount!="0") { //zero = full balance no need to set
								driver.findElement(By.xpath("//input[@name='total']")).clear();	
								driver.findElement(By.xpath("//input[@name='total']")).sendKeys(amount);	
							}
							break;
							
						case "surcharge total":
							
							break;
						case "gross total":
							
							break;
						case "negociated":
							
							break;
						case "settlement":
							
							break;
					}
					}
			}
			pageUtils.Scroll(600); 
			driver.findElement(By.xpath("//button[normalize-space()='Propose']")).click(); // propose
			driver.findElement(By.xpath("//button[@ng-show='vm.showSave()']")).click();  // save	
			pageUtils.ReturnHome(); 
		}


	


	public String paymentAmount(String amount) { 
		// interpret the parameters and set the payment amount
		switch (amount) {
			case "$arrears": // get the arrears value from the anchor panels
				amount = DesktopAnchorPanels.accountAnchorMap.get("arrears balance").substring(1);
			break;
			case "$balance":
				amount = "0";
			break;	
			}
		return amount;
	}
			
	
	
	
	
	
	
	
}
