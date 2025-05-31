import java.awt.Graphics;
import java.awt.Color;

import custom.maths;
import custom.vector.Vector3;

public class CubeMesh 
{
    TriangleMesh3D[] triangles;
    Vector3[] vertices;
    int[][] faces;
    Color[] colours;

    public CubeMesh(Double rotation)
    {

        vertices = new Vector3[]{
            new Vector3(-1, -1, -1),
            new Vector3(1, -1, -1),
            new Vector3(1, 1, -1),
            new Vector3(-1, 1, -1),
            new Vector3(-1, -1, 1),
            new Vector3(1, -1, 1),
            new Vector3(1, 1, 1),
            new Vector3(-1, 1, 1)
        };

        faces = new int[][]{
            {0, 1, 2, 3},
            {1, 5, 6, 2},
            {5, 4, 7, 6},
            {4, 0, 3, 7},
            {0, 4, 5, 1},
            {3, 2, 6, 7}
        };

        colours = new Color[]{
            Color.RED,
            new Color(243, 107, 107),
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA
        };

        triangles = new TriangleMesh3D[faces.length * 2]; 
        int tIndex = 0;
        int cIndex = 0;
        
        RotateCubeY(maths.DegToRad(-rotation));
        for(int[] face : faces)
        {
            
            triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[1]], vertices[face[2]]}, colours[cIndex]);
            triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[2]], vertices[face[3]]}, colours[cIndex++]);
        }

        
    }

    public void update()
    {
        int tIndex = 0;
        int cIndex = 0;
        for(int[] face : faces)
        {
            triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[1]], vertices[face[2]]}, colours[cIndex]);
            triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[2]], vertices[face[3]]}, colours[cIndex++]);
        }

    }

    public void draw(Graphics g)
    {
        for(TriangleMesh3D triangle : triangles)
        {
            triangle.drawTriangle(g);
        }
    }

    public void fill()
    {
        for(TriangleMesh3D triangle : triangles)
        {
            triangle.fillTriangle();
        }
    }

    public void RotateCubeX(Double angle)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            Double y = vertices[i].y;
            Double z = vertices[i].z;
            vertices[i].y = y * Math.cos(angle) - z * Math.sin(angle);
            vertices[i].z = y * Math.sin(angle) + z * Math.cos(angle);
        }
    }

    public void RotateCubeY(Double angle)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            Double x = vertices[i].x;
            Double z = vertices[i].z;
            vertices[i].x = x * Math.cos(angle) - z * Math.sin(angle);
            vertices[i].z = x * Math.sin(angle) + z * Math.cos(angle);
        }
    }

    public void RotateCubeZ(Double angle)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            Double x = vertices[i].x;
            Double y = vertices[i].y;
            vertices[i].x = x * Math.cos(angle) - y * Math.sin(angle);
            vertices[i].y = x * Math.sin(angle) + y * Math.cos(angle);
        }
    }

}
