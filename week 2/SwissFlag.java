import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// assume for simplicity that colors is 0, 1, 2  where each
// number represents swiss flag color
public class SwissFlag {

  private void swap(int i, int j, int[] colors) {
    int temp = colors[i];
    colors[i] = colors[j];
    colors[j] = temp;
  }

  private void printColors(int[] colors) {
    for (int i = 0; i < colors.length; i++) {
      StdOut.print(colors[i] + ", ");
    }
    StdOut.println();
  }

  public void sortColors(int[] colors) {
    int low = 0;
    int high = colors.length - 1;
    int mid = 0;
    while (mid <= high) {
      if (colors[mid] == 0) {
        swap(mid, low, colors);
        low += 1;
        mid += 1;
      } else if (colors[mid] == 1)
        mid += 1;
      else {
        swap(mid, high, colors);
        high -= 1;
      }
    }
  }

  public static void main(String[] args) {
    int[] colors = {1, 1, 1, 0, 0, 0, 2, 2, 2, 1};
    SwissFlag flag = new SwissFlag();
    flag.sortColors(colors);
    flag.printColors(colors);
  }
}
