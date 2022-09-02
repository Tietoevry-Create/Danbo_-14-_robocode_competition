package competition_bots;
import robocode.*;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * $CLASSNAME - a robot by (your name here)
 */
public class AaneiDinBotErDod extends RateControlRobot {
	boolean movingForward = false;
	/**
	 * run: $CLASSNAME's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// Set colors
		var hotpink = new Color(255, 105,180);
		setBodyColor(hotpink);
		setGunColor(hotpink);
		setRadarColor(hotpink);
		setBulletColor(hotpink);
		setScanColor(hotpink);

		// Robot main loop
		while(true) {
			// if (!movingForward) {
			// 	continue;
			// }
			setTurnGunRight(10000);
			setTurnLeft(10000);
			setMaxVelocity(Rules.MAX_VELOCITY);
			ahead(10000);
			beAggressive();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
		
    // double distance = e.getDistance(); //get the distance of the scanned robot
    // if (distance > 800) //this conditions adjust the fire force according the distance of the scanned robot.
		// 	fire(3.0);
    // else if (distance > 600 && distance <= 800)
		// 	fire(2.5);
    // else if (distance > 400 && distance <= 600)
		// 	fire(2.0);
    // else if (distance > 200 && distance <= 400)
		// 	fire(1.5);
    // else
		// 	fire(1);

		// Our target is too close!  Back up.
		if (e.getDistance() < 100) {
			if (e.getBearing() > -90 && e.getBearing() <= 90) {
				back(40);
			} else {
				ahead(40);
			}
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
		setTurnLeft(50);
	}
	
	// /**
	//  * onHitWall: What to do when you hit a wall
	//  */
	// public void onHitWall(HitWallEvent e) {
	// 	// Move away from the wall
	// 	setVelocityRate(-1 * getVelocityRate());
	// }	

	private void beAggressive() {
		setAhead(40000);
		movingForward = true;

		setTurnRight(90);
		waitFor(new TurnCompleteCondition(this));

		setTurnLeft(180);
		waitFor(new TurnCompleteCondition(this));

		setTurnRight(180);
		waitFor(new TurnCompleteCondition(this));
	}
	
	public void onHitRobot(HitRobotEvent e) {
		var isWithinShootingRange = e.getBearing() > -10 && e.getBearing() < 10;
		var shouldTurnLeft = e.getBearing() <= -10;
		var shouldTurnRight = e.getBearing() >= 10;

		if (isWithinShootingRange) {
			fire(3);
			return;
		}

		if (shouldTurnLeft) {
			turnLeft(-e.getBearing());
			scan();
		} else {
			turnRight(-e.getBearing());
			scan();
		}
	}

	/**
	 * onWin:  Do a victory dance
	 */
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}
}
