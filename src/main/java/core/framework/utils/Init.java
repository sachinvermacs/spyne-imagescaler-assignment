package core.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class Init {

	public static final String PROJECTPATH = System.getProperty("user.dir");

	protected Init() {
	}

	static {
		System.setProperty("current.date.time", getDate());
	}

	protected static String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return (df.format(new Date()));
	}

	public static String getTime() {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("HH-mm-ss");
		df.setTimeZone(TimeZone.getTimeZone("IST"));
		return (df.format(new Date()));
	}
}