package net.azzerial.imcg.idols.passion;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class MikaJougasaki extends CuteGirl {

	public MikaJougasaki() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			14, IdolTier.TIER_1,
			"Mika Jougasaki", "城ヶ崎美嘉", IdolType.PASSION,
			17, new Birthday(12, Month.NOVEMBER),
			162, 43, new Measurement(80, 56, 82),
			BloodType.AB, Handedness.LEFT,
			"https://www.project-imas.com/w/images/e/e1/Mika_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Mika Jougasaki", "城ヶ崎美嘉",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/9/9c/Mika1.jpg", "https://www.project-imas.com/w/images/thumb/9/9c/Mika1.jpg/192px-Mika1.jpg"),
				new Card("https://www.project-imas.com/w/images/5/5d/Mika_R2%2B.jpg", "https://www.project-imas.com/w/images/thumb/5/5d/Mika_R2%2B.jpg/192px-Mika_R2%2B.jpg")
			), new IdolSkin(
				1, "Secret Heart", "シークレット★ハート",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/3/32/Mikash1.jpg", "https://www.project-imas.com/w/images/thumb/3/32/Mikash1.jpg/192px-Mikash1.jpg"),
				new Card("https://www.project-imas.com/w/images/4/49/Mikash2.jpg", "https://www.project-imas.com/w/images/thumb/4/49/Mikash2.jpg/192px-Mikash2.jpg")
			), new IdolSkin(
				2, "Wonderful Magic", "ﾜﾝﾀﾞﾌﾙﾏｼﾞｯｸ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/4b/MikaWonderful.jpg", "https://www.project-imas.com/w/images/thumb/4/4b/MikaWonderful.jpg/192px-MikaWonderful.jpg"),
				new Card("https://www.project-imas.com/w/images/7/71/MikaWonderful1.jpg", "https://www.project-imas.com/w/images/thumb/7/71/MikaWonderful1.jpg/192px-MikaWonderful1.jpg")
			), new IdolSkin(
				3, "Summer Temptation", "夏のﾕｳﾜｸ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/d/db/MikaBeach.jpg", "https://www.project-imas.com/w/images/thumb/d/db/MikaBeach.jpg/192px-MikaBeach.jpg"),
				new Card("https://www.project-imas.com/w/images/d/d9/MikaBeach%2B.jpg", "https://www.project-imas.com/w/images/thumb/d/d9/MikaBeach%2B.jpg/192px-MikaBeach%2B.jpg")
			), new IdolSkin(
				4, "Love ★ Summer Night", "ﾗﾌﾞ★ｻﾏｰﾅｲﾄ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/4/47/Mika_SR8.jpg", "https://www.project-imas.com/w/images/thumb/4/47/Mika_SR8.jpg/192px-Mika_SR8.jpg"),
				new Card("https://www.project-imas.com/w/images/6/69/Mika_SR9%2B.jpg", "https://www.project-imas.com/w/images/thumb/6/69/Mika_SR9%2B.jpg/192px-Mika_SR9%2B.jpg")
			), new IdolSkin(
				5, "Your ★ Warm", "ﾕｱ★ｳｫｰﾑ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/1/1e/Mika_SR11.jpg", "https://www.project-imas.com/w/images/thumb/1/1e/Mika_SR11.jpg/192px-Mika_SR11.jpg"),
				new Card("https://www.project-imas.com/w/images/1/1c/Mika_SR11%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/1c/Mika_SR11%2B.jpg/192px-Mika_SR11%2B.jpg")
			), new IdolSkin(
				6, "Early Summer Vacation", "ｱｰﾘｰｻﾏｰﾊﾞｶﾝｽ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f0/Mika_SR15.jpg", "https://www.project-imas.com/w/images/thumb/f/f0/Mika_SR15.jpg/192px-Mika_SR15.jpg"),
				new Card("https://www.project-imas.com/w/images/1/15/Mika_SR15%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/15/Mika_SR15%2B.jpg/192px-Mika_SR15%2B.jpg")
			)
		));
	}

}
