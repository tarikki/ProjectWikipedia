package main;

import analysis.StatisticGenerator;
import org.joda.time.DateTime;

/**
 * Created by extradikke on 20/05/15.
 */
public class StatisticGeneratorMain {

    public static void main(String[] args) {
        StatisticGenerator.SaveStatisticsToExcel("2014 winter olympics", "2014 winter olympics", new DateTime(2014, 1, 2, 0, 0), new DateTime(2014, 3, 31, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("ebola virus epidemic in west africa", "ebola virus epidemic in west africa", new DateTime(2014, 2, 25, 0, 0), new DateTime(2014, 12, 31, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("2014 fifa world cup", "2014 fifa world cup", new DateTime(2014, 5, 1, 0, 0), new DateTime(2014, 8, 15, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("eurovision song contest 2014", "eurovision song contest 2014", new DateTime(2014, 4, 1, 0, 0), new DateTime(2014, 6, 15, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("malaysia airlines flight 370", "malaysia airlines flight 370", new DateTime(2014, 3, 8, 0, 0), new DateTime(2014, 11, 30, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("chibok schoolgirls kidnapping", "chibok schoolgirls kidnapping", new DateTime(2014, 4, 23, 0, 0), new DateTime(2014, 11, 30, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("sinking of the mv sewol", "sinking of the mv sewol", new DateTime(2014, 4, 16, 0, 0), new DateTime(2014, 7, 31, 0, 0), 2);
        StatisticGenerator.SaveStatisticsToExcel("malaysia airlines flight 17", "malaysia airlines flight 17", new DateTime(2014, 7, 17, 0, 0), new DateTime(2014, 12, 31, 0, 0), 2);
    }
}
