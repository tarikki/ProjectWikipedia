package testers;

import util.JavaToExcel;

import java.io.IOException;

/**
 * Created by Pepe on 8.5.2015.
 */
public class ExcelTester {

    public static void main(String[] args) throws IOException {
        JavaToExcel javaToExcel = new JavaToExcel();
        javaToExcel.writeExcel("/media/extradikke/UbuntuData/wikipedia_data/mappedGraphs/eurovision song contest 2014.json");
    }
}
