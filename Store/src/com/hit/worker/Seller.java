package com.hit.worker;

/**
 * Created by snir on 25/05/2017.
 */
public class Seller extends Worker
{
    String type;

    public String getType()
    {
        return type;
    }

    public Seller(String name, String id, String phone, String branchName, String numberAcount, String password)
    {
        super(name, id, phone, branchName, numberAcount, password);
        type= typeWorker.Seller.toString();
    }
}
