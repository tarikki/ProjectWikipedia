package main;

import modules.Graph;
import util.GraphIO;
import util.ViewCountLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by extradikke on 06/05/15.
 */
public class ViewCountMapper {
    private ViewCountLoader viewCountLoader;
    private String[] graphDirectories;
    private Graph[] graphs;
//    private ArrayList<>


    //    "/media/extradikke/BigStorage/wiki_project/view_counts"
    public ViewCountMapper(String pathToViews, String pathToGraphs, String outputDirectory) {
        this.viewCountLoader = new ViewCountLoader(pathToViews);
        File graphsDirectory = new File(pathToGraphs);
        File[] files= graphsDirectory.listFiles();
        graphs = new Graph[files.length];
        GraphIO graphIO = new GraphIO();
        int counter = 0;
        for (File directory : files) {
            try{
            System.out.println(directory.getCanonicalPath());
            graphs[counter] = graphIO.loadGraph(directory.getCanonicalPath());
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }


}
