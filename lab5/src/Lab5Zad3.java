import java.util.Scanner;

public class Lab5Zad3 {
    private static Scanner scanner;

    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();

//        magazyn.dodaj(new Krzeslo(50, 50, 100, "bialy", Mebel.getMaterialId("plastik")));
//        magazyn.dodaj(new Stol(50, 50, 100, "bialy", Mebel.getMaterialId("drewno")));
//        magazyn.dodaj(new Szafa(new Wymiary(100, 80, 60), "czarny", Mebel.getMaterialId("drewno")));
//        magazyn.dodaj(new Szafa(120, 100, 80, "brązowy", Mebel.getMaterialId("drewno")));
//        magazyn.dodaj(new Stol(new Wymiary(100, 100, 80), "czarny", Mebel.getMaterialId("plastik")));

        while(true) {
            System.out.println("[1] Wyświetl stan");
            System.out.println("[2] Szukaj mebli");
            System.out.println("[3] Dodaj mebel");
            System.out.println("[4] Usuń mebel");

            System.out.print("Wybor: ");
            scanner = new Scanner(System.in);
            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    magazyn.stan();
                    break;
                case 2:
                    System.out.print("Filtr: ");
                    String filtr = scanner.next();
                    magazyn.filtruj(filtr).stan();
                    break;
                case 3:
                    Mebel mebel = dodajMebel();
                    if(mebel != null)
                        magazyn.dodaj(mebel);
                    break;
                case 4:
                    System.out.print("Id do usunięcia: ");
                    int id = scanner.nextInt();
                    magazyn.usunId(id);
                    break;
            }

            System.out.print("Koniec [y/N]: ");
            String reply = scanner.next().toLowerCase();

            if(reply.charAt(0) == 'y')
                break;
        }
    }

    private static Mebel dodajMebel() {
        System.out.print("Typ: ");
        String mebel = scanner.next().toLowerCase();

        System.out.print("Długość: ");
        int dlugosc = scanner.nextInt();

        System.out.print("Szerokość: ");
        int szerokosc = scanner.nextInt();

        System.out.print("Wysokość: ");
        int wysokosc = scanner.nextInt();

        System.out.print("Kolor: ");
        String kolor = scanner.next().toLowerCase();

        System.out.print("Materiał: ");
        String material = scanner.next().toLowerCase();

        return switch (mebel) {
            case "krzesło" -> new Krzeslo(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
            case "stół" -> new Stol(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
            case "szafa" -> new Szafa(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
            default -> null;
        };
    }
}
