package com.hit;

import com.hit.worker.Worker;
import com.hit.customer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * class that manage the inventory of the branch
 */
public class Branch {
    private String branchName;
    private String phoneNumber;
    private String address;
    private List<Worker> workerInBranch;
    private List<Product> productsInInventory;
    private int quantityOfSales;
    private List<Customer> vipCustomers;

    public Branch(String branchName, String phoneNumber, String address) {
        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productsInInventory = new ArrayList<Product>();
        this.workerInBranch = new ArrayList<Worker>();
        this.vipCustomers = new ArrayList<Customer>();
        this.quantityOfSales = 0;
    }

    public List<Customer> getVipCustomers() {
        return vipCustomers;
    }

    public void addVIPCustomer(Customer customer)
    {
        this.vipCustomers.add(customer);
    }

    public void addWorker(Worker workerInput)
    {
        this.workerInBranch.add(workerInput);
    }
    public void deleteWorker(Worker workerInput)
    {
        this.workerInBranch.remove(workerInput);
    }

    public void setWorkerInBranch(List<Worker> workerInBranch)
    {
        this.workerInBranch = workerInBranch;
    }

    public void setProductsInInventory(List<Product> productsInInventory)
    {
        this.productsInInventory = productsInInventory;
    }

    public List<Worker> getWorkerInBranch()
    {
        return workerInBranch;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantityOfSales() {
        return quantityOfSales;
    }

    public void setQuantityOfSales(int quantityOfSales) {
        this.quantityOfSales = quantityOfSales;
    }

    //what products in inventory
    public List<Product> getProductsInInventory() {
        return productsInInventory;
    }

    //sell
    public void removeProduct(Product product) {
        try {
            productsInInventory.remove(product);
            quantityOfSales++;
        }catch (Exception e){
            System.out.println("buy exception: " + e.getMessage());
        }
    }

    //buy
    public void addProduct(Product product) {
        try {
            productsInInventory.add(product);
        }catch (Exception e){
            System.out.println("buy exception: " + e.getMessage());
        }
    }


}
