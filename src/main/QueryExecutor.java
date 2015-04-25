package main;

import modules.QueryInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by extradikke on 17/04/15.
 */
public class QueryExecutor implements QueryInterface {

    private String locationOfGraph = "/media/extradikke/UbuntuData/wikipedia_data/data_dump/dataMaps/wiki_in_numbers.txt";
    private HashMap<Integer, int[]> pageToLinks;

    public QueryExecutor() {
        this.pageToLinks = new HashMap<>();
        long startTime = System.nanoTime();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(locationOfGraph))) {
            String currentLine;
            int index = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                index++;
                String[] tempPageAndLinks = currentLine.split("\\|");
                int[] pageAndLinks = new int[tempPageAndLinks.length];
                int arrayPosition = 0;
                for (String link : tempPageAndLinks) {

                    pageAndLinks[arrayPosition] = (Integer.valueOf(link.trim()));
                    arrayPosition++;
                }

                if (index % 100000 == 0) {
                    System.out.println(index);
                }
                Integer page = pageAndLinks[0];
                int[] links = null;
                if (pageAndLinks.length > 1) {
                    links = Arrays.copyOfRange(pageAndLinks, 1, pageAndLinks.length - 1);
                }
                pageToLinks.put(page, links);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime() - startTime) / 1000000000 + " second loading time");
//        int startPostion = 216620;
//        for (int i = startPostion; i < 20+startPostion; i++) {
//            int[] links = pageToLinks.get(i);
//            String result = i + ": ";
//            if (links != null) {
//                for (int j = 0; j < links.length-1; j++) {
//                    result += links[j] + ", ";
//                }
//
//            }
//            System.out.println(result);
//        }
    }

    @Override
    public int[] query(int articleId) {
        return pageToLinks.get(articleId);
    }
}
