package testComponents;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeDateCalcs {

	
	


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

}
