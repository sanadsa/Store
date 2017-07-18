package com.hit;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

/**
 * class that manage reports of the branch
 */
public class Report {
    private Branch branch;
    private Product productA;
    private JSONObject json;
    private JsonFormat jsonFormat = new JsonFormat("C:\\dev\\java\\filename.txt");

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
//        try{
//            json = new JSONObject("{\"quantity of sales\":\""+ branch.getQuantityOfSales() + "\"}");
//        } catch (Exception json){
//            System.out.println(json.getMessage());
//        }

        String toString = jsonFormat.toJson(branch.getQuantityOfSales());
        jsonFormat.writeFile(toString);

//        writeFile(json.toString());
//        System.out.println(json);//send to file...
    }

    /**
     * get report of a specific product
     * @param product the product we want to get its report
     */
    public void showReportOfProduct(String product){
        Product.productType enumProduct = Product.productType.valueOf(product);
        int salesOfProduct;
        System.out.println(enumProduct);
        if(productA.numberOfSales.get(enumProduct) != null) {
            salesOfProduct = productA.numberOfSales.get(enumProduct);
        }
        else{
            salesOfProduct = 0;
        }

        String stringJson = jsonFormat.toJson(enumProduct + "" + salesOfProduct);
        jsonFormat.writeFile(stringJson);
    }

    /**
     * shows the vip customers
     * @return list of the vip customer
     */
    public void getVipCustomers(Branch branch) {
        String stringJson = jsonFormat.toJson(branch.getVipCustomers());
        jsonFormat.writeFile(stringJson);
    }
}
