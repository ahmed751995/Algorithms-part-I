import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
// i used nuts and bols as an array of ints for simplicity

public class NutsAndBolts {
  private void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
  private void sort(int[] a, int lo, int hi) {
    int i = lo;
    int lt = lo;
    int gt = hi;

    if(hi <= lo) return;

    while (i <= gt) {
      if(a[i] < a[lt]) {
        swap(a, lt++, i++);
      } else if(a[i] > a[lt]) {
        swap(a, i, gt--);
      } else {
        i++;
      }
    }
    sort(a, lo, lt -  1);
    sort(a, gt+1, hi);
  }

  public void sort(int[] a) {
    sort(a, 0, a.length - 1);
  }

  public  int[] generateRandom(int n) {
    int[] a = new int[2*n];
    int i = 0;
    while (i < 2*n) {
      a[i] = i/2;
      a[i+1] = i/2;
      i += 2;
    }
    StdRandom.shuffle(a);
    return a;
  }

  public void printNuts(int[] a) {
    for(int i = 0; i < a.length; i++) {
      StdOut.print(a[i]);
    }
    StdOut.println();
  }
  public static void main(String[] args) {
    StdOut.println("here");
    int n = Integer.parseInt(args[0]);
    NutsAndBolts nuts = new NutsAndBolts();
    int[] a = nuts.generateRandom(n);
    nuts.printNuts(a);
    nuts.sort(a);
    nuts.printNuts(a);
  }
}
