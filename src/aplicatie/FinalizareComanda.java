package aplicatie;

import java.util.Scanner;

public class FinalizareComanda {

    public String nume, prenume, adresa, titularCard, nrTelefon, numarCard;
    public int codPostal, MM, YY, CVC;

    //Constructori
    public FinalizareComanda() {
        this.nume = "";
        this.prenume = "";
        this.adresa = "";
        this.nrTelefon = "";
        this.codPostal = 0;
        this.titularCard = "";
        this.numarCard = "";
        this.MM = 0;
        this.YY = 0;
        this.CVC = 0;
    }

    public FinalizareComanda(String n, String p, String nr, String a, int cp, String tc, String nc, int mm, int yy, int cvc) {
        this.nume = n;
        this.prenume = p;
        this.adresa = a;
        this.nrTelefon = nr;
        this.codPostal = cp;
        this.titularCard = tc;
        this.numarCard = nc;
        this.MM = mm;
        this.YY = yy;
        this.CVC = cvc;
    }

    public String verifNume(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 3) {
            System.out.println("Numele este prea scurt. Reintroduceti.");
            System.out.print("Nume: ");
            x = cin.next();
        }
        return x;
    }

    public String verifPrenume(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 3) {
            System.out.println("Prenumele este prea scurt. Reintroduceti.");
            System.out.print("Prenume: ");
            x = cin.next();
        }
        return x;
    }

    public String verifAdresa(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 10) {
            System.out.println("Va rog sa introduceti o adresa valida.");
            System.out.print("Adresa: ");
            x = cin.next();
        }
        return x;
    }

    public String verifTitularCard(String x) {
        Scanner cin = new Scanner(System.in);
        while (x.length() < 6) {
            System.out.println("Numele este prea scurt. Va rog sa introduceti numele complet al titularului.");
            System.out.print("Titular card: ");
            x = cin.next();
        }
        return x;
    }

    public String verifNrTelefon(String x) {
        Scanner cin = new Scanner(System.in);
        while (String.valueOf(x).length() < 10) {
            System.out.println("Va rog sa introduceti un numar de telefon valid.");
            System.out.print("Numar telefon: ");
            x = cin.next();
        }
        return x;
    }

    public int verifCodPostal(int x) {
        Scanner cin = new Scanner(System.in);
        while (x < 6) {
            System.out.println("Codul postal este prea scurt.");
            System.out.print("Cod postal: ");
            x = cin.nextInt();
        }
        return x;
    }

    public String verifNumarCard(String x) {
        int OK = 0;
        Scanner cin = new Scanner(System.in);
        while (OK == 0) {
            if (String.valueOf(x).length() == 19) {
                OK = 1;
            }
            if (String.valueOf(x).length() == 16) {
                OK = 1;
            }

            if (OK == 0) {
                System.out.println("Numarul cardului nu este valid.");
                System.out.print("Numar card: ");
                x = cin.nextLine();
            }
        }
        return x;
    }

    public int verifMM(int x) {
        Scanner cin = new Scanner(System.in);
        while (x < 1 || x > 12) {
            System.out.println("MM este gresit.");
            System.out.print("MM: ");
            x = cin.nextInt();
        }
        return x;
    }

    public int verifYY(int x) {
        Scanner cin = new Scanner(System.in);
        while (x < 20) {
            System.out.println("YY este gresit.");
            System.out.print("YY: ");
            x = cin.nextInt();
        }
        return x;
    }

    public int verifCVC(int x) {
        Scanner cin = new Scanner(System.in);
        while (x < 99 || x > 999) {
            System.out.println("CVC este gresit.");
            System.out.print("CVC: ");
            x = cin.nextInt();
        }
        return x;
    }
}
