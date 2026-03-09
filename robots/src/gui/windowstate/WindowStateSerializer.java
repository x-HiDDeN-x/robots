package gui.windowstate;

import java.util.Properties;

public class WindowStateSerializer {

    private static final String[] intFields = {"x", "y", "w", "h"};
    private static final String[] boolFields = {"icon", "max"};

    public static WindowState load(Properties props, String id, WindowState def) {
        int[] ints = {def.x, def.y, def.width, def.height};
        boolean[] bools = {def.iconified, def.maximized};
        for (int i = 0; i < intFields.length; i++) {
            ints[i] = getInt(props, key(id, intFields[i]), ints[i]);
        }
        for (int i = 0; i < boolFields.length; i++) {
            bools[i] = getBool(props, key(id, boolFields[i]), bools[i]);
        }
        return new WindowState(ints[0], ints[1], ints[2], ints[3], bools[0], bools[1]);
    }

    public static void save(Properties props, String id, WindowState s) {
        int[] ints = {s.x, s.y, s.width, s.height};
        boolean[] bools = {s.iconified, s.maximized};
        for (int i = 0; i < intFields.length; i++) {
            props.setProperty(key(id, intFields[i]), String.valueOf(ints[i]));
        }
        for (int i = 0; i < boolFields.length; i++) {
            props.setProperty(key(id, boolFields[i]), String.valueOf(bools[i]));
        }
    }

    private static String key(String id, String field) {
        return id + "." + field;
    }

    private static int getInt(Properties props, String key, int def) {
        try {
            return Integer.parseInt(props.getProperty(key, String.valueOf(def)));
        } catch (Exception e) {
            return def;
        }
    }

    private static boolean getBool(Properties props, String key, boolean def) {
        try {
            return Boolean.parseBoolean(props.getProperty(key, String.valueOf(def)));
        } catch (Exception e) {
            return def;
        }
    }
}
