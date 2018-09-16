package net.azzerial.imcg.idols.cool;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class HinaAraki extends CuteGirl {

	public HinaAraki() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			6, IdolTier.TIER_3,
			"Hina Araki", "荒木比奈", IdolType.COOL,
			20, new Birthday(9, Month.APRIL),
			157, 43, new Measurement(83, 57, 82),
			BloodType.A, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/a/ae/Hina_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Hina Araki", "荒木比奈",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/1/11/HinaArakiNormal1.jpg", "https://www.project-imas.com/w/images/thumb/1/11/HinaArakiNormal1.jpg/192px-HinaArakiNormal1.jpg"),
				new Card("https://www.project-imas.com/w/images/9/94/HinaArakiNormal%2B1.jpg", "https://www.project-imas.com/w/images/thumb/9/94/HinaArakiNormal%2B1.jpg/192px-HinaArakiNormal%2B1.jpg")
			), new IdolSkin(
				1, "Cyber Glass", "サイバーグラス",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/1/1f/HinaArakiRare1.jpg", "https://www.project-imas.com/w/images/thumb/1/1f/HinaArakiRare1.jpg/192px-HinaArakiRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/c/c5/HinaArakiRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/c/c5/HinaArakiRare%2B1.jpg/192px-HinaArakiRare%2B1.jpg")
			), new IdolSkin(
				2, "Night Party", "ﾅｲﾄﾊﾟｰﾃｨｰ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/77/HinaArakiR3.jpg", "https://www.project-imas.com/w/images/thumb/7/77/HinaArakiR3.jpg/192px-HinaArakiR3.jpg"),
				new Card("https://www.project-imas.com/w/images/1/18/HinaArakiR%2B3.jpg", "https://www.project-imas.com/w/images/thumb/1/18/HinaArakiR%2B3.jpg/192px-HinaArakiR%2B3.jpg")
			), new IdolSkin(
				3, "Blue Float Party", "ﾌﾞﾙｰﾌﾛｰﾄﾊﾟｰﾃｨｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/75/HinaArakiSRare1.jpg", "https://www.project-imas.com/w/images/thumb/7/75/HinaArakiSRare1.jpg/192px-HinaArakiSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/2/28/HinaArakiSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/2/28/HinaArakiSRare%2B1.jpg/192px-HinaArakiSRare%2B1.jpg")
			), new IdolSkin(
				4, "Summer Memories", "夏の思い出",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/9b/HinaArakiSR3.jpg", "https://www.project-imas.com/w/images/thumb/9/9b/HinaArakiSR3.jpg/192px-HinaArakiSR3.jpg"),
				new Card("https://www.project-imas.com/w/images/6/63/HinaArakiSR%2B3.jpg", "https://www.project-imas.com/w/images/thumb/6/63/HinaArakiSR%2B3.jpg/192px-HinaArakiSR%2B3.jpg")
			), new IdolSkin(
				5, "Heart Paint", "こころﾍﾟｲﾝﾄ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f6/Hina_SR4.jpg", "https://www.project-imas.com/w/images/thumb/f/f6/Hina_SR4.jpg/192px-Hina_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/0/0e/Hina_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/0/0e/Hina_SR4%2B.jpg/192px-Hina_SR4%2B.jpg")
			), new IdolSkin(
				6, "Blue Day Memory", "青き日のﾒﾓﾘｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/96/Hina_SR5.jpg", "https://www.project-imas.com/w/images/thumb/9/96/Hina_SR5.jpg/192px-Hina_SR5.jpg"),
				new Card("https://www.project-imas.com/w/images/2/29/Hina_SR5%2B.jpg", "https://www.project-imas.com/w/images/thumb/2/29/Hina_SR5%2B.jpg/192px-Hina_SR5%2B.jpg")
			)
		));
	}

}
