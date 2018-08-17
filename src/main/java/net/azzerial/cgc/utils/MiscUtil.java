package net.azzerial.cgc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Date;

public class MiscUtil {

	public static String listToString(List<String> list, String separator) {
		String str = "";

		if (list == null || list.isEmpty()) {
			return (str);
		}
		for (String s : list) {
			str += s + separator;
		}
		return (str.substring(0, str.length() - separator.length()));
	}

	public static List<String> stringToList(String str, String separator) {
		if (str == null || str.isEmpty()) {
			return (new ArrayList<String>());
		}
		return (new ArrayList<String>(Arrays.asList(str.split(separator))));
	}

	public static class sortIgnoreCase implements Comparator<String> {
		public int compare(String str1, String str2) {
			return (str1.toLowerCase().compareTo(str2.toLowerCase()));
		}
	}

	public static GregorianCalendar stringToGregorianCalendar(String str) {
		if (str == null) {
			return (null);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		GregorianCalendar gregorianCalendar = new GregorianCalendar();

		try {
			Date date = dateFormat.parse(str);
			gregorianCalendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return (null);
		}
		return (gregorianCalendar);
	}

	public static String gregorianCalendarToString(GregorianCalendar gregorianCalendar) {
		if (gregorianCalendar == null) {
			return (null);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		return (dateFormat.format(gregorianCalendar.getTime()));
	}

}
