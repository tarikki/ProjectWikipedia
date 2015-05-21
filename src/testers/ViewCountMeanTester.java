package testers;

import modules.Graph;
import modules.Node;
import modules.StatisticsNode;
import org.joda.time.DateTime;
import util.CorrelationCounter;
import util.CorrelationSorter;
import util.GraphIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by extradikke on 20/05/15.
 */
public class ViewCountMeanTester {

    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/ebola virus epidemic in west africa.json");
//        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/2014 fifa world cup.json");

        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            if (node.getDistanceFromStart() <2){
                nodes.add(node);}
        }
        CorrelationCounter correlationCounter = new CorrelationCounter();
        correlationCounter.countAverageViewCountsForEachNode(new DateTime(2014, 10, 2, 0, 0), new DateTime(2014, 11, 6, 0, 0), 1, nodes);


        correlationCounter.calculateCovarianceAndSTDForArticle("ebolavirus", nodes);

        ArrayList<StatisticsNode> statisticsNodes = new ArrayList<>(correlationCounter.getStatisticsNodes().values());
//        Collections.sort(statisticsNodes, new CorrelationSorter());
//        Collections.sort(statisticsNodes);
        StatisticsNode max = null;
        for (StatisticsNode statisticsNode: statisticsNodes) {
            if (max == null){
                max = statisticsNode;
            }
            if (max.getCorrelation() < statisticsNode.getCorrelation()){
                max = statisticsNode;
            }
            System.out.println(statisticsNode.getArticleName() + ": "+statisticsNode.getCorrelation());
        }
        System.out.println(max.getArticleName() + ": "+max.getCorrelation());
        System.out.println(correlationCounter.getStatisticsNodes().get("zmapp").getCorrelation());




    }
}
