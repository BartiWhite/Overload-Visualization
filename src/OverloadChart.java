import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OverloadChart extends ChartPanel {

    private final XYSeries simulationSeries;
    private final XYSeries theoreticalSeries;
    private int time = 0;

    public OverloadChart() {
        super(ChartFactory.createXYLineChart("",
                "samples",
                "overload probability",
                new XYSeriesCollection()));
        XYSeriesCollection collection =  (XYSeriesCollection) super.getChart().getXYPlot().getDataset(0);
        simulationSeries = new XYSeries("Simulation average", true, true);
        theoreticalSeries = new XYSeries("Formula value", true, true);
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
