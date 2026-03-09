package gui;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;

public class GameWindow extends JInternalFrame {

    public GameWindow(RobotModel model) {
        super("Game", true, true, true, true);
        setLayout(new BorderLayout());
        add(new GameVisualizer(model), BorderLayout.CENTER);
        pack();
    }
}
