/**
 * @author Thomas Wei
 * An Util Class which could be used as a circle-double-way connected list
 */
public class LinkedListDeque<T> {
    /**
     * an inner class to restore necessary data and pointers
     */
    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * the actual first node is sentinel.next
     */
    private Node sentinel;
    private int size;

    /**
     * create a empty version of deque
     *
     * @param senti_item whose value doesn't matter
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Creates a deep copy of other
     *
     * @param other another deque that you want to copy.
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }

    /**
     * to add an item to the front of the deque
     *
     * @param item the item you want to add
     */
    public void addFirst(T item) {
        Node current;
        current = new Node(item, sentinel, sentinel.next);
        sentinel.next = current;
        current.next.prev = current;
        size++;
    }

    /**
     * to add an item to the last of the deque
     *
     * @param item the item you want to add
     */
    public void addLast(T item) {
        Node last = sentinel.prev;
        last.next = new Node(item, last, sentinel);
        sentinel.prev = last.next;
        size++;
    }

    /**
     * to tell whether the deque is empty
     *
     * @return true when the deque is empty, while false when it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return the item removed form the front, null when no such item.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node ptr = sentinel.next;
            sentinel.next = ptr.next;
            ptr.next.prev = sentinel;
            size--;
            return ptr.item;
        }
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return the item removed form the last, null when no such item.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            Node ptr = sentinel.prev;
            ptr.prev.next = sentinel;
            sentinel.prev = ptr.prev;
            return ptr.item;
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     *
     * @param index the position of the item you want, start with 0.
     * @return the item at index, null if it does not exit.
     */
    public T get(int index) {
        if (index + 1 > size) {
            return null;
        } else {
            Node ptr = sentinel.next;
            for (int i = 0; i < index; i++) {
                ptr = ptr.next;
            }
            return ptr.item;
        }
    }
}
