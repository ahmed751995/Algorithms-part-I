// Social network connectivity. Given a social network containing nn members and
// a log file containing mm timestamps at which times pairs of members formed
// friendships, design an algorithm to determine the earliest time at which all
// members are connected (i.e., every member is a friend of a friend of a friend
// ... of a friend). Assume that the log file is sorted by timestamp and that
// friendship is an equivalence relation. The running time of your algorithm
// should be mlog⁡n or better and use extra space proportional to n

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetwork {
  int[] size;
  int[] member;
  boolean full = false;
  int n;
  public SocialNetwork(int n) {
    size = new int[n];
    member = new int[n];
    this.n = n;
    for (int i = 0; i < n; i++) {
      member[i] = i;
      size[i] = 1;
    }
  }

  private int root(int p) {
    while (member[p] != p) {
      p = member[p];
    }
    return p;
  }

  public boolean connected(int p1, int p2) { return root(p1) == root(p2); }

  public void connect(int p1, int p2) {

    if (connected(p1, p2))
      return;
    int r1 = root(p1);
    int r2 = root(p2);

    if (size[r1] < size[r2]) {
      member[r1] = r2;
      size[r2] += size[r1];
      if (size[r2] == n)
        this.full = true;
    } else {
      member[r2] = r1;
      size[r1] += size[r2];
      if (size[r1] == n)
        this.full = true;
    }
  }

  public boolean isFull() { return this.full; }

  public static void main(String[] args) {
    int people = StdIn.readInt();
    SocialNetwork network = new SocialNetwork(people);
    while (!StdIn.isEmpty()) {
      int p1 = StdIn.readInt();
      int p2 = StdIn.readInt();
      StdOut.println(p1 + " " + p2);
      network.connect(p1, p2);
      StdOut.println(network.isFull());
    }
    StdOut.println(network.isFull());

    // while(!StdIn.isEmpty()) {
    // int p1 = StdIn.readInt();
    // StdOut.println(network.find(p1));
    // }
  }
}
