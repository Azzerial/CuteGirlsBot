package net.azzerial.imcg.entities;

import net.azzerial.imcg.core.IdolsList;
import net.azzerial.imcg.entities.utils.Progress;
import net.azzerial.imcg.entities.utils.SkinData;

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

			collectionBuilder.setIdolId(idol.getId());
			for (int n = 0; n < skins.size(); n++) {
				collectionBuilder.addSkin(
					skins.get(n).getId(),
					new SkinData(0, 0)
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

	public Collection getCollection(String idolName) {
		if (idolName == null || idolName.isEmpty()) {
			return (null);
		}
		return (getCollection(IdolsList.getIdol(idolName)));
	}

	public Collection getCollection(Idol idol) {
		if (idol == null) {
			 return (null);
		}
		return (getCollection(idol.getId()));
	}

	public int getCollectionsSize() {
		return (collections.size());
	}

	public Progress getCollectionsProgress() {
		int size = 0;
		int progress = 0;
		Object[] keys = collections.keySet().toArray();
		for (int i = 0; i < keys.length; i += 1) {
			Collection collection = collections.get(keys[i]);
			progress += collection.getCollectionProgress().getProgress();
			size += collection.getCollectionProgress().getSize();
		}
		return (new Progress(progress, size));
	}

	public Progress getCollectionsBasicCardProgress() {
		int size = 0;
		int progress = 0;
		Object[] keys = collections.keySet().toArray();
		for (int i = 0; i < keys.length; i += 1) {
			Collection collection = collections.get(keys[i]);
			progress += collection.getCollectionBasicCardProgress().getProgress();
			size += collection.getCollectionBasicCardProgress().getSize();
		}
		return (new Progress(progress, size));
	}

	public Progress getCollectionsEvolvedCardProgress() {
		int size = 0;
		int progress = 0;
		Object[] keys = collections.keySet().toArray();
		for (int i = 0; i < keys.length; i += 1) {
			Collection collection = collections.get(keys[i]);
			progress += collection.getCollectionEvolvedCardProgress().getProgress();
			size += collection.getCollectionEvolvedCardProgress().getSize();
		}
		return (new Progress(progress, size));
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

		private final int idolId;
		private final HashMap<Integer, SkinData> skins;

		public Collection(int idolId, HashMap<Integer, SkinData> skins) {
			this.idolId = idolId;
			this.skins = skins;
		}

		public int getIdolId() {
			return (idolId);
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

		public Progress getCollectionProgress() {
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
			return (new Progress(progress, skins.size() * 2));
		}

		public Progress getCollectionBasicCardProgress() {
			int progress = 0;
			Object[] keys = skins.keySet().toArray();

			for (int i = 0; i < keys.length; i += 1) {
				SkinData skin = skins.get(keys[i]);
				if (skin.hasBasicSkin()) {
					progress += 1;
				}
			}
			return (new Progress(progress, skins.size()));
		}

		public Progress getCollectionEvolvedCardProgress() {
			int progress = 0;
			Object[] keys = skins.keySet().toArray();

			for (int i = 0; i < keys.length; i += 1) {
				SkinData skin = skins.get(keys[i]);
				if (skin.hasEvolvedSkin()) {
					progress += 1;
				}
			}
			return (new Progress(progress, skins.size()));
		}

		public boolean isCollectionCompleted() {
			if (getCollectionProgress().getMissingProgress() == 0) {
				return (true);
			}
			return (false);
		}

		public String convertToString() {
			StringBuilder builder = new StringBuilder();
			Object[] keys = skins.keySet().toArray();

			for (int i = 0; i < keys.length; i += 1) {
				SkinData skin = skins.get(keys[i]);

				builder.append(i)
					.append(":")
					.append(skin.getBasicSkinCount())
					.append("-")
					.append(skin.getEvolvedSkinCount());
				if (i + 1 < keys.length) {
					builder.append(",");
				}
			}
			return (builder.toString());
		}

		public static class Builder {

			private int idolId;
			private HashMap<Integer, SkinData> skinsCount = new HashMap<Integer, SkinData>();

			public Collection build() {
				return (new Collection(idolId, skinsCount));
			}

			public Builder setIdolId(int idolId) {
				this.idolId = idolId;
				return (this);
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
