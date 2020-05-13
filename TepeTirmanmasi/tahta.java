package TepeTirmanmasi;
import java.util.Random;

public class tahta {
    private int[][] board;
    private int[] dizi;
    private int h = 0;

    public tahta() {
        board = new int[8][8];
        dizi = new int[8];
        h = 0;
    }

    public void vezir_yerlestir(int gelen_dizi[]) { 
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = 0;

        for (int a = 0; a < 8; a++)
            board[gelen_dizi[a]][a] = 1;

    }

    public void dizi_yerlestir(int dizi2[]) { 
        for (int a = 0; a < 8; a++)
            dizi[a] = dizi2[a];
    }

    public void yazdirBoard() { 

        System.out.println("----------TAHTA------------");
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }


            System.out.println("--------------------------");

        }

    }

    public void setVezir(int satir, int sutun) {

        board[satir][sutun] = 1;
    }

    public void kaldirVezir(int satir, int sutun) {

        board[satir][sutun] = 0;
    }

    public void setDizi(int satir, int sutun) {

        dizi[sutun] = satir;
    }

    public void yazdirDizi() {

        System.out.println("-------------DIZI DEGERLERI------------------");
        System.out.print("| ");

        for (int i = 0; i < 8; i++) {

            System.out.print(dizi[i] + " | ");
        }
    }

    public int bir_hucre(int i, int j) {
        return board[i][j];
    }

    public void hesapla_heuristic() { 

        for (int i = 0; i < 8; i++)
            for (int j = i + 1; j < 8; j++) {
                if (dizi[i] == dizi[j]) { /
                    h++;
                }

                if (Math.abs(dizi[i] - dizi[j]) == Math.abs(i - j)) 
                    h++;
            }
    }

    public void vezirAtamasi() { 

        Random rnd = new Random();
        int sayi;
        for (int i = 0; i < 8; i++) {
            sayi = rnd.nextInt(8);
            setVezir(sayi, i);
            setDizi(sayi, i);
        }

        sifirlaHeuristic();
        hesapla_heuristic();
    }

    public int getHeuristic() {

        return h;
    }

    public void sifirlaHeuristic() {

        h = 0;
    }

    public int[] getDizi() {

        return dizi;
    }

}
