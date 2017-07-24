package com.hit;

import com.hit.customer.*;
import com.hit.worker.Worker;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
/*    private List<Customer> vipCustomers;
    private List<Customer> returningCustomers;
    private List<Customer> newCustomers;*/
    private List<Customer> allCustomers;
    private Map<Product.productType,Integer> numberOfSales;

    public Branch(String branchName, String phoneNumber, String address) {
        this.branchName = branchName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productsInInventory = new ArrayList<Product>();
        this.workerInBranch = new ArrayList<Worker>();
        this.allCustomers = new ArrayList<Customer>();
        this.quantityOfSales = 0;
        this.numberOfSales = new HashMap<Product.productType,Integer>();
        for (int i = 0; i < Product.productType.values().length; i++){
            this.numberOfSales.put(Product.productType.values()[i], 0);
        }
        System.out.println("%%%"+this.numberOfSales.get(Product.productType.values()[0]));
        System.out.println(Product.productType.values()[3]);
    }

    public List<Customer> getCustomerByType(customerType input){
        List <Customer> customerByType=null;
        switch (input)
        {
            case VIP:
                customerByType = new ArrayList<Customer>();
                for (Customer ViPCustomer: allCustomers)
                {
                    if (ViPCustomer instanceof VIPCustomer)
                    {
                        customerByType.add(ViPCustomer);
                    }
                }
                break;
            case Returning:
                customerByType = new ArrayList<Customer>();
                for (Customer returnCustomer: allCustomers)
                {
                    if (returnCustomer instanceof ReturningCustomer)
                    {
                        customerByType.add(returnCustomer);
                    }
                }
                break;
            case NEW:
                customerByType = new ArrayList<Customer>();
                for (Customer newCustomer: allCustomers)
                {
                    if (newCustomer instanceof NewCustomer)
                    {
                        customerByType.add(newCustomer);
                    }
                }
                break;
        }
        return customerByType;
    }

    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public Map<Product.productType, Integer> getNumberOfSales() {
        return numberOfSales;
    }

//    public List<Customer> getVipCustomers() {
//        return vipCustomers;
//    }
//
//    public void addVIPCustomer(Customer customer)
//    {
//        this.vipCustomers.add(customer);
//    }
//
//    public void addReturningCustomer(Customer customer) {
//        this.returningCustomers.add(customer);
//    }
//
//    public void addNewCustomer(Customer customer){ this.newCustomers.add(customer); }

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
    public void sellProduct(Product.productType product){//, Customer customer) {
        quantityOfSales++; //all sales in branch
        //product inventory less one
        int salesOfProduct = numberOfSales.get(product);
        if(salesOfProduct != 0) {
            salesOfProduct--;
        }
        numberOfSales.put(product, salesOfProduct);
        //customer expended  money
        //customer.expendedMoney += customer.getPrice(product);
    }

    //buy
    public void addProducts() {
        try {
            for (int i = 0; i < Product.productType.values().length; i++){
                int ind = this.numberOfSales.get(Product.productType.values()[i]);
                this.numberOfSales.put(Product.productType.values()[i], ind+1);
            }
            System.out.println("%%%"+this.numberOfSales.get(Product.productType.values()[1]));
        }catch (Exception e){
            System.out.println("buy exception: " + e.getMessage());
        }
    }
}
