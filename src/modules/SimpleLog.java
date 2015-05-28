package modules;

import org.joda.time.DateTime;

/**
 * Created by extradikke on 28/05/15.
 */
public class SimpleLog {
    private String articleName;
    private String start;
    private String end;

    public SimpleLog(String articleName, String tuub, DateTime start, DateTime end ) {
        this.articleName = articleName;
        this.end = end.toLocalDate().toString();
        this.start = start.toLocalDate().toString();
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
