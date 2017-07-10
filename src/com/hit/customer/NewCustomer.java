package com.hit.customer;

public class NewCustomer extends Customer {
    private final double salePercent = 0.3;


    public NewCustomer(String customerName, String customerId, String customerPhone, Branch belongName)
    {
        super(customerName, customerId, customerPhone, belongName);
    }

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }
}
