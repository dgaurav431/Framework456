package vtiger.genericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	
	public static int generateRandomNumber(int limit) {
		
		Random ran = new Random();
		return ran.nextInt(limit);
	}
	
	public static String generateSystemDateAndTime() {
		
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}
	
}
