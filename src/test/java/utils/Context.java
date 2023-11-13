package utils;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import testComponents.*;
import pageObjects.*;


public class Context extends BaseTest {
	private PageUtils pageUtils;
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

	
	public Context() throws IOException
	{
		initilizeDriver();
		getPageUtils();
	}
	
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	
	public PageUtils getPageUtils()
	{
		if(pageUtils==null)
		{
			pageUtils = new PageUtils(driver);
		}
		return pageUtils;
	} 
	

	public OpenAccount getOpenAccount()
	{
		if(openAccount==null)
		{
			getDPA();
			getSearchPage();
			openAccount = new OpenAccount(driver, pageUtils, searchPage, dpa);
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
	
	//private DesktopInsolvency desktopInsolvency;
	
	public DesktopInsolvency getDesktopInsolvency()
	{
		if(desktopInsolvency==null)
		{
			desktopInsolvency = new DesktopInsolvency(driver, pageUtils);
		}
		return desktopInsolvency;
	} 
	
}