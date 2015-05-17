package modules;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3jsNode {
    private String name;
    private int group;
    private int weight = 1;

    public D3jsNode(String name, int group) {
        this.group = group;
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
