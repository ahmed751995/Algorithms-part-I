import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueWithTwoStacks<Item> {
  Stack<Item> m;
  Stack<Item> t;
  public QueueWithTwoStacks() {
    m = new Stack<Item>();
    t = new Stack<Item>();
  }

  public void enqueue(Item x) { m.push(x); }

  public Item dequeue() {
    if (t.isEmpty()) {
      while (!m.isEmpty()) {
        t.push(m.pop());
      }
    }
    return t.pop();
  }

  public static void main(String[] args) {
    QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<Integer>();
    while (!StdIn.isEmpty()) {
      String i = StdIn.readString();
      if (i.equals("-")) {
        StdOut.println(q.dequeue());
      } else {
        q.enqueue(Integer.parseInt(i));
      }
    }
  }
}
