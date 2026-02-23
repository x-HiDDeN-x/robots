package gui;

import log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class MainMenuBuilder {

    private final MainApplicationFrame frame;

    public MainMenuBuilder(MainApplicationFrame frame) {
        this.frame = frame;
    }

    public JMenuBar build() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createLookAndFeelMenu());
        menuBar.add(createTestMenu());
        return menuBar;
    }

    private JMenuItem item(String key, Integer mnemonic, Runnable action) {
        JMenuItem item = new JMenuItem(Localization.get(key));
        if (mnemonic != null) item.setMnemonic(mnemonic);
        item.addActionListener(e -> action.run());
        return item;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu(Localization.get("menu.file"));
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem exit = item(
                "menu.exit",
                KeyEvent.VK_Q,
                () -> Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
                        new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)
                )
        );
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        fileMenu.add(exit);
        return fileMenu;
    }

    private JMenu createLookAndFeelMenu() {
        JMenu lookAndFeelMenu = new JMenu(Localization.get("menu.view"));
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.add(item(
                "menu.view.system",
                KeyEvent.VK_S,
                () -> {
                    frame.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    frame.invalidate();
                }
        ));
        lookAndFeelMenu.add(item(
                "menu.view.cross",
                KeyEvent.VK_S,
                () -> {
                    frame.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    frame.invalidate();
                }
        ));
        return lookAndFeelMenu;
    }

    private JMenu createTestMenu() {
        JMenu testMenu = new JMenu(Localization.get("menu.tests"));
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.add(item(
                "menu.tests.log",
                KeyEvent.VK_S,
                () -> Logger.debug("Новая строка")
        ));
        return testMenu;
    }
}
