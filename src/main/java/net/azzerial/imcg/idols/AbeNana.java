package net.azzerial.imcg.idols;

import net.azzerial.imcg.entities.CuteGirl;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;

import java.util.Arrays;
import java.util.List;

public class AbeNana extends CuteGirl {

	@Override
	public Idol getIdol() {
		return (new Idol(
			0,"Abe Nana", "安部菜々", Idol.IdolType.CUTE,
			17, new Idol.Birthday(15, 5),
			146, 40, new Idol.Measurement(84, 57, 84),
			Idol.BloodType.O, Idol.Handedness.RIGHT,
			"https://www.project-imas.com/w/images/8/83/Nana_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "New Year", "ニューイヤー",
				IdolSkin.Rarity.RARE,
				new IdolSkin.Stats(7871, 7155),
				new IdolSkin.Card("https://cgdex.project-imas.com/cards/1207001.jpg", null),
				new IdolSkin.Card("https://cgdex.project-imas.com/cards/1307002.jpg", null)
			), new IdolSkin(
				1, "Moon Watching Usamin", "お月見ウサミン",
				IdolSkin.Rarity.S_RARE,
				new IdolSkin.Stats(11926, 17172),
				new IdolSkin.Card("https://cgdex.project-imas.com/cards/1402901.jpg", "https://cgdex.project-imas.com/cards/frameless/1402901.jpg"),
				new IdolSkin.Card("https://cgdex.project-imas.com/cards/1502902.jpg", "https://cgdex.project-imas.com/cards/frameless/1502902.jpg")
			)
		));
	}
}
