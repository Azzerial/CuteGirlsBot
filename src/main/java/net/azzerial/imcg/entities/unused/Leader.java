package net.azzerial.imcg.entities.unused;

import net.azzerial.imcg.idols.core.IdolsList;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.Card;

public class Leader {

	private final int idolId;
	private final int idolSkinId;
	private final boolean isEvolved;

	public Leader(int idolId, int idolSkinId, boolean isEvolved) {
		this.idolId = idolId;
		this.idolSkinId = idolSkinId;
		this.isEvolved = isEvolved;
	}

	public int getIdolId() {
		return (idolId);
	}

	public int getIdolSkinId() {
		return (idolSkinId);
	}

	public boolean isEvolved() {
		return (isEvolved);
	}

	public Idol getIdol() {
		return (IdolsList.getIdol(idolId));
	}

	public IdolSkin getIdolSkin() {
		return (getIdol().getSkin(idolSkinId));
	}

	public Card getCard() {
		if (isEvolved) {
			return (getIdolSkin().getEvolvedCard());
		} else {
			return (getIdolSkin().getBasicCard());
		}
	}

	public String getRarity() {
		StringBuilder builder = new StringBuilder();

		builder.append(getIdolSkin().getRarity().asString());
		if (isEvolved) {
			builder.append("+");
		}
		return (builder.toString());
	}

}
