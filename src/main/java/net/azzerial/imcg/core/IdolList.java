package net.azzerial.imcg.core;

import net.azzerial.imcg.entities.Idol;

import java.util.ArrayList;
import java.util.List;

public class IdolList {

	private static IdolList instance;
	private static List<Idol> idols;

	public IdolList() {
		List<Idol> idols = new ArrayList<Idol>();

		// Abe Nana
		idols.add(new Idol(0,"Abe Nana", "安部菜々", Idol.IdolType.CUTE,
			17, new Idol.Birthday(15, 5),
			146, 40, new Idol.Measurement(84, 57, 84),
			Idol.BloodType.O, Idol.Handedness.RIGHT,
			"https://www.project-imas.com/w/images/8/83/Nana_SS.png")
		);
		this.idols = idols;
	}

	public static IdolList loadIdols() {
		if (instance == null) {
			instance = new IdolList();
			System.out.println("[IMCG/IdolList]: Loaded Idols.");
		}
		return (instance);
	}

	public static Idol getIdol(int id) {
		if (id < 0 || id > idols.size() - 1) {
			return (null);
		}
		return (idols.get(id));
	}

	public static Idol getIdol(String name) {
		for (int i = 0; i < idols.size(); i += 1) {
			if (idols.get(i).getLatinName().equalsIgnoreCase(name)) {
				return (idols.get(i));
			}
		}
		return (null);
	}
}
