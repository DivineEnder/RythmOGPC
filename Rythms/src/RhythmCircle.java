import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class RhythmCircle
{
	//Creates a variable for the duration of the circle, i.e. how long it lasts in ms
	int duration;
	//Creates a variable for the x position of the circle on the screen
	int x;
	//Creates a variable for the y position of the circle on the screen
	int y;
	//Creates a static variable for the width of the rhythm circles (NOTE::Really only needed so it can be referenced easily and not changed easily)
	static int width = 50;
	//Creates a static variable for the height of the rhythm circles (NOTE::Really only needed so it can be referenced easily and not changed easily)
	static int height = 50;
	//Creates a variable for starting time of the circle (NOTE::Timer workings are unsure, but most likely start time in ms)
	int startTime;
	//Creates a variable to determine whether or not the circle is displayed and clickable
	boolean clickable;
	
	RhythmCircle(int px, int py, int sT, int d)
	{
		//Initializes clickable to false since circles start out not shown
		clickable = false;
		//Initializes some variables to passed parameters to the class
		x = px;
		y = py;
		startTime = sT;
		//Initializes duraiton of the circle to the start time + the duration (NOTE::At the moment to draw circle there is a check to see if the timer is within certain bounds, between start time and duration, this is why this needs to be this way, can be changed later on if needed or if better)
		duration = d + sT;
	}
	
	//Toggles whether the circle is clickable (NOTE::Could be done manually, but was easier to simply call the function so that you didn't have to screw around with other classes variables)
	public void toggleClickable()
	{
		//If clickable is false then set to true
		if (!clickable)
			clickable = true;
		//If clickable is true then set to false
		else
			clickable = false;
	}
	
	//Checks to see if the circle was clicked and returns true or false depending on whether it was or wasn't
	public boolean checkClicked(Point clickPoint)
	{
		//Creates and intializes a variable for whether or not the point passed is inside or outside the circle
		boolean inside = false;
		
		//Check to see if the circle is clickable first
		if (clickable)
		{
			//Check the distance between the circle center and the point passed is less than the radius of the circle through the distance formula (NOTE::Circle drawing is a little unclear, radius should be width/2 but unclear, and x and y are hopefully the center of the circle but also unclear)
			if (Math.sqrt(Math.pow(clickPoint.x - x, 2) + Math.pow(clickPoint.y - y, 2)) < width/2)
			{
				//Set inside to true because the point is inside the circle
				inside = true;
			}
		}
		
		//Return whether or not the point is inside the circle in the form of a bool
		return inside;
	}
	
	public void drawCircle(Graphics2D g2d)
	{
		g2d.setPaint(Color.cyan);
        g2d.fillOval(x, y, width, height);
	}
}
