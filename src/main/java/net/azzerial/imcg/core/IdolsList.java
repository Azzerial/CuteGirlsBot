package net.azzerial.imcg.core;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.idols.AbeNana;

import java.util.ArrayList;
import java.util.List;

public class IdolsList {

	private static IdolsList instance;
	private static List<Idol> idols;

	public IdolsList() {
		List<Idol> idols = new ArrayList<Idol>();

		// Abe Nana
		idols.add(new AbeNana().getIdol());

		this.idols = idols;
	}

	public static IdolsList loadIdols() {
		if (instance == null) {
			instance = new IdolsList();
			System.out.println("[IMCG/IdolList]: Loaded Idols.");
		}
		return (instance);
	}

	public static Idol getIdol(int id) {
		if (id < 0 || id >= idols.size() || idols.isEmpty()) {
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

	public static List<Idol> getIdols() {
		return (idols);
	}

}
