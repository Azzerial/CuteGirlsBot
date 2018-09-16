package net.azzerial.imcg.idols.cool;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class NoaTakamine extends CuteGirl {

	public NoaTakamine() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			8, IdolTier.TIER_2,
			"Noa Takamine", "高峯のあ", IdolType.COOL,
			24, new Birthday(25, Month.MARCH),
			168, 48, new Measurement(87, 55, 86),
			BloodType.B, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/6/6e/Noa_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Noa Takamine", "高峯のあ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/46/NoaTakamineRare2.jpg", "https://www.project-imas.com/w/images/thumb/4/46/NoaTakamineRare2.jpg/192px-NoaTakamineRare2.jpg"),
				new Card("https://www.project-imas.com/w/images/3/3d/NoaTakamineRare%2B2.jpg", "https://www.project-imas.com/w/images/thumb/3/3d/NoaTakamineRare%2B2.jpg/192px-NoaTakamineRare%2B2.jpg")
			), new IdolSkin(
				1, "Cybernetic Beauty", "ｻｲﾊﾞﾈﾃｨｯｸﾋﾞｭｰﾃｨｰ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/8/86/NoaTakamineRare1.jpg", "https://www.project-imas.com/w/images/thumb/8/86/NoaTakamineRare1.jpg/192px-NoaTakamineRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/1/14/NoaTakamineRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/1/14/NoaTakamineRare%2B1.jpg/192px-NoaTakamineRare%2B1.jpg")
			), new IdolSkin(
				2, "Elegant Style", "ｴﾚｶﾞﾝﾄｽﾀｲﾙ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/42/Noa_R6.jpg", "https://www.project-imas.com/w/images/thumb/4/42/Noa_R6.jpg/192px-Noa_R6.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4d/Noa_R6%2B.jpg", "https://www.project-imas.com/w/images/thumb/4/4d/Noa_R6%2B.jpg/192px-Noa_R6%2B.jpg")
			), new IdolSkin(
				3, "Queen of Silence", "寡黙の女王",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/ce/NoaTakamineSRare1.jpg", "https://www.project-imas.com/w/images/thumb/c/ce/NoaTakamineSRare1.jpg/192px-NoaTakamineSRare1.jpg"),
				new Card("https://www.project-imas.com/w/images/7/7d/NoaTakamineSRare%2B1.jpg", "https://www.project-imas.com/w/images/thumb/7/7d/NoaTakamineSRare%2B1.jpg/192px-NoaTakamineSRare%2B1.jpg")
			), new IdolSkin(
				4, "Transcend Bunny", "ﾄﾗﾝｾﾝﾄﾞﾊﾞﾆｰ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/0/0a/NoaTakamineSRare3.jpg", "https://www.project-imas.com/w/images/thumb/0/0a/NoaTakamineSRare3.jpg/192px-NoaTakamineSRare3.jpg"),
				new Card("https://www.project-imas.com/w/images/4/4d/NoaTakamineSRare%2B3.jpg", "https://www.project-imas.com/w/images/thumb/4/4d/NoaTakamineSRare%2B3.jpg/192px-NoaTakamineSRare%2B3.jpg")
			), new IdolSkin(
				5, "Silver Bullet Shooter", "銀弾の射手",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/2f/NoaTakamineSRare4.jpg", "https://www.project-imas.com/w/images/thumb/2/2f/NoaTakamineSRare4.jpg/192px-NoaTakamineSRare4.jpg"),
				new Card("https://www.project-imas.com/w/images/f/f6/NoaTakamineSRare%2B4.jpg", "https://www.project-imas.com/w/images/thumb/f/f6/NoaTakamineSRare%2B4.jpg/192px-NoaTakamineSRare%2B4.jpg")
			), new IdolSkin(
				6, "To be great transborder person", "大いなる越境者",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/a/a4/Noa_SR7.jpg", "https://www.project-imas.com/w/images/thumb/a/a4/Noa_SR7.jpg/192px-Noa_SR7.jpg"),
				new Card("https://www.project-imas.com/w/images/0/0f/Noa_SR7%2B.jpg", "https://www.project-imas.com/w/images/thumb/0/0f/Noa_SR7%2B.jpg/192px-Noa_SR7%2B.jpg")
			)
		));
	}

}
