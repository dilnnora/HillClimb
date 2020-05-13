package TepeTirmanmasi;
import java.util.ArrayList;
import java.util.Random;

public interface islemler {

    public static tahta en_optimalSec(ArrayList < tahta > tahtalar) { 

        int en_heuristic;
        en_heuristic = tahtalar.get(0).getHeuristic(); 
        int i, tut = 0;
        for (i = 1; i < tahtalar.size(); i++) {
            if (en_heuristic > tahtalar.get(i).getHeuristic()) { 
                en_heuristic = tahtalar.get(i).getHeuristic();
                tut = i;
            }
        }
        return tahtalar.get(tut);
    }

    public static ArrayList sutun_icindeYerDegistir(tahta bir_tahta, int sutun) {
        ArrayList < tahta > tahtalar = new ArrayList < tahta > ();
        tahta bir_tahta1;
        int[] dizi;
        dizi = bir_tahta.getDizi();

        for (int satir = 0; satir < 8; satir++) { 

            if (dizi[sutun] != satir) {
                bir_tahta1 = new tahta();
                bir_tahta1.vezir_yerlestir(bir_tahta.getDizi()); 
                bir_tahta1.dizi_yerlestir(bir_tahta.getDizi());
                bir_tahta1.setVezir(satir, sutun);
                bir_tahta1.setDizi(satir, sutun); 
                bir_tahta1.kaldirVezir(dizi[sutun], sutun); 
                bir_tahta1.sifirlaHeuristic();
                bir_tahta1.hesapla_heuristic();
                tahtalar.add(bir_tahta1); 

            }

        }
        return tahtalar;

    }
}
