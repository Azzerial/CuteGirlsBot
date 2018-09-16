package net.azzerial.imcg.idols.passion;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class AyunaHamakawa extends CuteGirl {

	public AyunaHamakawa() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			12, IdolTier.TIER_2,
			"Ayuna Hamakawa", "浜川愛結奈", IdolType.PASSION,
			22, new Birthday(25, Month.MAY),
			168, 50, new Measurement(92, 58, 85),
			BloodType.B, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/b/b0/Ayuna_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Ayuna Hamakawa", "浜川愛結奈",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/fa/AyunaHamakawaRare2.jpg", "https://www.project-imas.com/w/images/thumb/f/fa/AyunaHamakawaRare2.jpg/192px-AyunaHamakawaRare2.jpg"),
				new Card("https://www.project-imas.com/w/images/8/8d/AyunaHamakawaRare%2B2.jpg", "https://www.project-imas.com/w/images/thumb/8/8d/AyunaHamakawaRare%2B2.jpg/192px-AyunaHamakawaRare%2B2.jpg")
			), new IdolSkin(
				1, "Beachside", "ﾋﾞｰﾁｻｲﾄﾞ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/69/AyunaR3.jpg", "https://www.project-imas.com/w/images/thumb/6/69/AyunaR3.jpg/192px-AyunaR3.jpg"),
				new Card("https://www.project-imas.com/w/images/b/b6/AyunaR%2B3.jpg", "https://www.project-imas.com/w/images/thumb/b/b6/AyunaR%2B3.jpg/192px-AyunaR%2B3.jpg")
			), new IdolSkin(
				2, "Backside of Self-Confidence", "自信の裏側",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/72/Ayuna_R7.jpg", "https://www.project-imas.com/w/images/thumb/7/72/Ayuna_R7.jpg/192px-Ayuna_R7.jpg"),
				new Card("https://www.project-imas.com/w/images/f/f9/Ayuna_R7%2B.jpg", "https://www.project-imas.com/w/images/thumb/f/f9/Ayuna_R7%2B.jpg/192px-Ayuna_R7%2B.jpg")
			), new IdolSkin(
				3, "Summer Girl", "ｻﾏｰｶﾞｰﾙ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/99/Ayuna_R8.jpg", "https://www.project-imas.com/w/images/thumb/9/99/Ayuna_R8.jpg/192px-Ayuna_R8.jpg"),
				new Card("https://www.project-imas.com/w/images/b/bc/Ayuna_R8%2B.jpg", "https://www.project-imas.com/w/images/thumb/b/bc/Ayuna_R8%2B.jpg/192px-Ayuna_R8%2B.jpg")
			), new IdolSkin(
				4, "Sexy Diva", "セクシーディーヴァ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c5/AyunaHamakawaSRare1.jpg", "https://www.project-imas.com/w/images/thumb/c/c5/AyunaHamakawaSRare1.jpg/192px-AyunaHamakawaSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/8/85/AyunaHamakawaSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/8/85/AyunaHamakawaSRare%2B1.jpg/192px-AyunaHamakawaSRare%2B1.jpg")
			), new IdolSkin(
				5, "Panache Royal", "ﾊﾟﾅｼｪ･ﾛﾜｲﾔﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/7f/AyunaHamakawaSR2.jpg", "https://www.project-imas.com/w/images/thumb/7/7f/AyunaHamakawaSR2.jpg/192px-AyunaHamakawaSR2.jpg"),
				new Card("https://www.project-imas.com/w/images/f/ff/AyunaHamakawaSR%2B2.jpg", "https://www.project-imas.com/w/images/thumb/f/ff/AyunaHamakawaSR%2B2.jpg/192px-AyunaHamakawaSR%2B2.jpg")
			), new IdolSkin(
				6, "Unruly Cheer", "ｱﾝﾙｰﾘｨﾁｱｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/0/06/Ayuna_SR3.jpg", "https://www.project-imas.com/w/images/thumb/0/06/Ayuna_SR3.jpg/192px-Ayuna_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/3/36/Ayuna_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/3/36/Ayuna_SR3%2B.jpg/192px-Ayuna_SR3%2B.jpg")
			), new IdolSkin(
				7, "Long Autumn Night", "ﾛﾝｸﾞｵｰﾀﾑﾅｲﾄ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/d4/Ayuna_SR4.jpg", "https://www.project-imas.com/w/images/thumb/d/d4/Ayuna_SR4.jpg/192px-Ayuna_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/7/73/Ayuna_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/7/73/Ayuna_SR4%2B.jpg/192px-Ayuna_SR4%2B.jpg")
			)
		));
	}

}
