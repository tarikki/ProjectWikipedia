package testers;

import modules.Node;
import org.joda.time.DateTime;
import util.NodeLoader;
import util.ViewCountLoader;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by extradikke on 05/05/15.
 */
public class ViewCountLoaderTester {
    public static void main(String[] args) {
        ViewCountLoader viewCountLoader = new ViewCountLoader("/media/extradikke/BigStorage/wiki_project/view_counts");
        DateTime dateTime = new DateTime(2015, 2, 8, 0, 0);
        HashMap<String, Integer> viewCounts = viewCountLoader.loadDate(dateTime);
        long start = System.nanoTime();
//        String filepath = "/media/extradikke/UbuntuData/wikipedia_data/finalGraphs/2014 Winter Olympics 04-05-2015 15-04-39/";
//        String filepath = "/media/extradikke/UbuntuData/wikipedia_data/finalGraphs/Chibok schoolgirls kidnapping 04-05-2015 15-18-21/";
//        String filepath = "/media/extradikke/FastFiles/2014 FIFA World Cup 04-05-2015 15-05-26/";
        NodeLoader nodeLoader = new NodeLoader();
//        ArrayList<Node> nodes = nodeLoader.loadAllNodes(filepath);
//        System.out.println(nodes.size());
        System.out.println("done in " + String.valueOf((System.nanoTime() - start) / 1000000000));
        int count = 0;
//        for (Node node : nodes) {
//            if (viewCounts.containsKey(node.getArticleName())){
//                System.out.println(node.getArticleName()+", "+viewCounts.get(node.getArticleName()));
//                count++;
//            }
//        }
        System.out.println(count);
    }
}
