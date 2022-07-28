package org.esisalama.weatherapp;

public class Dataserie {
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    private int temperature;
    public Dataserie(int timepoint, int transparency){
        this.transparency = transparency;
        this.timepoint = timepoint;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    private int transparency;
    public Dataserie(){}

    public Dataserie(int timepoint){
        this.timepoint = timepoint;
    }
    public int getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(int timepoint) {
        this.timepoint = timepoint;
    }

    private int timepoint;
}
