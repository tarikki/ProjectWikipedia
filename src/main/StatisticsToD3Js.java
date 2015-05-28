package main;

import analysis.StatisticGenerator;
import org.joda.time.DateTime;

/**
 * Created by extradikke on 25/05/15.
 */
public class StatisticsToD3Js {

    public static void main(String[] args) {
        StatisticGenerator.StatisticsToD3JS("2014 winter olympics", "2014 winter olympics", new DateTime(2014, 2, 1, 0, 0), new DateTime(2014, 3, 4, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("ebola virus epidemic in west africa", "ebola virus epidemic in west africa", new DateTime(2014, 9, 1, 0, 0), new DateTime(2014, 10, 31, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("2014 fifa world cup", "2014 fifa world cup", new DateTime(2014, 6, 1, 0, 0), new DateTime(2014, 7, 21, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("eurovision song contest 2014", "eurovision song contest 2014", new DateTime(2014, 5, 1, 0, 0), new DateTime(2014, 5, 17, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("malaysia airlines flight 370", "malaysia airlines flight 370", new DateTime(2014, 3, 8, 0, 0), new DateTime(2014, 4, 22, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("sinking of the mv sewol", "sinking of the mv sewol", new DateTime(2014, 4, 22, 0, 0), new DateTime(2014, 5, 25, 0, 0), 1, 0, true);
//        StatisticGenerator.StatisticsToD3JS("malaysia airlines flight 17", "malaysia airlines flight 17", new DateTime(2014, 7, 17, 0, 0), new DateTime(2014, 8, 31, 0, 0), 1, 0, true);
    }
}
