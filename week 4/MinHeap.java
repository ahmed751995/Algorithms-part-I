import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class MinHeap {

  public int[] minHeap;
  public int N = 0;

  MinHeap(int n) { minHeap = new int[n + 1]; }

  public void sink(int k) {
    int j;
    while (2 * k <= N) {
      j = 2 * k;
      if (j < N && minHeap[j + 1] < minHeap[j])
        j++;
      if (minHeap[j] >= minHeap[k])
        break;
      exch(k, j);
      k = j;
    }
  }

  public void swim(int k) {
    if (k <= 1)
      return;
    while (k / 2 >= 1 && minHeap[k / 2] > minHeap[k]) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  public void exch(int i, int j) {
    int temp = minHeap[i];
    minHeap[i] = minHeap[j];
    minHeap[j] = temp;
  }

  public int getMin() {
    if (isEmpty())
      return -1;
    return minHeap[1];
  }

  public void insert(int value) {
    if (N == minHeap.length - 1)
      throw new java.lang.IllegalArgumentException();
    minHeap[++N] = value;
    swim(N);
  }

  public void deleteMin() {
    if (N == 0)
      throw new java.lang.IllegalArgumentException();
    exch(0, N--);
    sink(0);
  }

  public void printArr() {
    for (int i = 1; i <= N; i++) {
      StdOut.print(minHeap[i] + " ");
    }
    StdOut.println();
  }

  public boolean isEmpty() { return N == 0; }

  public int size() { return N; }

  public static void main(String[] args) {
    MinHeap m = new MinHeap(6);
    int[] x = {1, 2, 5, 4, 3, 7};
    for (int i = 0; i < x.length; i++) {
      m.insert(x[i]);
    }

    m.printArr();
    m.deleteMin();
    m.deleteMin();
    m.printArr();
  }
}
