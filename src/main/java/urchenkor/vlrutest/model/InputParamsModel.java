package urchenkor.vlrutest.model;

public class InputParamsModel {
    private double availability;
    private int time;

    public InputParamsModel(double availability, int time) {
        this.availability = availability;
        this.time = time;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
