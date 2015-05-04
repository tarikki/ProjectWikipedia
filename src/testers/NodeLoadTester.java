package testers;

import util.NodeLoader;

/**
 * Created by Pepe on 4.5.2015.
 */
public class NodeLoadTester {

    public static void main(String[] args) {

        String filepath = "C:\\Workspace BU\\Wikipedia\\Nodes\\2014 Winter Olympics 25-04-2015 13-39-22";

        NodeLoader nodeLoader = new NodeLoader();
        nodeLoader.loadAllNodes(filepath);
    }
}
