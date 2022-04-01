package com.example.projekt1.Model;

import com.example.projekt1.Model.Devices.Computer;
import com.example.projekt1.Model.Devices.Printer;
import com.example.projekt1.Model.Devices.Smartphone;

import java.util.ArrayList;

public class ComputerService {

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
                new Computer("Asus", "A555", "Windows 10", true, "Laptop"),
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
                new Computer("Asus", "-", "Windows 10", false, "PC"),
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
                new Client("Czarek", "Karpowicz", "567890123"),
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

    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
