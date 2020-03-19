package edu.iit.cs445.gheo1.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateFormat {
	public static String toyyyyMMdd(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd").format(d);
	}
	
	public static String toyyyyMMddWithDash(String d) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date start = sdf.parse(d);
		sdf.applyPattern("yyyy-MM-dd");
		return sdf.format(start);
	}
}
