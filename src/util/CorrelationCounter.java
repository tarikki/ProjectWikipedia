package util;

import modules.Node;
import modules.StatisticsNode;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by extradikke on 20/05/15.
 */
public class CorrelationCounter {
    private HashMap<String, StatisticsNode> statisticsNodes = new HashMap<>();
    private DateTime startPeriod;
    private DateTime endPeriod;

    public void countAverageViewCountsForEachNode(DateTime startPeriod, DateTime endPeriod, int maxLevel, ArrayList<Node> nodes) {

        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;

        int nodesDone = 0;
        for (Node node : nodes) {

            double viewCountsSum = 0;
            double viewCountsCalculated = 0;

            for (DateTime currentDate = startPeriod; !currentDate.isAfter(endPeriod); currentDate = currentDate.plusDays(1)) {
                int viewcounts = node.getViewCountForDay(currentDate);
                if (viewcounts != -1) {
                    viewCountsSum += viewcounts;
                    viewCountsCalculated++;
                }

            }

            statisticsNodes.put(node.getArticleName(), new StatisticsNode(node.getArticleId(), node.getArticleName(), node.getDistanceFromStart(), (viewCountsSum / viewCountsCalculated)));


            nodesDone++;
            System.out.println("Nodes done " + nodesDone);
        }
    }

    public void calculateCovarianceAndSTDForArticle(String mainArticleName, ArrayList<Node> nodes) {
        Node mainNode = null;
        for (Node node : nodes) {
            if (node.getArticleName().equals(mainArticleName)) {
                mainNode = node;
            }
        }
        int numberOfDays = 0;

        for (DateTime currentDate = this.startPeriod; !currentDate.isAfter(endPeriod); currentDate = currentDate.plusDays(1)) {
            boolean nullDate = false;
            for (Node currentNode : nodes) {
                int mainNodeViewCount = mainNode.getViewCountForDay(currentDate);
                int currentNodeViewCount = currentNode.getViewCountForDay(currentDate);

                if (mainNodeViewCount > -1 && currentNodeViewCount > -1) {

                    double mainNodeDifference = mainNode.getViewCountForDay(currentDate) - statisticsNodes.get(mainArticleName).getMeanViewCountsForPeriod();
                    double currentNodeDifference = currentNode.getViewCountForDay(currentDate) - statisticsNodes.get(currentNode.getArticleName()).getMeanViewCountsForPeriod();
                    statisticsNodes.get(currentNode.getArticleName()).incrementPreCorrelationSum(mainNodeDifference * currentNodeDifference);
                    statisticsNodes.get(currentNode.getArticleName()).incrementSumOfSquares(Math.pow(currentNodeDifference, 2));
                    numberOfDays++;
                }

            }

        }

        for (StatisticsNode statisticsNode : statisticsNodes.values()) {
            statisticsNode.calculateCovariance(numberOfDays);
            statisticsNode.calculateSTD(numberOfDays);
        }

        for (StatisticsNode statisticsNode : statisticsNodes.values()) {
            statisticsNode.calculateCorrelation(statisticsNodes.get(mainArticleName).getSTD());
        }
    }

    public DateTime getEndPeriod() {
        return endPeriod;
    }

    public DateTime getStartPeriod() {
        return startPeriod;
    }

    public HashMap<String, StatisticsNode> getStatisticsNodes() {
        return statisticsNodes;
    }
}
