import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ControlPanel extends JPanel {

    private final JLabel currentAverageLabel;

    public ControlPanel(OverloadChart overloadChart) {
        this.setLayout(new BorderLayout());

        JSlider slider = new JSlider(0, 1000, 1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(20);
        slider.setLayout(new BorderLayout());

        JLabel sliderLabel = new JLabel("Simulation speed:" + slider.getValue(), SwingConstants.CENTER);

        slider.addChangeListener(e -> {
            if (slider.getValue() != 0) {
                sliderLabel.setText("Simulation speed:" + slider.getValue());
                Parameters.speed = slider.getValue();
            }
        });

        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.add(sliderLabel, BorderLayout.SOUTH);
        sliderPanel.add(slider, BorderLayout.NORTH);

        this.add(sliderPanel, BorderLayout.NORTH);

        JButton runButton = new JButton("RUN");
        runButton.addActionListener(e -> Parameters.isRunning = true);
        JButton stopButton = new JButton("STOP");
        stopButton.addActionListener(e -> Parameters.isRunning = false);

        JPanel leftPanel = new JPanel(new GridLayout(2, 1));
        leftPanel.add(runButton, BorderLayout.NORTH);
        leftPanel.add(stopButton, BorderLayout.SOUTH);

        JLabel currentSettingsLabel = new JLabel("Currently simulation runs with", SwingConstants.CENTER);
        JLabel currentLengthLabel = new JLabel("Length: 2", SwingConstants.CENTER);
        JLabel currentNrLabel = new JLabel("Nr. of classifiers: 4", SwingConstants.CENTER);
        currentAverageLabel = new JLabel("Average: ", SwingConstants.CENTER);
        JLabel expectedValueLabel = new JLabel("Expected value: 0.530", SwingConstants.CENTER);

        JPanel currentStatePanel = new JPanel(new GridLayout(5, 1));
        currentStatePanel.add(currentSettingsLabel);
        currentStatePanel.add(currentLengthLabel);
        currentStatePanel.add(currentNrLabel);
        currentStatePanel.add(currentAverageLabel);
        currentStatePanel.add(expectedValueLabel);

        JLabel nrLabel = new JLabel("Nr. of classifiers", SwingConstants.CENTER);
        JLabel lengthLabel = new JLabel("Classifier length", SwingConstants.CENTER);

        JPanel classifierLabels = new JPanel(new GridLayout(1, 2));
        classifierLabels.add(nrLabel, BorderLayout.WEST);
        classifierLabels.add(lengthLabel, BorderLayout.EAST);

        JTextField nrField = new JTextField("4", SwingConstants.CENTER);
        JTextField lengthField = new JTextField("2", SwingConstants.CENTER);

        JPanel classifierFields = new JPanel(new GridLayout(1, 2));
        classifierFields.add(nrField, BorderLayout.EAST);
        classifierFields.add(lengthField, BorderLayout.WEST);

        JButton newSimulationButton = new JButton("SIMULATE");
        newSimulationButton.addActionListener(e -> {
            Parameters.speed = 1;
            Parameters.iteration = 0;
            Parameters.sum = 0;
            Parameters.isRunning = false;
            Simulation.initSimulation(Integer.parseInt(lengthField.getText()), Integer.parseInt(nrField.getText()));
            slider.setValue(1);
            sliderLabel.setText("Simulation speed:" + 1);
            currentLengthLabel.setText("Length: " + lengthField.getText());
            currentNrLabel.setText("Nr. of classifiers: " + nrField.getText());
            currentAverageLabel.setText("Average: ");
            expectedValueLabel.setText("Expected value: " + BigDecimal.valueOf(Simulation.theoreticalValue)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue());
            overloadChart.reset();
            Parameters.isRunning = true;
        });

        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.add(classifierLabels, BorderLayout.NORTH);
        rightPanel.add(classifierFields, BorderLayout.CENTER);
        rightPanel.add(newSimulationButton, BorderLayout.SOUTH);

        JPanel controlsPanel = new JPanel(new GridLayout(1, 3));
        controlsPanel.add(leftPanel, BorderLayout.WEST);
        controlsPanel.add(currentStatePanel, BorderLayout.CENTER);
        controlsPanel.add(rightPanel, BorderLayout.EAST);

        this.add(controlsPanel, BorderLayout.CENTER);
    }

    protected void updateAverageLabel(double average) {
        currentAverageLabel.setText("Average: " + BigDecimal.valueOf(average)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue());
    }
}
