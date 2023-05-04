// Shuffling a linked list.
// Given a singly-linked list containing n items, rearrange the items uniformly at random.
//   Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlog⁡n,  nlogn in the worst case.

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
public class RandomLinkedList {
  private class Node {
    int value;
    Node next;
  }

  public Node merge(Node first, Node second, int len) {
    Node point = null;
    Node p = null;
    for (int i = 0; i < len; i++) {
      if (first == null) {
        p.next = second;
        p = p.next;
        second = second.next;
      } else if (second == null) {
        p.next = first;
        p = p.next;
        first = first.next;
      } else {
        if (StdRandom.uniformDouble() < 0.5) {
          if (point == null) {
            point = first;
            p = first;
          } else {
            p.next = first;
            p = p.next;
          }
          first = first.next;
        } else {
          if (point == null) {
            point = second;
            p = second;
          } else {
            p.next = second;
            p = p.next;
          }
          second = second.next;
        }
      }
    }
    p.next = null;
    return point;
  }

  public Node shuffle(Node point, int len) {
    if (len == 1){
      point.next = null;
      return point;
    }
    int mid = len / 2;
    Node nextPoint = point;
    for (int i = 0; i < mid; i++) {
      nextPoint = nextPoint.next;
    }

    Node first = shuffle(point, mid);
    Node second = shuffle(nextPoint, len - mid);
    Node mergedList = merge(first, second, len);
    return mergedList;
  }

  public Node getNodes(int[] arr) {
    Node first = null;
    Node p = null;
    for (int i = 0; i < arr.length; i++) {
      Node point = new Node();
      point.value = arr[i];
      point.next = null;
      if (first == null) {
        first = point;
        p = point;
      } else {
        p.next = point;
        p = point;
      }
    }
    return first;
  }

  public static void main(String[] args) {
    RandomLinkedList f = new RandomLinkedList();
    int[] arr = {1, 3, 5,7, 8, 9, 18, 19, 0, 2};
    // int[] arr2 = {4, 6, 11};
    Node r = f.getNodes(arr);
    // Node r2 = f.getNodes(arr2);
    Node p = f.shuffle(r, arr.length);
    while (p != null) {
      StdOut.print(p.value + ", ");
      p = p.next;
    }
    StdOut.println();
  }
}
