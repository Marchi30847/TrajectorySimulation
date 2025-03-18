package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class EulerSimulation {
    private final static double Gx = 0;
    private final static double Gy = -9.81;

    private final double deltaTime;
    private final double dragCoefficient;
    private final double mass;

    private double posX, posY;
    private double prevSpdX, prevSpdY;

    public List<Double> xData = new ArrayList<>();
    public List<Double> yData = new ArrayList<>();

    public EulerSimulation(
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

    public double speedX() {
        return prevSpdX + accelerationX(prevSpdX) * deltaTime;
    }

    public double speedY() {
        return prevSpdY + accelerationY(prevSpdY) * deltaTime;
    }

    public double accelerationX(double vx) {
        return (mass * Gx - dragCoefficient * Math.pow(vx, 2)) / mass;
    }

    public double accelerationY(double vy) {
        return (mass * Gy - dragCoefficient * Math.pow(vy, 2)) / mass;
    }

    public void update() {
        double newSpdX = speedX();
        double newSpdY = speedY();

        posX += prevSpdX * deltaTime;
        posY += prevSpdY * deltaTime;

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
