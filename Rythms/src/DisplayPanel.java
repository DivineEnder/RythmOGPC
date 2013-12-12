import java.awt.Color;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
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
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DisplayPanel extends JPanel implements MouseListener
{
	JFrame window;
	Timer t = new Timer();
	Timer game = new Timer();
	float opacity;
	ReadFile testingSong;
	ArrayList<RythmCircle> circles;
	int time = 0;
	boolean gameRunning = false;
	int duration = 0;
	int points = 0;
	
	public DisplayPanel(JFrame frame)
	{
		addMouseListener(this);
		circles = new ArrayList();
		//testingSong = new ReadFile("C:/Users/" + System.getProperty("user.name") + "/Desktop/test.txt");
		testingSong = new ReadFile("E:/OGPC-Workspace/Rythms/data/test.txt");
		window = frame;
		opacity = 0.0f;
		
        setSurfaceSize();
        setBackground(new Color(0, 0, 0));
        t.schedule(new TimerTask()
        {
            public void run() 
            {
            	window.setOpacity(opacity);
            	opacity += 0.003f;
            	if (opacity >= 1)
            	{
            		t.cancel();
            		startGame();
            	}
            }
        }, 5, 10); //delay in ms, period in ms
	}
	
	public void setSurfaceSize()
	{
		Dimension d = new Dimension();
		
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int width = ((int) tk.getScreenSize().getWidth());  
		int height = ((int) tk.getScreenSize().getHeight());  
		
        d.width = width;
        d.height = height;
        setPreferredSize(d);
	}
	
	public int increment(int time)
	{
		time++;
		return time;
	}
	
	public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        /*game.schedule(new TimerTask()
        {
            public void run() 
            {
            	time++;
            	for (int i = 0; i < circles.size(); i++)
            	{
            		if (time == circles.get(i).startTime)
            			circles.get(i).drawCircle(g2d);
            	}
            }
        }, 1, 1);*/
        
        for (int i = 0; i < circles.size(); i++)
        {
        	if (duration == circles.get(i).startTime)
        	{
        		circles.get(i).toggleClickable();
        	}
        	else if (duration >= circles.get(i).startTime && duration <= circles.get(i).duration)
        	{
        		circles.get(i).drawCircle(g2d);
        	}
        	else if (duration > circles.get(i).duration)
        	{
        		circles.get(i).toggleClickable();
        	}
        }
    }
	
	public void readSong() throws IOException
	{
		String[] testSong = testingSong.OpenFile();
		int[][] songInfo = testingSong.toIntList(testSong);
		/*for (int i = 0; i < songInfo.length; i++)
		{
			for (int j = 0; j < songInfo[i].length; j++)
			{
				System.out.println(songInfo[i][j]);
			}
		}*/
		for (int i = 0; i < songInfo.length; i++)
		{
			RythmCircle adding = new RythmCircle(songInfo[i][0], songInfo[i][1], songInfo[i][2], songInfo[i][3]);
			circles.add(adding);
		}
	}
	
	public void startGame()
	{
		try {readSong();} catch (IOException e) {System.out.println(e.getMessage());}
		game.schedule(new TimerTask()
        {
            public void run() 
            {
            	duration++;
            	repaint();
            }
        }, 5, 2); //delay in ms, period in ms
	}

	public void mouseClicked(MouseEvent arg0) 
	{
		PointerInfo p = MouseInfo.getPointerInfo();
        Point point = new Point(p.getLocation());
        SwingUtilities.convertPointFromScreen(point, this);
        //System.out.println("Points: " + points);
        
        for (int i = 0; i < circles.size(); i++)
        {
        	if (circles.get(i).checkClicked(point))
        	{
        		points++;
        		System.out.println("Points: " + points);
        	}
        }
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
