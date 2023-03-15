import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SocialNetwork {
  boolean full;
  int[] size;
  int[] person;
  int n;
  public SocialNetwork(int n) {
    this.n = n;
    size = new int[n];
    person = new int[n];
    full = false;
    for (int i = 0; i < n; i++) {
      person[i] = i;
      size[i] = 1;
    }
  }

  private int root(int p) {
    while (person[p] != p) {
      p = person[p];
    }

    return p;
  }

  public boolean isConnected(int p1, int p2) { return root(p1) == root(p2); }

  public void connect(int p1, int p2) {

    if (!isConnected(p1, p2)) {
      int r1 = root(p1);
      int r2 = root(p2);
      if(size[r1] < size[r2]) {
        person[r1] = r2;
        size[r2] += size[r1];
        if(size[r2] == n) this.full = true;
      } else {
        person[r2] = r1;
        size[r1] += size[r2];
        if(size[r1] == n) this.full = true;
      }
    }
  }

  public boolean isFull() {
    return this.full;
  }

  public static void main(String[] args) {
    int people = Integer.parseInt(args[0]);
    SocialNetwork network = new SocialNetwork(people);
    while(!StdIn.isEmpty()) {
      int p1 = StdIn.readInt();
      int p2 = StdIn.readInt();
      network.connect(p1, p2);
      StdOut.println(network.isFull());
    }
    StdOut.println(network.isFull());
  }
}
