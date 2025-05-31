package custom.vector;
public class Vector3 
{
    public Double x;
    public Double y;
    public Double z;
    public Double length;

    public Vector3()
    {
        this.x = null;
        this.y = null;
        this.z = null;
    }

    public Vector3(int x, int y, int z) 
    {
        this.x = (double)x;
        this.y = (double)y;
        this.z = (double)z;
        this.length = Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3(int[] array)
    {
        
    }

    public Vector3(Double x, Double y, Double z) 
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = Math.sqrt(x*x + y*y + z*z);
    }

    public Vector3 normalised()
    {
        if(length == 0)
        {
            return new Vector3(0.0, 0.0, 0.0);
        }
        return new Vector3(x/length, y/length, z/length);
    }

    @Override public String toString() 
    {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Double[] toArray()
    {
        return new Double[]{x, y, z};
    }
}
