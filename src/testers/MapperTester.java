package testers;

import main.ViewCountMapper;

/**
 * Created by extradikke on 06/05/15.
 */
public class MapperTester {

    public static void main(String[] args) {
        long start = System.nanoTime();
        ViewCountMapper viewCountMapper = new ViewCountMapper("/media/extradikke/BigStorage/wiki_project/view_counts/",
                "/media/extradikke/UbuntuData/wikipedia_data/GraphsSaved/", "/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/");
        System.out.println("Done in " + ((System.nanoTime() - start) / 1000000000));
    }
}
