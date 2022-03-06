package aplicatie;

public class Utilizator {

    protected String id_user;
    protected String password1;
    protected String password2;
    protected String email;

    //Constructori
    public Utilizator(String ID, String P, String E) {
        this.id_user = ID;
        this.password1 = P;
        this.email = E;
    }

    public Utilizator() {
        this.id_user = "";
        this.password1 = "";
        this.password2 = "";
        this.email = "";
    }

    public Utilizator(String ID, String P1, String P2, String E) {
        this.id_user = ID;
        this.password1 = P1;
        this.password2 = P2;
        this.email = E;
    }

    public void creeareCont() {
        System.out.println("Introduceti Email: " + this.email);
        System.out.println("Introduceti Username: " + this.id_user);
        System.out.println("Introduceti parola: " + this.password1);
        System.out.println("Reintroduceti parola: " + this.password2);
    }

    public String parola(String P1, String P2) {
        if (P1.equals(P2)) {
            return "Parolele se potrivesc";
        } else {
            return "Parolele nu se potrivesc";
        }
    }

    public void email(String E) {
        int a = E.indexOf('@');
        int b = E.indexOf('.');
        if (a >= 0 && b >= 0) {
            System.out.println("Emailul este introdus corect.");
        } else {
            System.out.println("Emailul nu este introdus corect.Reincercati");
        }
    }

    public void logare() {
        System.out.println("Username: " + this.id_user);
        System.out.println("Parola: " + this.password1);
    }

    public void deconectare() {

    }

    public void schimbareParola() {
        System.out.println("Introduceti parola noua: " + this.password1);
        System.out.println("Introduceti email pentru verificare: " + this.email);
    }

    public void schimbareEmail() {
        System.out.println("Introduceti email-ul nou: " + this.email);
        System.out.println("Introduceti parola pentru verificare: " + this.password1);
    }
}
