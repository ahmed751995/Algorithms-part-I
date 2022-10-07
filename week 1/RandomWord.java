import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomWord {
    public static void main(String[] args) {
        String r = "";
        double i = 1;
        while (!StdIn.isEmpty()) {
            String w = StdIn.readString();
            if (StdRandom.bernoulli(1 / i)) {
                r = w;
            }
            i += 1;
        }
        StdOut.println(r);
    }
}
