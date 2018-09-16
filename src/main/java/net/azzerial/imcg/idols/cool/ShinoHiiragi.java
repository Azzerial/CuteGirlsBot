package net.azzerial.imcg.idols.cool;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class ShinoHiiragi extends CuteGirl {

	public ShinoHiiragi() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			7, IdolTier.TIER_2,
			"Shino Hiiragi", "柊志乃", IdolType.COOL,
			31, new Birthday(25, Month.DECEMBER),
			167, 43, new Measurement(84, 54, 83),
			BloodType.AB, Handedness.LEFT,
			"https://www.project-imas.com/w/images/b/b4/Shino_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Shino Hiiragi", "柊志乃",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/a/a3/ShinoHiiragiRare1.jpg", "https://www.project-imas.com/w/images/thumb/a/a3/ShinoHiiragiRare1.jpg/192px-ShinoHiiragiRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/d/d7/ShinoHiiragiRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/d/d7/ShinoHiiragiRare%2B1.jpg/192px-ShinoHiiragiRare%2B1.jpg")
			), new IdolSkin(
				1, "New Year Collection", "新春ｺﾚｸｼｮﾝ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/5f/ShinoHiiragiR4.jpg", "https://www.project-imas.com/w/images/thumb/5/5f/ShinoHiiragiR4.jpg/192px-ShinoHiiragiR4.jpg"),
				new Card("https://www.project-imas.com/w/images/3/3a/ShinoHiiragiR%2B4.jpg", "https://www.project-imas.com/w/images/thumb/3/3a/ShinoHiiragiR%2B4.jpg/192px-ShinoHiiragiR%2B4.jpg")
			), new IdolSkin(
				2, "Lure de Ready", "ﾙｱｰﾄﾞﾚﾃﾞｨ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/28/ShinoHiiragiR5.jpg", "https://www.project-imas.com/w/images/thumb/2/28/ShinoHiiragiR5.jpg/192px-ShinoHiiragiR5.jpg"),
				new Card("https://www.project-imas.com/w/images/9/9a/ShinoHiiragiR%2B5.jpg", "https://www.project-imas.com/w/images/thumb/9/9a/ShinoHiiragiR%2B5.jpg/192px-ShinoHiiragiR%2B5.jpg")
			), new IdolSkin(
				3, "Tipsy Dance Performance", "ほろ酔い演舞",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/0/0c/ShinoHiiragiSRare1.jpg", "https://www.project-imas.com/w/images/thumb/0/0c/ShinoHiiragiSRare1.jpg/192px-ShinoHiiragiSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/1/15/ShinoHiiragiSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/1/15/ShinoHiiragiSRare%2B1.jpg/192px-ShinoHiiragiSRare%2B1.jpg")
			), new IdolSkin(
				4, "Drunk Pirate", "ﾄﾞﾗﾝｶｰﾊﾟｲﾚｰﾂ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/71/ShinoHiiragiSR2.jpg", "https://www.project-imas.com/w/images/thumb/7/71/ShinoHiiragiSR2.jpg/192px-ShinoHiiragiSR2.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5d/ShinoHiiragiSR%2B2.jpg", "https://www.project-imas.com/w/images/thumb/5/5d/ShinoHiiragiSR%2B2.jpg/192px-ShinoHiiragiSR%2B2.jpg")
			), new IdolSkin(
				5, "Drunk Royale", "ﾄﾞﾗﾝｸ･ﾛﾜｲﾔﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/46/Shino_SR3.jpg", "https://www.project-imas.com/w/images/thumb/4/46/Shino_SR3.jpg/192px-Shino_SR3.jpg"),
				new Card("https://www.project-imas.com/w/images/1/19/Shino_SR3%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/19/Shino_SR3%2B.jpg/192px-Shino_SR3%2B.jpg")
			), new IdolSkin(
				6, "Garden Spring Breeze", "花園の春風",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/9b/Shino_SR4.jpg", "https://www.project-imas.com/w/images/thumb/9/9b/Shino_SR4.jpg/192px-Shino_SR4.jpg"),
				new Card("https://www.project-imas.com/w/images/f/f9/Shino_SR4%2B.jpg", "https://www.project-imas.com/w/images/thumb/f/f9/Shino_SR4%2B.jpg/192px-Shino_SR4%2B.jpg")
			)
		));
	}

}
