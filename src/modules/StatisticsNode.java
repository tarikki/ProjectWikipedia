package modules;

/**
 * Created by extradikke on 20/05/15.
 */
public class StatisticsNode implements Comparable<StatisticsNode>{
    private String articleName;
    private int articleId;
    private int distanceFromStart;
    private double meanViewCountsForPeriod;
    private String mainArticle;
    private double precorrelationSum;
    private double sumOfSquares;
    private double STD;
    private double covariance;
    private double correlation;

    public StatisticsNode(int articleId, String articleName, int distanceFromStart, double meanViewCountsForPeriod) {

        this.articleId = articleId;
        this.articleName = articleName;
        this.distanceFromStart = distanceFromStart;
        this.meanViewCountsForPeriod = meanViewCountsForPeriod;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public double getCovariance() {
        return covariance;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public String getMainArticle() {
        return mainArticle;
    }

    public double getMeanViewCountsForPeriod() {
        return meanViewCountsForPeriod;
    }



    public void incrementPreCorrelationSum(double increment){
        this.precorrelationSum += increment;
    }
    public void incrementSumOfSquares(double increment){
        this.sumOfSquares += increment;
    }

    public void calculateCovariance(int numberOfDays){

        this.covariance = this.precorrelationSum/numberOfDays;
    }
    public void calculateSTD(int numberOfDays){
        this.STD = Math.sqrt(this.sumOfSquares/numberOfDays);
    }

    public void calculateCorrelation(double mainSTD){
        this.correlation = this.covariance/(this.STD*mainSTD);
    }

    public double getSTD() {
        return STD;
    }

    public double getCorrelation() {
        return correlation;
    }

    public double getPrecorrelationSum() {
        return precorrelationSum;
    }

    public double getSumOfSquares() {
        return sumOfSquares;
    }

    @Override
    public int compareTo(StatisticsNode other) {
        if (this.getCorrelation() < other.getCorrelation()){ return -1;}
        else if (this.correlation > other.getCorrelation()){ return 1;}
        else return 0;
    }

    public void setCorrelation(double correlation) {
        this.correlation = correlation;
    }
}
