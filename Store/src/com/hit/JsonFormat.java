package com.hit;

import jdk.nashorn.internal.objects.Global;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import com.hit.worker.Seller;
import com.hit.worker.Worker;
import org.json.JSONArray;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

public class JsonFormat {
    private Gson gson ;
    private PrintWriter out;
    private JSONParser parser = new JSONParser();
    private JSONObject mainJson;
    private JSONArray jsonArray;
    private String jsonPath;

    /**
     * ctor of json format
     * @param path the path of the file we want to put the json format in
     */
    public JsonFormat(String path) {
        this.gson = new Gson();
        mainJson = new JSONObject();
        jsonArray = new JSONArray();
        jsonPath = path;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void toJson(Worker input)
    {
        JSONObject tempJson= new JSONObject(input);
        fromJson();
        jsonArray.put(tempJson);
        try
        {
            mainJson.put("employee", jsonArray);
            out = new PrintWriter(new BufferedWriter(new FileWriter(jsonPath,false)));
            out.print("");
            out.println(mainJson);
            out.flush();
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String toJsonObject(Object input)
    {
        return gson.toJson(input);
    }


    public void fromJson()
    {
        org.json.simple.JSONObject jsonObject;
        try
        {
            storeManager.HaifaStore.getWorkerInBranch().clear();
            storeManager.TLVStore.getWorkerInBranch().clear();
            jsonObject = (org.json.simple.JSONObject) parser.parse(new FileReader(jsonPath));
            String s= jsonObject.toString();
            mainJson =new JSONObject(s);
            jsonArray = mainJson.getJSONArray("employee");
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject temp = jsonArray.getJSONObject(i);
                String[] allProperties = new String[8];
                allProperties[2] = (String) temp.get("name");
                allProperties[3] = (String) temp.get("id");
                allProperties[4] = (String) temp.get("phone");
                allProperties[6]  = (String) temp.get("numberAcount");
                allProperties[7]  = (String) temp.get("password");
                allProperties[5] = (String) temp.get("branch");
                allProperties[1] = (String) temp.get("type");
                storeManager.createWorkerAndInsert(allProperties);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String[] fromFile(String path)
    {
        org.json.simple.JSONObject jsonObject;
        String[] array = null;
        try
        {
            jsonObject = (org.json.simple.JSONObject) parser.parse(new FileReader(path));
            String s = jsonObject.toString();
            mainJson =new JSONObject(s);


        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return array;
    }

    public void writeFile(String report){
        out.println(report);
        out.flush();
        //out.close();
    }
}
