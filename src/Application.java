public class Application extends Parameters implements Runnable {
    private final OverloadChart overloadChart;
    private final ControlPanel controlPanel;

    public Application() {
        overloadChart = new OverloadChart();
        controlPanel = new ControlPanel(overloadChart);
        GraphicFrame graphicFrame = new GraphicFrame(controlPanel, overloadChart);
        graphicFrame.setVisible(true);
    }

    @Override
    public void run() {
        Simulation.initSimulation(2, 4);
        while (Parameters.isOn) {

            while (Parameters.isRunning) {
                iteration++;

                Simulation.updateClassifiers();
                sum += Simulation.simulate();
                if (Parameters.isRunning) {
                    double average = (double) sum/iteration;
                    overloadChart.updateSeries(average);
                    controlPanel.updateAverageLabel(average);
                }

                try {
                    Thread.sleep(1000/Parameters.speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
