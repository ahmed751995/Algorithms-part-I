import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SelectionTwoSorted {
  
  public int kth(int[] a, int[] b, int k) {
    if(a.length > b.length)
      return kth(b, a, k);

    int mx = Math.max(a[a.length - 1], b[b.length - 1]);
    int mn = Math.min(a[0], b[0]);
    
    int hi = Math.min(a.length - 1, k - 1);
    int lo = 0;

    while(lo <= hi) {
      int am = Math.max((lo + hi)/2, k - b.length - 1);
      int bm = k - am - 2;
      // StdOut.println("am = " + am);
      // StdOut.println("bm = " + bm);
      // StdOut.println("lo = " + lo);
      // StdOut.println("hi = " + hi);
      int ar = am < a.length - 1? a[am + 1]: mx;
      int al = a[am];
      int bl = bm >= 0? b[bm]: mn;
      int br = bm < b.length -  1? b[bm + 1]: mx;

      if(al <= br && bl <= ar)
        return Math.max(al, bl);

      if(al > br) hi = am - 1;
      else lo = am + 1;
    }

    return b[k - 1];
  }

  public static void main(String[] args) {
    int[] b = {5, 7, 8, 12, 13, 20, 22, 23};
    int[] a = {1, 2, 3, 10, 15, 16};
    SelectionTwoSorted x = new SelectionTwoSorted();
    while(!StdIn.isEmpty()) {
      int k = StdIn.readInt();
      StdOut.println(x.kth(a, b, k));
    }


  }
}
