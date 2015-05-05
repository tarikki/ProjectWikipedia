package testers;

import org.joda.time.DateTime;
import util.ViewCountLoader;

import javax.swing.text.View;
import java.util.Date;

/**
 * Created by extradikke on 05/05/15.
 */
public class ViewCountLoaderTester {
    public static void main(String[] args) {
        ViewCountLoader viewCountLoader = new ViewCountLoader("/media/extradikke/BigStorage/wiki_project/view_counts");
        DateTime dateTime = new DateTime(2014, 5,16,0,0);
        viewCountLoader.loadDate(dateTime);

    }
}
