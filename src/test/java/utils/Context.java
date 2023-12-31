package utils;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import testComponents.*;
import pageObjects.*;


public class Context extends BaseTest {
	private PageUtils pageUtils;
	private FileTools fileTools;
	private DesktopVulnerabilites desktopVulnerabilites;
	private LoginPage loginPage;
	private SearchPage searchPage;
	private SplashPage splashPage;
	private DesktopAnchorPanels desktopAnchorPanels;
	private DPA dpa;
	private OpenAccount openAccount;
	private DesktopArrangements desktopArrangements;
	private DesktopDMC desktopDMC; 
	private DesktopInsolvency desktopInsolvency;
	private DesktopDisputes desktopDisputes; 
	private DesktopComplaints desktopComplaints; 
	private DesktopCommunications desktopCommunications;
	private DesktopCustomers desktopCustomers;
	private DesktopEmail desktopEmail;
	private DesktopPhone desktopPhone;
	private DesktopAddress desktopAddress;
	private StepDefCommonFunctions stepDefCommonFunctions;
	private DesktopBatchApi desktopBatchApi;
	
	
	public Context() throws IOException
	{
		initilizeDriver();
		getPageUtils();
		getFileTools();
	}
	
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	
	public PageUtils getPageUtils()
	{
		if(pageUtils==null)
		{
			pageUtils = new PageUtils(driver, testMap);
		}
		return pageUtils;
	} 
	
	public FileTools getFileTools()
	{
		if(fileTools==null)
		{
			fileTools = new FileTools(pageUtils);
		}
		return fileTools;
	} 
	

	public OpenAccount getOpenAccount()
	{
		if(openAccount==null)
		{
			getDPA();
			getSearchPage();
			getDesktopAnchorPanels();
			openAccount = new OpenAccount(driver, pageUtils, searchPage, dpa, desktopAnchorPanels);
		}
		return openAccount;
	} 
	
	
	public DPA getDPA()
	{
		if(dpa==null)
		{
			
			dpa = new DPA(driver, pageUtils);
			
		}
		return dpa;
	} 
	
	public StepDefCommonFunctions getStepDefCommonFunctions()
	{
		if(stepDefCommonFunctions==null)
		{
			getDesktopCustomers();
			stepDefCommonFunctions = new StepDefCommonFunctions(driver, pageUtils,fileTools, desktopCustomers );
		}
		return stepDefCommonFunctions;
	} 
	
	
	public DesktopVulnerabilites getDesktopVulnerabilites()
	{
		if(desktopVulnerabilites==null)
		{
			
			desktopVulnerabilites = new DesktopVulnerabilites(driver, pageUtils);
		}
		return desktopVulnerabilites;
	} 
	
	public LoginPage getLoginPage()
	{
		if(loginPage==null)
		{
			loginPage = new LoginPage(driver);
		}
		return loginPage;
	} 	
	
	public SplashPage getSplashPage()
	{
		if(splashPage==null)
		{
			splashPage = new SplashPage(driver);
		}
		return splashPage;
	} 	
	
	public SearchPage getSearchPage()
	{
		if(searchPage==null)
		{
			searchPage = new SearchPage(driver);
		}
		return searchPage;
	} 	
	
	public DesktopAnchorPanels getDesktopAnchorPanels()
	
	{
		if(desktopAnchorPanels==null)
		{
			desktopAnchorPanels = new DesktopAnchorPanels(driver, pageUtils);
		}
		return desktopAnchorPanels;
	} 	
	
	public DesktopArrangements getDesktopArrangements()
	{
		if(desktopArrangements==null)
		{
			
			desktopArrangements = new DesktopArrangements(driver, pageUtils);
		}
		return desktopArrangements;
	} 
	
	
	
	public DesktopDMC getDesktopDMC()
	{
		if(desktopDMC==null)
		{
			desktopDMC = new DesktopDMC(driver, pageUtils);
		}
		return desktopDMC;
	} 
	
	
	public DesktopInsolvency getDesktopInsolvency()
	{
		if(desktopInsolvency==null)
		{
			desktopInsolvency = new DesktopInsolvency(driver, pageUtils);
		}
		return desktopInsolvency;
	} 
	
	public DesktopDisputes getDesktopDisputes()
	{
		if(desktopDisputes==null)
		{
			desktopDisputes = new DesktopDisputes(driver, pageUtils);
		}
		return desktopDisputes;
	} 
	
	public DesktopComplaints getDesktopComplaints()
	{
		if(desktopComplaints==null)
		{
			desktopComplaints = new DesktopComplaints(driver, pageUtils);
		}
		return desktopComplaints;
	} 
	
	public DesktopCommunications getDesktopCommunications()
	{
		if(desktopCommunications==null)
		{
			desktopCommunications = new DesktopCommunications(driver, pageUtils);
		}
		return desktopCommunications;
	} 
	
	public DesktopCustomers getDesktopCustomers()
	{
		if(desktopCustomers==null)
		{
			desktopCustomers = new DesktopCustomers(driver, pageUtils);
		}
		return desktopCustomers;
	} 
	
	public DesktopEmail getDesktopEmail()
	{
		if(desktopEmail==null)
		{
			desktopEmail = new DesktopEmail(driver, pageUtils);
		}
		return desktopEmail;
	} 
	
	public DesktopPhone getDesktopPhone()
	{
		if(desktopPhone==null)
		{
			desktopPhone = new DesktopPhone(driver, pageUtils);
		}
		return desktopPhone;
	} 
	
	public DesktopAddress getDesktopAddress()
	{
		if(desktopAddress==null)
		{
			desktopAddress = new DesktopAddress(driver, pageUtils);
		}
		return desktopAddress;
	} 
	
	public DesktopBatchApi getDesktopBatchApi()
	{
		if(desktopBatchApi==null)
		{
			desktopBatchApi = new DesktopBatchApi(driver, pageUtils);
		}
		return desktopBatchApi;
	} 
	
}