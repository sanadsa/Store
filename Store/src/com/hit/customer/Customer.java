package com.hit.customer;

import com.hit.Branch;
import com.hit.Person;
import com.hit.Product;

public abstract class Customer extends Person
{
    public int expendedMoney;
    private customerType type;
    //private double salePercent;
    private String branch;
    public enum customerType
    {
        NEW,
        Returning,
        VIP
    }
    //Product p = new Product();

    public Customer(String name, String id, String phone, String branch, customerType type)
    {
        super(name, id, phone);
        this.expendedMoney = 0;
        this.branch = branch;
        this.type = type;
        //this.salePercent = salePercent;
    }

    public customerType getType() {
        return type;
    }

    public abstract double getPrice(Product.productType product);

    //protected abstract void printPrice(double price);
}
