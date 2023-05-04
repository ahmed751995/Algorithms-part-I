import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Dominant {
    public int dominantNumber(int[] arr) {
        if(arr.length <= 10) return -1;
        int r = arr.length / 10;
        int[] values = new int[r];
        int p = 0;
        for (int ind = r; ind < arr.length; ind += r + 1) {
            int value = quickSelect(arr, 0, arr.length - 1, ind, r);
            if(value != -1){
              values[p++] = value;  
            } 
        }
        for(int j = 0; j < r; j++) {
            StdOut.print(values[j] + " ");
        }
        StdOut.println();
        return -1;
    }

    public int quickSelect(int[] arr, int lo, int hi, int ind, int r) {
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        int v = arr[lt];
        while (i <= gt) {
            if (arr[i] < v)
                exch(arr, i++, lt++);
            else if (arr[i] > v)
                exch(arr, i, gt--);
            else
                i++;
        }

        if (ind < lt)
            return quickSelect(arr, lo, lt - 1, ind, r);
        if (ind > gt)
            return quickSelect(arr, gt + 1, hi, ind, r);

        if (gt - lt + 1 > r)
            return v;
        return -1;
    }

    public void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Dominant a = new Dominant();
        int[] arr = { 2, 2, 2, 4, 4, 4 };
        StdOut.println(a.dominantNumber(arr));
    }
}