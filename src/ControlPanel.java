import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JSlider slider;
    private JLabel sliderLabel;

    public ControlPanel() {
        this.setLayout(new BorderLayout());

        JPanel sliderPanel = new JPanel(new BorderLayout());

        slider = new JSlider(0, 1000, 1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(20);
        slider.setLayout(new FlowLayout());

        sliderLabel = new JLabel("Simulation speed:" + slider.getValue(), SwingConstants.CENTER);

        slider.addChangeListener(e -> sliderLabel.setText("value of Slider is =" + slider.getValue()));

        sliderPanel.add(sliderLabel, BorderLayout.SOUTH);
        sliderPanel.add(slider, BorderLayout.NORTH);

        this.add(sliderPanel, BorderLayout.NORTH);
    }
}
