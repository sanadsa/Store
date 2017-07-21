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

    public static Branch getBranch(String branchName){
        if(TLVStore.getBranchName()==branchName)
            return TLVStore;
        else return HaifaStore;
    }

    public static Worker createWorkerAndInsert(String[] input){
        Worker typeWorker=null;
        switch (input[1])
        {
            case "responsible Shift":
                typeWorker= new responsibleShift(input[2],input[3],input[4],input[6],input[7]);
                break;
            case "Seller":
                typeWorker= new Seller(input[2],input[3],input[4],input[6],input[7]);
                break;
            case "Cashier":
                typeWorker= new Cashier(input[2],input[3],input[4],input[6],input[7]);
                break;
        }
        if(input[5]=="TLV")
            storeManager.TLVStore.addWorker(typeWorker);
        else storeManager.HaifaStore.addWorker(typeWorker);
        return typeWorker;
    }

    public static Customer createCustomer(String[] input){
        Customer customerType = null;
        switch (input[1])
        {
            case "NewCustomer":
                customerType = new NewCustomer(input[2], input[3], input[4], input[5]);
                if(input[5] == "TLV")
                    storeManager.TLVStore.addNewCustomer(customerType);
                else storeManager.HaifaStore.addNewCustomer(customerType);
                break;
            case "ReturningCustomer":
                customerType = new ReturningCustomer(input[2], input[3], input[4], input[5]);
                if(input[5] == "TLV")
                    storeManager.TLVStore.addReturningCustomer(customerType);
                else storeManager.HaifaStore.addReturningCustomer(customerType);
                break;
            case "VIPCustomer":
                customerType = new VIPCustomer(input[2], input[3], input[4], input[5]);
                if(input[5] == "TLV")
                    storeManager.TLVStore.addVIPCustomer(customerType);
                else storeManager.HaifaStore.addVIPCustomer(customerType);
                break;
        }
        return customerType;
    }

    public static void addProducts(String[] input){
        if(input[1].equals("TLV"))
            storeManager.TLVStore.addProducts();
        else
            storeManager.HaifaStore.addProducts();
    }

    public static String[] getAction(String action){
        String[] allParameter= action.split(",");
        return allParameter;
    }

    public static Worker searchWorker(String name, String Password)
    {
        boolean isFound=false;
        Worker workerFound=null;
        for (Worker wo :TLVStore.getWorkerInBranch())
        {
            if(wo.getPassword()==Password && wo.getName() ==name)
            {
                workerFound = wo;
                isFound=true;
                break;
            }
        }
        if(!isFound)
        for (Worker wo : HaifaStore.getWorkerInBranch())
        {
            if(wo.getPassword()==Password && wo.getName() ==name)
            {
                workerFound = wo;
                break;
            }
        }
        return  workerFound;
    }


}
