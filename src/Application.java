public class Application implements Runnable{
    private final GraphicFrame graphicFrame;
    private final OverloadChart overloadChart;
    private final ControlPanel controlPanel;

    public Application() {
        this.overloadChart = new OverloadChart();
        this.controlPanel = new ControlPanel();
        this.graphicFrame = new GraphicFrame(controlPanel, overloadChart);
        graphicFrame.setVisible(true);
    }

    @Override
    public void run() {
        while (Parameters.isOn) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            runSimulation();
        }
    }

    private void runSimulation() {
        overloadChart.updateSeries(1);
    }
}
