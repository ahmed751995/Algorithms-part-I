import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Comparable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
;

public class Solver {
  private Node firstNode;

  private class Node implements Comparable<Node> {
    Board board;
    int moves;
    Board prev;
    int manhDist;

    public int compareTo(Node that) {
      int thisNode = this.manhDist + this.moves;
      int thatNode = that.manhDist + that.moves;
      if (thisNode < thatNode)
        return -1;
      if (thisNode == thatNode)
        return 0;
      return 1;
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null)
      throw new java.lang.IllegalArgumentException();

    firstNode = new Node();
    firstNode.board = initial;
    firstNode.moves = 0;
    firstNode.prev = null;
    firstNode.manhDist = initial.manhattan();
  }

  // // is the initial board solvable? (see below)
  public boolean isSolvable() {
    if (Goal(firstNode.board).equals(firstNode.board)) {
      return true;
    }
    return !firstNode.board.twin().equals(Goal(firstNode.board));
  }

  private Board Goal(Board b) {
    int n = b.dimension();
    int[][] t = new int[n][n];
    int v = 1;
    for (int r = 0; r < n; r++)
      for (int c = 0; c < n; c++)
        if (v != n * n)
          t[r][c] = v++;
    return new Board(t);
  }
  // // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    if (isSolvable()) {
      int size = -1;
      for (Board b : solution())
        size++;
      return size;
    }
    return -1;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    if (!isSolvable()) {
      return null;
    }
    int moves = 0;
    MinPQ<Node> pq = new MinPQ<Node>();
    Stack<Node> sol = new Stack<Node>();
    Stack<Board> steps = new Stack<Board>();
    Node node;
    Node temp = firstNode;
    Board goal = Goal(temp.board);

    // if(goal.equals(temp.board)) {
    //   steps.push(temp.board);
    //   return steps;
    // }
    sol.push(temp);
    // StdOut.println("goal board = " + goal.toString());

    while (!goal.equals(temp.board)) {
      // moves++;
      for (Board neighbour : temp.board.neighbors()) {
        if (temp.prev != null && temp.prev.equals(neighbour))
          continue;
        node = new Node();
        node.board = neighbour;
        node.moves = temp.moves + 1;
        node.prev = temp.board;
        node.manhDist = neighbour.manhattan();
        pq.insert(node);
      }
      temp = pq.delMin();

      sol.push(temp);
    }

    Stack<Board> fin = new Stack<Board>();
    temp = sol.pop();
    fin.push(temp.board);
    // StdOut.println("last on " + temp.board.toString());
    while (sol.size() > 0) {
      // StdOut.println("temp prev = " + temp.prev.toString());
      // StdOut.println("last element in sol " +
      // sol.lastElement().board.toString());
      if (temp.prev.equals(sol.lastElement().board)) {
        temp = sol.pop();
        fin.push(temp.board);
      } else {
        sol.pop();
      }
      // StdOut.println("==========");
    }
    // fin.push(sol.pop().board);
    while (fin.size() > 0)
      steps.push(fin.pop());
    return steps;
  }

  // private boolean isNeighbour(Board b1, Board b2) {
  //   if(Math.abs(b1.manhattan() - b2.manhattan()) <= 1)
  //     return true;
  //   return false;
  // }

  // test client (see below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);
    StdOut.println(solver.isSolvable());
    // solver.solution();
    for (Board board : solver.solution())
      StdOut.println(board);
    StdOut.println("Minimum number of moves = " + solver.moves());
    // print solution to standard output
    // if (!solver.isSolvable())
    //   StdOut.println("No solution possible");
    // else {
    //   StdOut.println("Minimum number of moves = " + solver.moves());
    //   for (Board board : solver.solution())
    //     StdOut.println(board);
    // }
  }
}
