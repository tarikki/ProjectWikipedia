package testers;

import modules.Graph;
import modules.Node;
import org.joda.time.DateTime;
import util.GraphIO;

import java.util.ArrayList;

/**
 * Created by extradikke on 06/05/15.
 */
public class GraphLoaderTester {

    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
//        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/ebola virus epidemic in west africa.json");
        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/2014 fifa world cup.json");
        System.out.println(graph.getNodes().get(2).getViewCountForDay(new DateTime(2014, 1, 2, 0, 0)));
//        boolean done = false;
//        ArrayList<Node> nodes = graph.getNodes();
//        Node node = null;
//        String articleName = "ebolavirus";
//        articleName = articleName.replace("_", " ").toLowerCase();
//        for (Node node1 : nodes) {
//            if (node1.getArticleName().equals(articleName)){
//                node = node1;
//            }
//        }
//        System.out.println(node.getArticleName());
//        DateTime dateTime= node.getJodaStartDate();
//        while (!done){
//
//            System.out.println(dateTime.toLocalDate() + " "+node.getViewCountForDay(dateTime));
//            dateTime = dateTime.plusDays(1);
//            if (dateTime.isAfter(node.getJodaEndDate())){
//                done = true;
//            }
//        }

    }
}
