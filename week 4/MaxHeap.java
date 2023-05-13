import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxHeap {

  public int[] maxHeap;
  public int N = 0;

  MaxHeap(int n) { maxHeap = new int[n + 1]; }

  public void sink(int k) {
    int j;
    while (2 * k <= N) {
      j = 2 * k;
      if (j < N && maxHeap[j + 1] > maxHeap[j])
        j++;
      if (maxHeap[j] <= maxHeap[k])
        break;
      exch(k, j);
      k = j;
    }
  }

  public void swim(int k) {
    if (k <= 1)
      return;
    while (k / 2 >= 1 && maxHeap[k / 2] < maxHeap[k]) {
      exch(k, k / 2);
      k = k / 2;
    }
  }

  public void exch(int i, int j) {
    int temp = maxHeap[i];
    maxHeap[i] = maxHeap[j];
    maxHeap[j] = temp;
  }

  public int getMax() {
    if (isEmpty())
      return -1;
    return maxHeap[1];
  }

  public void insert(int value) {
    if (N == maxHeap.length - 1)
      throw new java.lang.IllegalArgumentException();
    maxHeap[++N] = value;
    swim(N);
  }

  public void deleteMax() {
    if (isEmpty())
      return;
    exch(0, N--);
    sink(0);
  }

  public void printArr() {
    for (int i = 1; i <= N; i++) {
      StdOut.print(maxHeap[i] + " ");
    }
    StdOut.println();
  }

  public boolean isEmpty() { return N == 0; }

  public int size() { return N; }

  public static void main(String[] args) {
    MaxHeap m = new MaxHeap(6);
    int[] x = {1, 2, 5, 4, 3, 7};
    for (int i = 0; i < x.length; i++) {
      m.insert(x[i]);
    }

    m.printArr();
    m.deleteMax();
    m.deleteMax();
    m.printArr();
  }
}
