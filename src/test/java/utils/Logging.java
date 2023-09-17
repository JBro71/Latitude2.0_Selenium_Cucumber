package utils;

import testComponents.BaseTest;

public class Logging {
	public static void logToConsole(String messageLevel, String message) {
		Integer messageLevelInt = logLevelLookup(messageLevel);
		Integer setLevelInt = Integer.parseInt(BaseTest.prop.getProperty("logLevel"));
		if (setLevelInt >= messageLevelInt) {
			System.out.println(messageLevel.toUpperCase() + " Message: " + message);
		}

	}
	
	public static Integer logLevelLookup(String logLevelLiteral) {
		int levelInt = 0;
		
		switch (logLevelLiteral.toLowerCase()) {
		case "debug":
			levelInt = 4;
			break;
		case "info":
			levelInt = 3;
			break;	
		case "warning":
			levelInt = 2;
			break;
		case "error":
			levelInt = 1;
			break;	
		case "off":
			levelInt = 0;
			break;	
		}
		return levelInt;
		
	}
	
}