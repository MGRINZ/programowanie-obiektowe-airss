import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Lab5Zad4 {

    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();

        magazyn.dodaj(new Krzeslo(50, 50, 100, "bialy", Mebel.getMaterialId("plastik")));
        magazyn.dodaj(new Stol(50, 50, 100, "bialy", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Szafa(new Wymiary(100, 80, 60), "czarny", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Szafa(120, 100, 80, "brązowy", Mebel.getMaterialId("drewno")));
        magazyn.dodaj(new Stol(new Wymiary(100, 100, 80), "czarny", Mebel.getMaterialId("plastik")));

        MainWindow window = new MainWindow(magazyn);
        window.setVisible(true);
    }
}

class MainWindow extends JFrame implements ActionListener {

    private Magazyn magazyn;
    private Magazyn filteredMagazyn;

    private JPanel panel;
    private JPanel addPanel;
    private JPanel searchPanel;
    private JPanel listPanel;

    private JComboBox<String> mebelComboBox;
    private JTextField dlugoscText;
    private JTextField szerokoscText;
    private JTextField wysokoscText;
    private JTextField kolorText;
    private JTextField materialText;

    private JTextField searchMebelText;

    private JList<String> mebleList;

    public MainWindow(Magazyn magazyn) {
        this.magazyn = magazyn;
        initComponents();        
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        initAddPanel();
        initSearchPanel();
        initListPanel();
        
        panel.add(addPanel);
        panel.add(searchPanel);
        panel.add(listPanel);

        add(panel);
        pack();
    }

    private void initAddPanel() {
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);

        c.gridx = 0;
        c.gridy = 0;
        addPanel.add(new JLabel("Mebel"), c);

        c.gridx = 1;
        addPanel.add(new JLabel("Długość"), c);
        
        c.gridx = 2;
        addPanel.add(new JLabel("Szerokość"), c);

        c.gridx = 3;
        addPanel.add(new JLabel("Wysokość"), c);

        c.gridx = 4;
        addPanel.add(new JLabel("Kolor"), c);

        c.gridx = 5;
        addPanel.add(new JLabel("Materiał"), c);

        mebelComboBox = new JComboBox<>(new String[] { "Krzesło", "Stół", "Szafa" });
        dlugoscText = new JTextField(20);
        szerokoscText = new JTextField(20);
        wysokoscText = new JTextField(20);
        kolorText = new JTextField(20);
        materialText = new JTextField(20);

        c.gridx = 0;
        c.gridy = 1;
        addPanel.add(mebelComboBox, c);
        
        c.gridx = 1;
        addPanel.add(dlugoscText, c);

        c.gridx = 2;
        addPanel.add(szerokoscText, c);
        
        c.gridx = 3;
        addPanel.add(wysokoscText, c);

        c.gridx = 4;
        addPanel.add(kolorText, c);

        c.gridx = 5;
        addPanel.add(materialText, c);

        c.gridy = 2;
        c.gridx = 0;

        JButton addButton = new JButton("Dodaj");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        addPanel.add(addButton, c);
    }

    private void initSearchPanel() {
        searchPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);

        c.gridx = 0;
        c.gridy = 0;
        searchPanel.add(new JLabel("Wyszukaj:"), c);

        c.gridx = 1;
        searchMebelText = new JTextField(20);
        searchPanel.add(searchMebelText, c);

        c.gridx = 2;
        JButton searchButton = new JButton("Wyszukaj");
        searchButton.setActionCommand("search");
        searchButton.addActionListener(this);
        searchPanel.add(searchButton, c);
    }

    private void initListPanel() {
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));

        mebleList = new JList<>(this.magazyn.pobierzListe());
        mebleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton deleteButton = new JButton("Usuń");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(this);

        listPanel.add(new JLabel("Meble"));
        listPanel.add(mebleList);
        listPanel.add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "add":
                addMebel();
                break;
            case "delete":
                deleteMebel();
                break;
            case "search":
                searchMebel();
                break;
        }
    }

    public void addMebel() {
        try {
            int dlugosc = Integer.parseInt(dlugoscText.getText());
            int szerokosc = Integer.parseInt(szerokoscText.getText());
            int wysokosc = Integer.parseInt(wysokoscText.getText());
            String kolor = kolorText.getText();
            String material = materialText.getText();
    
            Mebel mebel = switch (mebelComboBox.getSelectedIndex()) {
                case 0 -> new Krzeslo(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
                case 1 -> new Stol(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
                case 2 -> new Szafa(new Wymiary(dlugosc, szerokosc, wysokosc), kolor, Mebel.getMaterialId(material));
                default -> null;
            };

            if(mebel == null)
                return;
            
            magazyn.dodaj(mebel);
            mebleList.setModel(magazyn.pobierzListe());
        } catch(Exception e) {

        }
    }
    
    private void deleteMebel() {
        if(mebleList.isSelectionEmpty())
            return;

        int index = mebleList.getSelectedIndex();
        Mebel mebel = magazyn.pobierzMebel(index);
        if(filteredMagazyn != null)
            mebel = filteredMagazyn.pobierzMebel(index);
        magazyn.usun(mebel);
        mebleList.setModel(magazyn.pobierzListe());
        if(filteredMagazyn != null) {
            filteredMagazyn.usun(mebel);
            mebleList.setModel(filteredMagazyn.pobierzListe());
        }
    }

    private void searchMebel() {
        String searchString = searchMebelText.getText();

        if(searchString.isEmpty()) {
            mebleList.setModel(magazyn.pobierzListe());    
            filteredMagazyn = null;
            return;
        }

        filteredMagazyn = magazyn.filtruj(searchString);
        mebleList.setModel(filteredMagazyn.pobierzListe());
    }

}