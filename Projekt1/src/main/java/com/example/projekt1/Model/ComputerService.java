package com.example.projekt1.Model;

import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;

import java.util.ArrayList;

public final class ComputerService {

    private static volatile ComputerService instance;

    private final ArrayList<Maintainer> maintainers = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();

    public static ComputerService getInstance() {
        if(instance != null)
            return instance;

        synchronized(ComputerService.class) {
            if(instance == null)
                instance = new ComputerService();
            return instance;
        }
    }

    private ComputerService() {
        initMaintainers();
        initOrders();
    }

    private void initMaintainers() {
        maintainers.add(new Maintainer("Michał", "Klukowski"));
        maintainers.add(new Maintainer("Patryk", "Górski"));
        maintainers.add(new Maintainer("Adrian", "Sarnowski"));
        maintainers.add(new Maintainer("Mateusz", "Nieora"));
    }

    private void initOrders() {
        Order order = new Order(
                new Client("Patryk", "Kaźmierczak", "123456789"),
                new Computer("Gigabyte", "-", "Windows 7", false, "PC"),
                "Komputer nie chodzi");
        orders.add(order);

        order = new Order(
                new Client("Mateusz", "Zieliński", "234567890"),
                new Computer("ASUS", "A555", "Windows 10", true, "Laptop"),
                "Reinstalacja systemu");
        order.setMaintainer(maintainers.get(0));
        order.setStatus(OrderStatus.IN_SERVICE);
        orders.add(order);

        order = new Order(
                new Client("Adam", "Nowak", "345678901"),
                new Printer("HP", "DesignJet 100plus", "Atramentowy ploter"),
                "Nie drukuje");
        order.setMaintainer(maintainers.get(2));
        order.setStatus(OrderStatus.READY);
        orders.add(order);

        order = new Order(
                new Client("Karol", "Wasiewski", "456789012"),
                new Computer("ASUS", "-", "Windows 10", false, "PC"),
                "Komputer do złożenia");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Bartłomiej", "Wiśniewski", "567890123"),
                new Smartphone("Samsung", "Galaxy S20", "Android 11"),
                "Zbita szybka");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.IN_SERVICE);
        orders.add(order);

        order = new Order(
                new Client("Cezary", "Karpowicz", "567890123"),
                new Smartphone("Nokia", "3310", "-"),
                "Spalony kabel od Wi-Fi");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.IN);
        orders.add(order);

        order = new Order(
                new Client("Dariusz", "Woźniak", "678901234"),
                new Printer("Brother", "MFC-L2712DN", "Laserowe urządzenie wielofunkcyjne"),
                "Nie działa skaner");
        order.setMaintainer(maintainers.get(0));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Eryk", "Kwiatkowski", "789012345"),
                new Computer("Acer", "Aspire F15", "Arch Linux", false, "Laptop"),
                "System nie startuje");
        order.setMaintainer(maintainers.get(0));
        order.setStatus(OrderStatus.IN_SERVICE);
        orders.add(order);

        order = new Order(
                new Client("Filip", "Szczepański", "890123456"),
                new Smartphone("Prestigio", "GV7780", "Android 4.3"),
                "Bootloop");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Grzegorz", "Nowicki", "901234567"),
                new Smartphone("Samsung", "Galaxy J5", "Android 8"),
                "Usunięcie blokady");
        order.setMaintainer(maintainers.get(2));
        order.setStatus(OrderStatus.READY);
        orders.add(order);

        order = new Order(
                new Client("Hubert", "Góralski", "135792468"),
                new Computer("MSI", "-", "Windows 11", true, "PC"),
                "Artefakty");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Igor", "Kamiński", "246801357"),
                new Computer("ASUS", "-", "Windows XP", true, "PC"),
                "Nagle się wyłącza");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.IN_SERVICE);
        orders.add(order);

        order = new Order(
                new Client("Jakub", "Mech", "357924680"),
                new Computer("Gigabyte", "-", "Windows Vista", true, "PC"),
                "Reinstalacja systemu");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.IN);
        orders.add(order);

        order = new Order(
                new Client("Kamil", "Steinbach", "468013579"),
                new Computer("MSI", "-", "Windows XP", false, "PC"),
                "Podkręcenie DVD na Blu-ray");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.IN);
        orders.add(order);

        order = new Order(
                new Client("Lesław", "Majewski", "579246801"),
                new Smartphone("Sony", "Xperia M2", "Android 4.4"),
                "Zbita szybka");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Marcin", "Malinowski", "680135792"),
                new Smartphone("iPhone", "13 Pro Max", "iOS 15"),
                "Instalacja Androida");
        order.setMaintainer(maintainers.get(1));
        order.setStatus(OrderStatus.IN);
        orders.add(order);

        order = new Order(
                new Client("Nikodem", "Michalski", "792468013"),
                new Printer("EPSON", "L365", "Atramentowe urządzenie wielofunkcyjne"),
                "Nie drukuje czarnego koloru");
        order.setMaintainer(maintainers.get(2));
        order.setStatus(OrderStatus.IN);
        orders.add(order);

        order = new Order(
                new Client("Oskar", "Stępień", "801357924"),
                new Computer("MSI", "-", "Windows 98", false, "PC"),
                "Aktualizacja do wersji SE");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.IN_SERVICE);
        orders.add(order);

        order = new Order(
                new Client("Piotr", "Kamiński", "924680135"),
                new Computer("Lenovo", "IdeaPad Z710", "Windows 7", false, "Laptop"),
                "Usunięcie wirusów");
        order.setMaintainer(maintainers.get(2));
        order.setStatus(OrderStatus.OUT);
        orders.add(order);

        order = new Order(
                new Client("Rafał", "Zakrzewski", "013579246"),
                new Computer("ASUS", "-", "Windows XP", false, "PC"),
                "Wymiana dyska");
        order.setMaintainer(maintainers.get(3));
        order.setStatus(OrderStatus.IN);
        orders.add(order);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<Maintainer> getMaintainers() {
        return maintainers;
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}
