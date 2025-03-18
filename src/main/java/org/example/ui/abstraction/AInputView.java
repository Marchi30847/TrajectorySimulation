package org.example.ui.abstraction;

import javax.swing.*;

public abstract class AInputView extends JPanel {
    public abstract void addSimulateListener(SimulationStartedListener simulateListener);
}
