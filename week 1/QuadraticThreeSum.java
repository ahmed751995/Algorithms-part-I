
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class QuadraticThreeSum {
  int[] arr;
  public QuadraticThreeSum(int[] x) { arr = x; }

  public void findThreeSum(int i) {
    int lo = i + 1;
    int hi = arr.length - 1;
    int sum;

    while (lo < hi) {
      sum = arr[i] + arr[lo] + arr[hi];
      if (sum == 0) {
        StdOut.println(arr[i] + " " + arr[lo] + " " + arr[hi]);
        lo++;
        hi--;
      } else if (sum < 0)
        lo++;
      else
        hi--;
    }
  }

  public static void main(String[] args) {
    int[] x = {1, 0, -1};
    Arrays.sort(x);

    QuadraticThreeSum f = new QuadraticThreeSum(x);
    for (int i = 0; i < x.length - 2; i++) {
      f.findThreeSum(i);
    }
  }
}
