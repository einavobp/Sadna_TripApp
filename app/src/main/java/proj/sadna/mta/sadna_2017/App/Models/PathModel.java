package proj.sadna.mta.sadna_2017.App.Models;

import java.util.ArrayList;

public class PathModel
{
    private String name;
    private double rate;

    public PathModel(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
