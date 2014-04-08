package edu.bsu.slicktest;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Circle;

public class RhythmCircle
{
	//Creates a variable for the x position of the circle on the screen
	private float x;
	//Creates a variable for the y position of the circle on the screen
	private float y;
	private double currentRadius;
	private double speed;
	//Creates a static variable for the target radius of the rhythm circles (NOTE::Really only needed so it can be referenced easily and not changed easily)
	private static int target_radius = 25;
	//Creates a variable to determine whether or not the circle is displayed and clickable
	private boolean clickable;
	private int termination;
	
	
	RhythmCircle(float px, float py)
	{
		//Initializes clickable to false since circles start out not shown
		clickable = false;
		//Initializes some variables to passed parameters to the class
		x = px;
		y = py;
		speed = .1;
		termination = -1;
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
		
		termination++;
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
			if (Math.sqrt(Math.pow(clickPoint.x - (x + target_radius), 2) + Math.pow(clickPoint.y - (y + target_radius), 2)) < target_radius)//Math.sqrt(Math.pow(clickPoint.x - x, 2) + Math.pow(clickPoint.y - y, 2)) < width/2)
			{
				//clickPoint.x <= (x + width/2) && clickPoint.x >= (x + width/2) && clickPoint.y <= (y + height/2) && clickPoint.y >= (y + height/2)
				//Set inside to true because the point is inside the circle
				inside = true;
			}
		}
		
		//Return whether or not the point is inside the circle in the form of a bool
		return inside;
	}
	
	public void smoothAppear()
	{
		if (currentRadius <= target_radius)
			currentRadius += speed;
	}
	
	public void drawCircle(Graphics g, Selector s)
	{
		if (s.getPosition(x, y) && termination != 1)
			toggleClickable();
		if (clickable)
		{
			smoothAppear();
			//Sets the drawing color for the circles
			g.setColor(Color.red);
			//Fills a slick circle at the indicated x and y coordinates and with the given radius and the color set in the line above
			g.fill(new Circle(x, y, (float)currentRadius));
		}
	}
}
