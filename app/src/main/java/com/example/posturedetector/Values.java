package com.example.posturedetector;

public class Values {
    public String Angle;
    public String Status;

    public Values() {
    }

    public Values(String Angle, String Status) {
        this.Angle = Angle;
        this.Status = Status;
    }

    public String getAngle() {
        return Angle;
    }

    public void setAngle(String Angle) {
        this.Angle = Angle;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
