package gui;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class WindowStateManager {

    private final JDesktopPane desktopPane;
    private final File configFile;

    public WindowStateManager(JDesktopPane desktop) {
        this.desktopPane = desktop;
        this.configFile = new File(System.getProperty("user.home"), ".robots.windows");
    }

    public void save() {
        try {
            Properties props = new Properties();
            for (JInternalFrame frame : desktopPane.getAllFrames()) {
                String name = frame.getTitle();
                props.setProperty(name + ".x", String.valueOf(frame.getX()));
                props.setProperty(name + ".y", String.valueOf(frame.getY()));
                props.setProperty(name + ".w", String.valueOf(frame.getWidth()));
                props.setProperty(name + ".h", String.valueOf(frame.getHeight()));
                props.setProperty(name + ".icon", String.valueOf(frame.isIcon()));
                props.setProperty(name + ".max", String.valueOf(frame.isMaximum()));
            }
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                props.store(out, "Robots window state");
            }
        } catch (Exception ignored) {}
    }

    public void load() {
        try {
            if (!configFile.exists()) return;
            Properties props = new Properties();
            try (FileInputStream in = new FileInputStream(configFile)) {
                props.load(in);
            }
            for (JInternalFrame frame : desktopPane.getAllFrames()) {
                String name = frame.getTitle();
                int x = Integer.parseInt(props.getProperty(name + ".x", String.valueOf(frame.getX())));
                int y = Integer.parseInt(props.getProperty(name + ".y", String.valueOf(frame.getY())));
                int w = Integer.parseInt(props.getProperty(name + ".w", String.valueOf(frame.getWidth())));
                int h = Integer.parseInt(props.getProperty(name + ".h", String.valueOf(frame.getHeight())));
                boolean icon = Boolean.parseBoolean(props.getProperty(name + ".icon", "false"));
                boolean max = Boolean.parseBoolean(props.getProperty(name + ".max", "false"));

                frame.setBounds(x, y, w, h);
                if (max) frame.setMaximum(true);
                if (icon) frame.setIcon(true);
            }
        } catch (Exception ignored) {}
    }
}
