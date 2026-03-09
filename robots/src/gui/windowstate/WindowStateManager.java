package gui.windowstate;

import java.io.*;
import java.util.*;

public class WindowStateManager {

    private final Map<String, WindowState> lastApplied = new HashMap<>();
    private final List<WindowStatefulComponent> windows = new ArrayList<>();
    private final File configFile = new File(System.getProperty("user.home"), ".robots.windows");

    public void register(WindowStatefulComponent w) {
        windows.add(w);
    }

    public void load() {
        try {
            Properties props = new Properties();

            if (configFile.exists()) {
                try (FileInputStream in = new FileInputStream(configFile)) {
                    props.load(in);
                }
            }

            for (WindowStatefulComponent w : windows) {
                WindowState def = w.captureState();
                WindowState loaded = WindowStateSerializer.load(props, w.getStateId(), def);
                lastApplied.put(w.getStateId(), loaded);
                w.applyState(loaded);
            }

        } catch (Exception ignored) {}
    }

    public void save() {
        try {
            Properties props = new Properties();

            for (WindowStatefulComponent w : windows) {
                WindowState s = lastApplied.get(w.getStateId());
                if (s == null) s = w.captureState();
                WindowStateSerializer.save(props, w.getStateId(), s);
            }

            try (FileOutputStream out = new FileOutputStream(configFile)) {
                props.store(out, "Robots window state");
            }

        } catch (Exception ignored) {}
    }

    public void updateApplied(WindowStatefulComponent w, WindowState s) {
        lastApplied.put(w.getStateId(), s);
    }
}
