package testers;

import util.ExcelToJSON;

import java.io.IOException;

/**
 * Created by Pepe on 23.5.2015.
 */
public class ExcelToJSONTester {

    public static void main(String[] args) throws IOException {
        ExcelToJSON excelToJSON = new ExcelToJSON();
        excelToJSON.getArticleNamesFromExcel("C:\\Workspace BU\\Wikipedia\\sinking of the mv sewol 15-05-2015 16-54-17 CLEANED FINAL.xlsx");
    }
}
