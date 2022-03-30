public class Szafa extends Mebel {

    public Szafa(int dlugosc, int szerokosc, int wysokosc, String kolor, int material) {
        super(dlugosc, szerokosc, wysokosc, kolor, material);
    }

    public Szafa(Wymiary wymiary, String kolor, int material) {
        super(wymiary, kolor, material);
    }

    @Override
    public String Typ() {
        return "Szafa";
    }
}
