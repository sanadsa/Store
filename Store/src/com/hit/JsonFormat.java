package com.hit;

import com.google.gson.Gson;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.*;

public class JsonFormat {
    private Gson gson ;
    private PrintWriter out;
    private JSONParser parser;// = new JSONParser("", Global, true);

    /**
     * ctor of json format
     * @param path the path of the file we want to put the json format in
     */
    public JsonFormat(String path) {
        this.gson = new Gson();
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        }catch (IOException ex)
        {}
    }

    public String toJson(Object input)
    {
        return gson.toJson(input);
    }

    public void fromJson(String path){
        try {
//            Object obj = parser.parse(new FileReader(path));
//            JSONObject jsonObject = (JSONObject) obj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String report){
            out.println(report);
            out.flush();
            out.close();
    }
}
