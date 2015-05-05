package modules;

/**
 * Created by extradikke on 16/04/15.
 */

import main.Translator;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The node that will be used for processing, not the one that is saved as JSON
 * <p>
 * private int numberOfLinks: number of linkNames on the page
 * private String[] linkNames: all the linkNames on the page
 * private boolean[] linkHealth: whether the link is found or not, used mainly for debugging
 */
public class Node {
    private String articleName;
    private int articleId;
    private int distanceFromStart;
    private DateTime startDate;
    private DateTime endDate;
    private int[] viewcounts;
    private ArrayList<String> linkNames = new ArrayList<>();
    private ArrayList<Integer> linkNumbers = new ArrayList<>();


    public Node(int articleId, int[] linkNumbers, int distanceFromStart) {
        this.articleId = articleId;
        if (linkNumbers != null) {
            for (int linkNumber : linkNumbers) {
                this.linkNumbers.add(linkNumber);
            }
        } else {
            this.linkNumbers = null;
        }
        this.distanceFromStart = distanceFromStart;
    }


    public int getArticleId() {
        return articleId;
    }

    public void setupViewCount(DateTime startDate, DateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
        int duration = Days.daysBetween(startDate, endDate).getDays()+1;
        this.viewcounts = new int[duration];


    }

    public void setViewcount(int viewcount, DateTime dateTime){
        //TODO check date ranges
        int index = Days.daysBetween(startDate, dateTime).getDays();
        this.viewcounts[index] = viewcount;
    }
    public DateTime getEndDate() {
        return endDate;
    }


    public DateTime getStartDate() {
        return startDate;
    }


    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void translateAllNumbersToNames(Translator translator) {
        articleName = translator.getNameFromNumber(articleId);
        if (linkNumbers != null) {
            for (Integer linkNumber : linkNumbers) {
                linkNames.add(translator.getNameFromNumber(linkNumber));
            }
        }
    }

    public void nameArticle(String articleName) {
        this.articleName = articleName;
    }

    public String[] linksToArray() {
        return linkNames.toArray(new String[linkNames.size()]);
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public ArrayList<String> getLinkNames() {
        return linkNames;
    }

    public void setLinkNames(ArrayList<String> linkNames) {
        this.linkNames = linkNames;
    }

    public ArrayList<Integer> getLinkNumbers() {
        return linkNumbers;
    }

    public void setLinkNumbers(ArrayList<Integer> linkNumbers) {
        this.linkNumbers = linkNumbers;
    }

    public int getNumberOfLinks() {
        if (linkNumbers != null) {
            return linkNumbers.size();
        } else {
            return 0;
        }
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }


    @Override
    public String toString() {
        return "Node{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                ", distanceFromStart=" + distanceFromStart +
                ", linksSize=" + getNumberOfLinks() +
                '}';
    }
}
