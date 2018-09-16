package net.azzerial.cgc.utils;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.dv8tion.jda.core.entities.Message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class MiscUtil {

	// General utils.

	public static final String INVISIBLE_SEPARATOR = "\u2063";

	public static final Consumer<Message> deleteOnTimeout = (m) -> {
		m.delete().queue();
	};

	public static final Consumer<Message> clearReactionsOnTimeout = (m) -> {
		m.clearReactions().queue();
	};

	// Database utils.

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

	public static String gregorianCalendarToString(GregorianCalendar gregorianCalendar) {
		if (gregorianCalendar == null) {
			return (null);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		return (dateFormat.format(gregorianCalendar.getTime()));
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

	// Math utils.

	public static int getRandomNumber(int origin, int maximumBound) {
		return (origin + (int)(Math.random() * ((maximumBound - origin))));
	}

	// List utils.

	public static class sortStringByIgnoreCase implements Comparator<String> {
		public int compare(String o1, String o2) {
			return (o1.toLowerCase().compareTo(o2.toLowerCase()));
		}
	}

	public static class sortDatabaseUserByIdolCollectionProgress implements Comparator<DatabaseUser> {
		public int compare(DatabaseUser o1, DatabaseUser o2) {
			return (o2.getIdolCollection().getCollectionsCardsProgress().getProgress() - o1.getIdolCollection().getCollectionsCardsProgress().getProgress());
		}
	}

	public static class sortDatabaseUserByBalance implements Comparator<DatabaseUser> {
		public int compare(DatabaseUser o1, DatabaseUser o2) {
			return (Math.toIntExact(o2.getBalance() - o1.getBalance()));
		}
	}

}
