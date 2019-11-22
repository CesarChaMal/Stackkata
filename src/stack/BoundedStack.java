package stack;

public class BoundedStack implements Stack {
    private final int capacity;
    private int size;
    private int elements[];

    public static Stack make(int capacity) {
        if (capacity < 0)
            throw new IllegalCapacity();
        if (capacity == 0)
            return new ZeroCapacityStack();
        return new BoundedStack(capacity);
    }

    private BoundedStack(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public void push(int element) {
        if (size == capacity)
            throw new Overflow();
        this.elements[size++] = element;
    }

    @Override
    public Integer pop() {
        if (isEmpty())
            throw new Underflow();
        return elements[--size];
    }

    @Override
    public Integer top() {
        if (isEmpty())
            throw new Empty();
        return elements[size-1];
    }

    @Override
    public Integer find(int element) {
        for (int i=size-1; i>=0; i--)
            if (elements[i] == element)
                return (size-1)-i;
        return null;
    }

    private static class ZeroCapacityStack implements Stack {

        public boolean isEmpty() {
            return true;
        }

        public Integer getSize() {
            return 0;
        }

        public void push(int element) {
            throw new Overflow();
        }

        public Integer pop() {
            throw new Underflow();
        }

        @Override
        public Integer top() {
            throw new Empty();
        }

        @Override
        public Integer find(int i) {
            return null;
        }
    }
}
