package util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import modules.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Pepe on 4.5.2015.
 */
public class NodeLoader {

    JsonReader nodeReader;
    Gson gson;
    File dir;
    ArrayList<Node> nodes;


public ArrayList<Node> loadAllNodes(String dirPath)
{

    nodes = new ArrayList<>();
    dir = new File(dirPath);
    gson = new Gson();

    for (File file : dir.listFiles())
    {
        try {
            nodeReader = new JsonReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Not a valid file path!");
        }

        Node node = gson.fromJson(nodeReader, Node.class);

        nodes.add(node);
        try {
            nodeReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    System.out.println("Processed " + nodes.size() + " nodes ");

    return nodes;
}

}
