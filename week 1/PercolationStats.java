import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private double[] percThreshold;
  private Percolation grid;
  private int l;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0)
      throw new java.lang.IllegalArgumentException();
    l = trials;
    percThreshold = new double[trials];
    for (int i = 0; i < trials; i++) {
      grid = new Percolation(n);
      while (!grid.percolates()) {
          int row = StdRandom.uniformInt(1, n + 1);
          int col = StdRandom.uniformInt(1, n + 1);
          if(!grid.isOpen(row, col)) {
              grid.open(row, col);
          }
      }
      percThreshold[i] = (double)grid.numberOfOpenSites() / (n * n);
    }
  }

  // sample mean of percolation threshold
  public double mean() { return StdStats.mean(percThreshold); }

  // sample standard deviation of percolation threshold
  public double stddev() { return StdStats.stddev(percThreshold); }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(l));
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(l));
  }

  // test client (see below)
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    PercolationStats percStats = new PercolationStats(n, trials);
    StdOut.println("mean                    = " + percStats.mean());
    StdOut.println("stddev                  = " + percStats.stddev());
    StdOut.println("95% confidence interval = "
                   + "[" + percStats.confidenceLo() + ", " +
                   percStats.confidenceHi() + "]");
  }
}
