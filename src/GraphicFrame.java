import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicFrame extends JFrame {

    public GraphicFrame(ControlPanel controlPanel, OverloadChart overloadChart) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Overload Simulation");
        this.setSize(800, 660);
        this.setMinimumSize(new Dimension(800, 660));
        this.setLayout(new BorderLayout());

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.add(controlPanel, BorderLayout.CENTER);
        this.add(overloadChart, BorderLayout.NORTH);
    }
}
