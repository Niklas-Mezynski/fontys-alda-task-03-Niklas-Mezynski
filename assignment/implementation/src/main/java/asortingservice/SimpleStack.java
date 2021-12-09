package asortingservice;

public class SimpleStack<E> {
    private StackItem<E> bottom;
    private StackItem<E> top;

    public SimpleStack() {
        bottom = new StackItem<>(null);
        top = bottom;
        top.below = bottom;
    }

    public void push(E element) {
        StackItem<E> newStackItem = new StackItem<>(element);
        newStackItem.below = top;
        top = newStackItem;
    }

    public E pop() {
        E element = top.element;
        top = top.below;
        return element;
    }

    public boolean isEmpty() {
        return top == bottom;
    }

    private static class StackItem<E> {
        private E element;
        private StackItem<E> below;

        public StackItem(E element) {
            this.element = element;
        }
    }
}
