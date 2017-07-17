package com.hit;

import java.util.HashMap;
import java.util.Map;

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
    //the value of the product type is the quantity of sales
    Map<productType,Integer> numberOfSales = new HashMap<productType,Integer>();

    public Product(int id, productType name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.numberOfSales.put(name, 0);

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

