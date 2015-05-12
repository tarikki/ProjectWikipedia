package main;

import modules.Graph;
import modules.Node;
import org.joda.time.DateTime;
import util.GraphIO;
import util.NodeLoader;
import util.ViewCountLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by extradikke on 06/05/15.
 */
public class ViewCountMapper {
    private ViewCountLoader viewCountLoader;
    private String[] graphDirectories;
    private ArrayList<Graph> graphs;
    private String outputDirectory;
//    private ArrayList<>


    //    "/media/extradikke/BigStorage/wiki_project/view_counts"
    public ViewCountMapper(String pathToViews, String pathToGraphs, String outputDirectory) {
        this.outputDirectory = outputDirectory;
        this.viewCountLoader = new ViewCountLoader(pathToViews);
        File graphsDirectory = new File(pathToGraphs);
        File[] files = graphsDirectory.listFiles();
        graphs = new ArrayList<>();
        GraphIO graphIO = new GraphIO();
        int counter = 0;
        for (File directory : files) {
            try {
                System.out.println(directory.getCanonicalPath());
                graphs.add(graphIO.loadGraph(directory.getCanonicalPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void setupDatesForGraphs(DateTime startDate, DateTime endDate) {
//        System.out.println(graphs.length);
        for (Graph graph : graphs) {
            graph.setupViewCountMapping(startDate, endDate);
        }
    }

    public void loadAllViews(DateTime startDate, DateTime endDate) {
        setupDatesForGraphs(startDate, endDate);
        boolean Done = false;
        DateTime dateTime = startDate;
        while (!Done) {
            long start = System.nanoTime();
            HashMap<String, Integer> viewCounts = viewCountLoader.loadDate(dateTime);
            if (viewCounts == null) {
                for (Graph graph : graphs) {
                    for (Node node : graph.getNodes()) {
                        node.setViewCountForDay(-1, dateTime);
                    }
                }
            } else {
                for (Graph graph : graphs) {
                    for (Node node : graph.getNodes()) {

                        if (viewCounts.containsKey(node.getArticleName())) {
                            node.setViewCountForDay(viewCounts.get(node.getArticleName()), dateTime);
                        }
                    }
                }
            }
            dateTime = dateTime.plusDays(1);
            if (dateTime.isAfter(endDate)) {
                Done = true;
            }
            System.out.println("Cycle done in " + String.valueOf((System.nanoTime() - start) / 1000000000));

        }

saveResults();
    }

    private void saveResults(){
        System.out.println("Starting to save");
        long start = System.nanoTime();
        GraphIO graphIO = new GraphIO();
        for (Graph graph : graphs) {
            graphIO.saveGraph(this.outputDirectory, graph);
        }
        System.out.println("Saving done in " + String.valueOf((System.nanoTime() - start) / 1000000000));
    }


}
