package data;

import com.graphhopper.util.PointList;

public class Rout {

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTurnDescription() {
        return turnDescription;
    }

    public void setTurnDescription(String turnDescription) {
        this.turnDescription = turnDescription;
    }

    public PointList getPointList() {
        return pointList;
    }

    public void setPointList(PointList pointList) {
        this.pointList = pointList;
    }

    public Rout(double distance, String turnDescription, PointList pointList) {
        this.distance = distance;
        this.turnDescription = turnDescription;
        this.pointList = pointList;
    }

    public Rout() {
    }

    private double distance;
    private String turnDescription;
    private PointList pointList;
    private String position ; 

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
