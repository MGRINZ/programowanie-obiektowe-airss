public class Lab5Zad2 {
    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        magazyn.dodaj(new Krzeslo(50, 50, 100, "bialy", Mebel.getMaterialId("plastik")));
        magazyn.dodaj(new Stol(50, 50, 100, "bialy", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Szafa(new Wymiary(100, 80, 60), "czarny", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Szafa(120, 100, 80, "brązowy", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Stol(new Wymiary(100, 100, 80), "czarny", Mebel.getMaterialId("plastik")));

        magazyn.stan();
        System.out.println();

        magazyn.usun(magazyn.pobierzMebel(2));

        magazyn.stan();
        System.out.println();

        magazyn.filtruj("Stół").stan();
    }
}
