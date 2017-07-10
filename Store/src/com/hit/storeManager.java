package com.hit;

import com.hit.worker.Cashier;
import com.hit.worker.Seller;
import com.hit.worker.responsibleShift;
import com.hit.worker.Worker;

/**
 * Created by snir on 26/05/2017.
 */
public class storeManager
{
    public static Branch TLVStore=new Branch("TLV","03-2323232","ibn Gabirol 15");
    public static Branch HifaStore=new Branch("Haifa","02-2323232","Allenby");

    public static Branch getBranch(String branchName){
        if(TLVStore.getBranchName()==branchName)
            return TLVStore;
        else return HifaStore;

    }
    public static Worker createWorkerAndInsert(String[] input){
        Worker typeWorker=null;
        switch (input[1])
        {
            case "responsible Shift":
                typeWorker= new responsibleShift(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
            case "Seller":
                typeWorker= new Seller(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
            case "Cashier":
                typeWorker= new Cashier(input[2],input[3],input[4],input[5],input[6],input[7]);
                break;
        }
        if(typeWorker.getBranch()=="TLV")
            storeManager.TLVStore.addWorker(typeWorker);
        else storeManager.HifaStore.addWorker(typeWorker);
        return typeWorker;
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
        for (Worker wo :HifaStore.getWorkerInBranch())
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
