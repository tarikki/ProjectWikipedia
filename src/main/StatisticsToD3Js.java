package main;

import analysis.StatisticGenerator;
import org.joda.time.DateTime;

/**
 * Created by extradikke on 25/05/15.
 */
public class StatisticsToD3Js {

    public static void main(String[] args) {
        StatisticGenerator.StatisticsToD3JS("2014 winter olympics", "2014 winter olympics", new DateTime(2014, 1, 2, 0, 0), new DateTime(2014, 3, 31, 0, 0), 1, .3);
    }
}
