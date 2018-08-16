package net.azzerial.cgc.core;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CGCInfo {

	public static final String console_header =
		"____________________________________________________________________________________________________\r\n" +
		"__/\\\\\\\\\\\\_____/\\\\\\\\\\\\\\\\\\\\\\__________/\\\\\\\\\\\\\\\\\\____/\\\\\\\\\\\\\\\\\\______/\\\\\\\\\\\\\\\\\\\\\\\\_____/\\\\\\\\\\\\_________\r\n" + 
		"__\\/\\\\\\//____/\\\\\\/////////\\\\\\_____/\\\\\\////////___/\\\\\\///////\\\\\\___\\/\\\\\\////////\\\\\\__\\////\\\\\\________\r\n" + 
		"___\\/\\\\\\_____\\//\\\\\\______\\///____/\\\\\\/___________\\///______\\//\\\\\\__\\/\\\\\\______\\//\\\\\\____\\/\\\\\\_______\r\n" + 
		"____\\/\\\\\\______\\////\\\\\\__________/\\\\\\_______________________/\\\\\\/___\\/\\\\\\_______\\/\\\\\\____\\/\\\\\\______\r\n" + 
		"_____\\/\\\\\\_________\\////\\\\\\______\\/\\\\\\____________________/\\\\\\//_____\\/\\\\\\_______\\/\\\\\\____\\/\\\\\\_____\r\n" + 
		"______\\/\\\\\\____________\\////\\\\\\___\\//\\\\\\________________/\\\\\\//________\\/\\\\\\_______\\/\\\\\\____\\/\\\\\\____\r\n" + 
		"_______\\/\\\\\\_____/\\\\\\______\\//\\\\\\___\\///\\\\\\____________/\\\\\\/___________\\/\\\\\\_______/\\\\\\_____\\/\\\\\\___\r\n" + 
		"________\\/\\\\\\\\\\\\_\\///\\\\\\\\\\\\\\\\\\\\\\/______\\////\\\\\\\\\\\\\\\\\\__/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\/____/\\\\\\\\\\\\__\r\n" + 
		"_________\\//////____\\///////////___________\\/////////__\\///////////////__\\////////////_____\\//////__\r\n" +
		"____________________________________________________________________________________________________\r\n" +
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