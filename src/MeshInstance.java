import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import custom.maths;
import custom.vector.Vector3;

public class MeshInstance 
{
    String name;
    TriangleMesh3D[] triangles;
    Vector3[] vertices;
    int[][] faces;
    Color[] colours;

    public MeshInstance(String objFile)
    {
        GetMesh(objFile);

        triangles = new TriangleMesh3D[faces.length * 2];
        colours = new Color[faces.length];
        for(int i = 0; i < this.faces.length; i++)
        {
            //Add a random colour to colours
            colours[i] = new Color((int)(Math.random() * 255), (int)(Math.random() * 200), (int)(Math.random() * 255));
        }
        this.RotateMeshX(maths.DegToRad(180.0));
        this.update();
    }

    public void update()
    {
        int tIndex = 0;
        int cIndex = 0;
        for(int[] face : faces)
        {
            if(face.length == 3)
            {
                triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[1]], vertices[face[2]]}, colours[cIndex]);
            }
            else if(face.length == 4)
            {
                triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[1]], vertices[face[2]]}, colours[cIndex]);
                triangles[tIndex++] = new TriangleMesh3D(new Vector3[]{vertices[face[0]], vertices[face[2]], vertices[face[3]]}, colours[cIndex]);
            }
            cIndex++;
        }
        //System.out.println(Arrays.toString(triangles));

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
            if(triangle != null) triangle.fillTriangle();
        }
    }
    
    public void GetMesh(String objFile)
    {
        int numVertices = 0;
        int numFaces = 0;
        try
        {
            //Count the number of vertices and faces
            BufferedReader reader = new BufferedReader(new FileReader(objFile));
            String line;
            while((line = reader.readLine()) != null)
            {
                if(line.charAt(0) == 'v' || line.charAt(0) == 'f')
                {
                    if(line.charAt(1) == ' ')
                    {
                        if(line.charAt(0) == 'v')
                        {
                            numVertices++;
                        }
                        else if(line.charAt(0) == 'f')
                        {
                            numFaces++;
                        }
                    }
                }
            }
            reader.close();
            //Stores the vertices and faces
            reader = new BufferedReader(new FileReader(objFile));
            this.vertices = new Vector3[numVertices];
            this.faces = new int[numFaces][];
            int vIndex = 0;
            int fIndex = 0;
            while((line = reader.readLine()) != null)
            {
                if(line.charAt(0) == 'v' || line.charAt(0) == 'f')
                {
                    if(line.charAt(1) == ' ')
                    {
                        String[] values = line.split(" ");
                        if(line.charAt(0) == 'v')
                        {
                            this.vertices[vIndex] = new Vector3(Double.parseDouble(values[1]), Double.parseDouble(values[2]), -Double.parseDouble(values[3]));
                            vIndex++;
                            //System.out.println(Arrays.toString(this.vertices));
                        }
                        else if(line.charAt(0) == 'f')
                        {
                            int[] tempFace = new int[values.length - 1];
                            int tfIndex = 0;
                            for(int i = 1; i < values.length; i++)
                            {
                                values[i] = values[i].split("/")[0];
                                tempFace[tfIndex] = Integer.parseInt(values[i]) - 1;
                                tfIndex++;
                            }
                            //System.out.println(Arrays.toString(tempFace));
                            this.faces[fIndex] = tempFace;
                            fIndex++;
                        }
                    }
                }
            }

            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }   
    
    public void RotateMeshX(Double angle)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            Double y = vertices[i].y;
            Double z = vertices[i].z;
            vertices[i].y = y * Math.cos(angle) - z * Math.sin(angle);
            vertices[i].z = y * Math.sin(angle) + z * Math.cos(angle);
        }
    }

    public void RotateMeshY(Double angle)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            Double x = vertices[i].x;
            Double z = vertices[i].z;
            vertices[i].x = x * Math.cos(angle) - z * Math.sin(angle);
            vertices[i].z = x * Math.sin(angle) + z * Math.cos(angle);
        }
    }

    public void RotateMeshZ(Double angle)
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
