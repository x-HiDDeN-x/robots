package gui.windowstate;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class InternalFrameStateful extends WindowStatefulBase {

    private final JInternalFrame frame;
    private final WindowStateManager manager;

    public InternalFrameStateful(JInternalFrame frame, WindowStateManager manager) {
        this.frame = frame;
        this.manager = manager;

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }

            @Override
            public void componentResized(ComponentEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }
        });

        frame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                manager.updateApplied(InternalFrameStateful.this, captureState());
            }
        });
    }

    @Override
    public String getStateId() {
        return frame.getTitle();
    }

    @Override
    protected Component getComponent() {
        return frame;
    }

    @Override
    public WindowState captureState() {
        WindowState s = super.captureState();
        s.iconified = frame.isIcon();
        s.maximized = frame.isMaximum();
        return s;
    }

    @Override
    public void applyState(WindowState s) {
        frame.setLocation(s.x, s.y);
        frame.setSize(s.width, s.height);

        SwingUtilities.invokeLater(() -> {
            try {
                frame.setMaximum(s.maximized);
                frame.setIcon(s.iconified);
            } catch (Exception ignored) {}
        });
    }
}
