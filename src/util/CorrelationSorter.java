package util;

import modules.StatisticsNode;

import java.util.Comparator;

/**
 * Created by extradikke on 21/05/15.
 */
public class CorrelationSorter implements Comparator<StatisticsNode> {

    public int compare(StatisticsNode first, StatisticsNode second) {
        if (first.getCorrelation() < second.getCorrelation()) return -1;
        else if (first.getCorrelation() > second.getCorrelation()) return 1;
        else return 0;
    }
}
