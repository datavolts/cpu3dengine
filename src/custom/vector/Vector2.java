package custom.vector;
public class Vector2 
{
    public Double x;
    public Double y;
    public Double length;
    public Vector2()
    {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2(int x, int y) 
    {
        this.x = (double)x;
        this.y = (double)y;
        this.length = Math.sqrt(x*x + y*y);
    }

    public Vector2(Double x, Double y) 
    {
        this.x = x;
        this.y = y;
        this.length = Math.sqrt(x*x + y*y);
    }

    public Vector2 normalised()
    {
        if(length == 0)
        {
            return new Vector2(0.0, 0.0);
        }
        return new Vector2(x/length, y/length);
    }

    @Override public String toString() 
    {
        return "(" + x + ", " + y + ")";
    }

    public Double[] toArray()
    {
        return new Double[]{x, y};
    }
}
