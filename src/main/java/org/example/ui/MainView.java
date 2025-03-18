package org.example.ui;

import org.example.domain.EulerSimulation;
import org.example.domain.MidpointSimulation;
import org.example.ui.abstraction.AChartView;
import org.example.ui.abstraction.AInputView;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;
    private AChartView chart;
    private AInputView eulerInput;
    private AInputView midpointInput;

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
        chart = new ChartView();
    }

    private void initEulerInput() {
        eulerInput = new InputView("Euler Method");
        eulerInput.addSimulateListener((
                deltaTime,
                mass,
                dragCoefficient,
                sx0,
                sy0,
                vx0,
                vy0
        ) -> {
            EulerSimulation simulation = new EulerSimulation(
                    deltaTime,
                    mass,
                    dragCoefficient,
                    sx0, sy0,
                    vx0, vy0
            );
            simulation.simulate();

            chart.update("Euler", simulation.xData, simulation.yData, Color.BLUE);
        });
    }

    private void initMidpointInput() {
        midpointInput = new InputView("Midpoint Method");
        midpointInput.addSimulateListener((
                deltaTime,
                mass,
                dragCoefficient,
                sx0,
                sy0,
                vx0,
                vy0
        ) -> {
            MidpointSimulation simulation = new MidpointSimulation(
                    deltaTime,
                    mass,
                    dragCoefficient,
                    sx0, sy0,
                    vx0, vy0
            );
            simulation.simulate();
            chart.update("Midpoint", simulation.xData, simulation.yData, Color.YELLOW);
        });
    }

    private void addAll() {
        frame.add(chart, BorderLayout.CENTER);
        frame.add(eulerInput, BorderLayout.WEST);
        frame.add(midpointInput, BorderLayout.EAST);

        frame.setVisible(true);
    }

}