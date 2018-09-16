package net.azzerial.cgc.core;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.Executors;

import javax.security.auth.login.LoginException;

import net.azzerial.cgc.commands.*;
import net.azzerial.cgc.commands.currency.*;
import net.azzerial.cgc.commands.fun.HugCommand;
import net.azzerial.cgc.commands.imcg.*;
import net.azzerial.cgc.database.Database;
import net.azzerial.cgc.database.DatabaseUserManager;
import net.azzerial.cgc.database.Permissions;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.DebugUtil;
import net.azzerial.imcg.idols.core.IdolsList;
import net.azzerial.imcg.items.core.ItemsList;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class CGC {

	// Non error exit codes.
	public static final int NORMAL_SHUTDOWN = 0;
	public static final int NEWLY_CREATED_FILE = 1;
	
	// Error exit codes.
	public static final int INVALID_INFORMATION_PROVIDED = 10;
	public static final int BAD_TOKEN_PROVIDED = 11;
	public static final int UNABLE_TO_CONNECT_TO_DISCORD = 12;
	public static final int UNSUPPORTED_SYSTEM = 13;
	
	private static JDA api;
	private static MessageScheduler scheduler;

	public static String dataDirectory;
	public static boolean ready;

	public static void main(String[] args) {
		ready = false;
		if (args.length == 1) {
			dataDirectory = args[0];
		} else {
			dataDirectory = "./";
		}
		System.out.println(CGCInfo.console_header);
		
		if (System.getProperty("file.encoding").equals("UTF-8")) {
			setupBot();
			ready = true;
			System.out.println("[Core/CGC]: Hurray! Looks like we're online captain!");
		} else {
			System.out.println("[Core/CGC]: This system is actually running in: \"" + System.getProperty("file.encoding") + "\" mode.");
			System.out.println("[Core/CGC]: We are not running in UTF-8 mode! This is a problem!");
			relaunchInUTF8();
		}
	}

	public static JDA getAPI() {
		return (api);
	}

	public static MessageScheduler getMessageScheduler() {
		return (scheduler);
	}

	private static void setupBot() {
		try {
			// Load bot configuration settings.
			Settings settings = SettingsManager.getInstance().getSettings();

			// Cache database.
			Database.getInstance();
			IdolsList.loadIdols();			// Load the idols list.
			ItemsList.loadItems();			// Load the items.
			DatabaseUserManager.loadUsers();	// Load the users.

			// Set the Ops list.
			Permissions.setupPermissions();
			System.out.println("[Core/CGC]: Loaded Permissions Ops.");

			// Offline
			//System.exit(UNABLE_TO_CONNECT_TO_DISCORD);

			// Start the JDA api.
			JDABuilder jda_builder = new JDABuilder(AccountType.BOT);
			jda_builder.setToken(settings.getBotToken());
			jda_builder.setAudioEnabled(settings.getAudioEnabled());
			jda_builder.setAutoReconnect(settings.getAutoReconnected());
			jda_builder.setGame(settings.getGame());
			jda_builder.setStatus(settings.getStatus());
			
			// Register the commands.
			HelpCommand command = new HelpCommand();
			jda_builder.addEventListener(command.registerCommand(command));
			jda_builder.addEventListener(command.registerCommand(new BalanceCommand()));
			jda_builder.addEventListener(command.registerCommand(new CollectionCommand()));
			jda_builder.addEventListener(command.registerCommand(new DailyCommand()));
			jda_builder.addEventListener(command.registerCommand(new GiveCommand()));
			jda_builder.addEventListener(command.registerCommand(new HugCommand()));
			jda_builder.addEventListener(command.registerCommand(new IdolCommand()));
			jda_builder.addEventListener(command.registerCommand(new InventoryCommand()));
			jda_builder.addEventListener(command.registerCommand(new OpCommand()));
			jda_builder.addEventListener(command.registerCommand(new PayCommand()));
			jda_builder.addEventListener(command.registerCommand(new ShopCommand()));
			jda_builder.addEventListener(command.registerCommand(new ShutdownCommand()));
			jda_builder.addEventListener(command.registerCommand(new SlotsCommand()));
			jda_builder.addEventListener(command.registerCommand(new TestCommand()));

			// Add some needed event listeners.
			scheduler = new MessageScheduler(Executors.newSingleThreadScheduledExecutor());
			jda_builder.addEventListener(scheduler);
			jda_builder.addEventListener(new DebugUtil(false));

			// Login to Discord.
			api = jda_builder.buildBlocking();

		} catch (IllegalArgumentException e) {
			System.out.println("[Core/CGC]: Some of the needed informations went missing! Please provide valid informations in the Config.json file.");
			e.printStackTrace();
			System.exit(INVALID_INFORMATION_PROVIDED);
		} catch (LoginException e) {
			System.out.println("[Core/CGC]: The provided botToken was incorrect! Please provide a valid botToken in the Config.json file.");
			e.printStackTrace();
			System.exit(BAD_TOKEN_PROVIDED);
		} catch (InterruptedException e) {
			System.out.println("[Core/CGC]: Oh no! Our login thread got interruped!");
			e.printStackTrace();
			System.exit(UNABLE_TO_CONNECT_TO_DISCORD);
		}
	}
	
	public static File getJarFile() {
		String path = CGC.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = null;
		
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!decodedPath.endsWith(".jar")) {
			return (new File(CGCInfo.JAR_FILE_NAME));
		}
		return (new File(decodedPath));
	}
	
	private static void relaunchInUTF8() {
		String[] command = new String[] {"java", "-Dfile.encoding=UTF-8", "-jar", CGC.getJarFile().getAbsolutePath()};
		
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
		try {
			System.out.println("[Core/CGC]: Relaunching in \"UTF-8\" mode using: -Dfile.encoding=UTF-8\n");
			Process process = processBuilder.start();
			process.waitFor();
			System.exit(process.exitValue());
		} catch (IOException | InterruptedException e) {
			System.out.println("[Core/CGC]: Failed to relaunch in \"UTF-8\" mode using: -Dfile.encoding=UTF-8");
			e.printStackTrace();
			System.exit(UNSUPPORTED_SYSTEM);
		}
	}

}
