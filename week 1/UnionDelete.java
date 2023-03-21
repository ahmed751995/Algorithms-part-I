import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UnionDelete {
  int[] s;
  int[] as;
  int[] sz;
  public UnionDelete(int n) {
    s = new int[n];
    as = new int[n];
    sz = new int[n];

    for (int i = 0; i < n; i++) {
      s[i] = i;
      as[i] = i;
      sz[i] = 1;
    }
  }

  private int root(int p) {
    while (s[p] != p) {
      p = s[p];
    }

    return p;
  }

  public void union(int p1, int p2) {
    int r1 = root(p1);
    int r2 = root(p2);
    if (r1 == r2)
      return;

    if (sz[r1] < sz[r2]) {
      s[r1] = r2;
      sz[r2] += sz[r1];
      if (as[r2] < as[r1])
        as[r2] = as[r1];
    } else {
      s[r2] = r1;
      sz[r1] += sz[r2];
      if (as[r1] < as[r2])
        as[r1] = as[r2];
    }
  }

  public int delete(int v) {
    if (v == s.length - 1)
      return v;
    union(v, v + 1);
    return as[root(v + 1)];
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    UnionDelete s = new UnionDelete(n);
    while (!StdIn.isEmpty()) {
      int i = StdIn.readInt();
      StdOut.println(s.delete(i));
    }
  }
}
