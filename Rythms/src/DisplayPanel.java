import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DisplayPanel extends JPanel implements MouseListener
{
	
	//Create a variable for the opacity value of the frame window (NOTE::Must be here because otherwise must be final and cannot be assigned)
	float opacity;
	//Create an arraylist of rhythmCircles
	ArrayList<RhythmCircle> circles;
	//Creates a variable for the timing of the game loop (NOTE::Most likely need to change where this is depending on where it needs to be called from)
	int time;
	//Creates a test variable that will be used for determining whether circle gets clicked on
	int points;
	
	public DisplayPanel(JFrame frame)
	{
		//Adds a mouse listener to the JPanel canvas that is this class so that mouse input can be detected
		addMouseListener(this);
		//Intializing some variables
		circles = new ArrayList();
		time = 0;
		points = 0;
		
		//Sets the canvases size
        setSurfaceSize();
        //Sets the background color to black so that the full screen is black
        setBackground(new Color(0, 0, 0));
        
        //Creates and initializes a variable to hold the frame for opacity changes
    	final JFrame window = frame;
    	//Initializes opacity to be 0.0 or transparent at the start
    	opacity = 0.0f;
    	//Creates and initializes a timer for the opacity change of the frame
    	final Timer t = new Timer();
    	//Starts a timer to create fade in effect
        t.schedule(new TimerTask()
        {
            public void run() 
            {
            	//Sets the frames opacity
            	window.setOpacity(opacity);
            	//Adds a constant to the opacity to slowly increment it
            	opacity += 0.003f;
            	//If the opacity is 1.0 or if the screen is fully solid
            	if (opacity >= 1)
            	{
            		//Cancel the timer, stops the timer from running again and basically throws the timer away
            		t.cancel();
            		//Starts the game
            		startGame();
            	}
            }
        }, 5, 10); //delay in ms, period in ms
	}
	
	//Set the canvases size to full screen
	public void setSurfaceSize()
	{
		//Create a new dimension (NOTE::Holds width and height)
		Dimension d = new Dimension();
		
		//This gets a toolkit (NOTE::Not quite sure how this works)
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Gets width and height of the screen and sets it to the dimension width and height
		d.width = ((int) tk.getScreenSize().getWidth());
		d.height = ((int) tk.getScreenSize().getHeight());
		
		//Sets the size of the screen to the dimension (NOTE::The function setPreferredSize takes in dimension as a parameter
        setPreferredSize(d);
	}
	
	//Drawing to the screen done here
	public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        //Creates a graphics2D variable by casting Graphics g with Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        
        //Loop for drawing the rhythm circles 
        for (int i = 0; i < circles.size(); i++)
        {
        	//Check if the game time is equal to the starting time of the rhythm circle
        	if (time == circles.get(i).startTime)
        	{
        		//Then make the circle clickable
        		circles.get(i).toggleClickable();
        	}
        	//Check if the game time is between the starting time and duration
        	else if (time >= circles.get(i).startTime && time <= circles.get(i).duration)
        	{
        		//Then draw the rhythmcircle
        		circles.get(i).drawCircle(g2d);
        	}
        	//Check if the game time is greater than the duration of the rhythm circle
        	else if (time > circles.get(i).duration)
        	{
        		//Then make the circle not clickable
        		circles.get(i).toggleClickable();
        	}
        }
    }
	
	public void readSong() throws IOException
	{
		//Creates and initializes a instance of the ReadFile class for reading files for songs (NOTE::Passes the path for the file to read from to the class)
		ReadFile testingSong = new ReadFile("C:/Users/" + System.getProperty("user.name") + "/Documents/GitHub/RythmOGPC/Rythms/data/test.txt");
		
		//Reads text from file to an array of Strings
		String[] testSong = testingSong.OpenFile();
		//Converts read array of Strings to 2-D int list
		int[][] songInfo = testingSong.toIntList(testSong);
		//Reads the info into an arraylist of rhythmcircles
		for (int i = 0; i < songInfo.length; i++)
		{
			//Creates a new rhythmcircle with the x position, y position, starting time of the circle, and duration of the circle
			RhythmCircle adding = new RhythmCircle(songInfo[i][0], songInfo[i][1], songInfo[i][2], songInfo[i][3]);
			//Adds the circle to the arraylist of rhythmcircles
			circles.add(adding);
		}
	}
	
	public void startGame()
	{
		//Reads song from file
		try {readSong();} catch (IOException e) {System.out.println(e.getMessage());}
		
		//Creates and initializes a timer for the game loop to play the rhythms
		Timer game = new Timer();
		//Game timer schedule to keep track of the time and update the screen
		game.schedule(new TimerTask()
        {
            public void run() 
            {
            	//Increments the time
            	time++;
            	//Updates the screens graphics
            	repaint();
            }
        }, 50, 10); //delay in ms, period in ms
	}
	
	//Detecting the mouse being pressed (NOTE::Used instead of mouseClicked because mouseClicked does not detect rapid fire clicks well)
	public void mousePressed(MouseEvent arg0) 
	{
		//Gets the point info of the mouse pointer
		PointerInfo p = MouseInfo.getPointerInfo();
		//Creates a point (x,y) with the pointer info from the screen
        Point point = new Point(p.getLocation());
        //Converts the point from the screen to a point on the window
        SwingUtilities.convertPointFromScreen(point, this);
        //Testing used to check if mouse detection is working correctly
        System.out.println("Points: " + points);
        
        //Loop to check if the mouse was pressed on a circle
        for (int i = 0; i < circles.size(); i++)
        {
        	//Checks if the point that the mouse was clicked on was on a circle
        	if (circles.get(i).checkClicked(point))
        	{
        		//Testing system that adds points if circle was clicked on and then print out the points
        		points++;
        		System.out.println("Points: " + points);
        	}
        }
	}

	//Excess mouse events that may be needed later for some other reason
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
