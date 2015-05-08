package main;

/**
 * Created by extradikke on 16/04/15.
 */
public class Main {

    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator(3);
        graphGenerator.start("Malaysia Airlines Flight 17");
        graphGenerator.processAllNodes();
        graphGenerator.saveNodes();

    }
}
