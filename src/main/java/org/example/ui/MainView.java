package org.example.ui;

import org.example.domain.EulerSimulation;
import org.example.domain.MidpointSimulation;
import org.example.ui.abstraction.ChartView;
import org.example.ui.abstraction.InputView;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;
    private ChartView chart;
    private InputView eulerInput;
    private InputView midpointInput;

    public MainView() {
        initFrame();
        initChart();
        initEulerInput();
        initMidpointInput();

        addAll();
    }

    private void initFrame() {
        frame = new JFrame("Trajectory Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout());
    }

    private void initChart() {
        chart = new ChartPanel();
    }

    private void initEulerInput() {
        eulerInput = new InputPanel("Euler Method");
        eulerInput.addSimulateListener((
                deltaTime,
                mass,
                resistanceCoefficient,
                sx0,
                sy0,
                vx0,
                vy0
        ) -> {
            EulerSimulation.simulate(deltaTime, mass, resistanceCoefficient, sx0, sy0, vx0, vy0);

            chart.update("Euler", EulerSimulation.xData, EulerSimulation.yData, Color.BLUE);
        });
    }

    private void initMidpointInput() {
        midpointInput = new InputPanel("Midpoint Method");
        midpointInput.addSimulateListener((
                deltaTime,
                mass,
                resistanceCoefficient,
                sx0,
                sy0,
                vx0,
                vy0
        ) -> {
            MidpointSimulation.simulate(deltaTime, mass, resistanceCoefficient, sx0, sy0, vx0, vy0);

            chart.update("Midpoint", MidpointSimulation.xData, MidpointSimulation.yData, Color.YELLOW);
        });
    }

    private void addAll() {
        frame.add(chart, BorderLayout.CENTER);
        frame.add(eulerInput, BorderLayout.WEST);
        frame.add(midpointInput, BorderLayout.EAST);

        frame.setVisible(true);
    }

}