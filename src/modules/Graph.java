package modules;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;

/**
 * Created by extradikke on 06/05/15.
 */
public class Graph {
    private ArrayList<Node> nodes;
    private String startingArticle;
    private String startDate;
    private DateTime jodaStartDate;
    private String endDate;
    private DateTime jodaEndDate;


    public Graph(ArrayList<Node> nodes, String startingArticle) {
        this.nodes = nodes;
        this.startingArticle = startingArticle;
    }

    public void nulllifyJoda() {
        this.jodaStartDate = null;
        this.jodaEndDate = null;
        for (Node node : nodes) {
            node.nullifyJoda();
        }

    }

    public void setupNodeJoda() {
        for (Node node : nodes) {
            node.setupJodaDateTime();
        }
    }

    public void setupDateTime() {
        if (this.startDate != null && this.endDate != null) {
            this.jodaStartDate = new DateTime(startDate);
            this.jodaEndDate = new DateTime(endDate);
            setupNodeJoda();
        }
    }

    public void setupViewCountMapping(DateTime startDate, DateTime endDate) {

        if (this.startDate == null && this.endDate == null) {
            this.startDate = startDate.toLocalDate().toString();
            this.endDate = endDate.toLocalDate().toString();
            for (Node node : nodes) {
                node.setupViewCount(startDate, endDate);
            }
            System.out.println(startingArticle + " all setup");
        } else {
            System.out.println("Already setup");
        }
    }

    public void countAverageViewCountsForEachNode(DateTime startPeriod, DateTime endPeriod, int maxLevel) {
        int nodesDone = 0;
        for (Node node : nodes) {
            if (node.getDistanceFromStart() <= maxLevel) {
                double viewCountsSum = 0;
                double viewCountsCalculated = 0;

                for (DateTime currentDate = startPeriod; !currentDate.isAfter(endPeriod); currentDate = currentDate.plusDays(1)) {
                    int viewcounts = node.getViewCountForDay(currentDate);
                    if (viewcounts != -1) {
                        viewCountsSum += viewcounts;
                        viewCountsCalculated++;
                    }

                }

                node.setMeanViewCountsForPeriod(viewCountsSum / viewCountsCalculated);
            } nodesDone++;
            System.out.println("Nodes done "+nodesDone);
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public DateTime getJodaEndDate() {
        return jodaEndDate;
    }

    public void setJodaEndDate(DateTime jodaEndDate) {
        this.jodaEndDate = jodaEndDate;
    }

    public DateTime getJodaStartDate() {
        return jodaStartDate;
    }

    public void setJodaStartDate(DateTime jodaStartDate) {
        this.jodaStartDate = jodaStartDate;
    }
}
