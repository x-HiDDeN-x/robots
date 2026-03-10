package gui;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public final class Localization {

    public enum Lang {RU, EN}

    private static Lang current = Lang.RU;

    private static final Map<String, String> RU = new HashMap<>();
    private static final Map<String, String> EN = new HashMap<>();

    static {
        // Русский
        RU.put("menu.file", "Файл");
        RU.put("menu.exit", "Выход");
        RU.put("menu.view", "Режим отображения");
        RU.put("menu.tests", "Тесты");
        RU.put("menu.view.system", "Системная схема");
        RU.put("menu.view.cross", "Универсальная схема");
        RU.put("menu.tests.log", "Сообщение в лог");

        RU.put("exit.title", "Подтверждение выхода");
        RU.put("exit.message", "Вы действительно хотите закрыть приложение?");
        RU.put("exit.ok", "Закрыть");
        RU.put("exit.cancel", "Отмена");

        RU.put("window.game", "Игровое поле");
        RU.put("window.log", "Протокол работы");
        RU.put("window.coords", "Координаты робота");


        RU.put("log.started", "Протокол работает");

        // Swing на Русском
        RU.put("OptionPane.yesButtonText", "Да");
        RU.put("OptionPane.noButtonText", "Нет");
        RU.put("OptionPane.cancelButtonText", "Отмена");
        RU.put("OptionPane.okButtonText", "ОК");
        RU.put("InternalFrame.closeButtonToolTip", "Закрыть");
        RU.put("InternalFrame.iconButtonToolTip", "Свернуть");
        RU.put("InternalFrame.maxButtonToolTip", "Развернуть");
        RU.put("InternalFrame.restoreButtonToolTip", "Восстановить");
        RU.put("InternalFrameTitlePane.restoreButtonText", "Восстановить");
        RU.put("InternalFrameTitlePane.moveButtonText", "Переместить");
        RU.put("InternalFrameTitlePane.sizeButtonText", "Размер");
        RU.put("InternalFrameTitlePane.minimizeButtonText", "Свернуть");
        RU.put("InternalFrameTitlePane.maximizeButtonText", "Развернуть");
        RU.put("InternalFrameTitlePane.closeButtonText", "Закрыть");
        RU.put("InternalFrameTitlePane.maximizeButtonAccessibleName", "Развернуть");
        RU.put("InternalFrameTitlePane.closeButtonAccessibleName", "Закрыть");


        // Английский
        EN.put("menu.file", "File");
        EN.put("menu.exit", "Exit");
        EN.put("menu.view", "View");
        EN.put("menu.tests", "Tests");
        EN.put("menu.view.system", "System Look");
        EN.put("menu.view.cross", "Cross-platform Look");
        EN.put("menu.tests.log", "Add log message");

        EN.put("exit.title", "Exit confirmation");
        EN.put("exit.message", "Do you really want to exit the application?");
        EN.put("exit.ok", "Exit");
        EN.put("exit.cancel", "Cancel");

        EN.put("window.game", "Game field");
        EN.put("window.log", "Log window");
        EN.put("window.coords", "Robot coordinates");

        EN.put("log.started", "Log started");

        // Swing на Английском
        EN.put("OptionPane.yesButtonText", "Yes");
        EN.put("OptionPane.noButtonText", "No");
        EN.put("OptionPane.cancelButtonText", "Cancel");
        EN.put("OptionPane.okButtonText", "OK");
        EN.put("InternalFrame.closeButtonToolTip", "Close");
        EN.put("InternalFrame.iconButtonToolTip", "Minimize");
        EN.put("InternalFrame.maxButtonToolTip", "Maximize");
        EN.put("InternalFrame.restoreButtonToolTip", "Restore");
        EN.put("InternalFrameTitlePane.restoreButtonText", "Restore");
        EN.put("InternalFrameTitlePane.moveButtonText", "Move");
        EN.put("InternalFrameTitlePane.sizeButtonText", "Size");
        EN.put("InternalFrameTitlePane.minimizeButtonText", "Minimize");
        EN.put("InternalFrameTitlePane.maximizeButtonText", "Maximize");
        EN.put("InternalFrameTitlePane.closeButtonText", "Close");
        EN.put("InternalFrameTitlePane.maximizeButtonAccessibleName", "Maximize");
        EN.put("InternalFrameTitlePane.closeButtonAccessibleName", "Close");
    }

    private Localization() {
    }

    public static void setLanguage(Lang lang) {
        current = lang;
        applySwingLocalization();
    }

    public static String get(String key) {
        return switch (current) {
            case RU -> RU.getOrDefault(key, key);
            case EN -> EN.getOrDefault(key, key);
        };
    }

    private static void applySwingLocalization() {
        Map<String, String> map = (current == Lang.RU ? RU : EN);
        map.forEach((key, value) -> {
            if (key.startsWith("OptionPane.") || key.startsWith("InternalFrame") || key.startsWith("InternalFrameTitlePane")) {
                UIManager.put(key, value);
            }
        });
    }
}
