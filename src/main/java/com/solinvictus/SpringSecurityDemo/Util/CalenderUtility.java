package com.solinvictus.SpringSecurityDemo.Util;

import com.solinvictus.SpringSecurityDemo.Exceptions.InvalidMonthValueException;

public class CalenderUtility {

	public static int monthStrToNum(String month) throws InvalidMonthValueException {
		if(month.equalsIgnoreCase("JANUARY"))
			return 1;
		else if(month.equalsIgnoreCase("FEBRUARY"))
			return 2;
		else if(month.equalsIgnoreCase("MARCH"))
			return 3;
		else if(month.equalsIgnoreCase("APRIL"))
			return 4;
		else if(month.equalsIgnoreCase("MAY"))
			return 5;
		else if(month.equalsIgnoreCase("JUNE"))
			return 6;
		else if(month.equalsIgnoreCase("JULY"))
			return 7;
		else if(month.equalsIgnoreCase("AUGUST"))
			return 8;
		else if(month.equalsIgnoreCase("SEPTEMBER"))
			return 9;
		else if(month.equalsIgnoreCase("OCTOBER"))
			return 10;
		else if(month.equalsIgnoreCase("NOVEMBER"))
			return 11;
		else if(month.equalsIgnoreCase("DECEMBER"))
			return 12;
		throw new InvalidMonthValueException("The month value passed is invalid.");
			
	}
}
