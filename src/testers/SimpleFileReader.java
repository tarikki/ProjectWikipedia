package testers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by extradikke on 25/04/15.
 */
public class SimpleFileReader {
    public static void main(String[] args) {
        String locationOfGraph = "/media/extradikke/UbuntuData/wikipedia_data/data_dump/dataMaps/wiki_in_numbers.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(locationOfGraph)))

        {
            String currentLine;
            int numberToCheck = 100;
            int currentNumber = 0;
            while ((currentLine= bufferedReader.readLine()) != null && currentNumber < numberToCheck){
                System.out.println(currentLine);
                currentNumber++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
