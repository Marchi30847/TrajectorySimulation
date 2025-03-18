package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class EulerSimulation {
    public final static List<Double> xData = new ArrayList<>();
    public final static List<Double> yData = new ArrayList<>();

    private EulerSimulation() {
    }

    private static void clearOldData() {
        xData.clear();
        yData.clear();
    }

    public static void simulate(
            double deltaTime,
            double mass,
            double resistanceCoefficient,
            double sx0, double sy0,
            double vx0, double vy0
    ) {
        simulate(
                deltaTime,
                mass,
                resistanceCoefficient,
                0, -9.81,
                sx0, sy0,
                vx0, vy0
        );
    }

    public static void simulate(
            double deltaTime,
            double mass,
            double resistanceCoefficient,
            double gx, double gy,
            double sx0, double sy0,
            double vx0, double vy0
    ) {
        clearOldData();

        double sx = sx0, sy = sy0;
        double vx = vx0, vy = vy0;

        while (sy >= 0) {
            xData.add(sx);
            yData.add(sy);

            double ax = (mass * gx - resistanceCoefficient * vx) / mass;
            double ay = (mass * gy - resistanceCoefficient * vy) / mass;

            vx += ax * deltaTime;
            vy += ay * deltaTime;

            sx += vx * deltaTime;
            sy += vy * deltaTime;

            if (sy < 0) {
                double tGround = -(yData.getLast() / vy);
                double xGround = xData.getLast() + vx * tGround;
                double yGround = 0.0;

                xData.add(xGround);
                yData.add(yGround);

                break;
            }
        }
    }

}