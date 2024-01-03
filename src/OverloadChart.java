import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class OverloadChart extends ChartPanel {

    private final XYSeries simulationSeries;
    private final XYSeries theoreticalSeries;
    private int time = 0;

    public OverloadChart() {
        super(ChartFactory.createXYLineChart("", // title
                "time", // OX axis
                "overload probability", // OY axis
                new XYSeriesCollection(), // database
                PlotOrientation.VERTICAL, // orientation
                true, // legend
                false, // tooltips
                false)); // urls);
        XYSeriesCollection collection =  (XYSeriesCollection) super.getChart().getXYPlot().getDataset(0);
        simulationSeries = new XYSeries("Simulation", true, true);
        theoreticalSeries = new XYSeries("Theory value", true, true);
        collection.addSeries(simulationSeries);
        collection.addSeries(theoreticalSeries);
    }

    protected void updateSeries(double value) {
        simulationSeries.add(time, value);
        theoreticalSeries.add(time, Simulation.theoreticalValue);
        time++;
    }

    protected void reset() {
        time = 0;
        simulationSeries.clear();
        theoreticalSeries.clear();
    }
}
