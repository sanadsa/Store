package com.hit.customer;

import com.hit.Branch;

import java.util.ArrayList;
import java.util.List;

public class VIPCustomer extends Customer
{
    private final double salePercent = 0.5;

    public VIPCustomer(String customerName, String customerId, String customerPhone, String branchName)
    {
        super(customerName, customerId, customerPhone, branchName, customerType.VIP, 0.5);
    }

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }
}
