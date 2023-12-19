package testComponents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeDateCalcs extends BaseTest{

	public static String now(String datePattern) {
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern(datePattern);
		return nowDateTime.format(pattern);  
	}

		
	
public static boolean stageRunDateReached(String startDate, String dateDelayString, String dateDelayUnits) throws Exception {
	// variable pattern expected is $+999
	 LocalDateTime nowDateTime = LocalDateTime.now();
	 LocalDateTime targetDateTime = nowDateTime;
	 DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	 LocalDateTime dateTime = LocalDateTime.parse(startDate, pattern);
	 dateDelayUnits = dateDelayUnits.toLowerCase().split("s")[0];
	 int intDateDelay = Integer.parseInt(dateDelayString);
	 switch (dateDelayUnits) {
		case "second":
			targetDateTime = dateTime.plusSeconds(intDateDelay);	
		break;
		case "minute":
			targetDateTime = dateTime.plusMinutes(intDateDelay);
		break;
		case "hour":
			targetDateTime = dateTime.plusHours(intDateDelay);
		break;
		case "day":
			targetDateTime = dateTime.plusDays(intDateDelay);
		break;
	 }
	 
	 if(targetDateTime.isBefore(nowDateTime)) {
		 return true;
	 }else {return false;}
	}

	


public static String CalculateDate(String date, String datePattern) {
	// variable pattern expected is $+999
	//return if not a variable
	if(!date.substring(0,1).equals("$")){
		return date;
		}
	
	//If a variable then add or subtract the given number of days from/to todays date
	 LocalDate today = LocalDate.now();
	//long dayDelta = Long.parseLong(date.substring(2,7));
	 
	 int dayDelta = Integer.parseInt(date.substring(2,date.length()));
	 LocalDate targetDate;
	 
	if (date.substring(1,2).equals("+")){
	targetDate = today.plusDays(dayDelta);
	}
	else {
		targetDate = today.minusDays(dayDelta);
		}
	
	DateTimeFormatter pattern = DateTimeFormatter.ofPattern(datePattern);
	return targetDate.format(pattern);  

	}



public static String ReturnDate(String date, String datePattern) throws Exception {
	// variable pattern expected is $+999
	
	//If a variable then add or subtract the given number of days from/to todays date
	 LocalDate today = LocalDate.now();
	 LocalDate targetDate;
	 DateTimeFormatter pattern;
	 try {
		 int dayDelta = Integer.parseInt(date.substring(1,date.length()));
		 
		if (date.substring(0,1).equals("+")){
			targetDate = today.plusDays(dayDelta);
			}
			else {
				targetDate = today.minusDays(dayDelta);
				}
		
		pattern = DateTimeFormatter.ofPattern(datePattern);
	 	}catch (Exception e) {
			throw new Exception("TimeDateCalcs/ReturnDate/"+staticTestMap.get("account")+" : cannot convert date variable to date");
		}
	
	return targetDate.format(pattern);  

	}

}

