package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class GameVisualizer extends JPanel implements Observer {

    private final RobotModel model;

    public GameVisualizer(RobotModel model) {
        this.model = model;
        this.model.addObserver(this);
        setDoubleBuffered(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        EventQueue.invokeLater(this::repaint);
    }

    private static int round(double value) {
        return (int)(value + 0.5);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        int x = round(model.getX());
        int y = round(model.getY());
        double dir = model.getDirection();

        drawRobot(g2d, x, y, dir);
        drawTarget(g2d, model.getTargetX(), model.getTargetY());
    }

    private static void fillOval(Graphics g, int cx, int cy, int w, int h) {
        g.fillOval(cx - w / 2, cy - h / 2, w, h);
    }

    private static void drawOval(Graphics g, int cx, int cy, int w, int h) {
        g.drawOval(cx - w / 2, cy - h / 2, w, h);
    }

    private void drawRobot(Graphics2D g, int x, int y, double direction) {
        AffineTransform old = g.getTransform();
        g.rotate(direction, x, y);

        g.setColor(Color.MAGENTA);
        fillOval(g, x, y, 24, 8);

        g.setColor(Color.BLACK);
        drawOval(g, x, y, 24, 8);

        g.setColor(Color.WHITE);
        fillOval(g, x + 8, y, 4, 4);

        g.setColor(Color.BLACK);
        drawOval(g, x + 8, y, 4, 4);

        g.setTransform(old);
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 4, 4);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 4, 4);
    }
}
