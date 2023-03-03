import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints {
    int n;
    int ls;
    Stack<LineSegment> lineStack;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points){

        if (points == null){ throw new IllegalArgumentException();}
        n = points.length;
        ls = 0;
        Point point1;
        Point point2;
        Point point3;
        Point point4;
        LineSegment segment;
        Point[] pointArray = new Point[4];

        for (int i = 0; i < n ; i++){
            for (int j = i + 1 ; j < n ; j++){
                for (int k = j + 1 ; k < n ; k++){
                    for (int l = k + 1; l < n; l++){

                        point1 = points[i];
                        point2 = points[j];
                        point3 = points[k];
                        point4 = points[l];

                        if (point1 == null || point2 == null || point3 == null || point4 == null){
                            throw new IllegalArgumentException();
                        }
                        if (point1 == point2){
                            throw new IllegalArgumentException();
                        }

                        double slope1 = point1.slopeTo(point2);
                        double slope2 = point2.slopeTo(point3);
                        double slope3 = point3.slopeTo(point4);
                        
                        if (slope1 == slope2 && slope2 == slope3){
                            ls++;
                            
                            pointArray[0] = point1;
                            pointArray[1] = point2;
                            pointArray[2] = point3;
                            pointArray[3] = point4;
                            Arrays.sort(pointArray);
                            
                            segment = new LineSegment(pointArray[0], pointArray[3]);
                            lineStack.push(segment);
                        }
                    }
                }
            }
        }
    }
    
    // the number of line segments
    public int numberOfSegments(){
        return ls;
    }
    
    // the line segments
    public LineSegment[] segments(){
        LineSegment[] res = new LineSegment[ls];
        for (int i = 0; i < ls; i++){
            res[i] = lineStack.pop();
        }
        return res;
    }                
 }