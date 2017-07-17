package com.hit;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonFormat {
    private Gson gson ;
    private PrintWriter out;

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

    public void writeFile(String report){
            out.println(report);
            out.flush();
            out.close();
    }
}
