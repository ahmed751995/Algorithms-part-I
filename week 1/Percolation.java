import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private boolean[] openSites;
  private WeightedQuickUnionUF fullSites;
  private WeightedQuickUnionUF normalWqf;
  private int size;
  private int nOpenSites = 0;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0)
      throw new java.lang.IllegalArgumentException();
    size = n;
    fullSites = new WeightedQuickUnionUF(n * n + 1);
    normalWqf = new WeightedQuickUnionUF(n * n + 2);
    openSites = new boolean[n * n + 1];
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    if (row <= 0 || col <= 0 || row > size || col > size)
      throw new java.lang.IllegalArgumentException();

    if (!isOpen(row, col)) {
      int site = mapGrid(row, col);
      openSites[site] = true;
      nOpenSites += 1;

      if (row == 1) {
        normalWqf.union(site, 0);
        fullSites.union(site, 0);
      }

      if (row == size) {
        normalWqf.union(site, size * size + 1);
      }

      if (row > 1 && isOpen(row - 1, col)) {
        normalWqf.union(site, mapGrid(row - 1, col));
        fullSites.union(site, mapGrid(row - 1, col));
      }
      if (row < size && isOpen(row + 1, col)) {
        normalWqf.union(site, mapGrid(row + 1, col));
        fullSites.union(site, mapGrid(row + 1, col));
      }
      if (col > 1 && isOpen(row, col - 1)) {
        normalWqf.union(site, mapGrid(row, col - 1));
        fullSites.union(site, mapGrid(row, col - 1));
      }
      if (col < size && isOpen(row, col + 1)) {
        normalWqf.union(site, mapGrid(row, col + 1));
        fullSites.union(site, mapGrid(row, col + 1));
      }
    }
  }

  private int mapGrid(int row, int col) { return (row - 1) * size + col; }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    if (row <= 0 || col <= 0 || row > size || col > size)
      throw new java.lang.IllegalArgumentException();
    return openSites[mapGrid(row, col)];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    if (row <= 0 || col <= 0 || row > size || col > size)
      throw new java.lang.IllegalArgumentException();

    return fullSites.find(mapGrid(row, col)) == fullSites.find(0);
  }

  // returns the number of open sites
  public int numberOfOpenSites() { return nOpenSites; }

  // does the system percolate?
  public boolean percolates() {
    return normalWqf.find(size * size + 1) == normalWqf.find(0);
  }

  // test client (optional)
  public static void main(String[] args) {
    int n = StdIn.readInt();
    Percolation grid = new Percolation(n);
    while (!StdIn.isEmpty()) {
      int row = StdIn.readInt();
      int col = StdIn.readInt();
      grid.open(row, col);
      if (grid.numberOfOpenSites() == 9) {
        StdOut.println(grid.isFull(2, 5));
      }
    }
    StdOut.println("number of open sites = " + grid.numberOfOpenSites());
    StdOut.println("percolates = " + grid.percolates());
  };
}
