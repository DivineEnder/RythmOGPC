import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
	JFrame frame = new JFrame();
	
	public Main() 
	{
		DisplayPanel canvas = new DisplayPanel(frame);
        initUI(canvas);
    }

    private void initUI(DisplayPanel canvas) 
    {
        frame.setTitle("Dehk");
        
        frame.add(canvas);
        frame.setUndecorated(true);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
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