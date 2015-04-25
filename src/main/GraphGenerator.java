package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modules.Node;
import util.FilePaths;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by extradikke on 16/04/15.
 */
public class GraphGenerator {
    private HashSet<Integer> queriesMade;
    private ConcurrentLinkedQueue<Node> newNodes;
    private ArrayList<Node> readyNodes;
    private QueryExecutor queryExecutor;
    private Translator translator;
    private FileWriter fileWriter;
    private int maxDistance;


    public GraphGenerator(int maxDistance) {
        this.maxDistance = maxDistance;
        queriesMade = new HashSet<>();
        newNodes = new ConcurrentLinkedQueue<Node>();
        readyNodes = new ArrayList<Node>();
        queryExecutor = new QueryExecutor();
        translator = new Translator();


    }

    public void start(String startingArticleName) {
        //TODO translate article name to int, execute query on startingArticle, make node, put it in newNodes
        int articleNumber = translator.getNumberFromName(startingArticleName);
        int[] links = queryExecutor.query(articleNumber);
        Node node = new Node(articleNumber, links, 0);
        newNodes.add(node);

    }

    public void processAllNodes() {
        while (!newNodes.isEmpty()) {

            Node currentNode = newNodes.poll();
            ArrayList<Integer> links = currentNode.getLinkNumbers();
            for (int currentLink : links) {
                if (currentLink != 0){
                if (!queriesMade.contains(currentLink) && currentNode.getDistanceFromStart() + 1 <= maxDistance) {
                    System.out.println("Querying page: " + translator.getNameFromNumber(currentLink));
                    int[] newLinks = queryExecutor.query(currentLink);
                    Node newNode = new Node(currentLink, newLinks, currentNode.getDistanceFromStart() + 1); // I guess here we input result from query?
                    newNodes.add(newNode);
                }}
            }
            readyNodes.add(currentNode);
        }

    }


    public void saveNodes() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        for (Node currentNode : readyNodes) {
            currentNode.translateAllNumbersToNames(translator);
            String nodeFileName = currentNode.getArticleId() + ".json";
            String finalNodePath = FilePaths.NODES_DIRECTORY + nodeFileName;
            String nodeAsJSON = gson.toJson(currentNode);
            //TODO something goes wrong here
            if (nodeAsJSON.length() < 10){
                System.out.println(currentNode);
            }
            try {
                fileWriter = new FileWriter(finalNodePath);
                //TODO or here
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

