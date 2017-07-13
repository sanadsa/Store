package com.hit;

/**
 * Created by snir on 25/05/2017.
 */
public abstract class Person
{
    private String Name;
    private String Id;
    private String Phone;

    public Person(String name, String id, String phone)
    {
        Name = name;
        Id = id;
        Phone = phone;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }


    public void setName(String name)
    {
        Name = name;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public void setPhone(String phone)
    {
        Phone = phone;
    }

    public String getName() {return Name;}

    public String getId()
    {
        return Id;
    }

    public String getPhone()
    {
        return Phone;
    }
}
