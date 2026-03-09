package gui;

import gui.windowstate.InternalFrameStateful;
import gui.windowstate.MainFrameStateful;
import gui.windowstate.WindowStateManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;

public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    private final WindowStateManager stateManager;

    public MainApplicationFrame() {

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        addWindow(gameWindow);

        stateManager = new WindowStateManager();
        stateManager.register(new MainFrameStateful(this, stateManager));
        stateManager.register(new InternalFrameStateful(logWindow, stateManager));
        stateManager.register(new InternalFrameStateful(gameWindow, stateManager));

        File stateFile = new File(System.getProperty("user.home"), ".robots.windows");
        boolean firstRun = !stateFile.exists();

        stateManager.load();

        if (firstRun) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);

            logWindow.setLocation(30, 100);
            logWindow.setSize(450, 540);

            gameWindow.setLocation(540, 100);
            gameWindow.setSize(620, 540);
        }

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                Object[] options = {
                        Localization.get("exit.ok"),
                        Localization.get("exit.cancel")
                };

                int result = JOptionPane.showOptionDialog(
                        MainApplicationFrame.this,
                        Localization.get("exit.message"),
                        Localization.get("exit.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]
                );

                if (result == JOptionPane.YES_OPTION) {
                    stateManager.save();
                    dispose();
                    System.exit(0);
                }
            }
        });
    }

    public void setLookAndFeel(String className)
    {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ignored) {}
    }

    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(log.Logger.getDefaultLogSource());
        logWindow.setSize(300, 800);
        return logWindow;
    }

    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }
}
