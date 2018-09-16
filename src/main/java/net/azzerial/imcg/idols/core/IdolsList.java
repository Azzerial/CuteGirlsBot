package net.azzerial.imcg.idols.core;

import net.azzerial.imcg.entities.Idol;
import net.azzerial.imcg.entities.utils.IdolTier;
import net.azzerial.imcg.entities.utils.IdolType;
import net.azzerial.imcg.idols.cool.*;
import net.azzerial.imcg.idols.cute.*;
import net.azzerial.imcg.idols.passion.*;

import java.util.ArrayList;
import java.util.List;

public class IdolsList {

	private static IdolsList instance;
	private static List<Idol> idols;
	private static List<CuteGirl> cuteGirls;

	public IdolsList() {
		this.cuteGirls = new ArrayList<CuteGirl>();
		this.idols = new ArrayList<Idol>();

		// Cute idols
		idols.add(addToCGList(new ShikiIchinose()));
		idols.add(addToCGList(new KozueYusa()));
		idols.add(addToCGList(new WakabaKusakabe()));
		idols.add(addToCGList(new AnzuFutaba()));
		idols.add(addToCGList(new KotokaSaionji()));

		// Cool idols
		idols.add(addToCGList(new NaoKamiya()));
		idols.add(addToCGList(new HinaAraki()));
		idols.add(addToCGList(new ShinoHiiragi()));
		idols.add(addToCGList(new NoaTakamine()));
		idols.add(addToCGList(new KaedeTakagaki()));

		// Passion idols
		idols.add(addToCGList(new TokikoZaizen()));
		idols.add(addToCGList(new NahoEbihara()));
		idols.add(addToCGList(new AyunaHamakawa()));
		idols.add(addToCGList(new RikaJougasaki()));
		idols.add(addToCGList(new MikaJougasaki()));
	}

	public static IdolsList loadIdols() {
		if (instance == null) {
			instance = new IdolsList();
			System.out.println("[IMCG/IdolList]: Loaded Idols.");
		}
		return (instance);
	}

	private Idol addToCGList(CuteGirl cuteGirl) {
		cuteGirls.add(cuteGirl);
		return (cuteGirl.getIdol());
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

	public static List<Idol> getIdolsByTier(IdolTier idolTier) {
		List<Idol> idolsByTier = new ArrayList<Idol>();

		idols.forEach(idol -> {
			if (idol.getIdolTier().equals(idolTier)) {
				idolsByTier.add(idol);
			}
		});
		return (idolsByTier);
	}

	public static List<Idol> getIdolsByType(IdolType idolType) {
		List<Idol> idolsByType = new ArrayList<Idol>();

		idols.forEach(idol -> {
			if (idol.getIdolType().equals(idolType)) {
				idolsByType.add(idol);
			}
		});
		return (idolsByType);
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
