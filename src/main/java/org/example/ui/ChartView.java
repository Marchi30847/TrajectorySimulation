package org.example.ui;

import org.example.ui.abstraction.AChartView;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYSeries;

import java.awt.*;
import java.util.List;

public class ChartView extends AChartView {
    private XYChart chart;

    public ChartView() {
        configure();

        initChart();

        addAll();
    }

    private void configure() {
        setLayout(new BorderLayout());
    }

    private void initChart() {
        chart = new XYChartBuilder()
                .title("Euler and Midpoint Trajectories")
                .xAxisTitle("X Position")
                .yAxisTitle("Y Position")
                .build();

        chart.getStyler().setChartTitleVisible(true);
    }

    private void addAll() {
        add(new XChartPanel<>(chart), BorderLayout.CENTER);
    }

    @Override
    public void update(String seriesName, List<Double> xData, List<Double> yData, Color color) {
        if (xData == null || yData == null || xData.isEmpty() || yData.isEmpty()) {
            ErrorPane.invokeError(
                    null,
                    "Bad data",
                    "Input data is empty"
                    );
            return;
        }

        if (chart.getSeriesMap().containsKey(seriesName)) {
            chart.updateXYSeries(seriesName, xData, yData, null);
        } else {
            XYSeries series = chart.addSeries(seriesName, xData, yData);

            series.setLineColor(color);
            series.setMarkerColor(color);
        }

        repaint();
        revalidate();
    }
}