package aplicatie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bilet {

    public int rand, loc, id_sector, pret;
    public String sector;

    // CONSTRUCTOR
    public Bilet(int x, int y, String s, Locatie L[], Eveniment event[], int z) throws FileNotFoundException {
        int i;
        String c1;
        Scanner cin = new Scanner(System.in);
        this.rand = x;
        this.loc = y;
        this.sector = s;

        for (i = 1; i <= L[event[z].locatie].numarSectoare; i++) {
            File read = new File("evenimente/Eveniment_" + z + "/Sector_" + i + ".txt");
            Scanner readSector = new Scanner(read);
            c1 = readSector.next();
            if (s.equals(c1)) {
                this.id_sector = i;
                break;
            }
        }
    }

    // METODE
    public void afis() {
        System.out.println("Sector " + this.sector + " Rand " + this.rand + " Loc " + this.loc);
    }
}
