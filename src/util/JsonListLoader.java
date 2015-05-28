package util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import jdk.nashorn.internal.parser.JSONParser;
import modules.Graph;
import sun.misc.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by extradikke on 26/05/15.
 */
public class JsonListLoader {
//    public static ArrayList<String> loadArticleNames(String filePath) {
//        JsonReader nameReader;
//        File dir = new File(filePath);
//        Gson gson = new Gson();
//        try {
//            nameReader = new JsonReader(new FileReader(filePath));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Not a valid file path!");
//        }
//
//        ArrayList = gson.fromJson(nameReader, Graph.class);
//
//
//        try {
//            nameReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        graph.setupDateTime();
//
//
//        return graph;
//    }

    public static ArrayList<String> load2(String filePath) {
        ArrayList<String> names = new ArrayList<>();
        try {

            FileReader file = new FileReader(filePath);

            BufferedReader buff = new BufferedReader(file);
            StringBuilder stringBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = buff.readLine()) != null) {
                stringBuilder.append(inputStr);

            }
            System.out.println(stringBuilder.toString());
            Gson gson = new Gson();
            names = gson.fromJson(stringBuilder.toString(), new TypeToken<List<String>>(){}.getType());
            for (String name : names) {
                System.out.println(name);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return names;
    }


}
