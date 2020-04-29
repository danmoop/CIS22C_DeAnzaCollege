/**
 * Queue.java
 * @author Simon Zhang
 * @author
 */

import java.util.NoSuchElementException;

public class Queue<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int length;
    private Node front;
    private Node end;

    /********** constructors ***********/

    /**
     *  Default constructor for the Queue class
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        length = 0;
        front = null;
        end = null;
    }

    /**
     * Copy constructor for the Queue class
     * @param original the Queue to copy
     * @postcondition a new Queue object which is
     * an identical, but distinct, copy of original
     */
    public Queue(Queue<T> original) {
        if (original == null) {
            return;
        } else if (original.isEmpty()) {
            front = end = null;
            length = 0;
        } else {
            Node temp = original.front;
            for (int i = 0; i < original.getLength(); i++) {
                enqueue(temp.data);
                temp = temp.next;
            }
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     * @return the value at the front of the queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T getFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("getFront: empty Queue, cannot get front node!");
        } else {
            return front.data;
        }
    }


    /**
     * Returns the length of the Queue
     * @return the length from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Determines whether a Queue is empty
     * @return whether the Queue is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param o the Object to compare to this
     * @return whether o and this are equal
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Queue)) {
            return false;
        } else {
            Queue<T> q = (Queue<T>) o;
            if (this.length != q.getLength()) {
                return false;
            } else {
                Node temp1 = this.front;
                Node temp2 = q.front;
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
     * Inserts a new value at the end of
     * the Queue
     * @param data the new data to insert
     * @postcondition a new node at the end
     * of the Queue
     */
    public void enqueue(T data) {
        if (isEmpty()) {
            front = end = new Node(data);
        } else {
            Node p = new Node(data);
            end.next = p;
            end = p;
        }
        length++;

    }

    /**
     * Removes the front element in the Queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has
     * been removed
     */
    public void dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("dequeue: empty Queue, cannot remove!");
        } else if (length == 1) {
            front = end = null;
        } else {
            front = front.next;
        }
        length--;
    }

    /****ADDITIONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override public String toString() {
        String result = "";
        Node temp = front;
        for (int i = 0; i < length; i++) {
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
        return;
    }

    /**
     * Recursively prints to the console
     * the data in reverse order (no loops)
     * @param node the current node
     */

    private void printReverse(Node node) {
        return;
    }

}
