package main;

import modules.Graph;
import modules.Node;
import util.GraphIO;
import util.NodeLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by extradikke on 06/05/15.
 */
public class NodesToGraphs {
    public static void main(String[] args) {
        long start = System.nanoTime();
        NodeLoader nodeLoader = new NodeLoader();
        GraphIO graphIO = new GraphIO();
        File graphsDirectory = new File("/media/extradikke/UbuntuData/wikipedia_data/finalGraphs/");
        File[] directories= graphsDirectory.listFiles();
        for (File directory : directories) {
            try{
                System.out.println(directory.getCanonicalPath());
                String name = directory.getName();
                String[] elements = name.trim().split("\\s+");
                StringBuilder sb = new StringBuilder();
                    String[] title = Arrays.copyOfRange(elements, 0, elements.length - 2);
                    for (String s : title) {
                        sb.append(s);
                        sb.append(" ");
                    }
                    String finalArticleName = sb.toString().trim().toLowerCase();
                System.out.println(finalArticleName);
                ArrayList<Node> nodes = nodeLoader.loadAllNodes(directory.getCanonicalPath());
                Graph graph = new Graph(nodes, finalArticleName);
                graphIO.saveGraph("/media/extradikke/UbuntuData/wikipedia_data/GraphsSaved/", graph);




            } catch (IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("Done in " + ((System.nanoTime() - start)/1000000000));
    }
}
