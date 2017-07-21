package com.hit;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import sun.util.resources.cldr.lv.TimeZoneNames_lv;

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
    String stringJson;
    private Product productA;
    private JSONObject json;
    private JsonFormat jsonFormat;// = new JsonFormat("C:\\dev\\java\\filename.txt");

    public Report(Branch branch)  {
        this.branch = branch;
    }

    public Report() {}

    /**
     * shows number of sales in the specific branch
     * @param branch the branch that we want to get its sales
     */
    public void getQuantityOfSales(String branch) {
        //JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
        //json = (JSONObject) parser.parse();
//        try{
//            json = new JSONObject("{\"quantity of sales\":\""+ branch.getQuantityOfSales() + "\"}");
//        } catch (Exception json){
//            System.out.println(json.getMessage());
//        }
        jsonFormat = new JsonFormat("C:\\dev\\java\\quantityOfSales.txt");
        if(branch == "TLV") {
            stringJson = jsonFormat.toJson(storeManager.TLVStore.getQuantityOfSales());
        }
        else{
            stringJson = jsonFormat.toJson(storeManager.HaifaStore.getQuantityOfSales());
        }
        jsonFormat.writeFile(stringJson);
    }

    /**
     * get report of a specific product
     * @param product the product we want to get its report
     */
    public void showReportOfProduct(String product){
        jsonFormat = new JsonFormat("C:\\dev\\java\\productsReport.txt");
        Product.productType enumProduct = Product.productType.valueOf(product);
        int salesOfProduct;
        System.out.println(enumProduct);
//        List<Product> products = branch.getProductsInInventory();
//        int ind = products.indexOf(product);
//        if(ind != -1) {
//            salesOfProduct = products.get(ind).numberOfSales.get(enumProduct); //number of sales of the product <enumProduct>
//        }
//        else{
//            salesOfProduct = 0;
//        }
        salesOfProduct = storeManager.TLVStore.getNumberOfSales().get(enumProduct);
        //String stringJson = jsonFormat.toJson(enumProduct + "" + salesOfProduct);
        stringJson = jsonFormat.toJson(storeManager.TLVStore.getNumberOfSales());
        System.out.println(stringJson);
        jsonFormat.writeFile(stringJson);
    }

    /**
     * shows the vip customers
     * @return list of the vip customer
     */
    public void getVipCustomers(String branch) {
        //String stringJson;
        jsonFormat = new JsonFormat("C:\\dev\\java\\vipReport.txt");

        if(branch.equals("TLV")) {
            stringJson = jsonFormat.toJson(storeManager.TLVStore.getVipCustomers());
        }
        else {
            stringJson = jsonFormat.toJson(storeManager.HaifaStore.getVipCustomers());
        }

        jsonFormat.writeFile(stringJson);
    }
}
