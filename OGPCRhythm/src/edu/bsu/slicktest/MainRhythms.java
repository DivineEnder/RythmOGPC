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
	RadarCircles radar;
	
	//This is a test RhyhtmCircle that is drawn on the screen
	RhythmCircle test;
	RhythmCircle test2;
	RhythmCircle test3;
	
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
    	//double deltaX1 = (screen_height/10 * 2) * Math.cos(1 * (Math.PI / 180));
		//double deltaY1 = (screen_height/10 * 2) * Math.sin(1 * (Math.PI / 180));
    	test = new RhythmCircle(45, 0, 5, gc);
    	//double deltaX = (screen_height/10 * 5) * Math.cos(135 * (Math.PI / 180));
		//double deltaY = (screen_height/10 * 5) * Math.sin(135 * (Math.PI / 180));
    	test2 = new RhythmCircle(135, 1, 3, gc);
    	test3 = new RhythmCircle(80, 1, 5, gc);
    	//Initializes an arraylist of RhythmCircles to be used for songs
    	circles = new ArrayList();
    	//circles.add(test);
    	//circles.add(test2);
    	//circles.add(test3);
    	//Initializes a the selector class, passing it the width and height of the screen
    	selector = new Selector(screen_width, screen_height);
    	//Initializes the debug class which will be used for testing render and update methods (NOTE::Could be used for other debug functions. Put anything that the player should not see, but could be useful in there)
    	debug = new Debug();
    	//Initializes the special input variable to a string with nothing inside so that it can be compared without throwing an error if a special key was not pressed
    	specialInput = "";
    	//Initializes the music (NOTE::The path to the file where the music is located is sent as a string variable when initialized. Music class only supports two formats it seems. We will use wavs as those seem more common)
    	song = new Music("data/funkyw.wav");
    	radar = new RadarCircles(screen_width, screen_height);
    	
    	//-----TESTING-----
    	//Testing to see if the music was loaded correctly and plays
    	//Sets the music volume to max
    	song.setVolume(1);
    	//Starts to play the song
    	//song.play();
    	
    	//-----TESTING-----
    	int[][] data = null;
    	ReadSong read = new ReadSong("data/Circles.txt");
    	try {
			data = read.OpenFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for (int i = 0; i < data.length; i++)
    	{
    		circles.add(new RhythmCircle(data[i][0], data[i][1], data[i][2], gc));
    	}
    }
    
    //Updates the game state (NOTE::Normally called roughly ever 16 ms with slight fluctuation. To check time between updates on current machine activate debug mode with `)
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between updates
    	debug.setUpdateTimes(gc.getTime());
    	
    	//Sets a variable which will grab input using slick libraries
    	final Input input = gc.getInput();
    	
    	//Checks to see if a special input key was pressed
    	if (specialInput.equals("`"))
    	{
    		//Toggles debug mode on and off
    		debug.debugToggle();
    		//Sets the specialInput variable back to an empty string so that it can be used again
    		specialInput = "";
    	}
    	
    	if (input.isKeyPressed(Input.KEY_J))
    	{
    		radar.keyPressed('j');
    	}
    	
    	if (input.isKeyPressed(Input.KEY_K))
    	{
    		radar.keyPressed('k');
    	}
    	
    	if (input.isKeyPressed(Input.KEY_L))
    	{
    		radar.keyPressed('l');
    	}
    	
    	if (input.isKeyPressed(Input.KEY_SEMICOLON))
    	{
    		radar.keyPressed(';');
    	}
    	
    	if (input.isKeyPressed(Input.KEY_SPACE))
    	{
    		radar.keyPressed(' ');
    	}
    	
    	//Example of how to use the built in slick input to detect input
    	/*if (input.isKeyPressed(Input.KEY_BACKSLASH))
    	{
    		debug.debugToggle();
    	}*/
    	
    	//Updates the selctors position
    	selector.updateSelector();
    	radar.update();
    }
    
    //Renders the game
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	//Sends the current time in ms to the debug functions to determine the time between renders
    	debug.setRenderTimes(gc.getTime());
    	
    	radar.draw(g);
    	
    	//-----Testing-----
    	//Draws a test circle (NOTE::Selector is passed to the draw function so that the circle can determine from the position of the selector when to start drawing)
    	for (int i = 0; i < circles.size(); i++)
    	{
    		if (circles.get(i).returnTermination() == 1)
    			circles.remove(i);
    		else
    			circles.get(i).drawCircle(g, selector);
    	}
    	//test2.drawCircle(g, selector);
    	
    	//Draws debug mode
    	debug.draw(g);
    	
    	//Draw the selector
    	selector.drawSelector(g);
    	
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