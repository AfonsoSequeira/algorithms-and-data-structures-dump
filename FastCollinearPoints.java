import java.util.Arrays;

public class FastCollinearPoints {
    int nPoints;
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points){
        int counter = 1;
        nPoints = points.length;

        for (int n = 0; n < nPoints; n++){
            Point curPoint = points[n];
            Arrays.sort(points, curPoint.slopeOrder());
            for (int k = 0; k < nPoints -1; k++){
                if (curPoint.slopeTo(points[k]) == curPoint.slopeTo(points[k+1])){
                    counter += 1;
                }
                else {
                    
                }
            }
        }
    } 
     
    // the number of line segments
    public int numberOfSegments(){

    }
    
    // the line segments
    public LineSegment[] segments(){}               
 }
