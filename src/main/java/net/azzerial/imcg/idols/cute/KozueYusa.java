package net.azzerial.imcg.idols.cute;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class KozueYusa extends CuteGirl {

	public KozueYusa() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			1, IdolTier.TIER_3,
			"Kozue Yusa", "遊佐こずえ", IdolType.CUTE,
			11, new Birthday(19, Month.FEBRUARY),
			130, 28, new Measurement(62, 50, 65),
			BloodType.B, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/c/c0/Kozue_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Kozue Yusa", "遊佐こずえ",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/91/KozueYusaNormal1.jpg", "https://www.project-imas.com/w/images/thumb/9/91/KozueYusaNormal1.jpg/192px-KozueYusaNormal1.jpg"),
				new Card("https://www.project-imas.com/w/images/5/54/KozueYusaNormal%2B1.jpg", "https://www.project-imas.com/w/images/thumb/5/54/KozueYusaNormal%2B1.jpg/192px-KozueYusaNormal%2B1.jpg")
			), new IdolSkin(
				1, "Space Style", "ｽﾍﾟｰｽｽﾀｲﾙ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/9b/KozueYusaRare1.jpg", "https://www.project-imas.com/w/images/thumb/9/9b/KozueYusaRare1.jpg/192px-KozueYusaRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/4/42/KozueYusaRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/4/42/KozueYusaRare%2B1.jpg/192px-KozueYusaRare%2B1.jpg")
			), new IdolSkin(
				2, "Mystic Power", "ふしぎなちから",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/a/ab/Kozue_R3.jpg", "https://www.project-imas.com/w/images/thumb/a/ab/Kozue_R3.jpg/192px-Kozue_R3.jpg"),
				new Card("https://www.project-imas.com/w/images/7/7b/Kozue_R3%2B.jpg", "https://www.project-imas.com/w/images/thumb/7/7b/Kozue_R3%2B.jpg/192px-Kozue_R3%2B.jpg")
			), new IdolSkin(
				3, "Sweet Fairy", "ｽｳｨｰﾄﾌｪｱﾘｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/8/81/KozueSRare2.jpg", "https://www.project-imas.com/w/images/thumb/8/81/KozueSRare2.jpg/192px-KozueSRare2.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5a/KozueSRare%2B2.jpg", "https://www.project-imas.com/w/images/thumb/5/5a/KozueSRare%2B2.jpg/192px-KozueSRare%2B2.jpg")
			), new IdolSkin(
				4, "Full First-Time", "はじめていっぱい",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/61/Kozue_SR3.jpg", "https://www.project-imas.com/w/images/thumb/6/61/Kozue_SR3.jpg/192px-Kozue_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/e/e8/Kozue_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/e/e8/Kozue_SR3%2B.jpg/192px-Kozue_SR3%2B.jpg")
			), new IdolSkin(
				5, "Innocent Demon King", "無垢なる魔王",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/7d/Kozue_SR5.jpg", "https://www.project-imas.com/w/images/thumb/7/7d/Kozue_SR5.jpg/192px-Kozue_SR5.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5b/Kozue_SR5%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/5b/Kozue_SR5%2B.jpg/192px-Kozue_SR5%2B.jpg")
			), new IdolSkin(
				6, "Fluffy Doll", "ふわふわﾄﾞｰﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/4b/Kozue_SR6.jpg", "https://www.project-imas.com/w/images/thumb/4/4b/Kozue_SR6.jpg/192px-Kozue_SR6.jpg"),
				new Card("https://www.project-imas.com/w/images/d/da/Kozue_SR6%2B.jpg", "https://www.project-imas.com/w/images/thumb/d/da/Kozue_SR6%2B.jpg/192px-Kozue_SR6%2B.jpg")
			)
		));
	}

}
