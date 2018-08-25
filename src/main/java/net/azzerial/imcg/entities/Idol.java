package net.azzerial.imcg.entities;

import net.azzerial.imcg.entities.utils.*;

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

	public String getName() {
		StringBuilder builder = new StringBuilder();

		builder.append(latinName).append("  ❲").append(japaneseName).append("❳");
		return (builder.toString());
	}

	public String getLatinName() {
		return (latinName);
	}

	public String getJapaneseName() {
		return (japaneseName);
	}

	public String getDatabaseName() {
		return (latinName.toLowerCase().replace(" ", "_"));
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

}
