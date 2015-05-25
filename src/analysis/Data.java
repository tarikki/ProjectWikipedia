package analysis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by HP on 22.5.2015.
 */
public class Data {
    public static HashMap<Integer, ArrayList<Integer>> map=new HashMap<Integer, ArrayList<Integer>>();
    private static ArrayList<Integer> keyList= new ArrayList<Integer>();


    /**
     * Use a BufferedReader to read the file line-by-line using readLine(). Then
     * split each line on whitespace using String.split("\\s") and the size of
     * the resulting array is the total number count.
     */

    public Data (File file) {

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null){
                ArrayList<Integer> values= new ArrayList<Integer>();
                int key=0;
                assert line != null;
                String[] parts = line.split(Pattern.quote("|"));

                for (int i=0;i<parts.length;i++) {
                    int partsValue= Integer.parseInt(parts[i]);

                    if (i==0){
                        key=partsValue;
                        keyList.add(key);
                    }else {
                        values.add(partsValue);
                    }

                }

                map.put(key,values);
                line = br.readLine();
            }

            System.out.println(map.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
