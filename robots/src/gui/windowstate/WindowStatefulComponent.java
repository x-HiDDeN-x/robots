package gui.windowstate;

public interface WindowStatefulComponent {
    String getStateId();                // id окна
    WindowState captureState();         // снять состояние
    void applyState(WindowState state); // применить состояние
}
