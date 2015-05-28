package analysis;

import modules.*;
import org.joda.time.DateTime;
import util.*;

import java.util.ArrayList;

/**
 * Created by extradikke on 20/05/15.
 */
public class StatisticGenerator {

    public static void SaveStatisticsToExcel(String graphName, String mainArticle, DateTime startDate, DateTime endDate, int maxDistance) {

        ArrayList<StatisticsNode> statisticsNodes = generateStatistics(graphName, mainArticle, startDate, endDate, maxDistance);



        CorrelationExcel correlationExcel = new CorrelationExcel();
        correlationExcel.preGeneratedStatisticsNode(statisticsNodes, mainArticle);
        correlationExcel.createWorkBook();
        correlationExcel.writeExcel(mainArticle, startDate, endDate);

    }

    public static ArrayList<StatisticsNode> generateStatistics(String graphName, String mainArticle, DateTime startDate, DateTime endDate, int maxDistance){
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph(FilePaths.ARTICLE_JSONS + graphName + ".json");
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : graph.getNodes())

        {
            if (node.getDistanceFromStart() < maxDistance + 1) {
                nodes.add(node);
            }
        }
        CorrelationCounter correlationCounter = new CorrelationCounter();
        correlationCounter.countAverageViewCountsForEachNode(startDate, endDate, 1, nodes);
        correlationCounter.calculateCovarianceAndSTDForArticle(mainArticle, nodes);

        ArrayList<StatisticsNode> statisticsNodes = new ArrayList<>(correlationCounter.getStatisticsNodes().values());
        //        Collections.sort(statisticsNodes, new CorrelationSorter());
//        Collections.sort(statisticsNodes);
        StatisticsNode max = null;
        for (StatisticsNode statisticsNode : statisticsNodes) {
            if (statisticsNode.getCorrelation() == Double.NaN) {
                statisticsNode.setCorrelation(0);
            }
            if (max == null) {
                max = statisticsNode;
            }
            if (max.getCorrelation() < statisticsNode.getCorrelation()) {
                max = statisticsNode;
            }
            System.out.println(statisticsNode.getArticleName()+ ": " + statisticsNode.getCorrelation());

        }

        return statisticsNodes;
    }

    public static void StatisticsToD3JS(String graphName, String mainArticle, DateTime startDate, DateTime endDate, int maxDistance, double correlationCutOff, boolean preselectedArticles) {

        ArrayList<String> acceptedNames = JsonListLoader.load2("/media/extradikke/UbuntuData/wikipedia_data/clean jsons/Articlenames as JSON/" + mainArticle + ".json");
        ArrayList<StatisticsNode> statisticsNodes = generateStatistics(graphName, mainArticle, startDate, endDate, maxDistance);

        D3Wrapper d3Wrapper = new D3Wrapper();

        for (StatisticsNode statisticsNode : statisticsNodes) {
            if (statisticsNode.getCorrelation() >= correlationCutOff){
//            if (statisticsNode.getCorrelation() >= correlationCutOff && acceptedNames.contains(statisticsNode.getArticleName())){

                d3Wrapper.addNode(new D3jsNode(statisticsNode.getArticleName(), statisticsNode.getDistanceFromStart(), statisticsNode.getCorrelation()));
            }
        }

        d3Wrapper.mapLinks(mainArticle, statisticsNodes,getRegularNodes(graphName, maxDistance));
        D3JsNodeSaver.saveGraph("/media/extradikke/UbuntuData/wikipedia_data/d3js/", "ALL_NODES_"+graphName.replaceAll("\\s+", "") + "_Correlation_from_" + mainArticle + "_start:" + startDate.toLocalDate() + "_end:" + endDate.toLocalDate(), d3Wrapper);

    }

    public static ArrayList<Node> getRegularNodes(String graphName, int maxDistance){
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph(FilePaths.ARTICLE_JSONS + graphName + ".json");
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : graph.getNodes())

        {
            if (node.getDistanceFromStart() < maxDistance + 1) {
                nodes.add(node);
            }
        }
        return nodes;
    }

}
