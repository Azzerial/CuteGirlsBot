package net.azzerial.imcg.entities;

import net.azzerial.imcg.core.IdolsList;

import java.util.HashMap;
import java.util.List;

public class IdolCollection {

	private HashMap<Integer, Collection> collections;

	public IdolCollection(HashMap<Integer, Collection> collections) {
		this.collections = collections;
	}

	public static IdolCollection createNewEmptyCollection() {
		List<Idol> idols = IdolsList.getIdols();
		Builder idolCollectionBuilder = new Builder();

		for (int i = 0; i < idols.size(); i += 1) {
			Idol idol = idols.get(i);
			List<IdolSkin> skins = idol.getSkins();
			Collection.Builder collectionBuilder = new Collection.Builder();

			for (int n = 0; n < skins.size(); n++) {
				collectionBuilder.addSkin(
					skins.get(n).getId(),
					new Collection.SkinData(0, 0)
				);
			}
			idolCollectionBuilder.addIdol(idol.getId(), collectionBuilder.build());
		}
		return (idolCollectionBuilder.build());
	}

	public Collection getCollection(int id) {
		if (!collections.containsKey(id)) {
			return (null);
		}
		return (collections.get(id));
	}

	public Collection getCollection(Idol idol) {
		if (idol == null) {
			 return (null);
		}
		return (getCollection(idol.getId()));
	}

	public int getCollectionsAmount() {
		return (collections.size());
	}

	public int getCollectionsSize() {
		int size = 0;
		Object[] keys = collections.keySet().toArray();
		for (int i = 0; i < keys.length; i += 1) {
			Collection collection = collections.get(keys[i]);
			size += collection.getCollectionSize();
		}
		return (size);
	}

	public int getCollectionsProgress() {
		int progress = 0;
		Object[] keys = collections.keySet().toArray();
		for (int i = 0; i < keys.length; i += 1) {
			Collection collection = collections.get(keys[i]);
			progress += collection.getCollectionProgress();
		}
		return (progress);
	}

	public static class Builder {

		private HashMap<Integer, Collection> idolsCollection = new HashMap<Integer, Collection>();

		public IdolCollection build() {
			return (new IdolCollection(idolsCollection));
		}

		public Builder addIdol(int idolId, Collection collection) {
			if (idolsCollection.containsKey(idolId)) {
				return (this);
			}
			idolsCollection.put(idolId, collection);
			return (this);
		}

	}

	public static class Collection {

		private final HashMap<Integer, SkinData> skins;

		public Collection(HashMap<Integer, SkinData> skins) {
			this.skins = skins;
		}

		public SkinData getSkinData(int id) {
			if (!skins.containsKey(id)) {
				return (null);
			}
			return (skins.get(id));
		}

		public SkinData getSkinData(IdolSkin skin) {
			if (skin == null) {
				return (null);
			}
			return (getSkinData(skin.getId()));
		}

		public int getCollectionSize() {
			return (skins.size() * 2);
		}

		public int getCollectionProgress() {
			int progress = 0;
			Object[] keys = skins.keySet().toArray();
			for (int i = 0; i < keys.length; i += 1) {
				SkinData skin = skins.get(keys[i]);
				if (skin.hasBasicSkin()) {
					progress += 1;
				}
				if (skin.hasEvolvedSkin()) {
					progress += 1;
				}
			}
			return (progress);
		}

		public boolean isCollectionCompleted() {
			if (getCollectionProgress() == getCollectionSize()) {
				return (true);
			}
			return (false);
		}

		public static class SkinData {

			private int basicSkinCount;
			private int evolvedSkinCount;

			public SkinData(int basicSkinCount, int evolvedSkinCount) {
				this.basicSkinCount = (basicSkinCount < 0 ? 0 : basicSkinCount) ;
				this.evolvedSkinCount = (evolvedSkinCount < 0 ? 0 : evolvedSkinCount);
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

		public static class Builder {

			private HashMap<Integer, SkinData> skinsCount = new HashMap<Integer, SkinData>();

			public Collection build() {
				return (new Collection(skinsCount));
			}

			public Builder addSkin(int skinId, SkinData skinData) {
				if (skinsCount.containsKey(skinId) || skinData == null) {
					return (this);
				}
				skinsCount.put(skinId, skinData);
				return (this);
			}

		}

	}

}
