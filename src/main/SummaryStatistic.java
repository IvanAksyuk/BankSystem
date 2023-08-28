package main;

public class SummaryStatistic {
    private final double sum;
    private final double max;
    private final double min;
    private final double average;

    private final int count;

    public SummaryStatistic(final double sum, final double max, final double min, final double average, final int count){
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.average = average;
        this.count = count;
    }

    public double getSum(){
        return sum;
    }

    public double getMax(){
        return max;
    }

    public double getMin(){
        return min;
    }

    public double getAverage(){
        return average;
    }

    public int getCount(){
        return count;
    }
}
