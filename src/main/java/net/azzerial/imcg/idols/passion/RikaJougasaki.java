package net.azzerial.imcg.idols.passion;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class RikaJougasaki extends CuteGirl {

	public RikaJougasaki() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			13, IdolTier.TIER_1,
			"Rika Jougasaki", "城ヶ崎莉嘉", IdolType.PASSION,
			12, new Birthday(30, Month.JULY),
			149, 39, new Measurement(72, 54, 75),
			BloodType.B, Handedness.LEFT,
			"https://www.project-imas.com/w/images/3/36/Rika_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Rika Jougasaki", "城ヶ崎莉嘉",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e9/Rika_rare.jpg", "https://www.project-imas.com/w/images/thumb/e/e9/Rika_rare.jpg/192px-Rika_rare.jpg"),
				new Card("https://www.project-imas.com/w/images/f/f3/Rika_rare_plus.jpg", "https://www.project-imas.com/w/images/thumb/f/f3/Rika_rare_plus.jpg/192px-Rika_rare_plus.jpg")
			), new IdolSkin(
				1, "Stylish Sakura", "ﾊｲｶﾗｻｸﾗ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/da/Rika_R3.jpg", "https://www.project-imas.com/w/images/thumb/d/da/Rika_R3.jpg/192px-Rika_R3.jpg"),
				new Card("https://www.project-imas.com/w/images/6/6e/Rika_R3%2B.jpg", "https://www.project-imas.com/w/images/thumb/6/6e/Rika_R3%2B.jpg/192px-Rika_R3%2B.jpg")
			), new IdolSkin(
				2, "Small Charisma Gal", "カリスマちびギャル",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/6b/Rikasrare.jpg", "https://www.project-imas.com/w/images/thumb/6/6b/Rikasrare.jpg/192px-Rikasrare.jpg"),
				new Card("https://www.project-imas.com/w/images/0/07/Rikasrareplus.jpg", "https://www.project-imas.com/w/images/thumb/0/07/Rikasrareplus.jpg/192px-Rikasrareplus.jpg")
			), new IdolSkin(
				3, "Wonderful Magic", "ﾜﾝﾀﾞﾌﾙﾏｼﾞｯｸ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/54/RikaWonderful.jpg", "https://www.project-imas.com/w/images/thumb/5/54/RikaWonderful.jpg/192px-RikaWonderful.jpg"),
				new Card("https://www.project-imas.com/w/images/9/9d/RikaWonderful1.jpg", "https://www.project-imas.com/w/images/thumb/9/9d/RikaWonderful1.jpg/192px-RikaWonderful1.jpg")
			), new IdolSkin(
				4, "A bit ☆ Gal", "ちょこっと☆ｷﾞｬﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/26/Rika_SR11.jpg", "https://www.project-imas.com/w/images/thumb/2/26/Rika_SR11.jpg/192px-Rika_SR11.jpg"),
				new Card("https://www.project-imas.com/w/images/3/3a/Rika_SR11%2B.jpg", "https://www.project-imas.com/w/images/thumb/3/3a/Rika_SR11%2B.jpg/192px-Rika_SR11%2B.jpg")
			), new IdolSkin(
				5, "Superior ☆ New Year", "とびきり☆ﾆｭｰｲﾔｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c8/Rika_SR13.jpg", "https://www.project-imas.com/w/images/thumb/c/c8/Rika_SR13.jpg/192px-Rika_SR13.jpg"),
				new Card("https://www.project-imas.com/w/images/9/92/Rika_SR13%2B.jpg", "https://www.project-imas.com/w/images/thumb/9/92/Rika_SR13%2B.jpg/192px-Rika_SR13%2B.jpg")
			), new IdolSkin(
				6, "Bursting ☆ Sunshine", "ﾊｼﾞｹﾙ☆ｻﾝｼｬｲﾝ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/4b/Rika_SR14.jpg", "https://www.project-imas.com/w/images/thumb/4/4b/Rika_SR14.jpg/192px-Rika_SR14.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5e/Rika_SR14%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/5e/Rika_SR14%2B.jpg/192px-Rika_SR14%2B.jpg")
			)
		));
	}

}
