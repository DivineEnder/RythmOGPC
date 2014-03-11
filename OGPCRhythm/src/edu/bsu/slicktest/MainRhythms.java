package edu.bsu.slicktest;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import org.newdawn.slick.geom.Circle;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.ResourceLoader;

public class MainRhythms extends BasicGame
{
	ArrayList<RhythmCircle> circles;
	
    public MainRhythms()
    {
        super("Slick2D Path2Glory - Rogue");
    }
  
    @Override
    public void init(GameContainer gc) throws SlickException 
    {
    	
    }
  
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
    	
    }
  
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
    	RhythmCircle circle = new RhythmCircle(25, 25, 5, 5);
    	circle.drawCircle(g);
    	
    	//Turn off FPS counter in top left
    	//gc.setShowFPS(false);
    }
    
    public static MainRhythms getReference()
    {
    	MainRhythms Game = new MainRhythms();
    	return Game;
    }
  
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer( new MainRhythms() );
         
         app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
         app.setFullscreen(true);
         app.start();
    }
}