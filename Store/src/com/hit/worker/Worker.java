package com.hit.worker;

import com.hit.Person;

public abstract class Worker extends Person
{
    private String numberAcount;
    private String password;
	 private String melak;

    public String getNumberAcount()
    {
        return numberAcount;
    }

    public String getPassword()
    {
        return password;
    }

    public Worker(String name, String id, String phone,  String numberAcount, String password)
    {
        super(name, id, phone);
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
