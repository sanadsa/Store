package com.hit.customer;

import com.hit.Branch;

import java.util.ArrayList;
import java.util.List;

public class VIPCustomer extends Customer
{
    private final double salePercent = 0.5;
    private List<Customer> vipCustomers;

    public VIPCustomer(String customerName, String customerId, String customerPhone, Branch belongName)
    {
        super(customerName, customerId, customerPhone, belongName);
    }

    public VIPCustomer(){
        this.vipCustomers = new ArrayList<Customer>();
    }

    public void addVIPCustomer(VIPCustomer customer)
    {
        this.vipCustomers.add(customer);
    }

    public List<Customer> getVipCustomers() {
        return vipCustomers;
    }

    @Override
    protected void printPrice(double price)
    {
        System.out.println("the price is" + salePercent*price);
    }
}
