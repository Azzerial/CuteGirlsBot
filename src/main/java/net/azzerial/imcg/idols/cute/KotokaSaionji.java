package net.azzerial.imcg.idols.cute;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class KotokaSaionji extends CuteGirl {

	public KotokaSaionji() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			4, IdolTier.TIER_2,
			"Kotoka Saionji", "西園寺琴歌", IdolType.CUTE,
			17, new Birthday(23, Month.JANUARY),
			156, 46, new Measurement(87, 57, 85),
			BloodType.O, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/4/45/Kotoka_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Kotoka Saionji", "西園寺琴歌",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/da/CGcard_Kotoka_Saionji_Rare.jpg", "https://www.project-imas.com/w/images/thumb/d/da/CGcard_Kotoka_Saionji_Rare.jpg/192px-CGcard_Kotoka_Saionji_Rare.jpg"),
				new Card("https://www.project-imas.com/w/images/3/39/Kotoka_rare_plus.jpg", "https://www.project-imas.com/w/images/thumb/3/39/Kotoka_rare_plus.jpg/192px-Kotoka_rare_plus.jpg")
			), new IdolSkin(
				1, "Tokimeki Valentine", "ﾄｷﾒｷﾊﾞﾚﾝﾀｲﾝ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/1/18/KotokaSaionjiRare2.jpg", "https://www.project-imas.com/w/images/thumb/1/18/KotokaSaionjiRare2.jpg/192px-KotokaSaionjiRare2.jpg"),
				new Card("https://www.project-imas.com/w/images/2/23/KotokaSaionjiRare%2B2.jpg", "https://www.project-imas.com/w/images/thumb/2/23/KotokaSaionjiRare%2B2.jpg/192px-KotokaSaionjiRare%2B2.jpg")
			), new IdolSkin(
				2, "Summer Season", "ｻﾏｰｼｰｽﾞﾝ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/2e/Kotoka_R4.jpg", "https://www.project-imas.com/w/images/thumb/2/2e/Kotoka_R4.jpg/192px-Kotoka_R4.jpg"),
				new Card("https://www.project-imas.com/w/images/8/80/Kotoka_R4%2B.jpg", "https://www.project-imas.com/w/images/thumb/8/80/Kotoka_R4%2B.jpg/192px-Kotoka_R4%2B.jpg")
			), new IdolSkin(
				3, "Enthusiastic Princess", "はりきりお嬢様",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/b1/Kotokasuper.jpg", "https://www.project-imas.com/w/images/thumb/b/b1/Kotokasuper.jpg/192px-Kotokasuper.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4d/Kotokasuperplus.jpg", "https://www.project-imas.com/w/images/thumb/4/4d/Kotokasuperplus.jpg/192px-Kotokasuperplus.jpg")
			), new IdolSkin(
				4, "Heart Sunny Pattern", "ｺｺﾛ晴れ模様",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e4/KotokaSR3.jpg", "https://www.project-imas.com/w/images/thumb/e/e4/KotokaSR3.jpg/192px-KotokaSR3.jpg"),
				new Card("https://www.project-imas.com/w/images/7/71/KotokaSR%2B3.jpg", "https://www.project-imas.com/w/images/thumb/7/71/KotokaSR%2B3.jpg/192px-KotokaSR%2B3.jpg")
			), new IdolSkin(
				5, "Gorgeous Brilliant", "絢爛華麗",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c6/KotokaSR4.jpg", "https://www.project-imas.com/w/images/thumb/c/c6/KotokaSR4.jpg/192px-KotokaSR4.jpg"),
				new Card("https://www.project-imas.com/w/images/e/e3/KotokaSR%2B4.jpg", "https://www.project-imas.com/w/images/thumb/e/e3/KotokaSR%2B4.jpg/192px-KotokaSR%2B4.jpg")
			), new IdolSkin(
				6, "Celebrity Road", "[ｾﾚﾌﾞﾘﾃｨﾛｰﾄﾞ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/a/ad/Kotoka_SR6.jpg", "https://www.project-imas.com/w/images/thumb/a/ad/Kotoka_SR6.jpg/192px-Kotoka_SR6.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4c/Kotoka_SR6%2B.jpg", "https://www.project-imas.com/w/images/thumb/4/4c/Kotoka_SR6%2B.jpg/192px-Kotoka_SR6%2B.jpg")
			)
		));
	}

}
