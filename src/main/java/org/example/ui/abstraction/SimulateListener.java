package org.example.ui.abstraction;

@FunctionalInterface
public interface SimulateListener {
    void onSimulate(
            double deltaTime,
            double mass,
            double dragCoefficient,
            double sx0, double sy0,
            double vx0, double vy0
    );
}