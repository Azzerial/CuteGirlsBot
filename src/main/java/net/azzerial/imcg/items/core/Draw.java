package net.azzerial.imcg.items.core;

import java.util.List;

public class Draw {

	private final List<Integer> options;
	private int draw;

	public Draw(List<Integer> options) {
		this.options = options;
		this.draw = 0;
	}

	public int getValue() {
		return (draw);
	}

	public int execute() {
		int number = (int)(Math.random() * optionsValue());
		int tmp = 0;

		for (int i = 0; i < options.size(); i++) {
			if (number < options.get(i).intValue() + tmp) {
				return (updateDrawValue(i));
			}
			tmp += options.get(i);
		}
		return (updateDrawValue(options.size() - 1));
	}

	private int optionsValue() {
		int value = 0;

		for (int i = 0; i < options.size(); i++) {
			value += options.get(i);
		}
		return (value);
	}

	private int updateDrawValue(int value) {
		this.draw = value;
		return (value);
	}
}
