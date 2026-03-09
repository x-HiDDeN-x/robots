package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class MouseController {

    private final RobotModel model;

    public MouseController(RobotModel model) {
        this.model = model;
    }

    public void attachTo(JComponent component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setTarget(e.getX(), e.getY());
            }
        });
    }
}
