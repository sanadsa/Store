package com.hit;

/**
 * Created by snir on 25/05/2017.
 */
public abstract class Person
{
    private String Name;
    private String Id;
    private String Phone;
    private String BranchName;

    public Person(String name, String id, String phone, String BranchName)
    {
        Name = name;
        Id = id;
        Phone = phone;
        this.BranchName = BranchName;
    }


    @Override
    public String toString()
    {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Phone='" + Phone + '\'' +
                ", BranchName='" + BranchName + '\'' +
                '}';
    }

    public String getBranch()
    {
        return BranchName;
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

    public String getName()
    {

        return Name;
    }

    public String getId()
    {
        return Id;
    }

    public String getPhone()
    {
        return Phone;
    }
}
