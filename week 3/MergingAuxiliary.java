// Merging with smaller auxiliary array. 

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MergingAuxiliary {
  private int[] arr;
  public MergingAuxiliary(int[] arr) { this.arr = arr; }

  public void merge() {
    int[] aux = new int[this.arr.length / 2];
    for (int i = 0; i < this.arr.length / 2; i++) {
      aux[i] = arr[i];
    }
    int low = 0;
    int high = this.arr.length / 2;
    for (int k = 0; k < this.arr.length; k++) {
      if (high == this.arr.length) {
        this.arr[k] = aux[low];
        low++;
      } else if (low == this.arr.length / 2) {
        this.arr[k] = this.arr[high];
        high++;
      } else if (this.arr[high] < aux[low]) {
        this.arr[k] = this.arr[high];
        high++;
      } else {
        this.arr[k] = aux[low];
        low++;
      }
    }
  }
  public void printArray() {
    for (int i = 0; i < this.arr.length; i++) {
      StdOut.print(this.arr[i] + ", ");
    }
    StdOut.println();
  }
  public static void main(String[] args) {
    int[] a = {1, 5, 7, 10, 12, 2, 4, 7, 9, 16};
    MergingAuxiliary arr = new MergingAuxiliary(a);
    arr.merge();
    arr.printArray();
  }
}
