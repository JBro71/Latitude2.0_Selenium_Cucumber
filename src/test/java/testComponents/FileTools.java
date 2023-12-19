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
	String testFilePath = "//src//test//java//data//tests//";
	
	
	public FileTools(PageUtils pageUtils) { 
		this.pageUtils = pageUtils;

	}

	public void getTestFile(String testId) throws Exception {
		String logEntryPrefix= "FileTools/getTestFile/file: " +testId+ ": " ; 
			String filePath = System.getProperty("user.dir")+ testFilePath + testId +".txt";
			if( FileUtils.isRegularFile(new File(filePath)) == true) {
				Logging.logToConsole("DEBUG", logEntryPrefix +"The Test file exists");
				List<String> fileLineList = FileUtils.readLines(new File(filePath), StandardCharsets.UTF_8);
				String[] lineArray = fileLineList.get(fileLineList.size()-1).split(",");
			 
				switch (lineArray[2]) {
					case "PASS":  // previous step passed on last run
						pageUtils.testMap.put("testAccount", lineArray[0]); //accountNumber
						pageUtils.testMap.put("lastStage", lineArray[1].split("E")[1]); //last stage number
						pageUtils.testMap.put("testStartDateTime", lineArray[3]); 
						pageUtils.testMap.put("lastStageDateTime", lineArray[4]);
						pageUtils.testMap.put("customer1", lineArray[5]);
						pageUtils.testMap.put("customer2", lineArray[6]);
						pageUtils.testMap.put("run", "false");
						return;	
					case "COMPLETE": //test has already been completed
						if(System.getProperty("restart").equalsIgnoreCase("true")) {
							caseStart();
						}else {
							pageUtils.testMap.put("lastStage", "0" ); //last stage number
							pageUtils.testMap.put("run", "false");
						}
						break;
					case "INPROGRESS": // previous step did not complete
						int lastStageInt = Integer.parseInt(lineArray[1].split("E")[1]);
						if(System.getProperty("retry").equalsIgnoreCase("true")) {
							if (lastStageInt > 1) { //retry from last stage tried
								pageUtils.testMap.put("testAccount", lineArray[0]); //accountNumber
								pageUtils.testMap.put("lastStage", Integer.toString(Integer.parseInt(lineArray[1].split("E")[1])-1)); //skip back one stage
								pageUtils.testMap.put("testStartDateTime", lineArray[3]); 
								pageUtils.testMap.put("lastStageDateTime", lineArray[4]);
								pageUtils.testMap.put("customer1", lineArray[5]);
								pageUtils.testMap.put("customer2", lineArray[6]);
								pageUtils.testMap.put("run", "false");	
								}else { //last stage attempted was first stage so try again from the start with new data
									pageUtils.testMap.put("lastStage", "0" ); //last stage number
									pageUtils.testMap.put("run", "true");									
									}
							
						}else {// retry is false so don't attempt the case again
							pageUtils.testMap.put("lastStage", "0" ); //last stage number
							pageUtils.testMap.put("run", "false");
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
		pageUtils.testMap.put("testAccount", ""); //accountNumber
		pageUtils.testMap.put("customer1", "");
		pageUtils.testMap.put("customer2", "");
		pageUtils.testMap.put("lastStage", "0" ); //last stage number
		pageUtils.testMap.put("testStartDateTime", nowDate ); //last stage number
		pageUtils.testMap.put("lastStageDateTime", nowDate ); //last stage number			
		pageUtils.testMap.put("run", "true");
		
	}
	
	
	public void writeTestFileLine(String testId, String status) throws IOException {
		String filePath = System.getProperty("user.dir")+ testFilePath + testId +".txt";
		String fileLine = pageUtils.testMap.get("testAccount")+","+ 
			"STAGE"+(Integer.parseInt(pageUtils.testMap.get("lastStage"))+1)+","+
			status+","+
			pageUtils.testMap.get("testStartDateTime")+","+ 
			TimeDateCalcs.now("dd-MM-yyyy HH:mm:ss")+","+ 
			pageUtils.testMap.get("customer1")+","+ 
			pageUtils.testMap.get("customer2");
		FileUtils.writeStringToFile(new File(filePath), "\n"+fileLine, StandardCharsets.UTF_8,true);
	}
	
	
}
