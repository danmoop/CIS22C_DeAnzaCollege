/**
 * Stack.java
 * @author Simon Zhang
 * @author Daniil Durnev
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int length;
    private Node top;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Stack class
     * @postcondition a new Stack object with all fields
     * assigned default values
     */
    public Stack() {
        length = 0;
        top = null;
    }

    /**
     * Copy constructor for the Stack class
     * @param original the Stack to copy
     * @postcondition a new Stack object which is
     * an identical, but distinct, copy of original
     */
    public Stack(Stack<T> original) {
        if (original == null) {
            return;
        } else if (original.isEmpty()) {
            top = null;
            length = 0;
        } else {
            Node temp1 = original.top;
            this.top = new Node(temp1.data);
            Node temp2 = top;
            while (temp1.next != null) {
                temp1 = temp1.next;
                temp2.next = new Node(temp1.data);
                temp2 = temp2.next;
            }
            this.length = original.getLength();
        }

    }

    /****ACCESSORS****/

    /**
     * Determines whether data is sorted
     * in ascending order by calling
     * its recursive helper method isSorted()
     * Note: when length == 0
     * data is (trivially) sorted
     * @return whether the data is sorted
     */
    public boolean isSorted() {
        return isSorted(top);
    }


    /**
    * Helper method to isSorted
    * Recursively determines whether data is sorted
    * @param node the current node
    * @return whether the data is sorted
    */
    private boolean isSorted(Node node) {
        if(node == null || node.next == null) {
          return true;
        }
        if(node.data.compareTo(node.next.data) == 1) {
          return false;
        }
        return isSorted(node.next);
    }

      /**
      * Uses the iterative linear search
      * algorithm to locate a specific
      * element and return its position
      * @param element the value to search for
      * @return the location of value
      * from 1 to length
      * Note that in the case length==0
      * the element is considered not found
      */
     public int linearSearch(T element) {
       Node temp = top;
       for(int i = 0; i < length; i++) {
         if(temp.data.equals(element)) {
           return (i+1);
         }
         temp = temp.next;
       }
       return -1;
     }

     /**
     * Returns the location from 1 to length
     * where value is located
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the location where value is
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
        if(!isSorted()) {
          throw new IllegalStateException("binarySearch: stack is not sorted");
        }
        return binarySearch(0, length - 1, value);
    }

    /**
     * Searches for the specified value in
     * by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the location at which value is located
     * from 1 to length or -1 to indicate not found
     */
    private int binarySearch(int low, int high, T value) {
        if (high < low) {
          return -1;
        }

        Node temp = top;
        int mid = (low + high) / 2;

        for(int i = 0; i < mid; i++) {
          temp = temp.next;
        }

        if(temp.data.compareTo(value) == 0) {
          return mid + 1;
        } else if (temp.data.compareTo(value) == 1) {
          return binarySearch(low, mid - 1, value);
        } else {
          return binarySearch(mid + 1, high, value);
        }
    }

    /**
     * Returns the value stored at the top
     * of the Stack
     * @return the value at the top of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("peek: empty stack, cannot peek!");
        } else {
            return top.data;
        }
    }

    /**
     * Returns the length of the Stack
     * @return the length from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Determines whether a Stack is empty
     * @return whether the Stack is emtpy
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Determines whether two Stacks contain
     * the same values in the same order
     * @param o the Stack to compare to this
     * @return whether Q and this are equal
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Stack)) {
            return false;
        } else {
            Stack<T> S = (Stack<T>) o;
            if (S.getLength() != this.length) {
                return false;
            } else {
                Node temp1 = this.top;
                Node temp2 = S.top;
                while (temp1 != null) {
                    if (temp1.data != temp2.data) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the top of
     * the Stack
     * @param data the new data to insert
     * @postcondition a new node at the end
     * of the Stack
     */
    public void push(T data) {
        Node p = new Node(data);
        p.next = top;
        top = p;
        length++;
    }

    /**
     * Removes the top element of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the top element has
     * been removed
     */
    public void pop() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException("pop: empty stack, cannot pop!");
        } else {
            top = top.next;
        }
        length--;
    }

    /****ADDITONAL OPERATIONS****/

    /**
     * Returns the values stored in the Stack
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Stack values
     */
    @Override public String toString() {
        String result = "";
        Node temp = top;
        while (temp != null) {
            result += temp.data + " ";
            temp = temp.next;
        }
        result += "\n";
        return result;
    }

    /**
     * Prints in reverse order to the
     * console, followed by a new line
     * by calling the recursive helper
     * method printReverse
     */
    public void printReverse() {
        printReverse(top);
        System.out.print("\n");
    }

    /**
     * Recursively prints to the console
     * the data in reverse order (no loops)
     * @param node the current node
     */
    private void printReverse(Node node) {
        if(node == null) {
          return;
        }
        printReverse(node.next);
        System.out.print(node.data + " ");
    }
}
