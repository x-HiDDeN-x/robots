package gui.windowstate;

import javax.swing.*;
import java.awt.*;

public abstract class WindowStatefulBase implements WindowStatefulComponent {

    protected abstract Component getComponent();

    @Override
    public WindowState captureState() {
        Component c = getComponent();
        return new WindowState(
                c.getX(),
                c.getY(),
                c.getWidth(),
                c.getHeight(),
                false,
                false
        );
    }

    @Override
    public void applyState(WindowState s) {
        Component c = getComponent();
        if (c instanceof JFrame f) {
            f.setBounds(s.x, s.y, s.width, s.height);
            f.setExtendedState(s.maximized ? Frame.MAXIMIZED_BOTH : Frame.NORMAL);
            return;
        }
        if (c instanceof JInternalFrame f) {
            f.setLocation(s.x, s.y);
            f.setSize(s.width, s.height);
            return;
        }
        c.setBounds(s.x, s.y, s.width, s.height);
    }
}
