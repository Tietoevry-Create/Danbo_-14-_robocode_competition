package competition_bots;
import robocode.*;
import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * $CLASSNAME - a robot by (your name here)
 */
public class Blaster7000 extends Robot
{
    
	boolean stopWhenSeeRobot = false; // See goCorner()
	static int corner = 3; // Which corner we are currently using
  static boolean wallFound = false;
  static int dir = 1;
  public static Color ColorRobot = Color.blue;
  public static Color ColorGun = Color.red;
  public static Color ColorRadar = Color.red;
  

	/**
	 * run: $CLASSNAME's default behavior
	 */
   public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

    setColors(Color.blue, Color.black, Color.black);
    setBulletColor(Color.red);
    out.println("Number of robots " + getNumSentries());
		// Robot main loop
		while(true) {
      // Replace the next 4 lines with any behavior you would like
      
      // Go to a corner
    fire(1);
		goCorner();
      	//ahead(1000);
        // fire(1);
        // turnGunRight(90);
        // fire(1);
        // turnGunRight(90);
			// turnGunRight(360);
			// back(100);
			// turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
     
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		// fire(1);
        double enemy;
        
        enemy = e.getEnergy();
        fire(3);

        
        setColors(Color.red, Color.PINK, Color.GREEN);
        // back(1000);
        
        
    //     turnGunRight(1);
    //     fire(1);
    //     turnGunLeft(2);
    //     fire(1);
	}

  
    
    //If we die...
    public void onDeath(DeathEvent e) {
        out.println("You guys got lucky this time, we will be back");
        //setColors(Color.BLACK,Color.BLACK,Color.BLACK);
        setAllColors(Color.BLACK);
    }

    //When we win ofc
    public void onWin(WinEvent e) {
        setColors(Color.YELLOW,Color.YELLOW,Color.YELLOW);
    }

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
    
    if(dir == 1) {
      dir = -1;
    }
    else {
      dir = 1;
    }
		// back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
    // turnRight(90);
		// Replace the next line with any behavior you would like
		// ahead(1000);
	}	

  public void getPos() {
    
  }

	public void goCorner() {
		// We don't want to stop when we're just turning...
     
    turnGunRight(10);
		// if(!wallFound){
      stopWhenSeeRobot = false;
      // turn to face the wall to the "right" of our desired corner.
      turnRight(normalRelativeAngleDegrees(corner - getHeading()));
      // Ok, now we don't want to crash into any robot in our way...
      stopWhenSeeRobot = true;
      // Move to that wall
      // back(5000);
      drive();
      // Turn to face the corner
      turnLeft(90);
      // Move to the corner
      drive();
      // ahead(5000);
      // Turn gun to starting point
      // turnGunLeft(90);
      wallFound = true;
    // }
    // drive();
	}
  public void drive() {
    if(dir == 1) {
      ahead(500);
    }
    else {
      back(500);
    }
  }
}


