package net.azzerial.imcg.core;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.idols.*;
import net.azzerial.imcg.idols.core.CuteGirl;

import java.util.ArrayList;
import java.util.List;

public class IdolsList {

	private static IdolsList instance;
	private static List<Idol> idols;
	private static List<CuteGirl> cuteGirls;

	public IdolsList() {
		this.cuteGirls = new ArrayList<CuteGirl>();
		this.idols = new ArrayList<Idol>();

		// Shiki Ichinose
		idols.add(addToCGList(new ShikiIchinose()).getIdol());
	}

	public static IdolsList loadIdols() {
		if (instance == null) {
			instance = new IdolsList();
			System.out.println("[IMCG/IdolList]: Loaded Idols.");
		}
		return (instance);
	}

	public CuteGirl addToCGList(CuteGirl cuteGirl) {
		cuteGirls.add(cuteGirl);
		return (cuteGirl);
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

	public static CuteGirl getCuteGirl(int id) {
		for (int i = 0; i < cuteGirls.size(); i++) {
			if (cuteGirls.get(i).getIdol().getId() == id) {
				return (cuteGirls.get(i));
			}
		}
		return (null);
	}

	public static CuteGirl getCuteGirl(Idol idol) {
		return (getCuteGirl(idol.getId()));
	}

	public static List<CuteGirl> getCuteGirls() {
		return (cuteGirls);
	}
}
