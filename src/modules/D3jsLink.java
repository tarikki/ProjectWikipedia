package modules;

/**
 * Created by extradikke on 17/05/15.
 */
public class D3jsLink {
    private int source;
    private int target;
    private double correlationBin;


    public D3jsLink(int source, int destination, int correlationBin) {
        this.target = destination;
        this.source = source;
        this.correlationBin = correlationBin;
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

    public double getCorrelationBin() {
        return correlationBin;
    }

    public void setCorrelationBin(double correlationBin) {
        this.correlationBin = correlationBin;
    }
}
