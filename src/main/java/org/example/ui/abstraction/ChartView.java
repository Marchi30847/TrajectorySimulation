package org.example.ui.abstraction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ChartView extends JPanel {
    public abstract void update(String seriesName, List<Double> xData, List<Double> yData, Color color);
}
