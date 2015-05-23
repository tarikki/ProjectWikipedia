package testers;

import util.CorrelationExcel;

import java.io.IOException;

/**
 * Created by Pepe on 23.5.2015.
 */
public class CorrelationExcelTester {

    public static void main(String[] args) throws IOException {
        CorrelationExcel correlationExcel = new CorrelationExcel();
        correlationExcel.createStatisticsNodes("C:\\Workspace BU\\Wikipedia\\Data\\mappedGraphs\\sinking of the mv sewol.json");
        correlationExcel.createWorkBook();
        correlationExcel.writeExcel();
    }
}
