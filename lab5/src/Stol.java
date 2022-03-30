public class Stol extends Mebel {

    public Stol(int dlugosc, int szerokosc, int wysokosc, String kolor, int material) {
        super(dlugosc, szerokosc, wysokosc, kolor, material);
    }

    public Stol(Wymiary wymiary, String kolor, int material) {
        super(wymiary, kolor, material);
    }

    @Override
    public String Typ() {
        return "Stół";
    }
}
