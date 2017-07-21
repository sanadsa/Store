package com.hit.customer;

import com.hit.Branch;

public class NewCustomer extends Customer {
    private final double salePercent = 0.3;

    public NewCustomer(String customerName, String customerId, String customerPhone, String belongName)
    {
        super(customerName, customerId, customerPhone, belongName, customerType.NEW, 0.3);
    }

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }
}
