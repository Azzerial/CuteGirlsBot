package net.azzerial.imcg.entities;

import net.azzerial.imcg.core.IdolsList;
import net.azzerial.imcg.entities.utils.Card;

public class Leader {

	private final int idolId;
	private final int idolSkinId;
	private final boolean isEvolvedCard;

	public Leader(int idolId, int idolSkinId, boolean isEvolvedCard) {
		this.idolId = idolId;
		this.idolSkinId = idolSkinId;
		this.isEvolvedCard = isEvolvedCard;
	}

	public int getIdolId() {
		return (idolId);
	}

	public int getIdolSkinId() {
		return (idolSkinId);
	}

	public boolean isEvolvedCard() {
		return (isEvolvedCard);
	}

	public Idol getIdol() {
		return (IdolsList.getIdol(idolId));
	}

	public IdolSkin getIdolSkin() {
		return (getIdol().getSkin(idolSkinId));
	}

	public Card getCard() {
		if (isEvolvedCard) {
			return (getIdolSkin().getEvolvedCard());
		} else {
			return (getIdolSkin().getBasicCard());
		}
	}

	public String getRarity() {
		StringBuilder builder = new StringBuilder();

		builder.append(getIdolSkin().getRarity().asString());
		if (isEvolvedCard) {
			builder.append("+");
		}
		return (builder.toString());
	}

}
