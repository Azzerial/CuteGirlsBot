package net.azzerial.imcg.idols.passion;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class TokikoZaizen extends CuteGirl {

	public TokikoZaizen() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			10, IdolTier.TIER_3,
			"Tokiko Zaizen", "財前時子", IdolType.PASSION,
			21, new Birthday(18, Month.APRIL),
			168, 46, new Measurement(83, 55, 85),
			BloodType.B, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/3/30/Tokiko_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Tokiko Zaizen", "財前時子",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/b4/TokikoZaizenNormal1.jpg", "https://www.project-imas.com/w/images/thumb/b/b4/TokikoZaizenNormal1.jpg/192px-TokikoZaizenNormal1.jpg"),
				new Card("https://www.project-imas.com/w/images/0/0b/TokikoZaizenNormal%2B1.jpg", "https://www.project-imas.com/w/images/thumb/0/0b/TokikoZaizenNormal%2B1.jpg/192px-TokikoZaizenNormal%2B1.jpg")
			), new IdolSkin(
				1, "Schoolgirl", "ｽｸｰﾙｶﾞｰﾙ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/db/TokikoR2.jpg", "https://www.project-imas.com/w/images/thumb/d/db/TokikoR2.jpg/192px-TokikoR2.jpg"),
				new Card("https://www.project-imas.com/w/images/b/b7/TokikoR%2B2.jpg", "https://www.project-imas.com/w/images/thumb/b/b7/TokikoR%2B2.jpg/192px-TokikoR%2B2.jpg")
			), new IdolSkin(
				2, "Summer Beach", "ｻﾏｰﾋﾞｰﾁ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/b8/Tokiko_R4.jpg", "https://www.project-imas.com/w/images/thumb/b/b8/Tokiko_R4.jpg/192px-Tokiko_R4.jpg"),
				new Card("https://www.project-imas.com/w/images/2/2f/Tokiko_R4%2B.jpg", "https://www.project-imas.com/w/images/thumb/2/2f/Tokiko_R4%2B.jpg/192px-Tokiko_R4%2B.jpg")
			), new IdolSkin(
				3, "Queen of Media", "ﾒﾃﾞｨｱの女王",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/9d/Tokiko5.jpg", "https://www.project-imas.com/w/images/thumb/9/9d/Tokiko5.jpg/192px-Tokiko5.jpg"),
				new Card("https://www.project-imas.com/w/images/9/9c/Tokiko6.jpg", "https://www.project-imas.com/w/images/thumb/9/9c/Tokiko6.jpg/192px-Tokiko6.jpg")
			), new IdolSkin(
				4, "Velvet Queen", "ﾍﾞﾙﾍﾞｯﾄｸｲｰﾝ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e6/Tokiko_SR2.jpg", "https://www.project-imas.com/w/images/thumb/e/e6/Tokiko_SR2.jpg/192px-Tokiko_SR2.jpg"),
				new Card("https://www.project-imas.com/w/images/d/d0/Tokiko_SR2%2B.jpg", "https://www.project-imas.com/w/images/thumb/d/d0/Tokiko_SR2%2B.jpg/192px-Tokiko_SR2%2B.jpg")
			), new IdolSkin(
				5, "Dominate Christmas", "ﾄﾞﾐﾈｲﾄｸﾘｽﾏｽ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/e/e0/Tokiko_SR3.jpg", "https://www.project-imas.com/w/images/thumb/e/e0/Tokiko_SR3.jpg/192px-Tokiko_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5a/Tokiko_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/5a/Tokiko_SR3%2B.jpg/192px-Tokiko_SR3%2B.jpg")
			), new IdolSkin(
				6, "Freezing Heat Wave", "凍てつく炎暑",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f9/Tokiko_SR4.jpg", "https://www.project-imas.com/w/images/thumb/f/f9/Tokiko_SR4.jpg/192px-Tokiko_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/b/be/Tokiko_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/b/be/Tokiko_SR4%2B.jpg/192px-Tokiko_SR4%2B.jpg")
			)
		));
	}

}
