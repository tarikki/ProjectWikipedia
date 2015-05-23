package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modules.D3Wrapper;
import modules.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3JsNodeSaver {


    public static void saveGraph(String saveDirectory, String graphName, D3Wrapper graph) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();



        /// Create a folder for node
        File nodeDir = new File(saveDirectory);

        if (!nodeDir.exists()) {
            nodeDir.mkdir();
        }

//            String nodeFileName = graph.getStartingArticle() + ".json";
        String finalNodePath = saveDirectory + graphName + ".json";
        String graphAsJSON = gson.toJson(graph);

        try {
            FileWriter fileWriter = new FileWriter(finalNodePath);
            fileWriter.write(graphAsJSON);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
