package testers;

import modules.Graph;
import org.joda.time.DateTime;
import util.GraphIO;

/**
 * Created by extradikke on 06/05/15.
 */
public class GraphLoaderTester {

    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/eurovision song contest 2014.json");
        System.out.println(graph.getNodes().get(0).getViewCountForDay(new DateTime(2014,1,2,0,0) ));
        System.out.println(graph.getNodes().get(0).getJodaEndDate());
    }
}
