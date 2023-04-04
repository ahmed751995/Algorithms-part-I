import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithMax {

  private Stack<Double> maxStack;
  private Stack<Double> mainStack;
  private Double max;
  
  public StackWithMax() {
    maxStack = new Stack<Double>();
    mainStack = new Stack<Double>();
  }

  public Double getMax() {
    return max;
  }

  public void push(Double n) {
    mainStack.push(n);
    if(max == null) max = n;
    else if(n > max) {
      maxStack.push(max);
      max = n;
    }
  }

  public Double pop() {
    Double n = mainStack.pop();

    if(n == max) {
      if(maxStack.isEmpty()) max = null;
      else max = maxStack.pop();
    }
    
    return n;
  }

  public void printStack() {
    for(Double i : mainStack) {
      StdOut.print(i + " ");
    }
    StdOut.println();
  }

  public static void main(String[] args) {
    StackWithMax s = new StackWithMax();
    while(!StdIn.isEmpty()) {
      String x = StdIn.readString();
      if(x.equals("-")) StdOut.println(s.pop());
      else if(x.equals("m")) StdOut.println(s.getMax());
      else s.push(Double.parseDouble(x));
      s.printStack();
    }
  }
}
