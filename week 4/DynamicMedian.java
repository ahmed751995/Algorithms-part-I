import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class DynamicMedian {
  MaxHeap mx;
  MinHeap mn;
  DynamicMedian(int n) {
    mx = new MaxHeap(n);
    mn = new MinHeap(n);
  }

  public void insert(int value) {
    if (mx.isEmpty())
      mx.insert(value);
    else {
      if (value > mx.getMax())
        mn.insert(value);
      else
        mx.insert(value);
      balance();
    }
  }

  private void balance() {
    if (mn.size() - mx.size() >= 1) {
      mx.insert(mn.getMin());
      mn.deleteMin();
    } else if (mx.size() - mn.size() > 1) {
      mn.insert(mx.getMax());
      mx.deleteMax();
    }
  }

  public int getMedian() { return mx.getMax(); }

  public static void main(String[] args) {
    int[] x = {7, 2, 3, 5, 4, 6};
    DynamicMedian dm = new DynamicMedian(6);
    for (int i = 0; i < x.length; i++) {
      dm.insert(x[i]);
      StdOut.println(dm.getMedian());
    }
  }
}
