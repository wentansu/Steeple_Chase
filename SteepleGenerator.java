//Randomly generate a Steeple course to a text file Steeple.txt.
//The course has at most 11 barriers that have a height of at most 5.
//The text file is used to create a City in the SteepleChaser class.
//Oct 16 2022
import java.io.*;

public class SteepleGenerator
{
	public static void main(String[] args) throws IOException
	{
		PrintWriter output = new PrintWriter(new FileWriter("Steeple.txt"));
		
		final String OBJECT = "becker.robots.Wall ";
		final String SPACE = " ";
		final String DIRECTION = " Direction.EAST";
		
		int barriers = (int) (Math.random() * 12);
		int[] avenue = new int[barriers];
		int height, num;
		boolean isRepeat = false;
		
		//Initial declarations of the City
		output.println("Steeplechase\n0 0 7 13\n38\n");
		
		//The ground which is 13 horizontal Walls
		for (int i = 0; i < 13; i++)
		{
			output.println("becker.robots.Wall 6 " + i + " Direction.NORTH");
		}
		
		//Randomly assign a different avenue for each barrier
		//Not efficient enough when barriers > 11
		for (int i = 0; i < barriers; i++)
		{	
			do
			{
				num = (int) (Math.random() * 11 + 1);
				
				for (int j = 0; j < i; j++)
				{
					//Determine if num is already in avenue
					if (avenue[j] == num)
					{
						isRepeat = true;
						break;
					}
					else
					{
						isRepeat = false;
					}
				}
			}
			while (isRepeat);
			
			avenue[i] = num;
		}
		
		//Randomly assign a height to each barrier and output each in a single line
		for (int i = 0; i < barriers; i++)
		{
			height = (int) (Math.random() * 5 + 1);
			
			//Output all Walls needed to create each barrier
			for (int j = 0; j < height; j++)
			{
				output.println(OBJECT + (5 - j) + SPACE + avenue[i] + DIRECTION);
			}
		}
		
		//The flasher indicates the end of the course
		output.println("becker.robots.Flasher 5 12 true");
		
		output.close();
		
		System.out.println("Steeple Generated.");
	}
}
