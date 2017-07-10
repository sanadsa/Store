package com.hit.worker;

import com.hit.Person;

/**
 * Created by snir on 25/05/2017.
 */
public abstract class Worker extends Person
{
    private String numberAcount;
    private String password;

    public String getNumberAcount()
    {
        return numberAcount;
    }

    public String getPassword()
    {
        return password;
    }

    public Worker(String name, String id, String phone, String inventory, String numberAcount, String password)
    {

        super(name, id, phone, inventory);
        this.numberAcount = numberAcount;
        this.password=password;
    }

    @Override
    public String toString()
    {
        return super.toString() +
                "numberAcount='" + numberAcount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
