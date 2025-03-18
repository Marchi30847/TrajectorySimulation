package org.example.ui;

import org.example.ui.abstraction.SimulationStartedListener;
import org.example.ui.abstraction.AInputView;

import javax.swing.*;
import java.awt.*;

public class InputView extends AInputView {
    private JTextField deltaTimeField;
    private JTextField massField;
    private JTextField resistanceCoefficientField;
    private JTextField initialXField;
    private JTextField initialYField;
    private JTextField initialSpdXField;
    private JTextField initialSpdYField;
    private JButton simulateButton;

    public InputView(String panelTitle) {
        configure(panelTitle);

        initDeltaTimeField();
        initMassField();
        initResistanceCoefficientField();
        initInitialXField();
        initInitialYField();
        initInitialSpdXField();
        initInitialSpdYField();
        initSimulateButton();

        addAll();
    }

    private void configure(String panelTitle) {
        setLayout(new GridLayout(15, 1));
        setBorder(BorderFactory.createTitledBorder(panelTitle));
        setPreferredSize(new Dimension(200, 0));
    }

    private void initDeltaTimeField() {
        deltaTimeField = new JTextField("0.01", 10);
    }

    private void initMassField() {
       massField = new JTextField("1.0", 10);
    }

    private void initResistanceCoefficientField() {
        resistanceCoefficientField = new JTextField("0.1", 10);
    }

    private void initInitialXField() {
        initialXField = new JTextField("0", 10);
    }

    private void initInitialYField() {
        initialYField = new JTextField("0", 10);
    }

    private void initInitialSpdXField() {
        initialSpdXField = new JTextField("10", 10);
    }

    private void initInitialSpdYField() {
        initialSpdYField = new JTextField("10", 10);
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
        add(initialXField);
        add(new JLabel("Initial Position Y:"));
        add(initialYField);
        add(new JLabel("Initial Speed X:"));
        add(initialSpdXField);
        add(new JLabel("Initial Speed Y:"));
        add(initialSpdYField);

        add(simulateButton);
    }

    @Override
    public void addSimulateListener(SimulationStartedListener simulateListener) {
        simulateButton.addActionListener(_ -> {
            try {
                double dt = Double.parseDouble(deltaTimeField.getText());
                double m = Double.parseDouble(massField.getText());
                double k = Double.parseDouble(resistanceCoefficientField.getText());
                double sx0 = Double.parseDouble(initialXField.getText());
                double sy0 = Double.parseDouble(initialYField.getText());
                double vx0 = Double.parseDouble(initialSpdXField.getText());
                double vy0 = Double.parseDouble(initialSpdYField.getText());

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