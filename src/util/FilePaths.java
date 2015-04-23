package util;

import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Created by Pepe on 27.2.2015.
 */
public class FilePaths {


    public static String NODES_DIRECTORY;
    private static String DEFAULT_DIRECTORY_NAME = "Nodes";


    // Static block so these are correct when program is first started
    static {

//

        // Pepe's Directory
        NODES_DIRECTORY = "C:\\Workspace BU\\Wikipedia\\" + DEFAULT_DIRECTORY_NAME + osPathCorrection();



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
