package testers;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * Created by extradikke on 05/05/15.
 */
public class JodaTester {

    public static void main(String[] args) {
        DateTime date1 = new DateTime(2014, 5, 1, 0, 0);
        DateTime date2 = new DateTime(2014, 5, 6, 0, 0);
        System.out.println(Days.daysBetween(date1, date2).getDays()+1);
        System.out.println(new DateTime("2014-5-1"));
        System.out.println(date1.toLocalDate().toString());

    }
}
