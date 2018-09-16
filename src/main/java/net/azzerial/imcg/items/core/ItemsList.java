package net.azzerial.imcg.items.core;

import net.azzerial.imcg.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ItemsList {

	private static ItemsList instance;
	private static TreeMap<ItemType, Item> items;

	public ItemsList() {
		items = new TreeMap<ItemType, Item>();

		// Idol Boxes
		items.put(ItemType.IDOL_BOX, new IdolBox());
		items.put(ItemType.SPECIAL_IDOL_BOX, new SpecialIdolBox());
		items.put(ItemType.DIVINE_IDOL_BOX, new DivineIdolBox());

		// Skin Boxes
		items.put(ItemType.SKIN_BOX, new SkinBox());
		items.put(ItemType.SPECIAL_SKIN_BOX, new SpecialSkinBox());
		items.put(ItemType.DIVINE_SKIN_BOX, new DivineSkinBox());

		// Passive Boxes
		items.put(ItemType.PASSIVE_BOX, new PassiveBox());
		items.put(ItemType.SPECIAL_PASSIVE_BOX, new SpecialPassiveBox());
		items.put(ItemType.DIVINE_PASSIVE_BOX, new DivinePassiveBox());
	}

	public static ItemsList loadItems() {
		if (instance == null) {
			instance = new ItemsList();
			System.out.println("[IMCG/ItemsList]: Loaded Items.");
		}
		return (instance);
	}

	public static Item getItem(int id) {
		return (getItem(ItemType.getItemType(id)));
	}

	public static Item getItem(ItemType itemType) {
		if (itemType.equals(ItemType.UNKNOWN_ITEM)) {
			return (null);
		}
		return (items.get(itemType));
	}

	public static List<Item> getItemsList() {
		return (new ArrayList<Item>(items.values()));
	}

}
