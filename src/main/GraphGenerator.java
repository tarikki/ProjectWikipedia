package main;

import modules.Node;
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

    public GraphGenerator() {
        queriesMade = new HashMap<String, Boolean>();
        newNodes = new ConcurrentLinkedQueue<Node>();
        readyNodes = new ArrayList<Node>();
        queryExecutor = new QueryExecutor();
        Translator translator = new Translator();



    }

    public void start(String startingArticleName) {
        //TODO translate article name to int, execute query on startingArticle, make node, put it in newNodes
    }

    public void processAllNodes() {
        while (!newNodes.isEmpty()) {
            //TODO do stuff here as described in specs file
        }
    }

    public void saveNodes() {
        //TODO take nodes out of readyNodes, get their names from the translator, save
    }
}
