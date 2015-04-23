package main;

/**
 * Created by extradikke on 16/04/15.
 */
public class Main {

    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator();
        graphGenerator.start("articleNameGoesHere");
        graphGenerator.processAllNodes();
        graphGenerator.saveNodes();

    }
}
