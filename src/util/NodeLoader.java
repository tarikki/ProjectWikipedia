package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import modules.Node;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import java.util.Date;


/**
 * Created by Pepe on 4.5.2015.
 */
public class NodeLoader {

    private JsonReader nodeReader;
    private Gson gson;
    private File dir;
    private ArrayList<Node> nodes;


    public ArrayList<Node> loadAllNodes(String dirPath) {

        long start = System.nanoTime();
    int nodeCounter = 0;

    nodes = new ArrayList<>();
    dir = new File(dirPath);
    gson = new Gson();

    for (File file : dir.listFiles())
    {

        try {
            nodeReader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not a valid file path!");
        }

        Node node = gson.fromJson(nodeReader, Node.class);
        nodeCounter++;
        System.out.println("Processing node #: " + nodeCounter);
        nodes.add(node);

        try {
            nodeReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     long end = System.nanoTime() - start;

    System.out.println("Processed " + nodes.size() + " nodes " + "in " + TimeUnit.SECONDS.convert(end, TimeUnit.NANOSECONDS) + " seconds");


        return nodes;
    }

    public void saveNodes(String saveDirectory, ArrayList<Node> nodes, String startingArticle) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String timestamp = dateFormat.format(date);


        String nodeFolder = FilePaths.NODES_DIRECTORY + startingArticle + " " + timestamp + FilePaths.osPathCorrection();

        /// Create a folder for node
        File nodeDir = new File(nodeFolder);

        if (!nodeDir.exists()) {
            nodeDir.mkdir();
        }

        int edges = 0;
        System.out.println(startingArticle);
        System.out.println("Nodes to save: " + nodes.size());
        for (Node currentNode : nodes) {
            String nodeFileName = currentNode.getArticleId() + ".json";
            String finalNodePath = nodeFolder + nodeFileName;
            String nodeAsJSON = gson.toJson(currentNode);

            try {
                FileWriter fileWriter = new FileWriter(finalNodePath);
                fileWriter.write(nodeAsJSON);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Number of Edges: " + edges);

    }

}
