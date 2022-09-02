package competition_bots;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * $CLASSNAME - a robot by (your name here)
 */
public class BotenAnna extends Robot {
    /**
     * run: $CLASSNAME's default behavior
     */

    int counter = 0;
    public void run() {
        // Initialization of the robot should be put here
        setBulletColor(Color.green);
        setColors(Color.magenta,Color.MAGENTA,Color.ORANGE);
        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        setColors(Color.red, Color.blue, Color.green); // body,gun,radar

        // Robot main loop
        while (true) {
            // Replace the next 4 lines with any behavior you would like
            ahead(30);
            back(30);
            turnRadarLeft(360);
            for (int i = 0; i < 3; i++) {
                turnRadarLeft(360);
                ahead(100);
                back(100);
            }
            ahead(300);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        //turnRight(90 - getHeading() + e.getBearing());
        turnGunRight(getHeading() - getGunHeading() + e.getBearing());
        fire(400 / e.getDistance());

    }


    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        turnRight(e.getBearing()-45);
        ahead(100);
    }

    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        counter++;
        int distance = 100;
        if(counter>2){
            distance = 500;
            counter = 0;
        }
        if(e.getBearing() < 0){
            back(distance);
        }
        else{
            ahead(distance);
        }

    }

}
