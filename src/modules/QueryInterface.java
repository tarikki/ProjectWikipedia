package modules;

/**
 * Created by extradikke on 17/04/15.
 */
public interface QueryInterface {

    /**
     *
     * @param articleId name of article queried
     * @return array of links, null if article not found
     */
    public int query(int articleId);

}
