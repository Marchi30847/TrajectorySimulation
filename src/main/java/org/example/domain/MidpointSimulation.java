package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class MidpointSimulation {
    private final static double Gx = 0;
    private final static double Gy = -9.81;

    private final double deltaTime;
    private final double dragCoefficient;
    private final double mass;

    private double posX, posY;
    private double prevSpdX, prevSpdY;

    public List<Double> xData = new ArrayList<>();
    public List<Double> yData = new ArrayList<>();

    public MidpointSimulation(
            double deltaTime,
            double mass,
            double dragCoefficient,
            double initialX, double initialY,
            double initialSpdX, double initialSpdY
    ) {
        this.deltaTime = deltaTime;
        this.dragCoefficient = dragCoefficient;
        this.mass = mass;
        this.posX = initialX;
        this.posY = initialY;
        this.prevSpdX = initialSpdX;
        this.prevSpdY = initialSpdY;
    }

    public double accelerationX(double speedX) {
        return (mass * Gx - dragCoefficient * Math.pow(speedX, 2)) / mass;
    }

    public double accelerationY(double speedY) {
        return (mass * Gy - dragCoefficient * Math.pow(speedY, 2)) / mass;
    }

    public void update() {
        double midSpdX = prevSpdX + accelerationX(prevSpdX) * (deltaTime / 2);
        double midSpdY = prevSpdY + accelerationY(prevSpdY) * (deltaTime / 2);

        double midAccX = accelerationX(midSpdX);
        double midAccY = accelerationY(midSpdY);

        double newSpdX = prevSpdX + midAccX * deltaTime;
        double newSpdY = prevSpdY + midAccY * deltaTime;

        posX += midSpdX * deltaTime;
        posY += midSpdY * deltaTime;

        prevSpdX = newSpdX;
        prevSpdY = newSpdY;
    }

    public void simulate() {
        while (posY >= -1) {
            xData.add(posX);
            yData.add(posY);
            update();
        }
    }
}