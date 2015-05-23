package modules;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3jsLink {
    private int source;
    private int target;
    private int value;
    private int weight = 1;

    public D3jsLink(int source, int destination, int value) {
        this.target = destination;
        this.source = source;
        this.value = value;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
