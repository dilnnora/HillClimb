package TepeTirmanmasi;

import java.util.ArrayList;
import java.util.Random;

public interface islemler {

	public static tahta en_optimalSec(ArrayList<tahta> tahtalar) { // komsular
																	// arasinda
																	// en uygun
																	// olani
																	// secilir

		int en_heuristic;
		en_heuristic = tahtalar.get(0).getHeuristic(); // bastaki en optimal
														// olarak kabul edilir

		int i, tut = 0;
		for (i = 1; i < tahtalar.size(); i++) {

			if (en_heuristic > tahtalar.get(i).getHeuristic()) { // karsilastirma,
																	// daha
																	// optimal
																	// olan
																	// aranir

				en_heuristic = tahtalar.get(i).getHeuristic();
				tut = i;
			}
		}

		return tahtalar.get(tut);

	}

	public static ArrayList sutun_icindeYerDegistir(tahta bir_tahta, int sutun) { // bir
																					// sutun
																					// icinde
																					// tum
																					// ihtimaller
																					// olusturulur

		ArrayList<tahta> tahtalar = new ArrayList<tahta>();

		tahta bir_tahta1;

		int[] dizi;
		dizi = bir_tahta.getDizi();

		for (int satir = 0; satir < 8; satir++) { // her satir icin

			if (dizi[sutun] != satir) { // eger bu hucrede vezir yoksa oraya
										// yerlesirilebilir
				bir_tahta1 = new tahta();
				bir_tahta1.vezir_yerlestir(bir_tahta.getDizi()); // tum degerler
																	// onceki
																	// tahta ile
																	// ayni
																	// olacak
																	// sekilde
																	// atanir

				bir_tahta1.dizi_yerlestir(bir_tahta.getDizi()); // dizi atanir

				bir_tahta1.setVezir(satir, sutun); // bu tahtaya yeni vezir
													// yerlestirilir
				bir_tahta1.setDizi(satir, sutun); // dizi guncellenir

				bir_tahta1.kaldirVezir(dizi[sutun], sutun); // eski olan vezir
															// kaldiririlir

				bir_tahta1.sifirlaHeuristic();
				bir_tahta1.hesapla_heuristic();

				tahtalar.add(bir_tahta1); // bu tahta arrayliste eklenir

			}

		}
		return tahtalar;

	}
}
