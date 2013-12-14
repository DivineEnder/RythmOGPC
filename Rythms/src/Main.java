import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
	//Creating a window for the class
	JFrame frame = new JFrame();
	
	public Main() 
	{
		//Create a canvas used for drawing to the screen
		DisplayPanel canvas = new DisplayPanel(frame);
        initUI(canvas);
    }

    private void initUI(DisplayPanel canvas) 
    {
    	//Set the frame title
        frame.setTitle("Dehk");
        
        //Add the drawing canvas to the frame
        frame.add(canvas);
        //Make the frame undecorated so that it will not have boarders
        frame.setUndecorated(true);
        
        //Pack the frame to the same size as its components (i.e. the canvas)
        frame.pack();
        //Set the close operation (closes when you hit x or alt-f4)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Makes the frame and its components visible
        frame.setVisible(true);
    }
    
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
            public void run()
            {
            	Main ex = new Main();
            }
        });
	}
}