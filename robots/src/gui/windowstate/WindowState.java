package gui.windowstate;

public class WindowState {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean iconified;
    public boolean maximized;

    public WindowState(int x, int y, int width, int height,
                       boolean iconified, boolean maximized)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.iconified = iconified;
        this.maximized = maximized;
    }
}
