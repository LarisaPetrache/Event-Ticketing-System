package aplicatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Eveniment {

    public int id, nrCategBilet;
    public String categorie, nume, descriere, data, oraInceput, durata, artist;
    boolean stare;
    int locatie;
    //Locatie L[];
    int[] categorieBilet;

    public Eveniment() {
        this.id = 0;
        this.durata = "";
        this.nrCategBilet = 0;
        this.stare = true;
        this.categorie = "";
        this.nume = "";
        this.descriere = "";
        this.data = "";
        this.oraInceput = "";
        this.artist = "";
    }

    public void citire(String x) throws FileNotFoundException, ParseException {
        int i;
        File file = new File(x);
        Scanner read = new Scanner(file);
        this.id = read.nextInt();
        read.nextLine();
        this.nume = read.nextLine();
        this.categorie = read.nextLine();
        this.data = read.nextLine();
        this.durata = read.nextLine();
        this.oraInceput = read.nextLine();
        this.locatie = read.nextInt();
        read.nextLine();
        this.descriere = read.nextLine();
        this.nrCategBilet = read.nextInt();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEveniment = df.parse(this.data);
        Date dataAstazi = df.parse(sdf.format(cal.getTime()));
        if (dataAstazi.compareTo(dataEveniment) <= 0) {
            this.stare = true;
        } else {
            this.stare = false;
        }

        categorieBilet = new int[nrCategBilet + 1];
        for (i = 1; i <= nrCategBilet; i++) {
            categorieBilet[i] = 0;
        }

        for (i = 1; i <= nrCategBilet; i++) {
            categorieBilet[i] = read.nextInt();
        }

        read.nextLine();
        this.artist = read.nextLine();
    }

    public void afisare(Locatie L[]) {
        System.out.println("Nume: " + this.nume + "\nCategorie: " + this.categorie + "\nData: " + this.data + "\nDurata: " + this.durata + "\nOra Inceput: " + this.oraInceput
                + "\nLocatie: " + L[locatie].nume + "\nDescriere eveniment:\n" + this.descriere + "\nArtist: " + this.artist);
        System.out.println("----------------------\nPret pe categorii:");

        for (int i = 1; i <= nrCategBilet; i++) {
            System.out.println("Categoria " + i + ": " + categorieBilet[i] + " RON");
        }
    }

    public void copiereSectoare(Locatie L[]) throws IOException {
        System.out.println(L[locatie].numarSectoare);
        for (int i = 1; i <= L[locatie].numarSectoare; i++) {
            FileWriter writer = new FileWriter("evenimente/Eveniment_" + this.id + "/Sector_" + i + ".txt", true);
            PrintWriter print = new PrintWriter(writer);
            writer.write(" ");
            print.println(L[locatie].S[i].sector);
            System.out.println(L[locatie].S[i].sector);
            print.println(L[locatie].S[i].categorieSector);
            System.out.println(L[locatie].S[i].categorieSector);
            for (int r = 1; r <= L[locatie].S[i].randuri; r++) {
                for (int c = 1; c <= L[locatie].S[i].coloane; c++) {
                    print.print(L[locatie].S[i].getLoc(r, c) + " ");
                }
                print.println("");
            }
            print.close();
            writer.close();
        }
    }

    public void rescrieSector(Locatie L[]) throws IOException {
        for (int Sec = 1; Sec <= L[locatie].numarSectoare; Sec++) {
            FileWriter Writer = new FileWriter("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt");
            Writer.write(L[locatie].S[Sec].sector + "\n" + L[locatie].S[Sec].categorieSector + "\n");
            Writer.close();
            FileWriter writer = new FileWriter("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt", true);
            PrintWriter print = new PrintWriter(writer);
            for (int i = 1; i <= L[locatie].S[Sec].randuri; i++) {
                for (int j = 1; j <= L[locatie].S[Sec].coloane; j++) {
                    print.print(L[locatie].S[Sec].getLoc(i, j) + " ");
                }
                print.println("");
            }
            print.close();
            writer.close();
        }
    }

    public void modificareSector(Locatie L[], int Sec, int x, int y) throws IOException {
        // iau datele matricei
        int n = L[locatie].S[Sec].randuri;
        int m = L[locatie].S[Sec].coloane;
        File file = new File("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt");
        Scanner read = new Scanner(file);
        String numeSec = read.nextLine();
        String cateSec = read.nextLine();
        String[][] A = new String[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                A[i][j] = read.next();
            }
        }
        A[x][y] = "X";
        // modific matricia
        FileWriter Writer = new FileWriter("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt");
        Writer.write(numeSec + "\n" + cateSec + "\n");
        Writer.close();
        FileWriter writer = new FileWriter("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt", true);
        PrintWriter print = new PrintWriter(writer);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                print.print(A[i][j] + " ");
            }
            print.println("");
        }
        print.close();
        writer.close();
    }

    public void afisareSector(Locatie L[], int Sec) throws FileNotFoundException {
        int n = L[locatie].S[Sec].randuri;
        int m = L[locatie].S[Sec].coloane;
        File file = new File("evenimente/Eveniment_" + this.id + "/Sector_" + Sec + ".txt");
        Scanner read = new Scanner(file);
        String numeSec = read.nextLine();
        System.out.println("Sector: " + numeSec);
        read.nextLine();
        String[][] A = new String[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.print("R" + i + "  ");
            for (int j = 1; j <= m; j++) {
                A[i][j] = read.next();
                System.out.print(A[i][j] + " ");
            }
        }
        System.out.println();
        System.out.print("Loc ");
        for (int j = 1; j <= m; j++) {
            System.out.print(j + " ");
        }
    }
}
