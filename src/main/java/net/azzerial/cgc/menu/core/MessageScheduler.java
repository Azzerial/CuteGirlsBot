package net.azzerial.cgc.menu.core;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageScheduler implements EventListener {

	private final HashMap<Class<?>, Set<ScheduledMessage>> scheduledMessages;
	private final ScheduledExecutorService threadPool;

	public MessageScheduler(ScheduledExecutorService threadPool) {
		if (threadPool == null) {
			throw new NullPointerException("MessageScheduler's thread pool can't be null.");
		}
		this.scheduledMessages = new HashMap<>();
		this.threadPool = threadPool;
	}

	public <T extends Event> void addNewMessage(Class<T> classType,
			Predicate<T> condition, Consumer<T> action,
			long timeout, TimeUnit unit, Runnable timeoutAction) {
		ScheduledMessage scheduled = new ScheduledMessage<>(condition, action);
		Set<ScheduledMessage> set = scheduledMessages.computeIfAbsent(classType, c -> new HashSet<>());
		set.add(scheduled);

		if (timeout > 0 && unit != null) {
			threadPool.schedule(() -> {
				if (set.remove(scheduled) && timeoutAction != null) {
					timeoutAction.run();
				}
			}, timeout, unit);
		}
	}

	@Override
	public void onEvent(Event event) {
		Class c = event.getClass();

		while (c != null) {
			if (scheduledMessages.containsKey(c)) {
				Set<ScheduledMessage> set = scheduledMessages.get(c);
				ScheduledMessage[] caughtEvents = set.toArray(new MessageScheduler.ScheduledMessage[set.size()]);

				set.removeAll(Stream.of(caughtEvents).filter(f -> f.attempt(event)).collect(Collectors.toSet()));
			}
			if (event instanceof ShutdownEvent) {
				threadPool.shutdown();
			}
			c = c.getSuperclass();
		}
	}

	private class ScheduledMessage<T extends Event> {

		final Predicate<T> condition;
		final Consumer<T> action;

		ScheduledMessage(Predicate<T> condition, Consumer<T> action) {
			this.condition = condition;
			this.action = action;
		}

		boolean attempt(T event) {
			if (condition.test(event)) {
				action.accept(event);
				return (true);
			}
			return (false);
		}

	}

}
