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
        c.setBounds(s.x, s.y, s.width, s.height);
    }
}
