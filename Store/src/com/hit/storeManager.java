package com.hit;

import com.hit.customer.Customer;
import com.hit.customer.NewCustomer;
import com.hit.customer.ReturningCustomer;
import com.hit.customer.VIPCustomer;
import com.hit.worker.Cashier;
import com.hit.worker.Seller;
import com.hit.worker.responsibleShift;
import com.hit.worker.Worker;

public class storeManager
{
    public static Branch TLVStore = new Branch("TLV","03-2323232","ibn Gabirol 15");
    public static Branch HaifaStore = new Branch("Haifa","02-2323232","Allenby");
    public static Product product = new Product();

    public static Branch getBranch(String branchName){
        if(TLVStore.getBranchName().equals(branchName))
            return TLVStore;
        else return HaifaStore;
    }

    /**
     * create worker by type
     * @param input all worker parameters
     * @return new worker
     */
    public static Worker createWorkerAndInsert(String[] input){
        Worker typeWorker=null;
        switch (input[1])
        {
            case "responsibleShift":
                typeWorker= new responsibleShift(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
            case "Seller":
                typeWorker= new Seller(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
            case "Cashier":
                typeWorker= new Cashier(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
        }
        if(typeWorker.getBranch().equals("TLV"))
            storeManager.TLVStore.addWorker(typeWorker);
        else storeManager.HaifaStore.addWorker(typeWorker);
        return typeWorker;
    }

    /**
     * creates new customer by type
     * @param input all parameters of customer
     * @return the created customer
     */
    public static Customer createCustomer(String[] input){
        Customer customerType = null;
        String stringJson;
        switch (input[1])
        {
            case "NewCustomer":
                customerType = new NewCustomer(input[2], input[3], input[4], input[5]);
                break;
            case "ReturningCustomer":
                customerType = new ReturningCustomer(input[2], input[3], input[4], input[5]);
                break;
            case "VIPCustomer":
                customerType = new VIPCustomer(input[2], input[3], input[4], input[5]);
                break;
        }
        if(input[5].equals("TLV"))
            storeManager.TLVStore.addCustomer(customerType);
        else storeManager.HaifaStore.addCustomer(customerType);
        return customerType;
    }

    /**
     * add products to branch
     * @param input branch
     */
    public static void addProducts(String[] input){
        if(input[1].equals("TLV"))
            storeManager.TLVStore.addProducts();
        else
            storeManager.HaifaStore.addProducts();
    }

    public static void sellProduct(String[] input){
        System.out.println(input[2]);
        Product.productType product = Product.productType.valueOf(input[2]);
        if(input[1].equals("TLV"))
            storeManager.TLVStore.sellProduct(product);
        else
            storeManager.HaifaStore.sellProduct(product);
    }

    public static String[] getAction(String action){
        String[] allParameter = action.split(",");
        return allParameter;
    }

    /**
     * search for worker in DB
     * @param name name of the worker
     * @param Password password of the worker
     * @return null if the worker didnt found else the worker that was found
     */
    public static Worker searchWorker(String name, String Password)
    {
        boolean isFound = false;
        Worker workerFound = null;
        for (Worker wo :TLVStore.getWorkerInBranch())
        {
            if(wo.getPassword().equals(Password) && wo.getName().equals(name))
            {
                workerFound = wo;
                isFound = true;
                break;
            }
        }
        if(!isFound)
            for (Worker wo : HaifaStore.getWorkerInBranch())
            {
                if(wo.getPassword().equals(Password) && wo.getName().equals(name))
                {
                    workerFound = wo;
                    break;
                }
            }
        return  workerFound;
    }


}
