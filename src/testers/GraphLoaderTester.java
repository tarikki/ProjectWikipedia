package testers;

import modules.Graph;
import util.GraphIO;

/**
 * Created by extradikke on 06/05/15.
 */
public class GraphLoaderTester {

    public static void main(String[] args) {
        GraphIO graphIO = new GraphIO();
        Graph graph = graphIO.loadGraph("/media/extradikke/UbuntuData/wikipedia_data/testGraphsSaved/chibok schoolgirls kidnapping.json");
        System.out.println(graph.getNodes().get(0).getLinkNumbers());
    }
}
