package modules;

/**
 * Created by extradikke on 16/04/15.
 */

import main.Translator;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;

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
    private double meanViewCountThisYear;
    private String startDate;
    private DateTime jodaStartDate;
    private String endDate;
    private DateTime jodaEndDate;
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

    public void nullifyJoda(){
        this.jodaStartDate = null;
        this.jodaEndDate = null;
    }


    public int getArticleId() {
        return articleId;
    }

    public void setupViewCount(DateTime startDate, DateTime endDate) {
        this.jodaStartDate = startDate;
        this.jodaEndDate = endDate;
        if (this.startDate == null && this.endDate == null) {
            this.startDate = startDate.toLocalDate().toString();
            this.endDate = endDate.toLocalDate().toString();
            int duration = Days.daysBetween(startDate, endDate).getDays() + 1;
            this.viewcounts = new int[duration];
        } else {
            System.out.println("Already setup");
        }
    }

    public void setupJodaDateTime(){
        if (this.startDate != null && this.endDate != null) {
        this.jodaStartDate = new DateTime(this.startDate);
        this.jodaEndDate = new DateTime(this.endDate);}
    }

    public void resetViewCount(DateTime startDate, DateTime endDate) {
        this.startDate = startDate.toLocalDate().toString();
        this.endDate = endDate.toLocalDate().toString();
        int duration = Days.daysBetween(startDate, endDate).getDays() + 1;
        this.viewcounts = new int[duration];
    }

    public void setViewCountForDay(int viewcount, DateTime dateTime) {
        //TODO check date ranges
        int index = Days.daysBetween(jodaStartDate, dateTime).getDays();
        this.viewcounts[index] = viewcount;
    }

    public int getViewCountForDay(DateTime dateTime){
        int index = Days.daysBetween(jodaStartDate, dateTime).getDays();
        return this.viewcounts[index];      //This might throw a nullPointerException
    }

    public void calculateMeanViewCount() {
        int sum = 0;
        int counter = 0;
        for (int viewcount : viewcounts) {
            if (viewcount != -1) {
                sum += viewcount;
                counter++;
            }
        }

        this.meanViewCountThisYear = sum / counter;
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

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int[] getViewcounts() {
        return viewcounts;
    }

    public void setViewcounts(int[] viewcounts) {
        this.viewcounts = viewcounts;
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

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
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
