import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node first;
  private Node last;
  private int N;

  private class Node {
    Item value;
    Node next;
    Node prev;
  }

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
    N = 0;
  }

  // is the deque empty?
  public boolean isEmpty() { return N == 0; }

  // return the number of items on the deque
  public int size() { return N; }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null)
      throw new java.lang.IllegalArgumentException();
    Node oldFirst = first;
    first = new Node();
    first.value = item;
    first.prev = null;
    if (isEmpty()) {
      first.next = null;
      last = first;
    } else {
      oldFirst.prev = first;
      first.next = oldFirst;
    }
    N++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null)
      throw new java.lang.IllegalArgumentException();
    Node oldLast = last;
    last = new Node();
    last.value = item;
    last.next = null;
    if (isEmpty()) {
      last.prev = null;
      first = last;
    } else {
      last.prev = oldLast;
      oldLast.next = last;
    }
    N++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    Item item = first.value;
    first = first.next;
    N--;
    if (isEmpty()) {
      last = null;
    } else {
      first.prev = null;
    }
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException();
    }
    Item item = last.value;
    last = last.prev;
    N--;
    if (isEmpty()) {
      first = null;
    } else {
      last.next = null;
    }
    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() { return new DequeIterator(); }

  private class DequeIterator implements Iterator<Item> {
    private Node current = first;

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
    public boolean hasNext() { return current != null; }

    public Item next() {
      if (!hasNext())
        throw new java.util.NoSuchElementException();
      Item item = current.value;
      current = current.next;
      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<String> deq = new Deque<String>();
    String s;
    while (!StdIn.isEmpty()) {
      s = StdIn.readString();
      if (s.equals("ef")) {
        String x = StdIn.readString();
        deq.addFirst(x);
      } else if (s.equals("el")) {
        String x = StdIn.readString();
        deq.addLast(x);
      } else if (s.equals("df"))
        StdOut.println(deq.removeFirst());
      else
        StdOut.println(deq.removeLast());
    }

    for (String i : deq) {
      StdOut.println(i);
    }
  }
}
