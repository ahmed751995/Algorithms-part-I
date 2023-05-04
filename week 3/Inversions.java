// Question 2

// Counting inversions.

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Inversions {
  private int[] aux;
  private int inv = 0;

  private void sort(int low, int high, int[] arr) {
    if (low >= high)
      return;
    int mid = (low + high) / 2;
    sort(low, mid, arr);
    sort(mid + 1, high, arr);
    merge(low, mid, high, arr);
  }

  private void merge(int low, int mid, int high, int[] arr) {
    if (arr[mid + 1] >= arr[mid])
      return;
    int i = low;
    int j = mid + 1;
    for (int k = low; k <= high; k++) {
      if (j > high) {
        aux[k] = arr[i++];
      }
      else if (i > mid)
        aux[k] = arr[j++];
      else if (arr[i] < arr[j])
        aux[k] = arr[i++];
      else {
        this.inv += mid + 1 - i;
        aux[k] = arr[j++];
      }
    }
    for(int k = low; k <= high; k++)
      arr[k] = aux[k];
  }

  public int getInversions(int[] arr) {
    this.aux = new int[arr.length];
    sort(0, arr.length - 1, arr);
    return this.inv;
  }

  public static void main(String[] args) {
    Inversions inv = new Inversions();
    int[] arr = {1, 5, 2, 4};
    int c = inv.getInversions(arr);
    for (int i = 0; i < arr.length; i++) {
      StdOut.print(arr[i] + " ,");
    }
    StdOut.println();
    StdOut.println(c);
  }
}
