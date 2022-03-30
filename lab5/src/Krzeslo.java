public class Krzeslo extends Mebel {

    public Krzeslo(int dlugosc, int szerokosc, int wysokosc, String kolor, int material) {
        super(dlugosc, szerokosc, wysokosc, kolor, material);
    }

    public Krzeslo(Wymiary wymiary, String kolor, int material) {
        super(wymiary, kolor, material);
    }

    @Override
    public String Typ() {
        return "Krzesło";
    }
}
