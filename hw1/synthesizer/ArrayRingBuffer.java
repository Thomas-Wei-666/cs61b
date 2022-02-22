package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last++;
        }
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        if (first == capacity - 1) {
            first = 0;
        } else {
            first++;
        }
        fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("can't peek an empty buffer");
        }
        return rb[first];
    }


    public void resetMark() {
        fillCount = 0;
        first = 0;
        last = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int ptr;

        public MyIterator() {
            ptr = first;
        }

        public boolean hasNext() {
            if (ptr == last - 1) {
                return false;
            }
            return true;
        }

        public T next() {
            T res = rb[ptr];
            if (ptr == capacity - 1) {
                ptr = 0;
            } else {
                ptr += 1;
            }
            return res;
        }
    }
}
