package com.example.pharmacyproject;

public class Dataclass {
    String medicinename,quantity;
    int price;

    public Dataclass(String medicinename, String quantity, int price) {
        this.medicinename = medicinename;
        this.quantity = quantity;
        this.price = price;
    }

    public Dataclass(String medicinename, String quantity) {
        this.medicinename = medicinename;
        this.quantity = quantity;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
