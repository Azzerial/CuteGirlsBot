package net.azzerial.imcg.idols.cute;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class WakabaKusakabe extends CuteGirl {

	public WakabaKusakabe() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			2, IdolTier.TIER_2,
			"Wakaba Kusakabe", "日下部若葉", IdolType.CUTE,
			20, new Birthday(4, Month.MAY),
			130, 28, new Measurement(77, 54, 78),
			BloodType.O, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/b/b5/Wakaba_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Wakaba Kusakabe", "日下部若葉",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/0/03/WakabaKusakabeRare1.jpg", "https://www.project-imas.com/w/images/thumb/0/03/WakabaKusakabeRare1.jpg/192px-WakabaKusakabeRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4a/WakabaKusakabeRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/4/4a/WakabaKusakabeRare%2B1.jpg/192px-WakabaKusakabeRare%2B1.jpg")
			), new IdolSkin(
				1, "Summer Vacation", "ｻﾏｰﾊﾞｹｰｼｮﾝ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/fd/WakabaKusakabeRare3.jpg", "https://www.project-imas.com/w/images/thumb/f/fd/WakabaKusakabeRare3.jpg/192px-WakabaKusakabeRare3.jpg"),
				new Card("https://www.project-imas.com/w/images/3/32/WakabaKusakabeRare%2B3.jpg", "https://www.project-imas.com/w/images/thumb/3/32/WakabaKusakabeRare%2B3.jpg/192px-WakabaKusakabeRare%2B3.jpg")
			), new IdolSkin(
				2, "Steamy Travelogue", "湯けむり紀行",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/56/Wakaba_R6.jpg", "https://www.project-imas.com/w/images/thumb/5/56/Wakaba_R6.jpg/192px-Wakaba_R6.jpg"),
				new Card("https://www.project-imas.com/w/images/0/0c/Wakaba_R6%2B.jpg", "https://www.project-imas.com/w/images/thumb/0/0c/Wakaba_R6%2B.jpg/192px-Wakaba_R6%2B.jpg")
			), new IdolSkin(
				3, "Roses & Gothic", "ﾛｰｾﾞｽｺﾞｼｯｸ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f5/WakabaKusakabeSRare1.jpg", "https://www.project-imas.com/w/images/thumb/f/f5/WakabaKusakabeSRare1.jpg/192px-WakabaKusakabeSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/d/db/WakabaKusakabeSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/d/db/WakabaKusakabeSRare%2B1.jpg/192px-WakabaKusakabeSRare%2B1.jpg")
			), new IdolSkin(
				4, "Little Royal", "ﾛﾜｲﾔﾙﾘﾄﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/a/a7/WakabaKusakabeSRare2.jpg", "https://www.project-imas.com/w/images/thumb/a/a7/WakabaKusakabeSRare2.jpg/192px-WakabaKusakabeSRare2.jpg"),
				new Card("https://www.project-imas.com/w/images/5/56/WakabaKusakabeSRare%2B2.jpg", "https://www.project-imas.com/w/images/thumb/5/56/WakabaKusakabeSRare%2B2.jpg/192px-WakabaKusakabeSRare%2B2.jpg")
			), new IdolSkin(
				5, "Sexy Detective", "ｾｸｼｰ刑事",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/3/3d/Wakaba_SR3.jpg", "https://www.project-imas.com/w/images/thumb/3/3d/Wakaba_SR3.jpg/192px-Wakaba_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/0/06/Wakaba_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/0/06/Wakaba_SR3%2B.jpg/192px-Wakaba_SR3%2B.jpg")
			), new IdolSkin(
				6, "Overheat", "ｵｰﾊﾞｰﾋｰﾄ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/6c/Wakaba_SR4.jpg", "https://www.project-imas.com/w/images/thumb/6/6c/Wakaba_SR4.jpg/192px-Wakaba_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/0/08/Wakaba_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/0/08/Wakaba_SR4%2B.jpg/192px-Wakaba_SR4%2B.jpg")
			)
		));
	}

}
