public class Lab5 {
    public static void main(String[] args) {
        Mebel meble[] = new Mebel[5];
        meble[0] = new Krzeslo(50, 50, 100, "bialy", Mebel.getMaterialId("plastik"));
        meble[1] = new Stol(50, 50, 100, "bialy", Mebel.getMaterialId("drewno"));
        Wymiary wymiarySzafy = new Wymiary(100, 80, 60);
        meble[2] = new Szafa(wymiarySzafy, "czarny", Mebel.getMaterialId("drewno"));
        meble[3] = new Szafa(120, 100, 80, "brązowy", Mebel.getMaterialId("drewno"));
        Wymiary wymiaryStolu = new Wymiary(100, 100, 80);
        meble[4] = new Stol(wymiaryStolu, "czarny", Mebel.getMaterialId("plastik"));

        for(Mebel mebel : meble) {
            System.out.println(mebel.Typ());
            System.out.println(mebel.getId());
        }

    }
}
