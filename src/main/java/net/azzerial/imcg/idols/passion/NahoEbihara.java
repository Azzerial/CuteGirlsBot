package net.azzerial.imcg.idols.passion;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class NahoEbihara extends CuteGirl {

	public NahoEbihara() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			11, IdolTier.TIER_3,
			"Naho Ebihara", "海老原菜帆", IdolType.PASSION,
			17, new Birthday(3, Month.AUGUST),
			162, 58, new Measurement(92, 65, 93),
			BloodType.O, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/4/48/Naho_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Naho Ebihara", "海老原菜帆",
				Rarity.NORMAL,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/90/NahoEbiharaNormal1.jpg", "https://www.project-imas.com/w/images/thumb/9/90/NahoEbiharaNormal1.jpg/192px-NahoEbiharaNormal1.jpg"),
				new Card("https://www.project-imas.com/w/images/a/a3/NahoEbiharaNormal%2B1.jpg", "https://www.project-imas.com/w/images/thumb/a/a3/NahoEbiharaNormal%2B1.jpg/192px-NahoEbiharaNormal%2B1.jpg")
			), new IdolSkin(
				1, "Punipuni Summer", "ぷにぷにｻﾏｰ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/df/NahoEbiharaRare3.jpg", "https://www.project-imas.com/w/images/thumb/d/df/NahoEbiharaRare3.jpg/192px-NahoEbiharaRare3.jpg"),
				new Card("https://www.project-imas.com/w/images/d/dc/NahoEbiharaRare%2B3.jpg", "https://www.project-imas.com/w/images/thumb/d/dc/NahoEbiharaRare%2B3.jpg/192px-NahoEbiharaRare%2B3.jpg")
			), new IdolSkin(
				2, "Summer Temptation", "ｻﾏｰﾃﾝﾌﾟﾃｰｼｮﾝ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c5/Naho_R8.jpg", "https://www.project-imas.com/w/images/thumb/c/c5/Naho_R8.jpg/192px-Naho_R8.jpg"),
				new Card("https://www.project-imas.com/w/images/5/55/Naho_R8%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/55/Naho_R8%2B.jpg/192px-Naho_R8%2B.jpg")
			), new IdolSkin(
				3, "Puny Maiden", "ﾌﾟﾆｮﾌﾜ乙女",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/4d/NahoEbiharaSRare1.jpg", "https://www.project-imas.com/w/images/thumb/4/4d/NahoEbiharaSRare1.jpg/192px-NahoEbiharaSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/f/f5/NahoEbiharaSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/f/f5/NahoEbiharaSRare%2B1.jpg/192px-NahoEbiharaSRare%2B1.jpg")
			), new IdolSkin(
				4, "Squishy Cheer", "ぷにっとﾁｱｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/4e/Naho_SR3.jpg", "https://www.project-imas.com/w/images/thumb/4/4e/Naho_SR3.jpg/192px-Naho_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/8/8c/Naho_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/8/8c/Naho_SR3%2B.jpg/192px-Naho_SR3%2B.jpg")
			), new IdolSkin(
				5, "Fluffy Glossy Maiden", "ふわっと艶乙女",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f5/Naho_SR4.jpg", "https://www.project-imas.com/w/images/thumb/f/f5/Naho_SR4.jpg/192px-Naho_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/1/1b/Naho_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/1b/Naho_SR4%2B.jpg/192px-Naho_SR4%2B.jpg")
			), new IdolSkin(
				6, "Cheering Star", "ﾁｱﾘﾝｸﾞｽﾀｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/b/b7/Naho_SR5.jpg", "https://www.project-imas.com/w/images/thumb/b/b7/Naho_SR5.jpg/192px-Naho_SR5.jpg"),
				new Card("https://www.project-imas.com/w/images/a/a3/Naho_SR5%2B.jpg", "https://www.project-imas.com/w/images/thumb/a/a3/Naho_SR5%2B.jpg/192px-Naho_SR5%2B.jpg")
			)
		));
	}

}
