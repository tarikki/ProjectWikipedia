package modules;

/**
 * Created by extradikke on 16/04/15.
 */
public class SimpleNode {

    private String articleName;
    private int numberOfLinks;
    private String[] linkNames;

    public SimpleNode(String articleName, String[] linkNames) {
        this.articleName = articleName;
        this.linkNames = linkNames;
        this.numberOfLinks = linkNames.length;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String[] getLinkNames() {
        return linkNames;
    }

    public void setLinkNames(String[] linkNames) {
        this.linkNames = linkNames;
    }

    public int getNumberOfLinks() {
        return numberOfLinks;
    }

    public void setNumberOfLinks(int numberOfLinks) {
        this.numberOfLinks = numberOfLinks;
    }
}
