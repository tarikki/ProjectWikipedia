package util;

import analysis.StatisticGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modules.DataWrapper;
import modules.SimpleLog;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by extradikke on 28/05/15.
 */
public class LogFileCreator {

    public static void main(String[] args) {

        DataWrapper dataWrapper = new DataWrapper();


        dataWrapper.addArticle(new SimpleLog("2014 winter olympics", "2014 winter olympics", new DateTime(2014, 2, 1, 0, 0), new DateTime(2014, 3, 4, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("ebola virus epidemic in west africa", "ebola virus epidemic in west africa", new DateTime(2014, 9, 1, 0, 0), new DateTime(2014, 10, 31, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("2014 fifa world cup", "2014 fifa world cup", new DateTime(2014, 6, 1, 0, 0), new DateTime(2014, 7, 21, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("eurovision song contest 2014", "eurovision song contest 2014", new DateTime(2014, 5, 1, 0, 0), new DateTime(2014, 5, 17, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("malaysia airlines flight 370", "malaysia airlines flight 370", new DateTime(2014, 3, 8, 0, 0), new DateTime(2014, 4, 22, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("sinking of the mv sewol", "sinking of the mv sewol", new DateTime(2014, 4, 22, 0, 0), new DateTime(2014, 5, 25, 0, 0)));
        dataWrapper.addArticle(new SimpleLog("malaysia airlines flight 17", "malaysia airlines flight 17", new DateTime(2014, 7, 17, 0, 0), new DateTime(2014, 8, 31, 0, 0)));


        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        /// Create a folder for node
        File nodeDir = new File("/media/extradikke/UbuntuData/wikipedia_data/d3js/");


//            String nodeFileName = graph.getStartingArticle() + ".json";
        String finalNodePath = "log.json";
        String graphAsJSON = gson.toJson(dataWrapper);

        try {
            FileWriter fileWriter = new FileWriter(finalNodePath);
            fileWriter.write(graphAsJSON);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

