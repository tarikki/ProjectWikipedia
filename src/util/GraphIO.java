package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import modules.Graph;
import modules.Node;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by extradikke on 06/05/15.
 */
public class GraphIO {

    private JsonReader GraphReader;
    private Gson gson;
    private File dir;
    private ArrayList<Node> nodes;


    public Graph loadGraph(String filePath) {

        dir = new File(filePath);
        gson = new Gson();
        try {
            GraphReader = new JsonReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not a valid file path!");
        }

        Graph graph = gson.fromJson(GraphReader, Graph.class);


        try {
            GraphReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }




    return graph;
}

    public void saveGraph(String saveDirectory, Graph graph) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();



        /// Create a folder for node
        File nodeDir = new File(saveDirectory);

        if (!nodeDir.exists()) {
            nodeDir.mkdir();
        }

//            String nodeFileName = graph.getStartingArticle() + ".json";
            String finalNodePath = saveDirectory + graph.getStartingArticle()+".json";
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
