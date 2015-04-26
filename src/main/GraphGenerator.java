package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modules.Node;
import util.FilePaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
    private String startingArticle;


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
        this.startingArticle = startingArticleName;
        int articleNumber = translator.getNumberFromName(startingArticleName);
        int[] links = queryExecutor.query(articleNumber);
        Node node = new Node(articleNumber, links, 0);
        queriesMade.add(articleNumber);
        newNodes.add(node);

    }

    public void processAllNodes() {
        while (!newNodes.isEmpty()) {

            Node currentNode = newNodes.poll();
            ArrayList<Integer> links = currentNode.getLinkNumbers();
            if (links != null) {
                for (int currentLink : links) {
                    if (currentLink != 0) {
                        if (!queriesMade.contains(currentLink) && currentNode.getDistanceFromStart() + 1 <= maxDistance) {
                            queriesMade.add(currentLink);
                            System.out.println("Querying page: " + translator.getNameFromNumber(currentLink));
                            int[] newLinks = queryExecutor.query(currentLink);
                            Node newNode = new Node(currentLink, newLinks, currentNode.getDistanceFromStart() + 1);
                            newNodes.add(newNode);
                        }
                    }
                }
            }
            readyNodes.add(currentNode);
        }

    }


    public void saveNodes() {

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
        System.out.println("Nodes to save: " + readyNodes.size());
        for (Node currentNode : readyNodes) {
            edges += currentNode.getNumberOfLinks();
            currentNode.translateAllNumbersToNames(translator);
            String nodeFileName = currentNode.getArticleId() + ".json";
            String finalNodePath = nodeFolder + nodeFileName;
            String nodeAsJSON = gson.toJson(currentNode);

            try {
                fileWriter = new FileWriter(finalNodePath);
                fileWriter.write(nodeAsJSON);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Number of Edges: " + edges);

    }
}


