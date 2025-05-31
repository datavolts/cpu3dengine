import javax.swing.JFrame;

import custom.maths;

public class Display
{
    static JFrame frame;
    static Screen screen = new Screen();
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static Boolean running = false;
    public static void main(String[] args) 
    {
        frame = new JFrame("Orc Render Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.add(screen);
        running = true;
        frame.setVisible(true);


        while(running)
        {
            //wait 0.01 seconds
            try
            {
                Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            Screen.toyhouse.RotateMeshY(maths.DegToRad(-1.0));
            Screen.toyhouse.update();
            screen.repaint();
        }
    }
}
