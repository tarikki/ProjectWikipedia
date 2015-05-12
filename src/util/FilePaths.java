package util;

import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Created by Pepe on 27.2.2015.
 */
public class FilePaths {


    public static String NODES_DIRECTORY;
    private static String DEFAULT_DIRECTORY_NAME = "Nodes";
    public static String LOCATION_OF_GRAPH;
    public static String ARTICLE_MAPPING_LOCATION;
    public static String EXCEL_FILES;

    // Static block so these are correct when program is first started
    static {

//

        // Pepe's Directory
//        NODES_DIRECTORY = "C:\\Workspace BU\\Wikipedia\\" + DEFAULT_DIRECTORY_NAME + osPathCorrection();
//       LOCATION_OF_GRAPH = "C:\\Workspace BU\\Wikipedia\\Data\\wiki_in_numbers.txt" + osPathCorrection();
//        ARTICLE_MAPPING_LOCATION = "C:\\Workspace BU\\Wikipedia\\Data\\article_name_to_number.txt" + osPathCorrection();
//        EXCEL_FILES = "C:\\Workspace BU\\Wikipedia\\Data\\Excels" + osPathCorrection();

        //Tariq's Directory
        NODES_DIRECTORY = "/media/extradikke/UbuntuData/wikipedia_data/finalGraphs/";
        LOCATION_OF_GRAPH = "/media/extradikke/UbuntuData/wikipedia_data/wikidump_processed/wiki_in_numbers.txt";
        ARTICLE_MAPPING_LOCATION = "/media/extradikke/UbuntuData/wikipedia_data/wikidump_processed/articles_to_numbers.txt";
        EXCEL_FILES = "/media/extradikke/UbuntuData/wikipedia_data/excelTester/";

    }

    public static String getWD() {
        Pattern opSysPattern = Pattern.compile("\\w+");
        String fullPath = "";
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        String opSys = System.getProperty("os.name").toLowerCase();

        java.util.regex.Matcher matcher = opSysPattern.matcher(opSys);
        if (matcher.find()) {

            opSys = matcher.group();
            if (opSys.equals("linux")) {
//                System.out.println("linux");
                fullPath = path + "/";
            }
            if (opSys.equals("windows")) {
                fullPath = path + "\\";
            }
//            System.out.println(fullPath);
        }
        return fullPath;
    }

    public static String osPathCorrection() {
        Pattern opSysPattern = Pattern.compile("\\w+");
        String opSys = System.getProperty("os.name").toLowerCase();
        java.util.regex.Matcher matcher = opSysPattern.matcher(opSys);
        String result = "";
        if (matcher.find()) {

            opSys = matcher.group();
            if (opSys.equals("linux")) {

                result = "/";
            }
            if (opSys.equals("windows")) {
                result = "\\";
            }

        }
        return result;


    }
}
