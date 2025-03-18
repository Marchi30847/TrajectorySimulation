package org.example.ui;

import org.example.ui.abstraction.SimulateListener;
import org.example.ui.abstraction.InputView;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends InputView {
    private JTextField deltaTimeField;
    private JTextField massField;
    private JTextField resistanceCoefficientField;
    private JTextField sx0Field;
    private JTextField sy0Field;
    private JTextField vx0Field;
    private JTextField vy0Field;
    private JButton simulateButton;

    public InputPanel(String panelTitle) {
        configure(panelTitle);

        initDeltaTimeField();
        initMassField();
        initResistanceCoefficientField();
        initSx0Field();
        initSy0Field();
        initVx0Field();
        initVy0Field();
        initSimulateButton();

        addAll();
    }

    private void configure(String panelTitle) {
        setLayout(new GridLayout(15, 1));
        setBorder(BorderFactory.createTitledBorder(panelTitle));
        setPreferredSize(new Dimension(200, 0));
    }

    private void initDeltaTimeField() {
        deltaTimeField = new JTextField("0.1", 10);
    }

    private void initMassField() {
       massField = new JTextField("1.0", 10);
    }

    private void initResistanceCoefficientField() {
        resistanceCoefficientField = new JTextField("0.1", 10);
    }

    private void initSx0Field() {
        sx0Field = new JTextField("0", 10);
    }

    private void initSy0Field() {
        sy0Field = new JTextField("0", 10);
    }

    private void initVx0Field() {
        vx0Field = new JTextField("10", 10);
    }

    private void initVy0Field() {
        vy0Field = new JTextField("10", 10);
    }

    private void initSimulateButton() {
        simulateButton = new JButton("Simulate");
    }

    private void addAll() {
        add(new JLabel("Delta Time:"));
        add(deltaTimeField);
        add(new JLabel("Mass:"));
        add(massField);
        add(new JLabel("Resistance coefficient:"));
        add(resistanceCoefficientField);
        add(new JLabel("Initial Position X:"));
        add(sx0Field);
        add(new JLabel("Initial Position Y:"));
        add(sy0Field);
        add(new JLabel("Initial Speed X:"));
        add(vx0Field);
        add(new JLabel("Initial Speed Y:"));
        add(vy0Field);

        add(simulateButton);
    }

    @Override
    public void addSimulateListener(SimulateListener simulateListener) {
        simulateButton.addActionListener(_ -> {
            try {
                double dt = Double.parseDouble(deltaTimeField.getText());
                double m = Double.parseDouble(massField.getText());
                double k = Double.parseDouble(resistanceCoefficientField.getText());
                double sx0 = Double.parseDouble(sx0Field.getText());
                double sy0 = Double.parseDouble(sy0Field.getText());
                double vx0 = Double.parseDouble(vx0Field.getText());
                double vy0 = Double.parseDouble(vy0Field.getText());

                simulateListener.onSimulate(dt, m, k, sx0, sy0, vx0, vy0);
            } catch (NumberFormatException ex) {
                ErrorPane.invokeError(
                        null,
                        "Bad data",
                        "Input must be a numeric value"
                        );
            }
        });
    }
}