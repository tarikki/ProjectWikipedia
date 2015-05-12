package testers;

import main.ViewCountMapper;
import org.joda.time.DateTime;

/**
 * Created by extradikke on 06/05/15.
 */
public class MapperTester {

    public static void main(String[] args) {
        long start = System.nanoTime();
        ViewCountMapper viewCountMapper = new ViewCountMapper("/media/extradikke/BigStorage/wiki_project/view_counts/",
                "/media/extradikke/UbuntuData/wikipedia_data/GraphsSaved/", "/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/");
//        viewCountMapper.loadAllViews(new DateTime(2014, 1,1,0,0), new DateTime(2014,12,31,0,0));
        viewCountMapper.loadAllViews(new DateTime(2014, 1,1,0,0), new DateTime(2014,1,10,0,0));
        System.out.println("Done in " + ((System.nanoTime() - start) / 1000000000));
    }
}
