package main;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by extradikke on 20/04/15.
 */
public class Translator {

    private BiMap<String, Integer> articleName_Number;
    private BiMap<Integer, String> number_Name;
    private String articleMappingLocation = "/media/extradikke/UbuntuData/wikipedia_data/data_dump/dataMaps/article_name_to_number.txt";


    public Translator() {
        articleName_Number = HashBiMap.create();
        populateMaps();


    }

    public void populateMaps() {

        System.out.println("Starting to load mappings");
        long startTime = System.nanoTime();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(articleMappingLocation))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] keyValue = currentLine.split("\\|");
//                System.out.println(keyValue[0] + " " + keyValue[1] + " " + Integer.valueOf(keyValue[1].trim()));
                if (keyValue[0] != null){
                articleName_Number.put(keyValue[0], Integer.valueOf(keyValue[1].trim()));}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        number_Name = articleName_Number.inverse();
        long elapsedTime = System.nanoTime() - startTime;
        String message = "Done loading mappings in " + String.valueOf(elapsedTime/1000000000) + " seconds.";
        System.out.println(message);
    }

    public int getNumberFromName(String name) {
        return articleName_Number.get(name.toLowerCase());
    }

    public String getNameFromNumber(int number) {
        return number_Name.get(number);
    }
}
