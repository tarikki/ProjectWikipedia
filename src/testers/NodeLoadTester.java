package testers;

import util.NodeLoader;

/**
 * Created by Pepe on 4.5.2015.
 */
public class NodeLoadTester {

    public static void main(String[] args) {

        String filepath = "C:\\Workspace BU\\Wikipedia\\Nodes\\Malaysia Airlines Flight 17 05-05-2015 16-50-22";

        NodeLoader nodeLoader = new NodeLoader();
        nodeLoader.loadAllNodes(filepath);
    }
}
