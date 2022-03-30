public abstract class Mebel {

    private static int maxId = 0;

    private int id;
    private Wymiary wymiary;
    private String kolor;
    private int material;

    public Mebel(int dlugosc, int szerokosc, int wysokosc, String kolor, int material) {
        this(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, material);
    }

    public Mebel(Wymiary wymiary, String kolor, int material) {
        this.id = Mebel.getNewId();
        this.wymiary = wymiary;
        this.kolor = kolor;
        this.material = material;
    }

    public static int getMaterialId(String material) {

        return switch (material) {
            case "plastik" -> 0;
            case "drewno" -> 1;
            default -> 0;
        };
    }

    private static int getNewId() {
        return maxId++;
    }

    public int getId() {
        return id;
    }

    public Wymiary getWymiary() {
        return wymiary;
    }

    public String getKolor() {
        return kolor;
    }

    public int getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return String.format(
            "%d: %s - %s - %dx%dx%d - %d",
            id,
            Typ(),
            kolor,
            wymiary.getDlugosc(),
            wymiary.getSzerokosc(),
            wymiary.getWysokosc(),
            material
    );
    }

    public abstract String Typ();
}
