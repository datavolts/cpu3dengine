import java.awt.Color;
import java.awt.Graphics;

import custom.vector.Vector3;

public class TriangleMesh3D 
{
    Triangle2D projectedTriangleMesh;
    Vector3[] projectedVertices = new Vector3[3];

    public TriangleMesh3D(Vector3[] vertices, Color colour)
    {
        for(int i = 0; i < 3; i++)
        {
            projectedVertices[i] = new Vector3(ProjectionCalculator.CalculateProjectedX(vertices[i]) + 400, ProjectionCalculator.CalculateProjectedY(vertices[i]) + 300, ProjectionCalculator.CalculateDepthValue(vertices[i]));
        }
        projectedTriangleMesh = new Triangle2D(projectedVertices, colour);
    }

    public void drawTriangle(Graphics g)
    {
        // Draw the triangle
        projectedTriangleMesh.drawTriangle(g);
    }

    public void fillTriangle()
    {
        // Fill the triangle
        projectedTriangleMesh.fillTriangle();
    }
}
