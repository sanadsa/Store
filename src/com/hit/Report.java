package com.hit;

import com.google.gson.Gson;
import com.hit.customer.VIPCustomer;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.util.List;

/**
 * class that manage reports of the branch
 */
public class Report {
    private Branch branch;
    private List<VIPCustomer> vipCustomers;
    private Product product;
    private JSONParser parser;
    private JSONObject json;
    private Gson gson = new Gson();

    public Report(Branch branch) {
        this.branch = branch;
    }

    public Report(){}

    /**
     * shows number of sales in the specific branch
     * @param branch the branch that we want to get its sales
     */
    public void getQuantityOfSales(Branch branch) {
        //JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
        //json = (JSONObject) parser.parse();
        try{
            json = new JSONObject("{\"quantity of sales\":\""+ branch.getQuantityOfSales() + "\"}");
        } catch (Exception json){
            System.out.println(json.getMessage());
        }

        System.out.println(json);//send to file...
    }

    /**
     * get report of a specific product
     * @param product the product we want to get its report
     */
    public void showReportOfProduct(Product product){
        //convert java object to JSON format
        String stringJson = gson.toJson(product);

        System.out.println(stringJson);//send to file...
    }

    /**
     * shows the vip customers
     * @return list of the vip com.hit.customer
     */
    public void getVipCustomers(VIPCustomer vip) {
        String stringJson = gson.toJson(vip.getVipCustomers());
        System.out.println(stringJson);//send to file
    }
}
