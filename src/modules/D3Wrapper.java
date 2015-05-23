package modules;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3Wrapper {
    private ArrayList<D3jsNode> nodes = new ArrayList<>();
    private ArrayList<D3jsLink> links = new ArrayList<>();


    public ArrayList<D3jsLink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<D3jsLink> links) {
        this.links = links;
    }

    public ArrayList<D3jsNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<D3jsNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(D3jsNode d3jsNode) {
        this.nodes.add(d3jsNode);
    }

    public void mapLinks(ArrayList<Node> regularNodes) {
        ArrayList<String> nodeNames = new ArrayList<>();
        for (D3jsNode node : nodes) {
            nodeNames.add(node.getName());
        }

        HashMap<String, Node> regularNodesHashed = new HashMap<>();
        for (Node regularNode : regularNodes) {
            regularNodesHashed.put(regularNode.getArticleName(), regularNode);
        }

        HashMap<String, Integer> nodeIndeces = new HashMap<>();
        for (D3jsNode node : nodes) {
            nodeIndeces.put(node.getName(), nodes.indexOf(node));
        }

        for (D3jsNode node : nodes) {
            System.out.println(node.getName() + " " + nodes.indexOf(node));
        }

        for (D3jsNode node : nodes) {
            Node regularNode = regularNodesHashed.get(node.getName());
            int source = nodeIndeces.get(node.getName());
            for (String linkName : regularNode.getLinkNames()) {
                if (nodeNames.contains(linkName)) {
                    int destination = nodeIndeces.get(linkName);
                    if (destination != source) {
                        D3jsLink link = new D3jsLink(source, destination, 1);
                        if (source == 0){
                            link.setValue(2);
                        }
                        links.add(link);
                    }
                }

            }
        }

        System.out.println("Nodes: " + nodes.size() + ", Links: " + links.size());
//        for (D3jsLink link : links) {
//            nodes.get(link.getSource());
//            nodes.get(link.getTarget());
//        }
    }
}
