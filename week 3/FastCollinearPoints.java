import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
  private LineSegment[] sg;

  public FastCollinearPoints(Point[] points) {
    checkNullPoints(points);
    Point[] mpoints = Arrays.copyOf(points, points.length);
    Point[] cpoints = Arrays.copyOf(points, points.length);
    Arrays.sort(cpoints);
    checkDuplication(cpoints);
    ArrayList<LineSegment> lsgs = new ArrayList<LineSegment>();

    for (int i = 0; i < cpoints.length; i++) {
      Arrays.sort(mpoints, cpoints[i].slopeOrder());
      double s = Double.NEGATIVE_INFINITY;
      double skipSlop = Double.NEGATIVE_INFINITY;
      int c = 0;
      Point mx = mpoints[0];
      for (int j = 1; j < mpoints.length; j++) {
        if (mpoints[0].compareTo(mpoints[j]) > 0 ||
            mpoints[0].slopeTo(mpoints[j]) == skipSlop) {
          skipSlop = mpoints[0].slopeTo(mpoints[j]);
          if (skipSlop != s && c > 2) {
            lsgs.add(new LineSegment(mpoints[0], mx));
          }
          c = 0;
        } else {
          if (mpoints[0].slopeTo(mpoints[j]) == s) {
            c += 1;
            if (mpoints[j].compareTo(mx) > 0)
              mx = mpoints[j];
          } else {
            if (c > 2) {
              lsgs.add(new LineSegment(mpoints[0], mx));
            }
            mx = mpoints[j];
            c = 1;
            s = mpoints[0].slopeTo(mpoints[j]);
          }
        }
      }
      if (c > 2) {
        lsgs.add(new LineSegment(mpoints[0], mx));
      }
    }
    sg = lsgs.toArray(new LineSegment[lsgs.size()]);
  }
  public int numberOfSegments() {
    return sg.length;
  } // the  number of line segments
  public LineSegment[] segments() {
    return Arrays.copyOf(sg, sg.length);
  } // the line segments

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

    FastCollinearPoints f = new FastCollinearPoints(points);
    for (LineSegment p : f.segments()) {
      StdOut.println(p);
    }
  }
}
