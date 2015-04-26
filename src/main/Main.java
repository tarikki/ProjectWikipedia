package main;

/**
 * Created by extradikke on 16/04/15.
 */
public class Main {

    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator(2);
        graphGenerator.start("2014 Winter Olympics");
        graphGenerator.processAllNodes();
        graphGenerator.saveNodes();

    }
}
