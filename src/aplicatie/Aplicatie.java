package aplicatie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;

public class Aplicatie {

    //#########################FUNCTII INTERFATA###############################//
    public static int verificare(int x, int a, int b) {
        Scanner cin = new Scanner(System.in);
        while (x < a || x > b) {
            System.out.print("Raspunsul dat nu corespunde!\nRaspuns:");
            x = cin.nextInt();
        }
        return x;
    }

    public static String verifId(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 5) {
            System.out.println("Id-ul trebuie sa contina mai mult de 5 caractere");
            System.out.print("ID: ");
            x = cin.next();
        }
        return x;
    }

    public static String verifPass(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 6) {
            System.out.println("Parola trebuie sa contina mai mult de 6 caractere");
            System.out.print("Parola: ");
            x = cin.next();
        }
        return x;
    }

    public static String verifEmail(String x) {
        Scanner cin = new Scanner(System.in);
        int OK = 0;
        while (OK == 0) {
            if (x.endsWith("@yahoo.com") == true) {
                OK = 1;
                if (x.startsWith("@") == true) {
                    OK = 0;
                }
            }
            if (x.endsWith("@gmail.com") == true) {
                OK = 1;
                if (x.startsWith("@") == true) {
                    OK = 0;
                }
            }

            if (OK == 0) {
                System.out.println("Email introdus gresit! (Momentam acceptam adrese de email doar de la Yahoo si Gmail)");
                System.out.print("Email: ");
                x = cin.next();
            }
        }
        return x;
    }

    public static int verifMeniuPrincipal(int x) {
        Scanner cin = new Scanner(System.in);
        int OK = 0;
        while (OK == 0) {
            switch (x) {
                case 1:
                    OK = 1;
                    break;
                case 2:
                    OK = 1;
                    break;
                case 3:
                    OK = 1;
                    break;
                case 4:
                    OK = 1;
                    break;
                case 0:
                    OK = 1;
                    break;
                case 5:
                    OK = 1;
                    break;
                default: {
                    System.out.println("Ati introdus gresit. Reincercati.");
                    System.out.print("Raspuns: ");
                    x = cin.nextInt();
                }
            }
        }
        return x;
    }

    public static void cumpara(int x, Eveniment event[], Locatie L[], String s[]) throws FileNotFoundException, IOException {
        Scanner cin = new Scanner(System.in);
        int r, i, j, a, b, OK, nr = 0, y = 0, OK1, pret = 0, Total = 0, i3;
        String c1, c2, c3;
        String[][] SECTOR = null;
        int raspuns[] = new int[20];
        Bilet B[] = new Bilet[10];
        raspuns[1] = 0;

        raspuns[2] = 0;
        while (raspuns[2] == 0) {
            event[x].afisare(L);
            System.out.println("\n--- Meniu ---");
            System.out.println("1. Cumpara bilet");
            System.out.println("0. Inapoi la meniul prinicipal");
            System.out.print("\nRaspuns: ");
            r = cin.nextInt();
            r = verificare(r, 0, 1);

            if (r == 1) {
                //partea de cumparare a biletelor
                raspuns[3] = 2;
                while (raspuns[3] == 2) {
                    for (i = 1; i <= event[x].nrCategBilet; i++) {
                        System.out.println("\nCategoria " + s[i]);
                        for (j = 1; j <= L[event[x].locatie].numarSectoare; j++) {
                            File read = new File("evenimente/Eveniment_" + x + "/Sector_" + j + ".txt");
                            Scanner readSector = new Scanner(read);
                            c1 = readSector.next();
                            c2 = readSector.next();
                            int total = 0;
                            int ocupate = 0;
                            File file2 = new File("evenimente/Eveniment_" + x + "/Sector_" + j + ".txt");
                            Scanner read1 = new Scanner(file2);
                            String randomString = read1.nextLine();
                            randomString = read1.nextLine();
                            SECTOR = new String[L[event[x].locatie].S[j].randuri + 1][L[event[x].locatie].S[j].coloane + 1];
                            for (int i2 = 1; i2 <= L[event[x].locatie].S[j].randuri; i2++) {
                                for (int j2 = 1; j2 <= L[event[x].locatie].S[j].coloane; j2++) {
                                    SECTOR[i2][j2] = read1.next();
                                    if (SECTOR[i2][j2].equals("X")) {
                                        ocupate++;
                                    }
                                    if (SECTOR[i2][j2].equals("O") || SECTOR[i2][j2].equals("X")) {
                                        total++;
                                    }
                                }
                            }
                            if (c2.equals(s[i])) {
                                System.out.println("Sector " + c1 + "  [" + ocupate + "/" + total + "]");
                            }
                        }
                    }
                    System.out.print("\nAlege sector:");
                    c2 = cin.next();

                    for (i = 1; i <= L[event[x].locatie].numarSectoare; i++) {
                        File read = new File("evenimente/Eveniment_" + x + "/Sector_" + i + ".txt");
                        Scanner readSector = new Scanner(read);
                        c1 = readSector.next();
                        if (c1.equals(c2)) {
                            event[x].afisareSector(L, i);
                            y = i;

                            File file2 = new File("evenimente/Eveniment_" + x + "/Sector_" + y + ".txt");
                            Scanner read2 = new Scanner(file2);
                            String randomString = read2.nextLine();
                            randomString = read2.nextLine();
                            for (i3 = 1; i3 <= event[x].nrCategBilet; i3++) {
                                if (randomString.equals(s[i3])) {
                                    pret = event[x].categorieBilet[i3];
                                    break;
                                }
                            }
                            SECTOR = new String[L[event[x].locatie].S[y].randuri + 1][L[event[x].locatie].S[y].coloane + 1];
                            for (int i2 = 1; i2 <= L[event[x].locatie].S[y].randuri; i2++) {
                                for (int j2 = 1; j2 <= L[event[x].locatie].S[y].coloane; j2++) {
                                    SECTOR[i2][j2] = read2.next();
                                }
                            }
                            break;
                        }
                    }

                    System.out.println("\n\nCate locuri vrei sa selectezi?");
                    System.out.print("Raspuns: ");
                    int raspunsRandom = cin.nextInt();
                    raspuns[1] = raspuns[1] + raspunsRandom;
                    Total = Total + pret * raspunsRandom;

                    for (i = 1; i <= raspuns[1]; i++) {
                        System.out.println("\nBilet " + i);
                        OK = 1;
                        while (OK == 1) {
                            do {
                                OK1 = 1;
                                System.out.print("Alege rand: ");
                                a = cin.nextInt();
                                System.out.print("Alege loc: ");
                                b = cin.nextInt();
                                if (a > L[event[x].locatie].S[y].randuri || a <= 0 || b > L[event[x].locatie].S[y].coloane || b <= 0) {
                                    System.out.println("Ai introdus datele gresit.\n");
                                    OK1 = 0;
                                }
                                if (!(SECTOR[a][b].equals("O"))) {
                                    System.out.println("Acest loc este deja ocupat.\n");
                                    OK1 = 0;
                                }

                            } while (OK1 == 0);

                            OK = 0;
                            for (i = 1; i <= nr; i++) {
                                if (B[i].rand == a && B[i].loc == b && B[i].sector.equals(c2)) {
                                    OK = 1;
                                    System.out.println("Locul a fost deja selectat.");
                                }

                            }
                            if (OK == 0) {
                                nr++;
                                B[nr] = new Bilet(a, b, c2, L, event, x);
                            }
                        }

                    }

                    System.out.println("\n1. Continua cu Finalizarea Comenzii ( " + Total + " LEI )");
                    System.out.println("2. Alege un alt Sector");
                    System.out.println("0. Inapoi");
                    System.out.print("\nRaspuns: ");
                    raspuns[3] = cin.nextInt();

                    if (raspuns[3] == 1) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        FinalizareComanda F = new FinalizareComanda();

                        System.out.println("\n-- FINALIZARE COMANDA --\n");
                        System.out.print("Nume: ");
                        F.nume = reader.readLine();
                        F.nume = F.verifNume(F.nume);
                        System.out.print("Prenume: ");
                        F.prenume = reader.readLine();
                        F.prenume = F.verifPrenume(F.prenume);
                        System.out.print("Adresa: ");
                        F.adresa = reader.readLine();
                        F.adresa = F.verifAdresa(F.adresa);
                        System.out.print("Cod postal: ");
                        F.codPostal = cin.nextInt();
                        F.codPostal = F.verifCodPostal(F.codPostal);
                        System.out.print("Numar telefon: ");
                        F.nrTelefon = reader.readLine();
                        F.nrTelefon = F.verifNrTelefon(F.nrTelefon);

                        System.out.println("\n-- Informatii card --\n");
                        System.out.print("Titular card: ");
                        F.titularCard = reader.readLine();
                        F.titularCard = F.verifTitularCard(F.titularCard);
                        System.out.print("Numar card: ");
                        F.numarCard = reader.readLine();
                        F.numarCard = F.verifNumarCard(F.numarCard);
                        System.out.print("MM: ");
                        F.MM = cin.nextInt();
                        F.MM = F.verifMM(F.MM);
                        System.out.print("YY: ");
                        F.YY = cin.nextInt();
                        F.YY = F.verifYY(F.YY);
                        System.out.print("CVC: ");
                        F.CVC = cin.nextInt();
                        F.CVC = F.verifCVC(F.CVC);

                        System.out.println("Felicitari! Comanda a fost plasata cu succes.\n");
                        for (i = 1; i <= nr; i++) {
                            event[x].modificareSector(L, B[i].id_sector, B[i].rand, B[i].loc);
                        }

                        raspuns[2] = -1;
                        System.out.println("Apasati enter pentru a va intoarce la meniul principal");
                        String z = reader.readLine();

                    } else if (raspuns[3] == 0) {
                        raspuns[3] = -1;
                    }

                }
            } else {
                raspuns[2] = -1;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        //declarare variabile
        Scanner cin = new Scanner(System.in);
        int i, j, y, n, x, u, OK, OKid, OKparola, r, nr; //n=nr de evenimente, u=nr de utlizatori
        String id, pass, email, verif;
        int raspuns[] = new int[20];

        //##############################LOAD##################################//
        // Load Users
        File user = new File("utilizatori/Nr utilizatori.txt");
        Scanner readUser = new Scanner(user);
        u = readUser.nextInt();
        Utilizator U[] = new Utilizator[u + 100];

        // Load Locations
        File file = new File("locatii/Nr locatii.txt");
        Scanner readFile = new Scanner(file);
        int nL;
        nL = readFile.nextInt();
        Locatie L[];
        L = new Locatie[nL + 100];
        /*for (i=1;i<=nL;i++) {
         L[i] = new Locatie();L[i].creareLocatieFisier(i);L[i] = new Locatie(i);
         for (int j=1;j<=L[i].numarSectoare;j++)
         L[i].rescrieSector(i, j);
         }*/

        for (i = 1; i <= nL; i++) {
            L[i] = new Locatie(i);
        }
        // Load Events
        File f = new File("evenimente/Nr evenimente.txt");
        Scanner readEv = new Scanner(f);
        n = readEv.nextInt();
        Eveniment event[] = new Eveniment[n + 100];
        for (i = 1; i <= n; i++) {
            event[i] = new Eveniment();
            event[i].citire("evenimente/Eveniment_" + i + "/Eveniment_" + i + ".txt");
        }

        int k = 0; //dimensiunea vectorului categorii[]
        String categorii[] = new String[n + 100];
        for (i = 1; i <= n; i++) {
            OK = 0;
            for (j = 1; j <= k; j++) {
                if (event[i].categorie.equals(categorii[j])) {
                    OK = 1;
                    break;
                }
            }
            if (OK == 0) {
                k++;
                categorii[k] = event[i].categorie;
            }
        }

        int a = 0; //dimensiunea vectorului artiti[]
        String artisti[] = new String[n + 100];
        for (i = 1; i <= n; i++) {
            OK = 0;
            for (j = 1; j <= a; j++) {
                if (event[i].artist.equals(artisti[j])) {
                    OK = 1;
                    break;
                }
            }
            if (OK == 0) {
                a++;
                artisti[a] = event[i].artist;
            }
        }

        //creare vector categorii pentru cumparare bilete
        int v[] = new int[n + 100];
        String s[] = new String[10];
        s[1] = "I";
        s[2] = "II";
        s[3] = "III";
        s[4] = "IV";
        s[5] = "V";
        s[6] = "VI";

        /*for(i=1; i<=n; i++) {
         event[i].copiereSectoare(L);
         event[i].rescrieSector(L);
         }*/
        //############################INTERFATA###############################//
        //parte de logare aici
        int varianta;
        System.out.println("Buna ziua!");
        System.out.println("\n--- Variante ---");
        System.out.println("1. Logare");
        System.out.println("2. Creare cont");
        System.out.print("0. Iesire\nRaspuns: ");
        varianta = cin.nextInt();
        varianta = verificare(varianta, 0, 2);

        int statusLogare = 0;
        while (statusLogare == 0) {
            if (varianta == 1) //partea de logare
            {
                OK = 0; //date incorecte
                while (OK == 0) {
                    OKid = OKparola = 0;
                    System.out.println("\n--- Introduceti datele contului ---");
                    System.out.print("ID: ");
                    id = cin.next();

                    if (OK == 0) //cand OK va fi 1, vom stii ca pe pozitia i se afla utilizatorul nostru, adica in fisierul Utilizator_i avem datele lui
                    {
                        i = 0;
                    }
                    while (OK == 0 && i != u) {
                        for (i = 0; i < u && OK != 1; i++) {
                            File verificare = new File("utilizatori/Utilizator_" + i + ".txt");
                            Scanner read = new Scanner(verificare);
                            verif = read.next();

                            if (verif.equals(id) == true) {
                                OKid = 1;
                                System.out.print("Parola: ");
                                pass = cin.next();
                                verif = read.next();
                                if (verif.equals(pass) == true) {
                                    System.out.println("\nAti fost logat cu succes!");
                                    OK = 1;
                                    break;
                                }
                            }
                        }
                        if (i == u && OK == 0 && OKid == 0) {
                            System.out.println("Id-ul a fost introdus gresit.");
                        } else if (i == u && OK == 0 && OKid == 1 && OKparola == 0) {
                            System.out.println("Parola a fost introdusa gresit.");
                        } else if (i == u && OK == 0 && OKid == 0 && OKparola == 0) {
                            System.out.println("Datele au fost introduse gresit.");
                        }
                    }
                }
                statusLogare = 1;
            } else if (varianta == 2) //creare cont
            {
                System.out.println("\n--- Creare cont ---");
                System.out.print("ID: ");
                id = cin.next();
                id = verifId(id);
                System.out.print("Parola: ");
                pass = cin.next();
                pass = verifPass(pass);
                System.out.print("Email: ");
                email = cin.next();
                email = verifEmail(email);
                //creare cont
                FileWriter writer = new FileWriter("utilizatori/Nr utilizatori.txt");
                PrintWriter print = new PrintWriter(writer);
                print.print(u + 1);
                print.close();
                writer.close();

                U[u] = new Utilizator(id, pass, email);

                FileWriter addUser = new FileWriter("utilizatori/Utilizator_" + u + ".txt");
                addUser.write(id + "\n" + pass + "\n" + email);
                addUser.close();

                System.out.println("Contul a fost creat cu succes!");
                varianta = 1; //daca s-a creat contul, se trece la logare
                u = u + 1;

            } else if (varianta == 0) {
                System.out.println("La revedere!");
                statusLogare = 1;
            }
        }
        if (i == 0) {
            //interfata admin
            System.out.println("Esti admin? Bine");
        } else {//interfata client
            raspuns[1] = 100;
            while (raspuns[1] != 0) {
                //meniu principal
                System.out.println("\n--- Meniu ---");
                System.out.println("1. Evenimente");
                System.out.println("2. Locatii");
                System.out.println("3. Artisti");
                System.out.println("4. Cautare eveniment");
                System.out.println("--------------------");
                System.out.println("0. Deconecteaza-te");
                System.out.print("\nRaspuns: ");
                raspuns[1] = cin.nextInt();
                raspuns[1] = verifMeniuPrincipal(raspuns[1]);
                System.out.println("");

                if (raspuns[1] == 1) {
                    raspuns[2] = 100;
                    while (raspuns[2] != 0) {
                        System.out.println("Categorii:");
                        for (i = 1; i <= k; i++) {
                            System.out.println(i + ". " + categorii[i]);
                        }

                        System.out.println("--------------------");
                        System.out.println("0. Inapoi");
                        System.out.print("\nRaspuns:");
                        raspuns[2] = cin.nextInt();
                        raspuns[2] = verificare(raspuns[2], 0, k);

                        if (raspuns[2] != 0) {
                            System.out.println("");
                            nr = 0;
                            for (i = 1; i <= n; i++) {
                                if (event[i].categorie.equals(categorii[raspuns[2]]) && event[i].stare == true) {
                                    nr++;
                                    System.out.println(nr + ". " + event[i].nume);
                                    v[nr] = i;
                                }
                            }
                            if (nr == 0) {
                                System.out.println("Nu sunt evenimente disponibile la aceasta categorie\n");
                            } else {
                                System.out.println("0. Inapoi la meniul principal");
                                System.out.print("\nRaspuns: ");
                                r = cin.nextInt();
                                r = verificare(r, 0, nr);
                                if (r != 0) {
                                    System.out.println("");
                                    cumpara(v[r], event, L, s);
                                    raspuns[2] = 0;
                                } else {
                                    raspuns[2] = 0;
                                }
                            }
                        }
                    }
                } else if (raspuns[1] == 2) {
                    raspuns[3] = -1;
                    while (raspuns[3] != 0) {
                        System.out.println("Locatii");
                        for (i = 1; i <= nL; i++) {
                            System.out.println(i + ". " + L[i].nume);
                        }

                        System.out.println("--------------------");
                        System.out.println("0. Inapoi");
                        System.out.print("\nRaspuns:");
                        raspuns[3] = cin.nextInt();
                        raspuns[3] = verificare(raspuns[3], 0, nL);

                        if (raspuns[3] != 0) {
                            System.out.println("");
                            nr = 0;
                            for (i = 1; i <= n; i++) {
                                if (event[i].locatie == raspuns[3]) {
                                    nr++;
                                    System.out.println(nr + ". " + event[i].nume);
                                    v[nr] = i;

                                }
                            }
                            if (nr == 0) {
                                System.out.println("Nu sunt evenimente disponibile la aceasta locatie\n");
                            } else {
                                System.out.println("0. Inapoi la meniul principal");
                                System.out.print("\nRaspuns: ");
                                r = cin.nextInt();
                                r = verificare(r, 0, nr);
                                if (r != 0) {
                                    System.out.println("");
                                    cumpara(v[r], event, L, s);
                                    raspuns[3] = 0;
                                } else {
                                    raspuns[3] = 0;
                                }
                            }
                        }
                    }
                } else if (raspuns[1] == 3) {
                    raspuns[4] = -1;
                    while (raspuns[4] != 0) {
                        System.out.println("Artisti:");
                        for (i = 1; i <= a; i++) {
                            System.out.println(i + ". " + artisti[i]);
                        }

                        System.out.println("--------------------");
                        System.out.println("0. Inapoi");
                        System.out.print("\nRaspuns:");
                        raspuns[4] = cin.nextInt();
                        raspuns[4] = verificare(raspuns[4], 0, k);

                        if (raspuns[4] != 0) {
                            System.out.println("");
                            nr = 0;
                            for (i = 1; i <= n; i++) {
                                if (event[i].artist.equals(artisti[raspuns[4]]) && event[i].stare == true) {
                                    nr++;
                                    System.out.println(nr + ". " + event[i].nume);
                                    v[nr] = i;
                                }
                            }
                            if (nr == 0) {
                                System.out.println("Nu sunt evenimente disponibile pentru acest artist\n");
                            } else {
                                System.out.println("0. Inapoi la meniul principal");
                                System.out.print("\nRaspuns: ");
                                r = cin.nextInt();
                                r = verificare(r, 0, nr);
                                if (r != 0) {
                                    System.out.println("");
                                    cumpara(v[r], event, L, s);
                                    raspuns[4] = 0;
                                } else {
                                    raspuns[4] = 0;
                                }
                            }
                        }
                    }
                } else if (raspuns[1] == 4) {
                    System.out.print("Cautare: ");
                    String sE = new String();
                    String vC[] = new String[100];
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    sE = reader.readLine();
                    int nrC = 0;
                    for (String val : sE.split(" ")) {
                        nrC++;
                        vC[nrC] = val;
                    }
                    String vE[] = new String[100];
                    int nrE = 0;
                    nr = 0;
                    for (i = 1; i <= n; i++) {
                        for (String val : event[i].nume.split(" ")) {
                            nrE++;
                            vE[nrE] = val;
                            for (j = 1; j <= nrC; j++) {
                                if ((vE[nrE].equalsIgnoreCase(vC[j])) && (vE[nrE].length() > 2)) {
                                    nr++;
                                    v[nr] = i;
                                    System.out.println(nr + ". " + event[i].nume);
                                    break;
                                }
                            }
                        }
                    }
                    if (nr == 0) {
                        System.out.println("\n" + "Nu a fost gasit niciun rezultat.");
                    } else {
                        System.out.println("0. Inapoi la meniul principal");
                        System.out.print("\nRaspuns: ");
                        r = cin.nextInt();
                        r = verificare(r, 0, nr);
                        if (r != 0 && event[v[r]].stare == true) {
                            System.out.println("");
                            cumpara(v[r], event, L, s);
                            raspuns[3] = 0;
                        } else {
                            raspuns[3] = 0;
                            System.out.println("Ne pare rau, acest eveniment a avut deja loc.");
                        }
                    }
                } else if (raspuns[1] == 0) {
                    System.out.println("La revedere!");
                    break;
                }
            }
        }
    } // Sfarsit main() // Sfarsit main() // Sfarsit main() // Sfarsit main()  
}
