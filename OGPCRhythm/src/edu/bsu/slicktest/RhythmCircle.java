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
		clickable = !clickable;
		
		termination++;
	}
	
	//Checks to see if the circle was clicked and returns true or false depending on whether it was or wasn't
	public int keyPressed(char key, Selector s, int points)
	{
		if (!clickable)
			return points;
		
		float selectorAngle = s.returnAngle();
		float distance = Math.abs(selectorAngle - angle);
		
		if (key == 'j' && layer == 5)
		{
			toggleClickable();
			if (distance < 5)
			{
				currentRadius += 5;
				points += Math.round(5 - distance);
			}
		}
		if (key == 'k' && layer == 4)
		{
			toggleClickable();
			if (distance < 5)
				points += Math.round(5 - distance);
		}
		if (key == 'l' && layer == 3)
		{
			toggleClickable();
			if (distance < 5)
				points += Math.round(5 - distance);
		}
		if (key == ';' && layer == 2)
		{
			toggleClickable();
			if (distance < 5)
				points += Math.round(5 - distance);
		}
		if (key == ' ' && layer == 1)
		{
			toggleClickable();
			if (distance < 5)
				points += Math.round(5 - distance);
		}
		
		return points;
	}
	
	public int returnTermination()
	{
		return termination;
	}
	
	public int getLayer()
	{
		return layer;
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
