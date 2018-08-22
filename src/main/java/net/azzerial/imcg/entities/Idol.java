package net.azzerial.imcg.entities;

import java.util.List;

public class Idol {

	private final int id;
	private final String latinName;
	private final String japaneseName;
	private final IdolType idolType;
	private final int age;
	private final Birthday birthday;
	private final int height;
	private final int weight;
	private final Measurement measurements;
	private final BloodType bloodType;
	private final Handedness handedness;
	private final String profilePicture;
	private final List<IdolSkin> skins;

	public Idol(int id, String latinName, String japaneseName, IdolType idolType, int age, Birthday birthday, int height, int weight, Measurement measurements, BloodType bloodType, Handedness handedness, String profilePicture, List<IdolSkin> skins) {
		this.id = id;
		this.latinName = latinName;
		this.japaneseName = japaneseName;
		this.idolType = idolType;
		this.age = age;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
		this.measurements = measurements;
		this.bloodType = bloodType;
		this.handedness = handedness;
		this.profilePicture = profilePicture;
		this.skins = skins;
	}

	public int getId() {
		return (id);
	}

	public String getLatinName() {
		return (latinName);
	}

	public String getJapaneseName() {
		return (japaneseName);
	}

	public IdolType getIdolType() {
		return (idolType);
	}

	public int getAge() {
		return (age);
	}

	public Birthday getBirthday() {
		return (birthday);
	}

	public int getHeight() {
		return (height);
	}

	public int getWeight() {
		return (weight);
	}

	public Measurement getMeasurements() {
		return (measurements);
	}

	public BloodType getBloodType() {
		return (bloodType);
	}

	public Handedness getHandedness() {
		return (handedness);
	}

	public String getProfilePicture() {
		return (profilePicture);
	}

	public List<IdolSkin> getSkins() {
		return (skins);
	}

	public IdolSkin getSkin(int id) {
		if (id < 0 || id >= skins.size() || skins.isEmpty()) {
			return (null);
		}
		return (skins.get(id));
	}

	public enum IdolType {
		CUTE("Cute"),
		COOL("Cool"),
		PASSION("Passion");

		private final String CUTE_ICON = "https://www.project-imas.com/w/images/6/63/Cute_icon.png";
		private final String COOL_ICON = "https://www.project-imas.com/w/images/d/db/Cool_icon.png";
		private final String PASSION_ICON = "https://www.project-imas.com/w/images/3/3d/Passion_icon.png";

		private String type;

		IdolType(String type) {
			this.type = type;
		}

		public String asString() {
			return (type);
		}

		public String getTypeIcon () {
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

	}

	public static class Birthday {

		private int day;
		private int month;

		public Birthday(int day, int month) {
			this.day = day;
			this.month = month;
		}

		public String getDay() {
			StringBuilder builder = new StringBuilder();

			builder.append(day);
			if (day == 1 || day == 21 || day == 31) {
				builder.append("st");
			} else if (day == 2 || day == 22) {
				builder.append("nd");
			} else if (day == 3 || day == 23) {
				builder.append("rd");
			} else {
				builder.append("th");
			}
			return (builder.toString());
		}

		public int getDayValue() {
			return (day);
		}

		public String getMonth() {
			switch (month) {
				case 1:
					return("January");
				case 2:
					return("February");
				case 3:
					return("March");
				case 4:
					return("April");
				case 5:
					return("May");
				case 6:
					return("June");
				case 7:
					return("July");
				case 8:
					return("August");
				case 9:
					return("September");
				case 10:
					return("October");
				case 11:
					return("November");
				case 12:
					return("December");
				default:
					return ("");
			}
		}

		public int getMonthValue() {
			return (month);
		}

		public String asString() {
			return (new StringBuilder().append(getDay()).append(" of ").append(getMonth()).toString());
		}

	}

	public static class Measurement {

		private int bust;
		private int waist;
		private int hip;

		public Measurement(int bust, int waist, int hip) {
			this.bust = bust;
			this.waist = waist;
			this.hip = hip;
		}

		public int getBustValue() {
			return (bust);
		}

		public int getWaistValue() {
			return (waist);
		}

		public int getHiptValue() {
			return (hip);
		}

		public String asString() {
			return (new StringBuilder().append(bust).append("-").append(waist).append("-").append(hip).toString());
		}

	}

	public enum BloodType {
		A("A"),
		AB("AB"),
		B("B"),
		O("O");

		private String type;

		BloodType(String type) {
			this.type = type;
		}

		public String asString() {
			return (type);
		}
	}

	public enum Handedness {
		LEFT("Left"),
		RIGHT("Right");

		private String hand;

		Handedness(String hand) {
			this.hand = hand;
		}

		public String asString() {
			return (hand);
		}
	}

}
