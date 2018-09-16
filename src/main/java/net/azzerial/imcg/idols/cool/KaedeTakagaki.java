package net.azzerial.imcg.idols.cool;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class KaedeTakagaki extends CuteGirl {

	public KaedeTakagaki() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			9, IdolTier.TIER_1,
			"Kaede Takagaki", "高垣楓", IdolType.COOL,
			25, new Birthday(14, Month.JUNE),
			171, 49, new Measurement(81, 57, 83),
			BloodType.AB, Handedness.LEFT,
			"https://www.project-imas.com/w/images/c/cd/Chara_197_00_base.dds.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Kaede Takagaki", "高垣楓",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/c7/2200501.jpg", "https://www.project-imas.com/w/images/thumb/c/c7/2200501.jpg/192px-2200501.jpg"),
				new Card("https://www.project-imas.com/w/images/c/c3/Tumblr_m0bb80fVub1qzvtljo1_500.jpg", "https://www.project-imas.com/w/images/thumb/c/c3/Tumblr_m0bb80fVub1qzvtljo1_500.jpg/192px-Tumblr_m0bb80fVub1qzvtljo1_500.jpg")
			), new IdolSkin(
				1, "Steamy Goddess", "湯けむり女神",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/7/76/Kaede_Takagaki_S_Rare_3.jpg", "https://www.project-imas.com/w/images/thumb/7/76/Kaede_Takagaki_S_Rare_3.jpg/192px-Kaede_Takagaki_S_Rare_3.jpg"),
				new Card("https://www.project-imas.com/w/images/0/09/Kaede_Takagaki_S_Rare_Plus_3.jpg", "https://www.project-imas.com/w/images/thumb/0/09/Kaede_Takagaki_S_Rare_Plus_3.jpg/192px-Kaede_Takagaki_S_Rare_Plus_3.jpg")
			), new IdolSkin(
				2, "Verdure Lady", "新緑の淑女",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/66/Kaedev1.jpg", "https://www.project-imas.com/w/images/thumb/6/66/Kaedev1.jpg/192px-Kaedev1.jpg"),
				new Card("https://www.project-imas.com/w/images/1/14/Kaedev2.jpg", "https://www.project-imas.com/w/images/thumb/1/14/Kaedev2.jpg/192px-Kaedev2.jpg")
			), new IdolSkin(
				3, "Happy New Year", "謹賀新年",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/6f/KaedeNewYear.jpg", "https://www.project-imas.com/w/images/thumb/6/6f/KaedeNewYear.jpg/192px-KaedeNewYear.jpg"),
				new Card("https://www.project-imas.com/w/images/5/57/KaedeNewYear%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/57/KaedeNewYear%2B.jpg/192px-KaedeNewYear%2B.jpg")
			), new IdolSkin(
				4, "Southerly White Lady", "白南風の淑女",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/d4/Kaede_SR8.jpg", "https://www.project-imas.com/w/images/thumb/d/d4/Kaede_SR8.jpg/192px-Kaede_SR8.jpg"),
				new Card("https://www.project-imas.com/w/images/d/d3/Kaede_SR8%2B.jpg", "https://www.project-imas.com/w/images/thumb/d/d3/Kaede_SR8%2B.jpg/192px-Kaede_SR8%2B.jpg")
			), new IdolSkin(
				5, "Holy Night Feast", "聖夜の祝宴",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/6/67/Kaede_SR10.jpg", "https://www.project-imas.com/w/images/thumb/6/67/Kaede_SR10.jpg/192px-Kaede_SR10.jpg"),
				new Card("https://www.project-imas.com/w/images/3/3b/Kaede_SR10%2B.jpg", "https://www.project-imas.com/w/images/thumb/3/3b/Kaede_SR10%2B.jpg/192px-Kaede_SR10%2B.jpg")
			), new IdolSkin(
				6, "Cinderella Girl", "ｼﾝﾃﾞﾚﾗｶﾞｰﾙ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/54/Kaede_SR12.jpg", "https://www.project-imas.com/w/images/thumb/5/54/Kaede_SR12.jpg/192px-Kaede_SR12.jpg"),
				new Card("https://www.project-imas.com/w/images/a/a8/Kaede_SR12%2B.jpg", "https://www.project-imas.com/w/images/thumb/a/a8/Kaede_SR12%2B.jpg/192px-Kaede_SR12%2B.jpg")
			)
		));
	}

}
