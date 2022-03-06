package aplicatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sector {

    public String categorieSector, sector;
    public int randuri;
    public int coloane;
    public String[][] loc = new String[randuri + 1][coloane + 1];

    // CONSTRUCTORI
    public Sector(String S, String CS, int R, int C) {
        this.sector = S;
        this.categorieSector = CS;
        this.randuri = R;
        this.coloane = C;
        loc = new String[this.randuri + 1][this.coloane + 1];
        for (int i = 1; i <= this.randuri; i++) {
            for (int j = 1; j <= this.coloane; j++) {
                this.loc[i][j] = "O";
            }
        }
    }

    public Sector(int x, int y, String S, String CS, int R, int C) throws FileNotFoundException {
        String SS = "locatii/Locatie_";
        SS = SS + x + "/Sector_" + y + ".txt";
        File file = new File(SS);
        Scanner readFile = new Scanner(file);
        this.sector = S;
        this.categorieSector = CS;
        this.randuri = R;
        this.coloane = C;
        this.categorieSector = readFile.nextLine();
        this.sector = readFile.nextLine();
        loc = new String[this.randuri + 1][this.coloane + 1];
        for (int i = 1; i <= this.randuri; i++) {
            for (int j = 1; j <= this.coloane; j++) {
                this.loc[i][j] = readFile.next();
            }
        }
    } // FOLOSESTE DUPA CE AI CREAT LOCATIILE CU SECTOARE!!!

    // METODE
    public String getLoc(int i, int j) {
        return this.loc[i][j];
    }

    public void adaugaLoc(int i, int j) {
        this.loc[i][j] = "O";
    }

    public void stergeLoc(int i, int j) {
        this.loc[i][j] = "_";
    }

    public void ocupaLoc(int i, int j) {
        this.loc[i][j] = "X";
    }

    public void afisareSector() {
        System.out.println("Sector " + this.sector + ":");
        for (int i = 1; i <= this.randuri; i++) {
            for (int j = 1; j <= this.coloane; j++) {
                System.out.printf(getLoc(i, j) + " ");
            }
            System.out.println();
        }
    }
}
