package gui;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;

public class GameWindow extends JInternalFrame {

    public GameWindow(RobotModel model) {
        super("Game", true, true, true, true);

        GameVisualizer visualizer = new GameVisualizer(model);
        MouseController controller = new MouseController(model);
        controller.attachTo(visualizer);

        setLayout(new BorderLayout());
        add(visualizer, BorderLayout.CENTER);

        pack();
    }
}
