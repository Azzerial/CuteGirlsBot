package net.azzerial.imcg.entities.utils;

import java.awt.*;

public enum IdolType {
	CUTE("Cute"),
	COOL("Cool"),
	PASSION("Passion");

	private final String CUTE_ICON = "https://www.project-imas.com/w/images/6/63/Cute_icon.png";
	private final String COOL_ICON = "https://www.project-imas.com/w/images/d/db/Cool_icon.png";
	private final String PASSION_ICON = "https://www.project-imas.com/w/images/3/3d/Passion_icon.png";

	private final Color CUTE_COLOR = new Color(255, 186, 212);
	private final Color COOL_COLOR = new Color(102, 194, 255);
	private final Color PASSION_COLOR = new Color(255, 230, 128);

	private String type;

	IdolType(String type) {
		this.type = type;
	}

	public String asString() {
		return (type);
	}

	public String getIcon () {
		switch (this) {
			case CUTE:
				return (CUTE_ICON);
			case COOL:
				return (COOL_ICON);
			case PASSION:
				return (PASSION_ICON);
			default:
				return (null);
		}
	}

	public Color getColor() {
		switch (this) {
			case CUTE:
				return (CUTE_COLOR);
			case COOL:
				return (COOL_COLOR);
			case PASSION:
				return (PASSION_COLOR);
			default:
				return (null);
		}
	}

}
