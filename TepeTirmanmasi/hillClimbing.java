package TepeTirmanmasi;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hillClimbing {

	static JFrame frame;
	static JPanel squares[][] = new JPanel[8][8]; // panelde 8x8 tahtayi temsil

	// etmek icin

	public hillClimbing() {

		frame = new JFrame("Satranç");
		frame.setSize(900, 700);
		frame.setLayout(new GridLayout(9, 9));

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j] = new JPanel();

				if ((i + j) % 2 == 0) {
					squares[i][j].setBackground(Color.gray);
				} else {
					squares[i][j].setBackground(Color.white);
				}
				frame.add(squares[i][j]);
			}

		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
	}

	public static void vezir_yerlestir(int satir, int sutun) { // veziri uygun
																// hucreye
																// yerlestirir

		JLabel label = new JLabel("VEZIR");

		squares[satir][sutun].add(label);

	}

	public static void vezirler_sil() { // panelden önceki döngüye ait vezirleri
										// siler

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				squares[i][j].removeAll();
	}

	public static void Labele_string_yaz(String mesaj, int h) { // heuristic
																// degeri panele
																// yazdýran
																// metot

		JLabel label = new JLabel(mesaj + String.valueOf(h) + " ");

		frame.add(label);

	}

	public static void main(String[] args) {

		new hillClimbing(); // panel yazdirma metotlar bu classta bulunduklari
		// icin bu
		// sinifi yaratmak zorundayiz

		ArrayList<tahta> tahtalarListesi = new ArrayList<tahta>(); // toplam 7
																	// farklisn
		
																	// tahta
		tahta en_optimal = new tahta();
		int bulundu = 0; // heuristic 0 oldugunda bulundu 1 olacak
		int sutun;
		int yerDegistrmeSayisi = 0;
		int restartSaydir = 0; // kac kez while dongusu calisacagini sayar
		int j;

		int toplamSure = 0;
		int toplamYerDegistrme = 0;
		int toplamRestart = 0;
		int[] array = null;

		int heuristic = 0;

		Scanner input = new Scanner(System.in);
		int calisacakSayisi;

		System.out
				.print("Toplam kac kez calistirmak istersiniz?(istenen sayi 25)");
		calisacakSayisi = input.nextInt();

		
		// System.out.println("       ÝÞLEM SÜRESÝ:  YER DEÐÝÞTÝRME SAYISI:  RESTART SAYISI: ");
		for (j = 1; j <= calisacakSayisi; j++) { // 25 kere

			hillClimbing.vezirler_sil(); // her yeni dongu icin eski konumlar
											// silinir
			bulundu = 0;
			restartSaydir = 0;
			yerDegistrmeSayisi = 0;
			long baslangic = System.currentTimeMillis();

			while (bulundu == 0) { // heuristic 0 olana kadar bu dongu
									// calisacaktir

				tahta bir_tahta = new tahta();
				bir_tahta.vezirAtamasi(); // tahtaya 8 vezirin rasgele atanir
				// hillClimbing.Labele_string_yaz("Baþtaki heuristic: ",bir_tahta.getHeuristic());
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ASIL TAHTA: ");
				bir_tahta.yazdirBoard(); // tahtayi 0 ve 1 seklinde yazdirir
				// 1
				// olan
				// hucrelerde vezir bulunur
				bir_tahta.yazdirDizi(); // vezirlerin konumlari tutan dizi
				System.out.println(" ");
				System.out.println(" Heuristic: " + bir_tahta.getHeuristic()); // heuristic
				// degeri
				// yazdirilir

				for (sutun = 0; sutun < 8; sutun++) { // her sutun icin

					System.out.println(" ");
					System.out.println(" ");
					System.out.println(sutun + ". SUTUN icin yer degisimler:");

					tahtalarListesi = islemler.sutun_icindeYerDegistir(
							bir_tahta, sutun);
					yerDegistrmeSayisi = yerDegistrmeSayisi + 7;

					for (int i = 0; i < tahtalarListesi.size(); i++) { // olusan
																		// yeni
																		// 7
																		// tane
																		// tahtayi
																		// yazdir

						// tahtalarListesi.get(i).yazdirBoard();
						tahtalarListesi.get(i).yazdirDizi();
						System.out.println("Heuristic: "
								+ tahtalarListesi.get(i).getHeuristic());
					}

					en_optimal = islemler.en_optimalSec(tahtalarListesi); // bu
																			// 7
																			// tane
																			// tahtalrdan
																			// heuristic
																			// degeri
																			// en
																			// az
																			// olani
																			// bulunur
					System.out.println(" ");
					System.out.println(" Aralarinda en optimal:");
					en_optimal.yazdirBoard();
					en_optimal.yazdirDizi();
					System.out
							.print("Heurisitic: " + en_optimal.getHeuristic());

					if (en_optimal.getHeuristic() < bir_tahta.getHeuristic()) {
						// burda Hill climbing mantigi ile calisan kisimdir,
						// curent
						// ile en_optimal heuristicleri karsilastirilir

						bir_tahta = en_optimal;

						bir_tahta.sifirlaHeuristic();
						bir_tahta.hesapla_heuristic();
					}

				}

			/*	System.out.println(" SON DURUM: ");
				bir_tahta.yazdirBoard();
				bir_tahta.yazdirDizi();
				System.out.println(" ");
				System.out.println("Heuristic : " + bir_tahta.getHeuristic());
				System.out.println("Restart sayisi : " + restartSaydir);

		*/		if (bir_tahta.getHeuristic() == 0) { // eger heuristic degeri 0
														// ise
														// amacina ulastýk while
														// dongusu bir daha
														// calismaz
					bulundu = 1;

					array = bir_tahta.getDizi();
					heuristic = bir_tahta.getHeuristic();

				}

				else
					restartSaydir++; // eger heuristic degeri 0 deðilse yeniden
										// baslanir
			}

			long bitis = System.currentTimeMillis();
			long gecenSure = bitis - baslangic;

			System.out
					.println("       ÝÞLEM SÜRESÝ:  YER DEÐÝÞTÝRME SAYISI:  RESTART SAYISI: ");
			System.out.println(j + ".döngü: " + gecenSure + "                 "
					+ yerDegistrmeSayisi + "                    "
					+ restartSaydir);
			toplamSure = (int) (toplamSure + gecenSure);
			toplamYerDegistrme = toplamYerDegistrme + yerDegistrmeSayisi;
			toplamRestart = toplamRestart + restartSaydir;

		}

		if (calisacakSayisi > 0) {
			hillClimbing.frame.setVisible(true);

			for (int i = 0; i < 8; i++) {

				// panele son durum yazdirilir
				hillClimbing.vezir_yerlestir(array[i], i);
			}

			hillClimbing.Labele_string_yaz("Heuristic: ", heuristic);
			hillClimbing.Labele_string_yaz("Restart sayisi : ", restartSaydir);
		}
		j--;
		if (j != 0) { // toplam 25 dongu ama eðer elle degistirilrse her
						// ihtimale karsi kontrol edilir
			System.out.println(" ");
			System.out.println("Ýþleme süre ortalamasý: " + toplamSure / j);
			System.out.println("Yer degistirme ortalamasý: "
					+ toplamYerDegistrme / j);
			System.out.println("Restart ortalamasý: " + toplamRestart / j);
		}
	}
}
