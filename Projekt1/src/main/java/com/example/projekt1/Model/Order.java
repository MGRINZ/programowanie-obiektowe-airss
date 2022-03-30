package com.example.projekt1.Model;

import java.util.Date;

public class Order {
    private static int nextNumber;

    private final int number;
    private final Client client;
    private final Device device;
    private final String problem;
    private Maintainer maintainer;
    private final Date dateIn;
    private Date dateOut;
    private int status;

    public Order(Client client, Device device, String problem) {
        this(client, device, problem, new Date());
    }

    public Order(Client client, Device device, String problem, Date dateIn) {
        this.number = getNextNumber();
        this.client = client;
        this.device = device;
        this.problem = problem;
        this.dateIn = dateIn;
    }

    private static int getNextNumber() {
        return nextNumber++;
    }

    public void setMaintainer(Maintainer maintainer) {
        this.maintainer = maintainer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public Client getClient() {
        return client;
    }

    public Device getDevice() {
        return device;
    }

    public String getProblem() {
        return problem;
    }

    public Maintainer getMaintainer() {
        return maintainer;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }
}
