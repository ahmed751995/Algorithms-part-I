import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Object;
import java.util.LinkedList;
import java.util.Queue;

public class Board {

  private int[][] tiles;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) { this.tiles = copyOf(tiles); }

  // string representation of this board
  public String toString() {
    int n = dimension();
    String board = n + "\n";
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        board += this.tiles[r][c] + " ";
      }
      board += "\n";
    }
    return board;
  }

  // board dimension n
  public int dimension() { return this.tiles.length; }

  // // number of tiles out of place
  public int hamming() {
    int hamDis = 0;
    int n = dimension();

    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        int v = (r * n) + (c + 1);
        if (v != this.tiles[r][c] && v != n * n)
          hamDis++;
      }
    }
    return hamDis;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int n = dimension();
    int manhDist = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        int v = (r * n) + (c + 1);
        if (this.tiles[r][c] != v && this.tiles[r][c] != 0) {
          int ar = (this.tiles[r][c] - 1) / n;
          int ac = this.tiles[r][c] - (ar * n) - 1;
          manhDist += Math.abs(c - ac) + Math.abs(ar - r);
        }
      }
    }
    return manhDist;
  }

  // is this board the goal board?
  public boolean isGoal() { return hamming() == 0; }

  // does this board equal y?
  public boolean equals(Object y) {

    Board newBoard = (Board)y;

    if (this.dimension() != newBoard.dimension())
      return false;

    int n = dimension();
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (this.tiles[r][c] != newBoard.tiles[r][c])
          return false;
      }
    }
    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    int br = 0;
    int bc = 0;
    int n = dimension();
    Queue<Board> bneighbours = new LinkedList<Board>();
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (this.tiles[r][c] == 0) {
          br = r;
          bc = c;
          break;
        }
      }
    }
    int[][] newTiles;
    if (bc > 0) {
      newTiles = copyOf(this.tiles);
      exch(br, bc, br, bc - 1, newTiles);
      bneighbours.add(new Board(newTiles));
    }
    if (bc < n - 1) {
      newTiles = copyOf(this.tiles);
      exch(br, bc, br, bc + 1, newTiles);
      bneighbours.add(new Board(newTiles));
    }
    if (br > 0) {
      newTiles = copyOf(this.tiles);
      exch(br, bc, br - 1, bc, newTiles);
      bneighbours.add(new Board(newTiles));
    }
    if (br < n - 1) {
      newTiles = copyOf(this.tiles);
      exch(br, bc, br + 1, bc, newTiles);
      bneighbours.add(new Board(newTiles));
    }

    // Iterable<Board> ab = Arrays.asList(b);
    return bneighbours;
  }

  private void exch(int r1, int c1, int r2, int c2, int[][] arr) {
    int temp = arr[r1][c1];
    arr[r1][c1] = arr[r2][c2];
    arr[r2][c2] = temp;
  }

  private int[][] copyOf(int[][] arr) {
    int n = arr.length;
    int[][] newArr = new int[n][n];

    for (int r = 0; r < n; r++)
      for (int c = 0; c < n; c++)
        newArr[r][c] = arr[r][c];

    return newArr;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    int r1 = 0, c1 = 0, r2 = 0, c2 = 1;
    int n = dimension();
    int v = 1;
    int count = 0;
    for (int r = 0; r < n; r++) {
      for (int c = 0; c < n; c++) {
        if (tiles[r][c] != v && tiles[r][c] != 0) {
          if (count == 0) {
            r1 = r;
            c1 = c;
            count++;
          } else {
            r2 = r;
            c2 = c;
            count++;
            break;
          }
        }
        v++;
      }
      if (count > 1)
        break;
    }
    int[][] tw = copyOf(this.tiles);
    exch(r1, c1, r2, c2, tw);
    return new Board(tw);
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    // int[][] x = {{1, 2, 3}, {4, 6, 5}, {7, 8, 0}};
    // int[][] y = {{8, 1, 3}, {4, 0, 2}, {7, 5, 6}};
    // Board b = new Board(x);
    // Board b2 = new Board(y);
    // StdOut.println(b.toString());
    // StdOut.println(b.hamming());
    // StdOut.println(b.manhattan());
    // StdOut.println(b.equals(b));

    // int[] z = {1, 2, 3};ca
    // StdOut.println("iterating");
    // for (Board i : b.neighbors())
    //   StdOut.println(i.toString());

    // StdOut.println("b = " + b.toString());
    // StdOut.print("the tiwins \n" + b.twin().toString());
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[j][i] = in.readInt();
    Board initial = new Board(tiles);
    StdOut.println("hamming init");
    StdOut.println(initial.hamming());
    StdOut.println("twin hamming");
    StdOut.println(initial.twin().hamming());
  }
}
