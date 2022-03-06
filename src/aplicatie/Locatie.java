package aplicatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Locatie {

    public String nume, adresa;
    public int numarSectoare;
    public int capacitate = 0;
    static Scanner read = new Scanner(System.in);
    Sector[] S;
    int[] N;

    // CONSTRUCTOR
    public Locatie() {
        this.nume = "";
        this.adresa = "";
        this.numarSectoare = 0;

    } // CONSTRUCTOR IMPLICIT

    public Locatie(String N, String A, int NR) {
        this.nume = N;
        this.adresa = A;
        this.numarSectoare = NR;
        S = new Sector[this.numarSectoare + 1];

        for (int i = 1; i <= numarSectoare; i++) {

            System.out.printf("\nIntrodu numele sectorului: ");
            String numeSector = read.next();
            System.out.printf("\nIntrodu categoria sectorului: ");
            String categorieSector = read.next();
            System.out.printf("Introdu numarul de randruri: ");
            int numarRanduri = read.nextInt();
            System.out.printf("Introdu numarul de coloane: ");
            int numarColoane = read.nextInt();

            S[i] = new Sector(numeSector, categorieSector, numarRanduri, numarColoane);
            S[i].afisareSector();
            for (int r = 1; r <= numarRanduri; r++) {
                for (int c = 1; c <= numarColoane; c++) {
                    String s = S[i].getLoc(r, c);
                    if (s.equals("O"));
                    this.capacitate++;
                }
            }
            //numarRanduri++;numarColoane--;
        }

    } // CONSTRUCTOR ADMIN // INTRODUCERE MANUALA

    public Locatie(int x) throws FileNotFoundException {
        File file = new File("locatii/Locatie_" + x + "/Locatie_" + x + ".txt");
        Scanner readFile = new Scanner(file);
        this.nume = readFile.nextLine();
        this.adresa = readFile.nextLine();
        this.numarSectoare = readFile.nextInt();
        readFile.nextLine();
        S = new Sector[this.numarSectoare + 1];

        for (int i = 1; i <= numarSectoare; i++) {
            String numeSector = readFile.next();
            String categorieSector = readFile.next();
            int numarRanduri = readFile.nextInt();//readFile.nextLine();
            int numarColoane = readFile.nextInt();//readFile.nextLine();
            S[i] = new Sector(x, i, numeSector, categorieSector, numarRanduri, numarColoane);

            //S[i].afisareSector();
            for (int r = 1; r <= numarRanduri; r++) // Capacitatea intregii sali
            {
                for (int c = 1; c <= numarColoane; c++) {
                    String s = S[i].getLoc(r, c);
                    if (s.equals("O"));
                    this.capacitate++;
                }
            }
            //numarRanduri++;numarColoane--;
        }

    } // FOLOSESTE DUPA CE AI CREAT LOCATIILE CU SECTOARE!!!

    // METODE
    public void creareLocatieFisier(int x) throws FileNotFoundException, IOException {
        File file = new File("locatii/Locatie_" + x + "/Locatie_" + x + ".txt");
        Scanner readFile = new Scanner(file);

        this.nume = readFile.nextLine();
        this.adresa = readFile.nextLine();
        this.numarSectoare = readFile.nextInt();
        readFile.nextLine();
        S = new Sector[this.numarSectoare + 1];

        for (int i = 1; i <= numarSectoare; i++) {
            String numeSector = readFile.next();
            String categorieSector = readFile.next();
            int numarRanduri = readFile.nextInt();//readFile.nextLine();
            int numarColoane = readFile.nextInt();//readFile.nextLine();

            S[i] = new Sector(numeSector, categorieSector, numarRanduri, numarColoane);
            FileWriter writer = new FileWriter("locatii/Locatie_" + x + "/Sector_" + i + ".txt", true);
            PrintWriter print = new PrintWriter(writer);
            print.println(numeSector + " ");
            print.println(categorieSector + " ");
            for (int r = 1; r <= numarRanduri; r++) {
                for (int c = 1; c <= numarColoane; c++) {
                    print.print("O ");
                    this.capacitate++;
                }
                print.println("");
            }
            print.close();
            writer.close();
            //numarRanduri++;numarColoane--;
        }
    } // FOLOSESTE DOAR LA INCEPUT PENTRU A CREA SECTOARE PLINE DE "O"!!!S

    public void rescrieSector(int x, int y) throws IOException {
        FileWriter myWriter = new FileWriter("locatii/Locatie_" + x + "/Sector_" + y + ".txt");
        myWriter.write(S[y].sector + "\n" + S[y].categorieSector + "\n");
        myWriter.close();
        FileWriter writer = new FileWriter("locatii/Locatie_" + x + "/Sector_" + y + ".txt", true);
        PrintWriter print = new PrintWriter(writer);
        for (int i = 1; i <= S[y].randuri; i++) {
            for (int j = 1; j <= S[y].coloane; j++) {
                print.print(S[y].getLoc(i, j) + " ");
            }
            print.println("");
        }
        print.close();
        writer.close();
    }

    public void afisareSector(int x) {        // x este sectorul
        System.out.println("\nSector " + S[x].sector + ":");
        for (int i = 1; i <= S[x].randuri; i++) {
            for (int j = 1; j <= S[x].coloane; j++) {
                System.out.printf(S[x].getLoc(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void afisareLocatie() {
        System.out.println(this.nume);
        System.out.println(this.adresa);
        System.out.println("Capacitate: " + this.capacitate + " locuri");
    }
}
