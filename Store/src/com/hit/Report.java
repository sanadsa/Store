package com.hit;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * class that manage reports of the branch
 */
public class Report {
    private Branch branch;
    private Product product;
    private JSONParser parser;
    private JSONObject json;
    private Gson gson = new Gson();
    private PrintWriter out;

    public Report(Branch branch)  {
        this.branch = branch;
    }

    public Report() {}

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

        writeFile(json.toString());
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
        writeFile(stringJson);
    }

    /**
     * shows the vip customers
     * @return list of the vip customer
     */
    public void getVipCustomers( Branch branch) {
        String stringJson = gson.toJson(branch.getVipCustomers());
        System.out.println(stringJson);//send to file
        writeFile(stringJson);
    }

    public void writeFile(String report){
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\dev\\java\\filename.txt", true)));
            out.println(report);
            out.flush();
        } catch (IOException ex) {
            // report
        } finally {
            out.close();
        }
    }
}
