package testers;

/**
 * Created by extradikke on 12/05/15.
 */
public class ColorTester {

    public static void main(String[] args) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_RED + "punainen tuubero " + ANSI_RESET + "- normituubero");
    }
}
