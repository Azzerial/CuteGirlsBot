package net.azzerial.imcg.entities.utils;

public class SkinData {

	private int skinId;
	private int basicSkinCount;
	private int evolvedSkinCount;

	public SkinData(int skinId, int basicSkinCount, int evolvedSkinCount) {
		this.skinId = skinId;
		this.basicSkinCount = (basicSkinCount < 0 ? 0 : basicSkinCount) ;
		this.evolvedSkinCount = (evolvedSkinCount < 0 ? 0 : evolvedSkinCount);
	}

	public int getSkinId() {
		return (skinId);
	}

	public int getBasicSkinCount() {
		return (basicSkinCount);
	}

	public boolean hasBasicSkin() {
		if (basicSkinCount != 0) {
			return (true);
		}
		return (false);
	}

	public SkinData updateBasicSkinCount(int newValue) {
		if (newValue < 0) {
			newValue = 0;
		}
		basicSkinCount = newValue;
		return (this);
	}

	public int getEvolvedSkinCount() {
		return (evolvedSkinCount);
	}

	public boolean hasEvolvedSkin() {
		if (evolvedSkinCount != 0) {
			return (true);
		}
		return (false);
	}

	public SkinData updateEvolvedSkinCount(int newValue) {
		if (newValue < 0) {
			newValue = 0;
		}
		evolvedSkinCount = newValue;
		return (this);
	}

}
