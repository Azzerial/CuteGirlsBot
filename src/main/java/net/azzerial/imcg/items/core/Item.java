package net.azzerial.imcg.items.core;

import net.azzerial.cgc.database.entities.DatabaseUser;
import net.dv8tion.jda.core.entities.Message;

import java.util.List;

public abstract class Item {

	public abstract String getName();
	public abstract String getDescription();
	public abstract ItemType getItemType();
	public abstract List<Integer> getPrizesPercentages();
	public abstract Message useItem(DatabaseUser user);

}
