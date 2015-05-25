package modules;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3jsNode {
    private String name;
    private int group;
    private double correlation;

    public D3jsNode(String name, int group, double correlation) {
        this.group = group;
        this.name = name;
        this.correlation = correlation;

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

    public double getCorrelation() {
        return correlation;
    }

    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }
}
