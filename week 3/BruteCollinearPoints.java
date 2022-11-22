import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BruteCollinearPoints {
  private int nSegments = 0;
  private LineSegment[] lSegments;
  private Point[] lp;
  private double[] slopes;

  public BruteCollinearPoints(Point[] points) {
    checkNullPoints(points);
    int lpoints = points.length;
    Point[] mpoints = Arrays.copyOf(points, points.length);
    Arrays.sort(mpoints);
    checkDuplication(mpoints);

    lSegments = new LineSegment[lpoints];
    lp = new Point[lpoints];
    slopes = new double[lpoints];
    for (int i = 0; i < lpoints - 3; i++) {
      for (int j = i + 1; j < lpoints - 2; j++) {
        double s = mpoints[i].slopeTo(mpoints[j]);
        for (int k = j + 1; k < lpoints - 1; k++) {
          if (mpoints[j].slopeTo(mpoints[k]) != s)
            continue;
          int dist = -1;
          for (int m = k + 1; m < lpoints; m++) {
            if (mpoints[k].slopeTo(mpoints[m]) == s) {
              dist = m;
            }
          }
          if (dist > 0) {
            boolean flag = true;
            for (int sg = 0; sg < nSegments; sg++) {
              if (lp[sg] == mpoints[dist] &&
                  slopes[sg] == mpoints[i].slopeTo(mpoints[dist])) {
                flag = false;
                break;
              }
            }
            if (flag) {
              lSegments[nSegments] = new LineSegment(mpoints[i], mpoints[dist]);
              lp[nSegments] = mpoints[dist];
              slopes[nSegments] = s;
              nSegments++;
            }
          }
        }
      }
    }
  }

  public int numberOfSegments() { return nSegments; }

  public LineSegment[] segments() {
    LineSegment[] line = new LineSegment[nSegments];
    for (int i = 0; i < nSegments; i++)
      line[i] = lSegments[i];
    return line;
  }

  private void checkNullPoints(Point[] points) {
    if (points == null)
      throw new java.lang.IllegalArgumentException();
    for (int i = 0; i < points.length; i++) {
      if (points[i] == null)
        throw new java.lang.IllegalArgumentException();
    }
  }
  private void checkDuplication(Point[] points) {
    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0)
        throw new java.lang.IllegalArgumentException();
    }
  }

  public static void main(String[] args) {
    int n = StdIn.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = StdIn.readInt();
      int y = StdIn.readInt();
      points[i] = new Point(x, y);
    }

    BruteCollinearPoints bruteForce = new BruteCollinearPoints(points);
    for (LineSegment p : bruteForce.segments()) {
      StdOut.println(p);
    }
  }
}
