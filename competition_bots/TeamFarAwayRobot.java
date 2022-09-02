package competition_bots;
import robocode.*;

import java.util.Arrays;
import java.util.Vector;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * $CLASSNAME - a robot by (your name here)
 */
public class MyBot extends Robot
{
	/**
	 * run: $CLASSNAME's default behavior
	 */
	Vector<Vector<Double>> positions = new Vector<>();
	int i = 0;
	int samples = 0;
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:


		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnRight(90);
			turnGunLeft(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		var dist = e.getDistance();
		var bearing = e.getBearing();
		var y = dist * Math.acos(bearing / 180.0 * Math.PI);
		var x = dist * Math.asin(bearing / 180.0 * Math.PI);

		positions.add(i, new Vector(Arrays.asList(x, y, (double)System.currentTimeMillis())));
		i += 1;
		if (i > 10) { i = 0; }
		samples += 1;

		if (samples > 10)
		{
			var first = positions.get(i);
			var last = positions.get(i-1<0?10:i-1);

			var d_t = last.get(2) - first.get(2);
			var d_x = (last.get(0) - first.get(0)) / d_t;
			var d_y = (last.get(1) - first.get(1)) / d_t;

			var C = 1.0;
			var predicted_x = last.get(0) + d_x * C;
			var predicted_y = last.get(1) + d_y * C;

			var leading_ang = Math.atan2(predicted_x, predicted_y);
			// ignore all above
			//System.out.println(leading_ang);
			fire(3);
			turnGunRight(30);

			System.out.println("---");
			System.out.println(predicted_x);
			System.out.println(predicted_y);
		}
		else
		{
			fire(3);
			turnGunRight(30);
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(100);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(180);
		ahead(100);
	}	
}