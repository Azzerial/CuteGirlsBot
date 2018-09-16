package net.azzerial.imcg.entities.utils;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;

public class SingleSkin {

	private final Idol idol;
	private final IdolSkin skin;
	private final boolean evolved;
	private final int count;

	public SingleSkin(Idol idol, IdolSkin skin, boolean evolved, int count) {
		this.idol = idol;
		this.skin = skin;
		this.evolved = evolved;
		this.count = count;
	}

	public Idol getIdol() {
		return (idol);
	}

	public IdolSkin getSkin() {
		return (skin);
	}

	public boolean isEvolved() {
		return (evolved);
	}

	public int getCount() {
		return (count);
	}

}
