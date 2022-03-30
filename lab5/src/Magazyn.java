import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class Magazyn {
    private ArrayList<Mebel> meble = new ArrayList<>();

    public void dodaj(Mebel mebel) {
        meble.add(mebel);
    }

    public void usun(Mebel mebel) {
        meble.remove(mebel);
    }

    public void stan() {
        for (Mebel mebel : meble) {
            System.out.println(mebel);
        }
    }

    public Mebel pobierzMebel(int i) {
        return meble.get(i);
    }

    public Magazyn filtruj(String typ) {
        Magazyn m = new Magazyn();
        for(Mebel mebel : meble) {
            if(mebel.Typ().matches(typ))
                m.dodaj(mebel);
        }
        return m;
    }

    public void usunId(int id) {
        for(Mebel mebel : meble) {
            if(mebel.getId() == id) {
                meble.remove(mebel);
                break;
            }
        }
    }

    public ListModel<String> pobierzListe() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Mebel mebel : meble) {
            listModel.addElement(mebel.toString());
        }
        return listModel;
    }
}
