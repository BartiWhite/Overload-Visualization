import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicFrame extends JFrame {

    public GraphicFrame(ControlPanel controlPanel, OverloadChart overloadChart) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Overload Simulation");
        this.setSize(900, 760);
//        this.setMinimumSize(new Dimension(900, 760));
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parameters.isOn = false;
            }
        });

        this.add(controlPanel, BorderLayout.CENTER);
        this.add(overloadChart, BorderLayout.NORTH);
    }
}
