package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modules.Node;
import util.FilePaths;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by extradikke on 16/04/15.
 */
public class GraphGenerator {
    private HashMap<String, Boolean> queriesMade;
    private ConcurrentLinkedQueue<Node> newNodes;
    private ArrayList<Node> readyNodes;
    private QueryExecutor queryExecutor;
    private Translator translator;
    private FileWriter fileWriter;


    public GraphGenerator() {
        queriesMade = new HashMap<String, Boolean>();
        newNodes = new ConcurrentLinkedQueue<Node>();
        readyNodes = new ArrayList<Node>();
        queryExecutor = new QueryExecutor();
        translator = new Translator();



    }

    public void start(String startingArticleName) {
        //TODO translate article name to int, execute query on startingArticle, make node, put it in newNodes
    }

    public void processAllNodes(int articleName, int maxDistance) {
        while (!newNodes.isEmpty()) {

            Node currentNode = newNodes.remove();
            ArrayList<String> links = currentNode.getLinkNames();


            for (String currentLink : links) {
                if (!queriesMade.containsKey(currentLink) && currentNode.getDistanceFromStart() < maxDistance) {
                    if (queryExecutor.query(translator.getNumberFromName(currentLink)) != 0) {
                        Node newNode = new Node(); // I guess here we input result from query?
                        newNodes.add(newNode);
                        currentNode.markAsProcessed(translator.getNameFromNumber(articleName), true);

                    } else {
                        currentNode.markAsProcessed(currentLink, true);
                    }
                }
            }
            readyNodes.add(currentNode);
        }
    }

    public void saveNodes() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        while (newNodes.isEmpty())
        {

            for (int i = 0; i < readyNodes.size(); i++)
            {
                Node currentNode = readyNodes.get(i);
                String nodeFileName = currentNode.getArticleId() + ".json";
                String finalNodePath = FilePaths.NODES_DIRECTORY + nodeFileName;
                String nodeAsJSON = gson.toJson(currentNode);
                try {
                    fileWriter = new FileWriter(finalNodePath);
                    fileWriter.write(nodeAsJSON);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
