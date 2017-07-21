package com.hit.customer;

import com.hit.Branch;
import com.hit.Person;
import com.hit.Product;

public abstract class Customer extends Person
{
    public int expendedMoney;
    private customerType name;
    private double salePercent;
    public enum customerType
    {
        NEW,
        Returning,
        VIP
    }
    Product p = new Product();

    public Customer(String name, String id, String phone, String branch, customerType type, double salePercent)
    {
        super(name, id, phone);
        this.expendedMoney = 0;
        this.salePercent = salePercent;
    }

    public customerType getType() {
        return name;
    }

    public double getPrice(Product.productType product){
        int productPrice = p.priceOfProducts.get(product);
        return productPrice*salePercent;
    }

    protected abstract void printPrice(double price);
}
