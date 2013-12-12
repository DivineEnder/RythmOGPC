import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class RythmCircle
{
	int duration; // in ms
	int x; //x pos on screen
	int y; //y pos on screen
	static int width = 50; //width of circles
	static int height = 50; //height of circles
	int startTime; //timing of circle in song
	boolean clickable; //tells the game if the circle is displayed and clickable
	
	RythmCircle(int px, int py, int sT, int d)
	{
		clickable = false;
		x = px;
		y = py;
		startTime = sT;
		duration = d + sT;
	}
	
	public void toggleClickable()
	{
		if (!clickable)
			clickable = true;
		else
			clickable = false;
	}
	
	public boolean checkClicked(Point clickPoint)
	{
		boolean inside = false;
		
		if (clickable)
		{
			if (Math.sqrt(Math.pow(clickPoint.x - x, 2) + Math.pow(clickPoint.y - y, 2)) < 25)
			{
				inside = true;
			}
		}
		
		return inside;
	}
	
	public void drawCircle(Graphics2D g2d)
	{
		g2d.setPaint(Color.cyan);
        g2d.fillOval(x, y, width, height);
	}
}
