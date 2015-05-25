package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;


public class Test {
public static Data map;
    public static void main(String[] args) {
        File file = new File("/media/extradikke/UbuntuData/wikipedia_data/wikidump_processed/wiki_in_numbers.txt");

        if (file.exists()) {
            map= new Data(file);
            Calculations calculations = new Calculations();
            System.out.println("Total Lines: " + calculations.getLineCount(map.map));
            System.out.println("Total numbers: " + calculations.getSetOfNumberCount(map.map));

            System.out.println("Average: " + calculations.getAverage(map.map));


        } else {
            System.out.println("File does not exists!");
        }

    }







}



