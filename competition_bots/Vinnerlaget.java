package competition_bots;
import robocode.*;
import java.awt.Color;
import java.util.Random;

import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * $CLASSNAME - a robot by (your name here)
 */
public class Vinnerlaget extends Robot
{
	int count = 0;
	double gunTurnAmt;
	double robotForward;
	String trackName;
	double firePower;

	static int corner = 0;
	boolean stopWhenSeeRobot = true;

	boolean didTryLeft = false;

	/**
	 * run: $CLASSNAME's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		trackName = null;
		setAdjustGunForRobotTurn(true);
		gunTurnAmt = 180;
		robotForward = 0;
		firePower = 1;



		// Robot main loop
		while(true) {
			goToCorner(getVelocity() == 0);
			
			turnGunRight(gunTurnAmt);
			ahead(100);
			count++;

			if (count > 2){
				gunTurnAmt = -180;
				robotForward = 50;
			}
			if (count > 4) {
				gunTurnAmt = 180;
				robotForward = 50;
			}
			if (count > 8) {
				trackName = null;
			}
		}
	}

	public void goToCorner(boolean shouldExecute) {
		if (!shouldExecute) return;
		// We don't want to stop when we're just turning...
		stopWhenSeeRobot = false;
		// turn to face the wall to the "right" of our desired corner.

		if (!didTryLeft) {
			turnRight(normalRelativeAngleDegrees(corner - getHeading()));
			didTryLeft = true;
		} else {
			turnLeft(normalRelativeAngleDegrees(corner - getHeading()));
			didTryLeft = false;
		}

		// Ok, now we don't want to crash into any robot in our way...
		stopWhenSeeRobot = true;
		// Move to that wall
		ahead(5000);
		// Turn to face the corner
		turnLeft(90);
		// Move to the corner
		ahead(5000);
		// Turn gun to starting point
		turnGunLeft(90);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {


		if (trackName == null) {
			trackName = e.getName();
			count = 0;
		};

		if (e.getDistance() > 100) {
			//gunTurnAmt = normalRelativeAngleDegrees(e.getBearings() + (getHeading() - getRadarHeading()));
			turnGunRight(gunTurnAmt);
			return;
			
		}

		fire(5);
	
		scan();
		
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		
		Random randgen = new Random();

        int int_dir = randgen.nextInt(2);

        int int_amount = randgen.nextInt(100)+ 20;

        int int_angle = randgen.nextInt(2);



        switch (int_dir) {

            case 0:

                ahead(int_amount);

                break;

            case 1:

                back(int_amount);

                break;

            case 2:

                break;

        }

        switch(int_angle) {

            case 0:

                turnLeft(int_amount);

                break;

            case 1:

                turnRight(int_amount);

                break;

            case 2:

                break;

        }

	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(50);
	}	

	public void onDeath(DeathEvent e) {
		System.out.print("OMG!");
	}
}
