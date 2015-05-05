package util;

import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by extradikke on 05/05/15.
 */
public class ViewCountLoader {
    private String filePath;
    private File dir;
    private File[] files;

    public ViewCountLoader(String filePath) {
        this.filePath = filePath;
        dir = new File(filePath);
        files = dir.listFiles();
        for (File file : files) {
//            System.out.println(file.getName());
        }
    }

    public HashMap<String, String> loadDate(DateTime dateTime) {
        System.out.println(dateTime.toLocalDate());
        File rightFile = null;
        for (File file : files) {
            if (file.getName().equals("pagecounts-" + dateTime.toLocalDate() + ".txt")) {
                System.out.println("found it: " + file.getName() + ", size: " + file.length());
                rightFile = file;

            }

        }
        if (rightFile != null) mapViewCounts(rightFile);
        return null;
    }

    private HashMap<String, Integer> mapViewCounts(File file) {
        HashMap<String, Integer> viewCounts = new HashMap<>();
        long start = System.nanoTime();
        int outOfBoundsCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            for (String line; (line = br.readLine()) != null; ) {
                String[] elements = line.trim().split("\\s+");
                try {
                    int dailyCount = 0;
                    StringBuilder sb = new StringBuilder();
//                    System.out.println(line.trim());
                    if (elements.length == 3 && line.matches(".*\\d+")) {
                        String[] title = Arrays.copyOf(elements, elements.length - 2);
                        for (String s : title) {
                            sb.append(s);
                            sb.append(" ");
                        }
                        String finalTitle = sb.toString().trim().toLowerCase();
                        if (!finalTitle.equals("")) {
                            try{
                            dailyCount = Integer.valueOf(elements[elements.length - 2]);} catch (NumberFormatException e){
                                System.out.println(line);
                                dailyCount = 0;
                            }

                        }


                        if (!viewCounts.containsKey(finalTitle)) {
                            viewCounts.put(finalTitle, dailyCount);
                        } else {
                            viewCounts.put(finalTitle, viewCounts.get(finalTitle)+dailyCount);

//                            System.out.println("Duplicate: " + line);
//                            System.out.println(finalTitle + ", views: " + viewCounts.get(finalTitle));
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    outOfBoundsCounter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(viewCounts.get("ace of spades"));
        System.out.println(viewCounts.get("ace_of_spades"));
        System.out.println("done in " + String.valueOf((System.nanoTime() - start) / 1000000000));
        return viewCounts;
    }

    private HashMap<String, String> countViewCounts(File file) {
        HashMap<String, Integer> viewCounts = new HashMap<>();
        long start = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int counter = 0;
            for (String line; (line = br.readLine()) != null; ) {
                String[] elements = line.split("\\s+");
                try {
                    if (!viewCounts.containsKey(elements[0])) {
                        viewCounts.put(elements[0], 1);
                    } else {
                        viewCounts.put(elements[0], viewCounts.get(elements[0]) + 1);
                    }
                    if (elements[0].equals("The")) {
                        System.out.println(line);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done in " + String.valueOf((System.nanoTime() - start) / 1000000000));
        System.out.println(viewCounts.size());
        int ones = 0;
        int zeros = 0;
        int twos = 0;
        int maxCount = 0;
        int more = 0;
        String maxArticle = "";
        for (String name : viewCounts.keySet()) {
            int count = viewCounts.get(name);

            if (maxCount < count) {
                maxCount = count;
                maxArticle = name;
            }
            if (count > 2) {
                more++;
            } else if (count == 2) {
                twos++;
            } else if (count == 1) {
                ones++;
            } else {
                zeros++;
            }

        }

        System.out.println(String.format("Zeros: (%d), Ones: (%d), Twos:(%d), Mores: (%d), Max: (%d), MaxArticle: (%s)", zeros, ones, twos, more, maxCount, maxArticle));
        System.out.println();
        return null;
    }
}
