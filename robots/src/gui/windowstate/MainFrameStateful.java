package gui.windowstate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrameStateful extends WindowStatefulBase {

    private final JFrame frame;
    private final WindowStateManager manager;

    public MainFrameStateful(JFrame frame, WindowStateManager manager) {
        this.frame = frame;
        this.manager = manager;

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                manager.updateApplied(MainFrameStateful.this, captureState());
            }

            @Override
            public void componentResized(ComponentEvent e) {
                manager.updateApplied(MainFrameStateful.this, captureState());
            }
        });
    }

    @Override
    public String getStateId() {
        return "MainFrame";
    }

    @Override
    protected Component getComponent() {
        return frame;
    }

    @Override
    public WindowState captureState() {
        WindowState s = super.captureState();
        int state = frame.getExtendedState();
        s.maximized = (state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
        return s;
    }
}
