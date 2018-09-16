package net.azzerial.imcg.idols.cute;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.IdolSkin;
import net.azzerial.imcg.entities.utils.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.Arrays;
import java.util.List;

public class AnzuFutaba extends CuteGirl {

	public AnzuFutaba() {
		if (addToDatabase()) {
			System.out.println("[Database]: " + getIdol().getLatinName() + " has been added to the database.");
		}
	}

	@Override
	public Idol getIdol() {
		return (new Idol(
			3, IdolTier.TIER_1,
			"Anzu Futaba", "双葉杏", IdolType.CUTE,
			17, new Birthday(2, Month.SEPTEMBER),
			139, 30, new Measurement(0, 0, 0),
			BloodType.B, Handedness.RIGHT,
			"https://www.project-imas.com/w/images/b/b1/Anzu_SS.png",
			getIdolSkins()
		));
	}

	@Override
	public List<IdolSkin> getIdolSkins() {
		return (Arrays.asList(
			new IdolSkin(
				0, "Anzu Futaba", "双葉杏",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/53/Tumblr_m0lcjmiEsy1qzpivvo1_500.jpg", "https://www.project-imas.com/w/images/thumb/5/53/Tumblr_m0lcjmiEsy1qzpivvo1_500.jpg/192px-Tumblr_m0lcjmiEsy1qzpivvo1_500.jpg"),
				new Card("https://www.project-imas.com/w/images/1/15/Tumblr_m0lcj7ZAo71qzpivvo1_500.jpg", "https://www.project-imas.com/w/images/thumb/1/15/Tumblr_m0lcj7ZAo71qzpivvo1_500.jpg/192px-Tumblr_m0lcj7ZAo71qzpivvo1_500.jpg")
			), new IdolSkin(
				1, "Stylish Sakura", "ﾊｲｶﾗｻｸﾗ",
				Rarity.RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/53/Anzu_R3.jpg", "https://www.project-imas.com/w/images/thumb/5/53/Anzu_R3.jpg/192px-Anzu_R3.jpg"),
				new Card("https://www.project-imas.com/w/images/c/c6/Anzu_R3%2B.jpg", "https://www.project-imas.com/w/images/thumb/c/c6/Anzu_R3%2B.jpg/192px-Anzu_R3%2B.jpg")
			), new IdolSkin(
				2, "Lazy Fairy", "だらだら妖精",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/c/cc/Anzu1.jpg", "https://www.project-imas.com/w/images/thumb/c/cc/Anzu1.jpg/192px-Anzu1.jpg"),
				new Card("https://www.project-imas.com/w/images/e/e2/Tumblr_lyomgs6cSY1rng6j0o1_500.jpg", "https://www.project-imas.com/w/images/thumb/e/e2/Tumblr_lyomgs6cSY1rng6j0o1_500.jpg/192px-Tumblr_lyomgs6cSY1rng6j0o1_500.jpg")
			), new IdolSkin(
				3, "Wonderful Magic", "ﾜﾝﾀﾞﾌﾙﾏｼﾞｯｸ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/2/2b/AnzuWonderful.jpg", "https://www.project-imas.com/w/images/thumb/2/2b/AnzuWonderful.jpg/192px-AnzuWonderful.jpg"),
				new Card("https://www.project-imas.com/w/images/6/68/AnzuWonderful1.jpg", "https://www.project-imas.com/w/images/thumb/6/68/AnzuWonderful1.jpg/192px-AnzuWonderful1.jpg")
			), new IdolSkin(
				4, "Anzu Summer Vacation", "杏の夏休み",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/f5/Anzu_Summer_Vacation.jpg", "https://www.project-imas.com/w/images/thumb/f/f5/Anzu_Summer_Vacation.jpg/192px-Anzu_Summer_Vacation.jpg"),
				new Card("https://www.project-imas.com/w/images/a/a8/Anzu_Summer_Vacation%2B.jpg", "https://www.project-imas.com/w/images/thumb/a/a8/Anzu_Summer_Vacation%2B.jpg/192px-Anzu_Summer_Vacation%2B.jpg")
			), new IdolSkin(
				5, "Selfish Santa", "わがままｻﾝﾀ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/5/58/Anzu_SR11.jpg", "https://www.project-imas.com/w/images/thumb/5/58/Anzu_SR11.jpg/192px-Anzu_SR11.jpg"),
				new Card("https://www.project-imas.com/w/images/9/9e/Anzu_SR11%2B.jpg", "https://www.project-imas.com/w/images/thumb/9/9e/Anzu_SR11%2B.jpg/192px-Anzu_SR11%2B.jpg")
			), new IdolSkin(
				6, "Beyond good things", "よきにはからえ",
				Rarity.S_RARE,
				new Stats(0, 0),
				new Card("https://www.project-imas.com/w/images/f/fb/Anzu_SR14.jpg", "https://www.project-imas.com/w/images/thumb/f/fb/Anzu_SR14.jpg/192px-Anzu_SR14.jpg"),
				new Card("https://www.project-imas.com/w/images/1/1a/Anzu_SR14%2B.jpg", "https://www.project-imas.com/w/images/thumb/1/1a/Anzu_SR14%2B.jpg/192px-Anzu_SR14%2B.jpg")
			)
		));
	}

}
