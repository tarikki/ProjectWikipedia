package testers;

import util.JsonListLoader;

/**
 * Created by extradikke on 26/05/15.
 */
public class ArrayReader {

    public static void main(String[] args) {
        JsonListLoader jsonListLoader = new JsonListLoader();
        jsonListLoader.load2("/media/extradikke/UbuntuData/wikipedia_data/clean jsons/Articlenames as JSON/2014 fifa world cup.json");
    }
}
