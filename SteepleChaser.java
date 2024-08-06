//SteepleChaser
//Description: This class creates a Robot that completes a steeplechase course.
//Created By: Wentan Su
//Last Modified: Oct 15, 2022
import becker.robots.*;

public class SteepleChaser extends RobotSE
{
	public SteepleChaser(City aCity, int aStreet, int anAvenue, Direction aDirection)
	{
		super(aCity, aStreet, anAvenue, aDirection);
	}
	
	//Run the entire course
	public void runRace()
	{
		//Stop when the flasher is reached
		while (!this.canPickThing())
		{
			this.raceStride();
		}
	}
	
	//Move one intersection forward
	private void raceStride()
	{
		//Determine if a barrier is reached
		if (this.frontIsClear())
		{
			this.move();
		}
		else
		{
			this.jumpBarrier();
		}
	}
	
	//Jump up a barrier and return to ground
	private void jumpBarrier()
	{
		this.turnLeft();
		
		//Keep moving along the barrier
		while (this.isRightWall())
		{
			this.move();
		}
		
		this.move();
		this.turnRight();
		
		//Keep moving until ground is reached
		while (this.frontIsClear())
		{
			this.move();
		}
		
		this.turnLeft();
	}
	
	//Determine if the right side of the Robot is the wall
	private boolean isRightWall()
	{
		this.turnRight();
		
		//Determine if the right side is wall
		if (this.frontIsClear())
		{
			return false;
		}
		else
		{
			this.turnLeft();
			return true;
		}
	}
	
	public static void main(String[] args)
	{
		City course = new City("Steeple.txt");
		SteepleChaser runner = new SteepleChaser(course, 5, 0, Direction.EAST);
		
		runner.runRace();
	} //End of main
} //End of class