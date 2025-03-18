package org.example.ui.abstraction;

@FunctionalInterface
public interface SimulationStartedListener {
    void onSimulate(
            double deltaTime,
            double mass,
            double dragCoefficient,
            double initialX, double initialY,
            double initialSpdX, double initialSpdY
    );
}