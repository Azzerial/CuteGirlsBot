package net.azzerial.imcg.idols.cool;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class NaoKamiya extends CuteGirl {

	public NaoKamiya() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			5, IdolTier.TIER_3,
			"Nao Kamiya", "神谷奈緒", IdolType.COOL,
			17, new Birthday(16, Month.SEPTEMBER),
			154, 44, new Measurement(83, 58, 81),
			BloodType.AB, Handedness.LEFT,
			"https://www.project-imas.com/w/images/b/b7/Nao_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Nao Kamiya", "神谷奈緒",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/bf/Nao_kamiya_normal.jpg", "https://www.project-imas.com/w/images/thumb/b/bf/Nao_kamiya_normal.jpg/192px-Nao_kamiya_normal.jpg"),
				new Card("https://www.project-imas.com/w/images/5/58/Nao_kamiya_normal_plus.jpg", "https://www.project-imas.com/w/images/thumb/5/58/Nao_kamiya_normal_plus.jpg/192px-Nao_kamiya_normal_plus.jpg")
			), new IdolSkin(
				1, "Christmas Eve", "聖夜",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/cd/2201901.jpg", "https://www.project-imas.com/w/images/thumb/c/cd/2201901.jpg/192px-2201901.jpg"),
				new Card("https://www.project-imas.com/w/images/5/51/2301902.jpg", "https://www.project-imas.com/w/images/thumb/5/51/2301902.jpg/192px-2301902.jpg")
			), new IdolSkin(
				2, "Kyomachi Girl", "京町乙女",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/25/Naokyomachi.jpg", "https://www.project-imas.com/w/images/thumb/2/25/Naokyomachi.jpg/192px-Naokyomachi.jpg"),
				new Card("https://www.project-imas.com/w/images/a/a6/Naokyomachiplus.jpg", "https://www.project-imas.com/w/images/thumb/a/a6/Naokyomachiplus.jpg/192px-Naokyomachiplus.jpg")
			), new IdolSkin(
				3, "Banquet Maid", "夜宴のﾒｲﾄﾞ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/8/8e/NaoMaid.jpg", "https://www.project-imas.com/w/images/thumb/8/8e/NaoMaid.jpg/192px-NaoMaid.jpg"),
				new Card("https://www.project-imas.com/w/images/3/30/NaoMaid1.jpg", "https://www.project-imas.com/w/images/thumb/3/30/NaoMaid1.jpg/192px-NaoMaid1.jpg")
			), new IdolSkin(
				4, "To Promise", "ﾄｩﾌﾟﾛﾐｽ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/b7/Nao2_SR7.jpg", "https://www.project-imas.com/w/images/thumb/b/b7/Nao2_SR7.jpg/192px-Nao2_SR7.jpg"),
				new Card("https://www.project-imas.com/w/images/1/1d/Nao2_SR7%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/1d/Nao2_SR7%2B.jpg/192px-Nao2_SR7%2B.jpg")
			), new IdolSkin(
				5, "Summer Sky Surprise", "夏空ｻﾌﾟﾗｲｽﾞ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e9/Nao2_SR8.jpg", "https://www.project-imas.com/w/images/thumb/e/e9/Nao2_SR8.jpg/192px-Nao2_SR8.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4c/Nao2_SR8%2B.jpg", "https://www.project-imas.com/w/images/thumb/4/4c/Nao2_SR8%2B.jpg/192px-Nao2_SR8%2B.jpg")
			), new IdolSkin(
				6, "Capricious Kitten", "気まぐれにゃんこ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/1/10/Nao2_SR9.jpg", "https://www.project-imas.com/w/images/thumb/1/10/Nao2_SR9.jpg/192px-Nao2_SR9.jpg"),
				new Card("https://www.project-imas.com/w/images/1/12/Nao2_SR9%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/12/Nao2_SR9%2B.jpg/192px-Nao2_SR9%2B.jpg")
			)
		));
	}

}
