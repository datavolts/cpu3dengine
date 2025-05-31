import javax.swing.JPanel;

import custom.vector.Vector2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




public class Screen extends JPanel
{
    static CubeMesh cube;
    static MeshInstance toyhouse;
    static Double[][] zBuffer = new Double[Display.WIDTH][Display.HEIGHT];
    public Screen()
    {
        toyhouse = new MeshInstance("src/../obj/Suzanne.obj");
        cube = new CubeMesh(0.0);

        for(int i = 0; i < Display.WIDTH; i++)
        {
            for(int j = 0; j < Display.HEIGHT; j++)
            {
                zBuffer[i][j] = Double.MAX_VALUE;
            }
        }
    }

    static BufferedImage frameImage = new BufferedImage(Display.WIDTH, Display.HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static void clearImage() {
        Graphics2D g2d = frameImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, Display.WIDTH, Display.HEIGHT);
        g2d.dispose();
    }

    public static void clearBuffer()
    {
        for(int i = 0; i < Display.WIDTH; i++)
        {
            for(int j = 0; j < Display.HEIGHT; j++)
            {
                zBuffer[i][j] = Double.MAX_VALUE;
            }
        }
    }
  
    public static void SetPixel(Vector2 pixel)
    {
        frameImage.setRGB(pixel.x.intValue(), pixel.y.intValue(), Color.RED.getRGB());
    }

    public static void SetPixel(Vector2 pixel, int colour)
    {
        frameImage.setRGB(pixel.x.intValue(), pixel.y.intValue(), colour);
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Screen.clearImage();
        Screen.clearBuffer();
        toyhouse.fill();
        g.drawImage(frameImage, 0, 0, null);
    }


}
