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
	private ArrayList<Integer> pulseRadius;
	
	RadarCircles(int w, int h)
	{
		center_x = w/2;
		center_y = h/2;
		normal_radius = h/10;
		pulseRadius = new ArrayList();
		for (int i = 0; i < 5; i++)
		{
			pulseRadius.add(0);
		}
		radar = new ArrayList();
		for (int i = 1; i <= 5; i++)
    	{
    		radar.add(new Circle(center_x, center_y, normal_radius * i));
    	}
	}
	
	public void keyPressed(char key)
	{
		if (key == 'j')
			pulseRadius.set(4, 5);
		else if (key == 'k')
			pulseRadius.set(3, 5);
		else if (key == 'l')
			pulseRadius.set(2, 5);
		else if (key == ';')
			pulseRadius.set(1, 5);
		else if (key == ' ')
			pulseRadius.set(0, 5);
	}
	
	public void update()
	{
		for (int i = 0; i < 5; i++)
		{
			if (pulseRadius.get(i) != 0)
				pulseRadius.set(i, pulseRadius.get(i) - 1);
		}
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
    		g.draw(new Circle(center_x, center_y, (normal_radius * i) + pulseRadius.get(i - 1)));
    	}
	}
}
