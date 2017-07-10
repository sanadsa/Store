package com.hit.customer;

import com.hit.Person;
import com.hit.Product;

public abstract class Customer extends Person
{
    public Customer(String name, String id, String phone, Branch branch)
    {
        super(name, id, phone, branch);
    }
    public Customer(){}

    public void toBuy(Product product){
        try {
            getBranch().removeProduct(product);
            printPrice(product.getPrice());
        }catch (Exception e){
            System.out.println("buy exception: " + e.getMessage());
        }
    }

    protected abstract void printPrice(double price);
}
