package net.azzerial.imcg.idols.cute;

import net.azzerial.imcg.idols.core.CuteGirl;
import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;

import java.util.Arrays;
import java.util.List;

public class ShikiIchinose extends CuteGirl {

	public ShikiIchinose() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			0, IdolTier.TIER_3,
			"Shiki Ichinose", "一ノ瀬志希", IdolType.CUTE,
			18, new Birthday(30, Month.MAY),
			161, 43, new Measurement(83, 57, 82),
			BloodType.O, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/5/53/Shiki_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Shiki Ichinose", "一ノ瀬志希",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c5/ShikiIchinoseNormal1.jpg", "https://www.project-imas.com/w/images/thumb/c/c5/ShikiIchinoseNormal1.jpg/192px-ShikiIchinoseNormal1.jpg"),
				new Card("https://www.project-imas.com/w/images/1/10/ShikiIchinoseNormal1%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/10/ShikiIchinoseNormal1+.jpg/192px-ShikiIchinoseNormal1+.jpg")
			), new IdolSkin(
				1, "Gunslinger", "ｶﾞﾝｽﾘﾝｶﾞｰ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e7/ShikiIchinoseRarel1.jpg", "https://www.project-imas.com/w/images/thumb/e/e7/ShikiIchinoseRarel1.jpg/192px-ShikiIchinoseRarel1.jpg"),
				new Card("https://www.project-imas.com/w/images/1/1b/ShikiIchinoseRare1%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/1b/ShikiIchinoseRare1%2B.jpg/192px-ShikiIchinoseRare1%2B.jpg")
			), new IdolSkin(
				2, "Miracle Cure", "ﾐﾗｸﾙｷｭｱｰ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/8/85/Shiki_R3.jpg", "https://www.project-imas.com/w/images/thumb/8/85/Shiki_R3.jpg/192px-Shiki_R3.jpg"),
				new Card("https://www.project-imas.com/w/images/5/52/Shiki_R3%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/52/Shiki_R3%2B.jpg/192px-Shiki_R3%2B.jpg")
			), new IdolSkin(
				3, "Seventh Heaven", "ｾﾌﾞﾝｽﾍﾌﾞﾝ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/3/33/ShikiIchinoseSR1.jpg", "https://www.project-imas.com/w/images/thumb/3/33/ShikiIchinoseSR1.jpg/192px-ShikiIchinoseSR1.jpg"),
				new Card("https://www.project-imas.com/w/images/2/21/ShikiIchinoseSR%2B1.jpg", "https://www.project-imas.com/w/images/thumb/2/21/ShikiIchinoseSR%2B1.jpg/192px-ShikiIchinoseSR%2B1.jpg")
			), new IdolSkin(
				4, "Perfume Tripper", "ﾊﾟﾌｭｰﾑﾄﾘｯﾊﾟｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/8/84/ShikiIchinoseSR2.jpg", "https://www.project-imas.com/w/images/thumb/8/84/ShikiIchinoseSR2.jpg/192px-ShikiIchinoseSR2.jpg"),
				new Card("https://www.project-imas.com/w/images/8/82/ShikiIchinoseSR%2B2.jpg", "https://www.project-imas.com/w/images/thumb/8/82/ShikiIchinoseSR%2B2.jpg/192px-ShikiIchinoseSR%2B2.jpg")
			), new IdolSkin(
				5, "Culture Bloom", "文明開花",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e0/Shiki_SR3.jpg", "https://www.project-imas.com/w/images/thumb/e/e0/Shiki_SR3.jpg/192px-Shiki_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/6/6c/Shiki_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/6/6c/Shiki_SR3%2B.jpg/192px-Shiki_SR3%2B.jpg")
			), new IdolSkin(
				6, "Odor of Night", "夜の匂い",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/58/Shiki_SR5.jpg", "https://www.project-imas.com/w/images/thumb/5/58/Shiki_SR5.jpg/192px-Shiki_SR5.jpg"),
				new Card("https://www.project-imas.com/w/images/e/ea/Shiki_SR5%2B.jpg", "https://www.project-imas.com/w/images/thumb/e/ea/Shiki_SR5%2B.jpg/192px-Shiki_SR5%2B.jpg")
			)
		));
	}

}
