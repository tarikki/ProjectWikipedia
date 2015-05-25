package main;

import modules.D3Wrapper;
import modules.D3jsNode;
import modules.Graph;
import modules.Node;
import org.joda.time.DateTime;
import util.D3JsNodeSaver;
import util.GraphIO;

import java.util.ArrayList;

/**
 * Created by extradikke on 17/05/15.
 */
public class GraphToD3JS {


    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/ebola virus epidemic in west africa.json");
//        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/2014 fifa world cup.json");
        System.out.println(graph.getNodes().get(0).getViewCountForDay(new DateTime(2014, 1, 2, 0, 0)));
        boolean done = false;
        ArrayList<Node> nodes = graph.getNodes();
        D3Wrapper d3Wrapper = new D3Wrapper();
        int counter = 0;
        for (Node node : nodes) {
            if (counter < nodes.size()+3) {
                if (node.getDistanceFromStart() < 2) {
//                    d3Wrapper.addNode(new D3jsNode(node.getArticleName(), node.getDistanceFromStart()));
                    counter++;
                }
            }
        }
//        d3Wrapper.mapLinks(nodes);
//        D3JsNodeSaver.saveGraph("//media/extradikke/UbuntuData/programming/HTML/WebstormProjects/D3js/tryouts/", "ebola", d3Wrapper);


    }
}


