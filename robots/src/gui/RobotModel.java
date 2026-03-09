package gui;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class RobotModel extends Observable {

    private volatile double x = 100;
    private volatile double y = 100;
    private volatile double direction = 0;

    private volatile int targetX = 150;
    private volatile int targetY = 100;

    private static final double maxVelocity = 0.1;
    private static final double maxAngularVelocity = 0.001;

    private final Timer timer = new Timer("robot-model", true);

    public RobotModel() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update(10);
            }
        }, 0, 10);
    }

    public synchronized void setTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }

    public synchronized double getX() { return x; }
    public synchronized double getY() { return y; }
    public synchronized double getDirection() { return direction; }
    public synchronized int getTargetX() { return targetX; }
    public synchronized int getTargetY() { return targetY; }

    private static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        return asNormalizedRadians(Math.atan2(toY - fromY, toX - fromX));
    }

    private static double asNormalizedRadians(double angle) {
        while (angle < 0) angle += 2 * Math.PI;
        while (angle >= 2 * Math.PI) angle -= 2 * Math.PI;
        return angle;
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        double newX = x + velocity * duration * Math.cos(direction);
        double newY = y + velocity * duration * Math.sin(direction);

        x = newX;
        y = newY;
        direction = asNormalizedRadians(direction + angularVelocity * duration);
    }

    public synchronized void update(double duration) {
        double dist = distance(targetX, targetY, x, y);
        if (dist < 0.5) return;

        double angleToTarget = angleTo(x, y, targetX, targetY);
        double angleDiff = asNormalizedRadians(angleToTarget - direction);
        if (angleDiff > Math.PI) angleDiff -= 2 * Math.PI;

        double angularVelocity = 0;
        if (angleDiff > 0) angularVelocity = maxAngularVelocity;
        if (angleDiff < 0) angularVelocity = -maxAngularVelocity;

        moveRobot(maxVelocity, angularVelocity, duration);

        setChanged();
        notifyObservers();
    }
}
