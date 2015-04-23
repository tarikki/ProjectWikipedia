package modules;

/**
 * Created by extradikke on 16/04/15.
 */

import java.util.ArrayList;

/**
 * The node that will be used for processing, not the one that is saved as JSON
 * <p/>
 * private int numberOfLinks: number of linkNames on the page
 * private String[] linkNames: all the linkNames on the page
 * private boolean[] linkHealth: whether the link is found or not, used mainly for debugging
 */
public class Node {
    private String articleName;
    private int articleId;
    private int distanceFromStart;
    private ArrayList<String> linkNames;
    private boolean[] linkHealth;


    public int getArticleId() {
        return articleId;
    }

    public Node(int articleId, ArrayList<String> linkNames, int distanceFromStart) {
        this.articleId = articleId;
        this.linkNames = linkNames;
        this.distanceFromStart = distanceFromStart;
        this.linkHealth = new boolean[linkNames.size()];
        this.linkHealth = new boolean[linkNames.size()];


    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void markAsProcessed(String link, boolean health) {
        //TODO link to error log
        //TODO check if link in arrayList
        int position = linkNames.indexOf(link);
        linkHealth[position] = health;
        //TODO throw error if linksProcessed > numberOfLinks
    }

    public void nameArticle(String articleName){
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

    public int getNumberOfLinks() {
        return linkNames.size();
    }

    public boolean[] getLinkHealth() {
        return linkHealth;
    }

    public void setLinkHealth(boolean[] linkHealth) {
        this.linkHealth = linkHealth;
    }
}
