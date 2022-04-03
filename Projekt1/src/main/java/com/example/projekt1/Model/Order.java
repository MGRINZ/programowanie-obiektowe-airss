package com.example.projekt1.Model;

import java.util.Date;

public class Order {
    private static int nextNumber;

    private final int number;
    private final Client client;
    private final Device device;
    private String problem;
    private Maintainer maintainer;
    private final Date dateIn;
    private Date dateOut;
    private OrderStatus status;

    public Order(Client client, Device device, String problem) {
        this(client, device, problem, new Date());
    }

    public Order(Client client, Device device, String problem, Date dateIn) {
        this.number = getNextNumber();
        this.client = client;
        this.device = device;
        this.problem = problem;
        this.dateIn = dateIn;
        this.status = OrderStatus.IN;
    }

    private static int getNextNumber() {
        return nextNumber++;
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

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Maintainer getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(Maintainer maintainer) {
        this.maintainer = maintainer;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        if(status == OrderStatus.OUT)
            dateOut = new Date();
        else
            dateOut = null;
    }
}
