package edu.bsu.slicktest;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class RadarCircles
{
	private static int center_x;
	private static int center_y;
	private static int normal_radius;
	private ArrayList<Circle> radar;
	private int pulseRadius;
	private int circlePulse;
	
	RadarCircles(int w, int h)
	{
		center_x = w/2;
		center_y = h/2;
		normal_radius = h/10;
		pulseRadius = 0;
		circlePulse = 0;
		radar = new ArrayList();
		for (int i = 1; i <= 5; i++)
    	{
    		radar.add(new Circle(center_x, center_y, normal_radius * i));
    	}
	}
	
	public void keyPressed(char key)
	{
		if (key == 'j')
		{
			pulseRadius = 5;
			circlePulse = 5;
		}
		else if (key == 'k')
		{
			pulseRadius = 5;
			circlePulse = 4;
		}
		else if (key == 'l')
		{
			pulseRadius = 5;
			circlePulse = 3;
		}
		else if (key == ';')
		{
			pulseRadius = 5;
			circlePulse = 2;
		}
		else if (key == ' ')
		{
			pulseRadius = 5;
			circlePulse = 1;
		}
	}
	
	public void update()
	{
		if (pulseRadius == 0)
			circlePulse = 0;
		else
			pulseRadius--;
	}
			
	public void draw(Graphics g)
	{
		Color myAlphaColor = new Color(72,131,218,1);
		//Sets the drawing color to blue
		g.setColor(Color.blue);
		//g.setColor(myAlphaColor);
    	//Draws the five "radar" circles based on the screen width and height
    	for (int i = 1; i <= 5; i++)
    	{
    		if (i == circlePulse)
    			g.draw(new Circle(center_x, center_y, (normal_radius * i) + pulseRadius));
    		else
    			g.draw(radar.get(i-1));
    	}
	}
}
