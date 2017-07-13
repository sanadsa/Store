package com.hit.customer;

import com.hit.Branch;
import com.hit.Person;
import com.hit.Product;

public abstract class Customer extends Person
{
    public Customer(String name, String id, String phone)
    {
        super(name, id, phone);
    }

    public void toBuy(Product product){
        try {
            printPrice(product.getPrice());
        }catch (Exception e){
            System.out.println("buy exception: " + e.getMessage());
        }
    }

    protected abstract void printPrice(double price);
}
