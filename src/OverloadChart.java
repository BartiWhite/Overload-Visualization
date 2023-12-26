import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class OverloadChart extends JPanel {

    private final XYSeries series;
    private int time = 0;

    public OverloadChart() {
        series = new XYSeries("Overload", true, true);
        XYSeriesCollection collection = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("", // title
                "time", // OX axis
                "opinion", // OY axis
                collection, // database
                PlotOrientation.VERTICAL, // orientation
                true, // legend
                false, // tooltips
                false); // urls
        ChartPanel chartPanel = new ChartPanel(chart);

        this.add(chartPanel);
    }

    protected void updateSeries(float value) {
        series.add(time, value);
        time++;
    }

    protected void reset() {
        time = 0;
        series.clear();
    }
}
