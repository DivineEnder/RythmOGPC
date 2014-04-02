package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import org.newdawn.slick.geom.Circle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.ResourceLoader;

public class MainRhythms extends BasicGame
{
	//Creates the selector class instance (NOTE::Most likely going to be moved somewhere else)
	Selector selector;
	//Creates an arraylist of rhythmcircles (NOTE::Not used at the moment, most likely used later)
	ArrayList<RhythmCircle> circles;
	//Creates a variable for the computer screen width
	static int screen_width;
	//Creates a variable for the computer screen height
	static int screen_height;
	//Creates an instance of the Debug class so that debug mode can be activated
	Debug debug;
	//Creates a variable for special keyboard input that is not support in the slick library
	String specialInput;
	//Creates a music variable to hold the song that will be played (NOTE::This is temporary for testing rhythms)
	Music song;
	
	//This is a test RhyhtmCircle that is drawn on the screen
	RhythmCircle test = new RhythmCircle(350, 450, 0, 0);
	
    public MainRhythms()
    {
        super("Rhythms");
    }
    
    //Creates a function that tests for special input that is not detected by slick2d
    @Override
    public void keyPressed(int key, char c)
    {
        if (c == '`')
            specialInput += "`";
    }
  
    //Initializes variables and class instances
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	//Initializes an arraylist of RhythmCircles to be used for songs
    	circles = new ArrayList();
    	//Initializes a the selector class, passing it the width and height of the screen
    	selector = new Selector(screen_width, screen_height);
    	//Initializes the debug class which will be used for testing render and update methods (NOTE::Could be used for other debug functions. Put anything that the player should not see, but could be useful in there)
    	debug = new Debug();
    	//Initializes the special input variable to a string with nothing inside so that it can be compared without throwing an error if a special key was not pressed
    	specialInput = "";
    	//Initializes the music (NOTE::The path to the file where the music is located is sent as a string variable when initialized. Music class only supports two formats it seems. We will use wavs as those seem more common)
    	song = new Music("data/funkyw.wav");
    	
    	//-----TESTING-----
    	//Testing to see if the music was loaded correctly and plays
    	//Sets the music volume to max
    	song.setVolume(1);
    	//Starts to play the song
    	song.play();
    	
    	//-----TESTING-----
    	//int[][] data;
    	//ReadSong read = new ReadSong("E:/Circles.txt");
    	//try {
		//	data = read.OpenFile();
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
    }
    
    //Updates the game state (NOTE::Normally called roughly ever 16 ms with slight fluctuation. To check time between updates on current machine activate debug mode with `)
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between updates
    	debug.setUpdateTimes(gc.getTime());
    	
    	//Sets a variable which will grab input using slick libraries
    	Input input = gc.getInput();
    	
    	//Checks to see if a special input key was pressed
    	if (specialInput.equals("`"))
    	{
    		//Toggles debug mode on and off
    		debug.debugToggle();
    		//Sets the specialInput variable back to an empty string so that it can be used again
    		specialInput = "";
    	}
    	
    	
    	//Example of how to use the built in slick input to detect input
    	/*if (input.isKeyPressed(Input.KEY_BACKSLASH))
    	{
    		debug.debugToggle();
    	}*/
    	
    	//Updates the selctors position
    	selector.updateSelector();
    }
    
    //Renders the game
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between renders
    	debug.setRenderTimes(gc.getTime());
    	
    	//Sets the drawing color to blue
    	g.setColor(Color.blue);
    	//Draws the five "radar" circles based on the screen width and height
    	for (int i = 1; i <= 5; i++)
    	{
    		g.draw(new Circle(screen_width/2, screen_height/2, (screen_height/10) * i));
    	}
    	
    	//Draw the selector
    	selector.drawSelector(g);
    	
    	//-----Testing-----
    	//Draws a test circle (NOTE::Selector is passed to the draw function so that the circle can determine from the position of the selector when to start drawing)
    	test.drawCircle(g, selector);
    	
    	//Draws debug mode
    	debug.draw(g);
    	
    	//Turn off FPS counter in top left
    	//gc.setShowFPS(false);
    }
    
    public static MainRhythms getReference()
    {
    	MainRhythms Game = new MainRhythms();
    	return Game;
    }
  
    public static void main(String[] args) throws SlickException, InterruptedException, BrokenBarrierException
    {
        AppGameContainer app = new AppGameContainer( new MainRhythms() );
         
        screen_width = app.getScreenWidth();
        screen_height = app.getScreenHeight();
         
        app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
        app.setFullscreen(true);
        app.setTargetFrameRate(60);
        app.setVSync(true);
        app.start();
    }
}