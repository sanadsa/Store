package com.hit;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private productType name;
    private double price;
    public enum productType
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
    //Map<productType,Integer> numberOfSales = new HashMap<productType,Integer>();
    public Map<productType,Integer> priceOfProducts = new HashMap<productType,Integer>();

    public Product() {
        //this.name = name;
        int p = 10;
        for (int i = 0; i < Product.productType.values().length; i++){
            this.priceOfProducts.put(Product.productType.values()[i], p);
            p += 5;
        }
    }

    public void setPrices(){

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
}

