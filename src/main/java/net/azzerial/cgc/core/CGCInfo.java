package net.azzerial.cgc.core;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CGCInfo {

	public static final String console_header =
		"_________________________________________________________________________________\n" +
		"__/\\\\\\\\\\\\________/\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\_________\n" +
		"__\\/\\\\\\//______/\\\\\\////////____/\\\\\\//////////______/\\\\\\////////__\\////\\\\\\________\n" +
		"___\\/\\\\\\______/\\\\\\/____________/\\\\\\_______________/\\\\\\/______________\\/\\\\\\_______\n" +
		"____\\/\\\\\\_____/\\\\\\_____________\\/\\\\\\____/\\\\\\\\\\\\\\__/\\\\\\________________\\/\\\\\\______\n" +
		"_____\\/\\\\\\____\\/\\\\\\_____________\\/\\\\\\___\\/////\\\\\\_\\/\\\\\\________________\\/\\\\\\_____\n" +
		"______\\/\\\\\\____\\//\\\\\\____________\\/\\\\\\_______\\/\\\\\\_\\//\\\\\\_______________\\/\\\\\\____\n" +
		"_______\\/\\\\\\_____\\///\\\\\\__________\\/\\\\\\_______\\/\\\\\\__\\///\\\\\\_____________\\/\\\\\\___\n" +
		"________\\/\\\\\\\\\\\\____\\////\\\\\\\\\\\\\\\\\\_\\//\\\\\\\\\\\\\\\\\\\\\\\\/_____\\////\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\__\n" +
		"_________\\//////________\\/////////___\\////////////__________\\/////////__\\//////__\n" +
			"_________________________________________________________________________________\n" +
		"[Core/CGCInfo]: Starting up the bot...";
	
	// Bot global infos
	public static final String BOT_AUTHOR = "Azzerial#5348";
	public static final String BOT_AUTHOR_ID = "153129618096783360";
	public static final String BOT_NAME = "Cute Girls Collection";
	public static final float BOT_VERSION = 0.1f;
	public static final String PREFIX = "/";

	// Jar file infos
	public static final String JAR_FILE_NAME = "CuteGirlsCollection.jar";
	
	public static String getPath() {
		File jarFile = CGC.getJarFile();
		String jarPath = jarFile.getAbsolutePath();
		int index = jarPath.lastIndexOf("/");
		
		if (index == -1) {
			return (".");
		}
		return (jarPath.substring(0, index));
	}
	
}
