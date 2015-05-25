package analysis;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Klara on 23.5.2015.
 */
public class Calculations {
//    public Calculations(){
//
//    }
    public double getSetOfNumberCount(HashMap map) {
        double count=0;
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
          //  Integer key = (Integer)entry.getKey();
            ArrayList<Integer> value = (ArrayList<Integer>)entry.getValue();
            count=count+value.size();
           // System.out.println("Key = " + key + ", Value = " + value);
//            iterator.remove();
        }

        return count;
    }

    public double getLineCount(HashMap map) {
        return (double)map.size();
    }

    public double getAverage(HashMap map) {
        System.out.println(map.size());
        return (getSetOfNumberCount(map)-getLineCount(map))/getLineCount(map);


    }
}
