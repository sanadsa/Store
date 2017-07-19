package com.hit.customer;

import com.hit.Branch;

public class ReturningCustomer extends Customer {
    private final double salePercent = 0.1;
    private Branch branch;

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }

    public ReturningCustomer(String customerName, String customerId, String customerPhone, String belongName)
    {
        super(customerName, customerId, customerPhone, belongName);
    }
}
