package asortingservice;

import sortingservice.Queue;
import sortingservice.Sorter;

import java.util.Comparator;

public class HeapSorter<T> implements Sorter<T> {

    private final Comparator<T> comp;

    public HeapSorter(Comparator<T> comp) {
        this.comp = comp;
    }

    @Override
    public Queue<T> sort(Queue<T> q) {
        if (q.isEmpty())
            return q;

        SimpleStack<TNode<T>> parentStack = new SimpleStack<>();
        SimpleStack<TNode<T>> lastNodeStack = new SimpleStack<>();
        //Build a tree
        TNode<T> root = buildTree(q, parentStack, lastNodeStack);
        //Heapify the tree
        heapify(parentStack);

        while (!lastNodeStack.isEmpty()) {
            //Put root (smallest) element in the result q
            q.put(root.element);
            //Swap root with last node and remove last note
            TNode<T> last = lastNodeStack.pop();
            exchElements(root, last);
            removeNode(last);
            //Sink the new root element until it's in proper position again
            sink(root);
        }
        //Now only the root element remains -> Add this to the queue as last element, sorting done
        q.put(root.element);

        return q;
    }

    private TNode<T> buildTree(Queue<T> q, SimpleStack<TNode<T>> parentStack, SimpleStack<TNode<T>> lastNodeStack) {
        if (q.isEmpty()) {
            return new TNode<>(null);
        }
        SimpleQueue<TNode<T>> heapQ = new SimpleQueue<>();
        TNode<T> root = new TNode<>(q.get());
        heapQ.put(root);

        while (!q.isEmpty()) {
            TNode<T> parent = heapQ.get();
            parentStack.push(parent);
            TNode<T> left = new TNode<>(q.get());
            parent.left = left;
            left.parent = parent;
            heapQ.put(left);
            lastNodeStack.push(left);
            if (!q.isEmpty()) {
                TNode<T> right = new TNode<>(q.get());
                parent.right = right;
                right.parent = parent;
                heapQ.put(right);
                lastNodeStack.push(right);
            }
        }

        return root;
    }

    private void heapify(SimpleStack<TNode<T>> parentStack) {
        while (!parentStack.isEmpty()) {
            sink(parentStack.pop());
        }
    }

    private void sink(TNode<T> parent) {
        while (parent.left != null) {
            TNode<T> childToCompare = parent.left;
            if (parent.right != null && less(parent.right, parent.left)) {
                childToCompare = parent.right;
            }
            if (!less(childToCompare, parent)) {
                break;
            }
            exchElements(childToCompare, parent);
            parent = childToCompare;
        }
    }

    private boolean less(TNode<T> a, TNode<T> b) {
        return comp.compare(a.element, b.element) < 0;
    }

    private void removeNode(TNode<T> last) {
        TNode<T> parent = last.parent;
        if (parent.right == last) {
            parent.right = null;
        } else {
            parent.left = null;
        }
    }

    private void exchElements(TNode<T> a, TNode<T> b) {
        T temp = a.element;
        a.element = b.element;
        b.element = temp;
    }

    private static class TNode<T> {
        private T element;
        private TNode<T> left;
        private TNode<T> right;
        private TNode<T> parent;

        private TNode(T element) {
            this.element = element;
        }
    }
}
