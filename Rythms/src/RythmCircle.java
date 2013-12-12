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
			if (clickPoint.x <= (x + width/2) && clickPoint.x >= (x + width/2) && clickPoint.y <= (y + height/2) && clickPoint.y >= (y + height/2))
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
