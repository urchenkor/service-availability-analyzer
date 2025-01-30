package urchenkor.vlrutest.model;

public class InputParamsModel {
    private double availability;
    private double maxResponseTime;
    private long statisticInterval;

    public InputParamsModel(double availability, double maxResponseTime, long statisticInterval) {
        this.availability = availability;
        this.maxResponseTime = maxResponseTime;
        this.statisticInterval = statisticInterval;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public double getMaxResponseTime() {
        return maxResponseTime;
    }

    public void setMaxResponseTime(double maxResponseTime) {
        this.maxResponseTime = maxResponseTime;
    }

    public long getStatisticInterval() {
        return statisticInterval;
    }

    public void setStatisticInterval(long statisticInterval) {
        this.statisticInterval = statisticInterval;
    }
}
