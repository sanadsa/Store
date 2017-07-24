package com.hit.customer;

import com.hit.Branch;
import com.hit.Person;
import com.hit.Product;

public abstract class Customer extends Person
{
    public int expendedMoney;
    private String type;
    //private double salePercent;
    private String branch;

    //Product p = new Product();

    public String getBranch() {
        return branch;
    }

    public Customer(String name, String id, String phone, String branch, customerType type)
    {
        super(name, id, phone);
        this.expendedMoney = 0;
        this.branch = branch;
        this.type = type.toString();
    }

    public String getType() {
        return type;
    }

    public abstract double getPrice(Product.productType product);

    //protected abstract void printPrice(double price);
}
