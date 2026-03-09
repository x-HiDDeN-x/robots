package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RobotCoordinatesWindow extends JInternalFrame implements Observer {

    private final RobotModel model;
    private final JLabel coordsLabel;

    public RobotCoordinatesWindow(RobotModel model) {
        super(Localization.get("window.coords"), true, true, true, true);
        this.model = model;
        this.model.addObserver(this);

        coordsLabel = new JLabel();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(coordsLabel, BorderLayout.CENTER);
        getContentPane().add(panel);

        pack();
        updateText();
    }

    private void updateText() {
        double x = model.getX();
        double y = model.getY();
        double dir = model.getDirection();
        coordsLabel.setText(String.format("x = %.2f, y = %.2f, dir = %.2f", x, y, dir));
    }

    @Override
    public void update(Observable o, Object arg) {
        EventQueue.invokeLater(this::updateText);
    }
}
