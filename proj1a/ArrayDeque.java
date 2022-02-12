/**
 * a deque using array
 *
 * @author Thomas Wei
 */
public class ArrayDeque<T> {
    private static final int REFACTOR = 2;
    private static final int REDUCE = 2; //0 < REDUCE < 4

    private T[] item;
    private int size;

    /**
     * create a new ArrayDeque which is empty
     */
    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
    }

    /**
     * deep copy another ArrayDeque
     *
     * @param other the resource of the copy
     */
    /**public ArrayDeque(ArrayDeque other) {
     item = (T[]) new Object[other.size];
     size = other.size;
     System.arraycopy(other.item, 0, this.item, 0, size);
     }

     */

    /**
     * helping method to resize the deque when it's necessary.
     *
     * @param capacity the new length you want.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(item, 0, a, 0, size);
        item = a;
    }

    /**
     * to add an item to the front of the deque
     *
     * @param itemAdd the item you want to add
     */
    public void addFirst(T itemAdd) {
        if (size == item.length) {
            resize(size * REFACTOR);
        }
        for (int i = size; i > 0; i--) {
            item[i] = item[i - 1];
        }
        item[0] = itemAdd;
        size++;
    }

    /**
     * to add an item to the last of the deque
     *
     * @param itemAdd the item you want to add
     */
    public void addLast(T itemAdd) {
        if (size == item.length) {
            resize(size * REFACTOR);
        }
        item[size] = itemAdd;
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
        for (int i = 0; i < size; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.print("\n");
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return the item removed form the front, null when no such item.
     */
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        T first = item[0];
        if (item.length > 16 && (float) (size - 1) / item.length < 0.25) {
            resize(item.length / REDUCE);
        }
        for (int i = 0; i < size - 1; i++) {
            item[i] = item[i + 1];
        }
        item[size - 1] = null;
        size--;
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return the item removed form the last, null when no such item.
     */
    public T removeLast() {
        T last = item[size - 1];
        if (item.length > 16 && (float) (size - 1) / item.length < 0.25) {
            resize(item.length / REDUCE);
        }
        item[size - 1] = null;
        size--;
        return last;
    }

    public T get(int index) {
        return item[index];
    }
}
