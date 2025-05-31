import custom.vector.Vector3;


public class ProjectionCalculator 
{
    static double CalculateProjectedX(Vector3 distance)
    {
        Double focalLength = 10.0;
        Double distanceX = distance.x;
        Double distanceZ = distance.z + 20.0;
        Double projectedX = distanceX * focalLength / (focalLength + distanceZ);
        projectedX = projectedX * 200;
        return projectedX;
    }
    static double CalculateProjectedY(Vector3 distance)
    {
        Double focalLength = 10.0;
        Double distanceY = distance.y;
        Double distanceZ = distance.z + 20.0;
        Double projectedY = distanceY * focalLength / (focalLength + distanceZ);
        projectedY = projectedY * 200;
        return projectedY;
    }

    // Method to calculate the depth value
    static double CalculateDepthValue(Vector3 distance)
    {
        Double focalLength = 10.0;
        Double distanceZ = distance.z + 20.0;
        Double near = 0.1;
        Double far = 100.0;
        
        // Calculate depth value based on the distanceZ and focal length
        Double depthValue = distanceZ / (focalLength + distanceZ);

        // Optionally, scale the depth value to fit into a specific range, e.g., [0, 1]
        // This assumes a normalized Z-buffer where 0 is near and 1 is far
        depthValue = (depthValue - near) / (far - near);
        
        return depthValue;
    }


}
