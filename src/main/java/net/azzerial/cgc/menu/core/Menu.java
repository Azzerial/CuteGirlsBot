package net.azzerial.cgc.menu.core;

import net.dv8tion.jda.core.entities.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class Menu {

	protected final MessageScheduler scheduler;
	protected Set<User> users;
	protected Set<Role> roles;
	protected final long timeout;
	protected final TimeUnit unit;

	protected Menu(MessageScheduler scheduler, Set<User> users, Set<Role> roles, long timeout, TimeUnit unit) {
		this.scheduler = scheduler;
		this.users = users;
		this.roles = roles;
		this.timeout = timeout;
		this.unit = unit;
	}
	
	public abstract void display(MessageChannel channel);
	
	protected boolean canUserInteract(User user) {
		return (canUserInteract(user, null));
	}
	
	protected boolean canUserInteract(User user, Guild guild) {
		if (user.isBot()) {
			return (false);
		}
		if (users.isEmpty() && roles.isEmpty()) {
			return (true);
		}
		if (users.contains(user)) {
			return (true);
		}
		if (guild == null || !guild.isMember(user)) {
			return (false);
		}
		return (guild.getMember(user).getRoles().stream().anyMatch(roles :: contains));
	}

	protected User getAuthor() {
		if (!this.roles.isEmpty() || this.users.isEmpty() || this.users.size() != 1) {
			return (null);
		}
		for (Iterator<User> userIterator = this.users.iterator(); userIterator.hasNext(); ) {
			return (userIterator.next());
		}
		return (null);
	}
	
	@SuppressWarnings("unchecked")
	public abstract static class Builder<T extends Builder<T, V>, V extends Menu> {
		
		protected MessageScheduler scheduler;
		protected Set<User> users = new HashSet<>();
		protected Set<Role> roles = new HashSet<>();
		protected long timeout = 1;
		protected TimeUnit unit = TimeUnit.MINUTES;
		
		public abstract V build();
		
		public final T setMessageScheduler(MessageScheduler scheduler) {
			this.scheduler = scheduler;
			return ((T) this);
		}
		
		public final T addUsers(User... users) {
			this.users.addAll(Arrays.asList(users));
			return ((T) this);
		}
		
		public final T setUsers(User... users) {
			this.users.clear();
			this.users.addAll(Arrays.asList(users));
			return ((T) this);
		}
		
		public final T addRoles(Role... roles) {
			this.roles.addAll(Arrays.asList(roles));
			return ((T) this);
		}
		
		public final T setRoles(Role... roles) {
			this.roles.clear();
			this.roles.addAll(Arrays.asList(roles));
			return ((T) this);
		}
		
		public final T setTimeout(long timeout, TimeUnit unit) {
			this.timeout = timeout;
			this.unit = unit;
			return ((T) this);
		}
		
	}
}
