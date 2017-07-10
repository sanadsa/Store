package com.hit;

public class Product {
    private int id;
    private  productType name;
    private double price;
    public enum  productType
    {
        SportsPants,
        customMade,
        jeans,
        tShirt,
        TailoredShirt,
        coat,
        sweater
    }
    public Product(int id, productType name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public productType getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ("Product id: " + this.id + "," +
                "product name: " + this.name + ", price: " + this.price + " shekels");
    }
}

