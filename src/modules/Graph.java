package modules;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by extradikke on 06/05/15.
 */
public class Graph {
    private ArrayList<Node> nodes;
    private String startingArticle;


    public Graph(ArrayList<Node> nodes, String startingArticle) {
        this.nodes = nodes;
        this.startingArticle = startingArticle;
    }

    public void setupNodeJoda(){
        for (Node node : nodes) {
            node.setupDateTime();
        }
    }



    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public String getStartingArticle() {
        return startingArticle;
    }

    public void setStartingArticle(String startingArticle) {
        this.startingArticle = startingArticle;
    }
}
