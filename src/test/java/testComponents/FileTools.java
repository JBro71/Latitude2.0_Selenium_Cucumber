package testComponents;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import testComponents.PageUtils;
import utils.Context;
import utils.Logging;


	
public class FileTools  extends BaseTest {
	PageUtils pageUtils;
	static String dataFilePath = "//src//test//java//data//working//";
	static String testReportPath = "//target//JunitReports//";
	String testFilePath = "//src//test//java//data//tests//";
	String[] testFileLineDataItems = {"account","lastStage","lastState","testStartDateTime","lastStageDateTime","customer1","customer2",
			"customer1Id","customer2Id","customer1ClientId","customer2ClientId","originalBalance","originalArrears"};
	String testId = "";
	
	public FileTools(PageUtils pageUtils) { 
		this.pageUtils = pageUtils;

	}
	

	
	@SuppressWarnings("null")
	public void loadTestDataFromFile(String filename) throws Exception {
		String logEntryPrefix= "FileTools/loadTestDataFromFile/file: "+ filename +" test: " +testId+ ": " ; 
		if(!BaseTest.staticTestDataMap.containsKey(filename)) {// if Test data map has not already been populated with this data by another test
			String filePath = System.getProperty("user.dir")+ dataFilePath + filename +".txt";
			if( FileUtils.isRegularFile(new File(filePath)) == true) {
				Logging.logToConsole("DEBUG", logEntryPrefix +"The test data file exists");
				List<String> fileLineList = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
				/*
				List<String[]> dataArraylist = null;
				for (String entry : fileLineList) {
					dataArraylist.add(entry.split(","));
				}
				*/
				BaseTest.staticTestDataMap.put(filename, fileLineList);
				
				}else {throw new Exception(logEntryPrefix+" test data file not found");}
		}else {Logging.logToConsole("DEBUG", logEntryPrefix +"The test data file already loaded");};
	}
	
	
	public static void writeTestDataToFile() throws Exception {
		//String logEntryPrefix= "FileTools/writeTestDataToFile: " ; 
		String filePath = System.getProperty("user.dir")+ dataFilePath;
		for (String key : BaseTest.staticTestDataMap.keySet()) { //for each set of test data
			List<String> fileLineList = BaseTest.staticTestDataMap.get(key);
			FileUtils.writeLines(new File(filePath+key+".txt"), fileLineList,"\n",false);
		} 
	}
	
	
	public void loadTestFileLine(String[] lineArray) throws Exception {
			for (int i=0; i<testFileLineDataItems.length; i++) {
				try {
					pageUtils.updateTestMap(testFileLineDataItems[i],lineArray[i], true);
					}
					catch (Exception e) {
						pageUtils.updateTestMap(testFileLineDataItems[i],"", true);
						}
			}
	}

	
	public void getTestFile(String testId) throws Exception {
		this.testId = testId;
		String logEntryPrefix= "FileTools/getTestFile/file: " +testId+ ": " ; 
			String filePath = System.getProperty("user.dir")+ testFilePath + testId +".txt";
			if(FileUtils.isRegularFile(new File(filePath)) == true) {
				Logging.logToConsole("DEBUG", logEntryPrefix +"The Test file exists");
				List<String> fileLineList = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
				String[] lineArray = fileLineList.get(fileLineList.size()-1).split(",");
			 
				switch (lineArray[2]) {
					case "PASS":  // previous step passed on last run
						loadTestFileLine(lineArray);
						//pageUtils.updateTestMap("account", lineArray[0],true); //accountNumber
						//pageUtils.updateTestMap("lastStage", lineArray[1].split("E")[1], true); //last stage number
						//pageUtils.updateTestMap("testStartDateTime", lineArray[3], false); 
						//pageUtils.updateTestMap("lastStageDateTime", lineArray[4], false);
						//pageUtils.updateTestMap("customer1", lineArray[5], true);
						//if(lineArray.length>6) {pageUtils.updateTestMap("customer2", lineArray[6], true);}
						pageUtils.updateTestMap("run", "false", true);
						
						return;	
					case "COMPLETE": //test has already been completed
						if(prop.getProperty("restart").equalsIgnoreCase("true")) {
							testComponents.FileTools.writeTestReport("Test Previously Completed, Restarting");
							caseStart();
						}else {// no restart so do nothing
							pageUtils.updateTestMap("lastStage", "0", true ); //last stage number
							pageUtils.updateTestMap("run", "false", true);
							testComponents.FileTools.writeTestReport("Test Previously Completed, RESTART not Enabled");
						}
						break;
					case "INPROGRESS": // previous step did not complete
						int lastStageInt = Integer.parseInt(lineArray[1]);
						if(prop.getProperty("retry").equalsIgnoreCase("true")) {
							if (lastStageInt > 1) { //retry from last stage tried
								loadTestFileLine(lineArray);
								//pageUtils.updateTestMap("account", lineArray[0], true); //accountNumber
								//pageUtils.updateTestMap("lastStage", Integer.toString(lastStageInt-1),true); //skip back one stage
								//pageUtils.updateTestMap("testStartDateTime", lineArray[3],false); 
								//pageUtils.updateTestMap("lastStageDateTime", lineArray[4],false);
								//pageUtils.updateTestMap("customer1", lineArray[5],true);
								//if(lineArray.length>6) {pageUtils.updateTestMap("customer2", lineArray[6], true);}
								pageUtils.updateTestMap("lastStage", Integer.toString(lastStageInt-1),true); //skip back one stage
								pageUtils.updateTestMap("run", "false",true);	
								}else { //last stage attempted was first stage so try again from the start with new data
									testComponents.FileTools.writeTestReport("STAGE1, Previously failed, retstarting with new data");
									caseStart();
									}
							
						}else {// retry is false so don't attempt the case again
							pageUtils.updateTestMap("lastStage", "0" ,true); //last stage number
							pageUtils.updateTestMap("run", "false",true);
							testComponents.FileTools.writeTestReport("STAGE"+(lastStageInt-1)+",Previously failed, RETRY not enabled");
							}
						break;
					default:
						break;
			 
			 		}
			 
				}else 
					{
					Logging.logToConsole("DEBUG", logEntryPrefix +"file does not exist. Creating Test File");
					caseStart();
					}
			Logging.logToConsole("DEBUG", logEntryPrefix);
			
				//throw new Exception("updateCheckBox "+ testMap.get("account")+" :  unable to update " + xpath);}
		}
	
	
	public void caseStart() throws IOException {
		String nowDate = TimeDateCalcs.now("dd-MM-YYYY HH:mm:ss");
		for (int i=0; i<testFileLineDataItems.length; i++) {
				pageUtils.updateTestMap(testFileLineDataItems[i],"", true);
				}
		pageUtils.updateTestMap("lastStage", "0",true ); //last stage number
		pageUtils.updateTestMap("testStartDateTime", nowDate , false); //last stage number
		pageUtils.updateTestMap("lastStageDateTime", nowDate , false); //last stage number			
		pageUtils.updateTestMap("run", "true", true);
	}
	
	
	public void writeTestFileLine(String testId, String status) throws IOException {
		String filePath = System.getProperty("user.dir")+ testFilePath + testId +".txt";
		String fileLine = pageUtils.testMap.get("account")+","+ 
			(Integer.parseInt(pageUtils.testMap.get("lastStage"))+1)+","+
			status+","+
			pageUtils.testMap.get("testStartDateTime")+","+ 
			TimeDateCalcs.now("dd-MM-yyyy HH:mm:ss")+","+ 
			pageUtils.testMap.get("customer1")+","+ 
			pageUtils.testMap.get("customer2")+","+
			pageUtils.testMap.get("customer1Id")+","+ 
			pageUtils.testMap.get("customer2Id")+","+
			pageUtils.testMap.get("customer1ClientId")+","+ 
			pageUtils.testMap.get("customer2ClientId")+","+
			pageUtils.testMap.get("originalBalance")+","+ 
			pageUtils.testMap.get("originalArrears");
		
		FileUtils.writeStringToFile(new File(filePath), "\n"+fileLine, StandardCharsets.UTF_8,true);
	}
	
	public static void writeTestReport(String filelineStr) throws IOException {
		String filePath = System.getProperty("user.dir")+ testReportPath + BaseTest.staticTestMap.get("testReportName") +".txt";
		FileUtils.writeStringToFile(new File(filePath), filelineStr+"\n", StandardCharsets.UTF_8,true);
	}
	
	
	
}
