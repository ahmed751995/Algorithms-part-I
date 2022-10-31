import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private int N;
  private Item[] rqueue;
  // construct an empty randomized queue
  public RandomizedQueue() {
    rqueue = (Item[]) new Object[1];
    N = 0;
  }

  // is the randomized queue empty?
  public boolean isEmpty() { return N == 0; }

  // return the number of items on the randomized queue
  public int size() { return N; }

  private void resize(int size) {
    Item[] temp = (Item[]) new Object[size];
    for (int i = 0; i < N; i++) {
      temp[i] = rqueue[i];
    }
    rqueue = temp;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null)
      throw new java.lang.IllegalArgumentException();
    if (N == rqueue.length)
      resize(N * 2);
    rqueue[N] = item;
    N++;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty())
      throw new java.util.NoSuchElementException();
    int i = StdRandom.uniformInt(N);
    Item item = rqueue[i];

    rqueue[i] = rqueue[--N];
    rqueue[N] = null;
    if (N > 0 && N == rqueue.length / 4)
      resize(rqueue.length / 2);
    return item;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty())
      throw new java.util.NoSuchElementException();
    int i = StdRandom.uniformInt(N);
    return rqueue[i];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() { return new queIterator(); }

  private class queIterator implements Iterator<Item> {
    private int i = N;
    private Item[] tqueue = (Item[]) new Object[N];

    // construct an empty randomized queue
    public queIterator() {
      for (int k = 0; k < N; k++)
        tqueue[k] = rqueue[k];
    }

    public boolean hasNext() { return i > 0; }

    public Item next() {
      if (i == 0)
        throw new java.util.NoSuchElementException();
      int n = StdRandom.uniformInt(i);
      Item item = tqueue[n];
      tqueue[n] = tqueue[--i];
      tqueue[i] = null;
      return item;
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> rque = new RandomizedQueue<Integer>();
    for (int i = 0; i < 10; i++) {
      rque.enqueue(i);
    }

    StdOut.println("dequeue = " + rque.dequeue());
    StdOut.println("Sample = " + rque.sample());

    for (int k : rque)
      StdOut.println(k);
  }
}
