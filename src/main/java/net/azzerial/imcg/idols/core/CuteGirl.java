package net.azzerial.imcg.idols.core;

import net.azzerial.cgc.database.Database;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;

import java.util.List;

public abstract class CuteGirl {

	public abstract Idol getIdol();
	public abstract List<IdolSkin> getIdolSkins();

	protected boolean addToDatabase() {
		String column = getIdol().getDatabaseName();
		String defaultValue = getDefaultValue();

		if (column == null || column.isEmpty() || defaultValue.isEmpty()) {
			return (false);
		}
		return (Database.getInstance().addIdolToDatabase(column, defaultValue));
	}

	protected String getDefaultValue() {
		StringBuilder builder = new StringBuilder();
		List<IdolSkin> skins = getIdolSkins();

		if (skins == null) {
			return (builder.toString());
		}
		builder.append("F;");
		for (int i = 0; i < skins.size(); i += 1) {
			builder.append(i).append(":0-0");
			if (i + 1 < skins.size()) {
				builder.append(",");
			}
		}
		return (builder.toString());
	}

}
