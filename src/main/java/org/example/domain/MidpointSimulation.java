package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class MidpointSimulation {
    public final static List<Double> xData = new ArrayList<>();
    public final static List<Double> yData = new ArrayList<>();

    private MidpointSimulation() {
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

            double ax1 = (mass * gx - resistanceCoefficient * vx) / mass;
            double ay1 = (mass * gy - resistanceCoefficient * vy) / mass;

            double vx_mid = vx + ax1 * (deltaTime / 2);
            double vy_mid = vy + ay1 * (deltaTime / 2);

            double ax2 = (mass * gx - resistanceCoefficient * vx_mid) / mass;
            double ay2 = (mass * gy - resistanceCoefficient * vy_mid) / mass;

            vx += ax2 * deltaTime;
            vy += ay2 * deltaTime;

            sx += vx_mid * deltaTime;
            sy += vy_mid * deltaTime;

            if (sy < 0) {
                double tGround = -yData.getLast() / vy;
                double xGround = xData.getLast() + vx * tGround;
                double yGround = 0.0;

                xData.add(xGround);
                yData.add(yGround);
                break;
            }
        }
    }
}