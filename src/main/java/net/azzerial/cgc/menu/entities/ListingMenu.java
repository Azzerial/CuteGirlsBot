package net.azzerial.cgc.menu.entities;

import net.azzerial.cgc.menu.core.Menu;
import net.azzerial.cgc.menu.core.MessageScheduler;
import net.azzerial.cgc.utils.EmoteUtil;
import net.azzerial.cgc.utils.MessageUtil;
import net.azzerial.cgc.utils.MiscUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.requests.RestAction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ListingMenu extends Menu {

	private static final String PLAY = "\u25B6";
	private static final String REVERSE = "\u25C0";
	private static final String FAST_FORWARD = "\u23E9";
	private static final String FAST_REVERSE = "\u23EA";
	private static final String HEAVY_MULTIPLICATION_X = "\u2716";

	private final String title;
	private final String titleEmote;
	private final String thumbnail;
	private final String description;
	private final String emptyItemsListDescription;
	private final String image;
	private final String codeblockLanguage;
	private final List<String> items;
	private final int pageAmount;
	private final int itemsPerPage;
	private final int pageSkipAmount;
	private final boolean showItemNumber;
	private final boolean showPageNumber;
	private final boolean enablePageSkip;
	private final boolean closable;
	private final Consumer<Message> timeoutAction;

	ListingMenu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit,
		    String title, String titleEmote, String thumbnail, String description, String emptyItemsListDescription, String image, String codeblockLanguage,
		    List<String> items, int itemsPerPage, int pageSkipAmount, boolean showItemNumber, boolean showPageNumber, boolean enablePageSkip, boolean orderItems,
		    boolean closable, Consumer<Message> timeoutAction) {
		super(scheduler, users, roles, timeout, unit);
		this.title = title;
		this.titleEmote = titleEmote;
		this.thumbnail = thumbnail;
		this.description = description;
		this.emptyItemsListDescription = emptyItemsListDescription;
		this.image = image;
		this.codeblockLanguage = codeblockLanguage;
		this.items = items;
		this.pageAmount = (int) Math.ceil((float) items.size() / itemsPerPage);
		this.itemsPerPage = itemsPerPage;
		this.pageSkipAmount = pageSkipAmount;
		this.showItemNumber = showItemNumber;
		this.showPageNumber = showPageNumber;
		this.enablePageSkip = enablePageSkip;
		this.closable = closable;
		this.timeoutAction = timeoutAction;

		if (orderItems) {
			Collections.sort(this.items, new MiscUtil.sortStringByIgnoreCase());
		}
	}

	@Override
	public void display(MessageChannel channel) {
		initializeOnPage(channel, 1);
	}

	public void initializeOnPage(MessageChannel channel, int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		} else if (pageNumber > pageAmount) {
			pageNumber = pageAmount;
		}
		initialize(channel.sendMessage(getMessage(pageNumber)), pageNumber);
	}

	public void initializeOnPage(Message message, int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = 1;
		} else if (pageNumber > pageAmount) {
			pageNumber = pageAmount;
		}
		initialize(message.editMessage(getMessage(pageNumber)), pageNumber);
	}

	private void initialize(RestAction<Message> restAction, int pageNumber) {
		List<String> emotes = new ArrayList<String>();

		if (pageAmount > 1) {
			if (enablePageSkip && pageAmount > pageSkipAmount) {
				emotes.add(FAST_REVERSE);
			}
			emotes.add(REVERSE);
			emotes.add(PLAY);
			if (enablePageSkip && pageAmount > pageSkipAmount) {
				emotes.add(FAST_FORWARD);
			}
		}
		if (closable) {
			emotes.add(HEAVY_MULTIPLICATION_X);
		}

		restAction.queue(m -> {
			for (int i = 0; i < emotes.size(); i += 1) {
				if (i + 1 < emotes.size()) {
					m.addReaction(emotes.get(i)).queue();
				} else {
					m.addReaction(emotes.get(i)).queue(a -> createEvent(m, emotes, pageNumber));
				}
			}
		});
	}
	
	private void createEvent(Message message, List<String> emotes, int pageNumber) {
		scheduler.addNewMessage(MessageReactionAddEvent.class,
			(event) -> {
				if (!event.getMessageId().equals(message.getId())) {
					return (false);
				}
				if (event.getReaction().getReactionEmote().isEmote()) {
					event.getReaction().removeReaction().queue();
					return (false);
				}
				if (!emotes.contains(event.getReaction().getReactionEmote().getName())) {
					event.getReaction().removeReaction().queue();
					return (false);
				}
				return (canUserInteract(event.getUser(), event.getGuild()));
			},
			(MessageReactionAddEvent event) -> {
				int newPageNumber = pageNumber;
				switch (event.getReaction().getReactionEmote().getName()) {
					case (FAST_REVERSE):
						newPageNumber -= pageSkipAmount;
						break;
					case (REVERSE):
						newPageNumber -= 1;
						break;
					case (PLAY):
						newPageNumber += 1;
						break;
					case (FAST_FORWARD):
						newPageNumber += pageSkipAmount;
						break;
					case (HEAVY_MULTIPLICATION_X):
						message.delete().queue();
						return;
				}
				event.getReaction().removeReaction(event.getUser()).queue();

				initializeOnPage(message, newPageNumber);
			},
			timeout, unit, () -> timeoutAction.accept(message));
	}
	
	private Message getMessage(int pageNumber) {
		User author = getAuthor();
		boolean noItems = (items == null || items.isEmpty() || items.size() == 0);

		String rows = "";
		if (!noItems) {
			int itemsStart = (pageNumber - 1) * itemsPerPage;
			int itemsEnd = (items.size() < pageNumber * itemsPerPage ? items.size() : pageNumber * itemsPerPage);

			rows = "```" + codeblockLanguage + "\n";
			for (int i = itemsStart; i < itemsEnd; i += 1) {
				rows += (showItemNumber ? (i + 1) + ". " : "");
				rows += items.get(i) + "\n";
			}
			rows += "```";
			if (showPageNumber) {
				rows += "\nPage `" + pageNumber + "/" + pageAmount + "`";
			}
		}
		String actionDetails;
		if (description != null && !description.isEmpty() && noItems) {
			actionDetails = description + "\n\n" + emptyItemsListDescription;
		} else if (noItems) {
			actionDetails = emptyItemsListDescription;
		} else if (description != null && !description.isEmpty()) {
			actionDetails = description + "\n" + rows;
		} else {
			actionDetails = rows;
		}

		return (MessageUtil.getActionMessage(titleEmote, title, author,
			actionDetails,
			thumbnail, image)
		);
	}

	public static class Builder extends Menu.Builder<Builder, ListingMenu> {

		private String title;
		private String titleEmote;
		private String thumbnail;
		private String description;
		private String emptyItemsListDescription = "The list is empty.";
		private String image;
		private String codeblockLanguage = "";
		private List<String> items = new ArrayList<String>();
		private int itemsPerPage = 10;
		private int pageSkipAmount = 5;
		private boolean showItemNumber = false;
		private boolean showPageNumber = true;
		private boolean orderItems = false;
		private boolean enablePageSkip = true;
		private boolean closable = true;
		private Consumer<Message> timeoutAction = m -> {
			m.clearReactions().queue();
		};
		
		@Override
		public ListingMenu build() {
			return (new ListingMenu(scheduler, users, roles, timeout, unit, title, titleEmote, thumbnail, description, emptyItemsListDescription, image, codeblockLanguage, items, itemsPerPage, pageSkipAmount, showItemNumber, showPageNumber, enablePageSkip, orderItems, closable, timeoutAction));
		}
		
		public Builder setTitle(String title) {
			this.title = title;
			return (this);
		}
		
		public Builder setTitleEmote(String titleEmote) {
			this.titleEmote = titleEmote;
			return (this);
		}
		
		public Builder setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
			return (this);
		}
		
		public Builder setDescription(String description) {
			this.description = description;
			return (this);
		}

		public Builder setEmptyItemsListDescription(String emptyItemsListDescription) {
			this.emptyItemsListDescription = emptyItemsListDescription;
			return (this);
		}

		public Builder setImage(String image) {
			this.image = image;
			return (this);
		}

		public Builder setCodeblockLanguage(String codeblockLanguage) {
			this.codeblockLanguage = codeblockLanguage;
			return (this);
		}

		public Builder addItems(String... items) {
			for (String item : items) {
				this.items.add(item);
			}
			return (this);
		}

		public Builder setItems(List<String> items) {
			this.items.clear();
			this.items = items;
			return (this);
		}

		public Builder setItemsPerPage(int amount) {
			this.itemsPerPage = amount;
			return (this);
		}

		public Builder setPageSkipAmount(int amount) {
			this.pageSkipAmount = amount;
			return (this);
		}

		public Builder showItemNumber(boolean showItemNumber) {
			this.showItemNumber = showItemNumber;
			return (this);
		}

		public Builder showPageNumber(boolean showPageNumber) {
			this.showPageNumber = showPageNumber;
			return (this);
		}

		public Builder enablePageSkip(boolean enablePageSkip) {
			this.enablePageSkip = enablePageSkip;
			return (this);
		}

		public Builder orderItems(boolean orderItems) {
			this.orderItems = orderItems;
			return (this);
		}

		public Builder setClosable(boolean closable) {
			this.closable = closable;
			return (this);
		}

		public Builder setTimeoutAction(Consumer<Message> timeoutAction) {
			this.timeoutAction = timeoutAction;
			return (this);
		}
		
	}
	
}
