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
        int i = 0;
        int sum = 0;
        Simulation.initSimulation(2, 4);
        while (Parameters.isOn) {
            i++;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Simulation.initClassifiers(2, 4);
            sum += Simulation.simulate();
            runSimulation((double) sum /i);
        }
    }

    private void runSimulation(double average) {
        overloadChart.updateSeries(average);
    }
}
