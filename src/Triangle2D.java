import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import custom.vector.Vector2;
import custom.vector.Vector3;

public class Triangle2D 
{
    Color colour;
    Polygon triangle;
    //Vector2[] vertices = new Vector2[3];
    Vector3[] vertices = new Vector3[3];

    public Triangle2D(Vector3[] vertices, Color colour)
    {
        this.vertices = vertices;
        this.colour = colour;
        triangle = new Polygon();
        for(int i = 0; i < 3; i++)
        {
            triangle.addPoint(vertices[i].x.intValue(), vertices[i].y.intValue());
        }   
    }

    public void drawTriangle(Graphics g)
    {
        g.setColor(this.colour);
        g.drawPolygon(triangle);
    }

    public void fillTriangle()
    {

        //g.setColor(this.colour);
        if(CalculateSetEdgeFunction() >= 0) RasteriseTriangle();//g.fillPolygon(triangle);
    }

    public void RasteriseTriangle()
    {
        Vector2 pixel = new Vector2(0.0, 0.0);

        Vector2 min = new Vector2((int)Math.min(vertices[0].x, Math.min(vertices[1].x, vertices[2].x)), (int)Math.min(vertices[0].y, Math.min(vertices[1].y, vertices[2].y)));
        Vector2 max = new Vector2((int)Math.max(vertices[0].x, Math.max(vertices[1].x, vertices[2].x)), (int)Math.max(vertices[0].y, Math.max(vertices[1].y, vertices[2].y)));

        for(pixel.y = min.y; pixel.y < max.y; pixel.y++)
        {
            for(pixel.x = min.x; pixel.x < max.x; pixel.x++)
            {
                
                int ABP = CalculateEdgeFunction(vertices[0], vertices[1], pixel);
                int BCP = CalculateEdgeFunction(vertices[1], vertices[2], pixel);
                int CAP = CalculateEdgeFunction(vertices[2], vertices[0], pixel);

                if(ABP >= 0 && BCP >= 0 && CAP >= 0)
                {
                    //Screen.SetPixel(pixel, colour.getRGB());

                    double[] barycentricCoordinates = CalculateBarycentricCoordinates(vertices[0], vertices[1], vertices[2], pixel);
                    double zPixel = InterpolateZ(barycentricCoordinates);

                    if(zPixel < Screen.zBuffer[pixel.x.intValue()][pixel.y.intValue()])
                    {
                        Screen.zBuffer[pixel.x.intValue()][pixel.y.intValue()] = zPixel;
                        //System.out.println("Z: " + zPixel + " ZBuffer: " + Screen.zBuffer[pixel.x.intValue()][pixel.y.intValue()]);
                        Screen.SetPixel(pixel, colour.getRGB());
                    }

                }


            }
        }


    }

    private int CalculateSetEdgeFunction()
    {
        int edgeFunction = (triangle.xpoints[1] - triangle.xpoints[0]) * (triangle.ypoints[2] - triangle.ypoints[0]) - (triangle.xpoints[2] - triangle.xpoints[0]) * (triangle.ypoints[1] - triangle.ypoints[0]);        
        return edgeFunction;
    }

    private int CalculateEdgeFunction(Vector3 a, Vector3 b, Vector2 c)
    {
        int edgeFunction = (b.x.intValue() - a.x.intValue()) * (c.y.intValue() - a.y.intValue()) - (c.x.intValue() - a.x.intValue()) * (b.y.intValue() - a.y.intValue());
        return edgeFunction;
    }

    private double[] CalculateBarycentricCoordinates(Vector3 a, Vector3 b, Vector3 c, Vector2 p)
    {
        double denom = (b.y - c.y) * (a.x - c.x) + (c.x - b.x) * (a.y - c.y);
        double lambda1 = ((b.y - c.y) * (p.x - c.x) + (c.x - b.x) * (p.y - c.y)) / denom;
        double lambda2 = ((c.y - a.y) * (p.x - c.x) + (a.x - c.x) * (p.y - c.y)) / denom;
        double lambda3 = 1 - lambda1 - lambda2;
        double[] barycentricCoordinates = {lambda1, lambda2, lambda3};
        return barycentricCoordinates;
    }

    private double InterpolateZ(double[] barycentricCoordinates)
    {
        double z = barycentricCoordinates[0] * vertices[0].z + barycentricCoordinates[1] * vertices[1].z + barycentricCoordinates[2] * vertices[2].z;
        return z;
    }
}
