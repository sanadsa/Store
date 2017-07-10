package com.hit.customer;

public class ReturningCustomer extends Customer {
    private final double salePercent = 0.1;

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }

    public ReturningCustomer(String customerName, String customerId, String customerPhone, Branch belongName)
    {
        super(customerName, customerId, customerPhone, belongName);
    }
}
