package modules;

import java.util.ArrayList;

/**
 * Created by extradikke on 28/05/15.
 */
public class DataWrapper {
    private ArrayList<SimpleLog> simpleLogs = new ArrayList<>();

    public ArrayList<SimpleLog> getSimpleLogs() {
        return simpleLogs;
    }

    public void setSimpleLogs(ArrayList<SimpleLog> simpleLogs) {
        this.simpleLogs = simpleLogs;
    }

    public void addArticle(SimpleLog simpleLog){
        this.simpleLogs.add(simpleLog);
    }
}
