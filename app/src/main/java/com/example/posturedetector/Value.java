package com.example.posturedetector;

public class Value {
    String angle;
    String position;
    String timedate;

    public Value() {
    }

    public Value(String angle, String position,String timedate) {
        this.angle = angle;
        this.position = position;
        this.timedate = timedate;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getTimedate() {
        return timedate;
    }

    public void setTimedate(String timedate) {
        this.timedate = timedate;
    }
}
