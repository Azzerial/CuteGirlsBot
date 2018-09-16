package net.azzerial.imcg.items.core;

import net.azzerial.cgc.database.DatabaseUserManager;

public class Inventory {

	private int idolBox;
	private int specialIdolBox;
	private int divineIdolBox;
	private int skinBox;
	private int specialSkinBox;
	private int divineSkinBox;
	private int passiveBox;
	private int specialPassiveBox;
	private int divinePassiveBox;

	public Inventory(int idolBox, int specialIdolBox, int divineIdolBox, int skinBox, int specialSkinBox, int divineSkinBox, int passiveBox, int specialPassiveBox, int divinePassiveBox) {
		this.idolBox = idolBox;
		this.specialIdolBox = specialIdolBox;
		this.divineIdolBox = divineIdolBox;
		this.skinBox = skinBox;
		this.specialSkinBox = specialSkinBox;
		this.divineSkinBox = divineSkinBox;
		this.passiveBox = passiveBox;
		this.specialPassiveBox = specialPassiveBox;
		this.divinePassiveBox = divinePassiveBox;
	}

	public static Inventory createNewEmptyInventory() {
		return (new Inventory(0, 0, 0, 0, 0, 0, 0, 0, 0));
	}

	public int getItemCount(ItemType itemType) {
		switch (itemType) {
			case IDOL_BOX:
				return (idolBox);
			case SPECIAL_IDOL_BOX:
				return (specialIdolBox);
			case DIVINE_IDOL_BOX:
				return (divineIdolBox);
			case SKIN_BOX:
				return (skinBox);
			case SPECIAL_SKIN_BOX:
				return (specialSkinBox);
			case DIVINE_SKIN_BOX:
				return (divineSkinBox);
			case PASSIVE_BOX:
				return (passiveBox);
			case SPECIAL_PASSIVE_BOX:
				return (specialPassiveBox);
			case DIVINE_PASSIVE_BOX:
				return (divinePassiveBox);
		}
		return (0);
	}

	public boolean updateItemCount(long userId, ItemType itemType, int value) {
		switch (itemType) {
			case IDOL_BOX:
				this.idolBox = value;
				break;
			case SPECIAL_IDOL_BOX:
				this.specialIdolBox = value;
				break;
			case DIVINE_IDOL_BOX:
				this.divineIdolBox = value;
				break;
			case SKIN_BOX:
				this.skinBox = value;
				break;
			case SPECIAL_SKIN_BOX:
				this.specialSkinBox = value;
				break;
			case DIVINE_SKIN_BOX:
				this.divineSkinBox = value;
				break;
			case PASSIVE_BOX:
				this.passiveBox = value;
				break;
			case SPECIAL_PASSIVE_BOX:
				this.specialPassiveBox = value;
				break;
			case DIVINE_PASSIVE_BOX:
				this.divinePassiveBox = value;
				break;
		}
		return (DatabaseUserManager.updateUserInventory(userId, itemType, value));

	}

}
