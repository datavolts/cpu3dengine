package custom.vector;

public class Vector 
{
    public static Vector3 CrossProduct(Vector3 a, Vector3 b)
    {
        return new Vector3(
            a.y*b.z - a.z*b.y,
            a.z*b.x - a.x*b.z,
            a.x*b.y - a.y*b.x
        );
    }

    public static Double DotProduct(Vector3 a, Vector3 b)
    {
        return a.x*b.x + a.y*b.y + a.z*b.z;
    }
}
