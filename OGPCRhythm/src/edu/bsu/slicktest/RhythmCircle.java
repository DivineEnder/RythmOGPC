package edu.bsu.slicktest;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
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
	private float angle;
	private int rotation;
	private int layer;
	
	private int termination;
	
	
	RhythmCircle(float a, int r, int l, GameContainer gc)
	{
		//Initializes clickable to false since circles start out not shown
		clickable = false;
		//Initializes some variables to passed parameters to the class
		angle = a;
		rotation = r;
		layer = l;
		x = gc.getScreenWidth()/2 + (float)((gc.getScreenHeight()/10 * l) * Math.cos(angle * (Math.PI/180)));
		y = gc.getScreenHeight()/2 + (float)((gc.getScreenHeight()/10 * l) * Math.sin(angle * (Math.PI/180)));
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
	public boolean keyPressed(char key, int screen_height, Selector s)
	{
		boolean hit = false;
		float[] selectorPoint = new float[2];
		
		if (key == 'j')
		{
			selectorPoint = s.returnPosition(screen_height);
		}
		
		return hit;
	}
	
	public int returnTermination()
	{
		return termination;
	}
	
	public void smoothAppear()
	{
		if (currentRadius <= target_radius)
			currentRadius += speed;
	}
	
	public void drawCircle(Graphics g, Selector s)
	{
		if (s.getPosition(angle, rotation) && termination != 1)
			toggleClickable();
		if (clickable)
		{
			smoothAppear();
			//Sets the drawing color for the circles
			g.setColor(Color.red);
			//Fills a slick circle at the indicated x and y coordinates and with the given radius and the color set in the line above
			g.fill(new Circle(x, y, (float)currentRadius));
			if (s.passed(angle))
				toggleClickable();
		}
	}
}
