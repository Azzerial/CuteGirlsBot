package net.azzerial.cgc.utils;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;

public class EmoteUtil {

	public static final String BACK_ARROW = "\uD83D\uDD19";
	public static final String BALLOT_BOX_CHECK = "\u2611";
	public static final String CREDIT_CARD = "\uD83D\uDCB3";
	public static final String DIGIT_ONE = "\u0031\u20E3";
	public static final String DIGIT_TWO = "\u0032\u20E3";
	public static final String DIGIT_THREE = "\u0033\u20E3";
	public static final String DRESS = "\uD83D\uDC57";
	public static final String HANDBAG = "\uD83D\uDC5C";
	public static final String JAPANESE_DOLLS = "\ud83c\udf8e";
	public static final String LABEL = "\uD83C\uDFF7";
	public static final String LIGHT_BULB = "\uD83D\uDCA1";
	public static final String MEMO = "\uD83D\uDCDD";
	public static final String MONEY_WITH_WINGS = "\uD83D\uDCB8";
	public static final String NEW_BUTTON = "\uD83C\uDD95";
	public static final String OPEN_BOOK = "\uD83D\uDCD6";
	public static final String PROHIBITED = "\uD83D\uDEAB";
	public static final String PURSE = "\uD83D\uDC5B";
	public static final String REVOLVING_HEARTS = "\ud83d\udc9e";
	public static final String RIBBON = "\uD83C\uDF80";
	public static final String SHOPPING_BAGS = "\uD83D\uDECD";
	public static final String SHOPPING_CART = "\uD83D\uDED2";
	public static final String SLOT_MACHINE = "\uD83C\uDFB0";
	public static final String WARNING = "\u26A0\uFE0F";
	public static final String WRAPPED_GIFT = "\uD83C\uDF81";
	public static final String YEN_BANKNOTE = "\ud83d\udcb4";

	public static void printStringUnicode(String str) {
		System.out.println("Unicode of \"" + str + "\", char by char:");
		for (char c : str.toCharArray()) {
			System.out.printf("\\u%04x \n", (int) c);
		}
	}

	public static void printStringUnicode(MessageReactionAddEvent event) {
		if (event.getReactionEmote().isEmote()) {
			return;
		}
		printStringUnicode(event.getReactionEmote().getName());
	}

}
