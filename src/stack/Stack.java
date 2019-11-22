package stack;

public interface Stack {
    boolean isEmpty();

    Integer getSize();

    void push(int element);

    Integer pop();

    Integer top();

    Integer find(int i);

    public static class IllegalCapacity extends RuntimeException {
    }

    public static class Empty extends RuntimeException {
    }

    public static class Overflow extends RuntimeException {
    }

    public static class Underflow extends RuntimeException {
    }
}
