package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public boolean add(T value) {
        Node<T> newNode = new Node<>(tail, value, null);
        if (size == 0) {
            head = newNode;
        } else {
            tail.nextElement = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index == 0 && index < size) {
            Node<T> atIndexNode = head;
            atIndexNode.prevElement = new Node<>(null, value, atIndexNode);
            head = atIndexNode.prevElement;
            size++;
            return;
        }
        if (index > 0 && index < size) {
            Node<T> atIndexNode = getNode(index);
            atIndexNode.prevElement
                    = new Node<>(atIndexNode.prevElement, value, atIndexNode);
            atIndexNode.prevElement.prevElement.nextElement = atIndexNode.prevElement;
            size++;
            return;
        }
        throw new IndexOutOfBoundsException(index + " Out of bounds");
    }

    @Override
    public boolean addAll(List<T> list) {
        for (T t : list) {
            add(t);
        }
        return true;
    }

    @Override
    public T get(int index) {
        indexExtends(index);
        return getNode(index).element;
    }

    @Override
    public T set(T value, int index) {
        Node<T> node = getNode(index);
        T oldValue = node.element;
        node.element = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        Node<T> removable;
        removable = getNode(index);
        if (removable.prevElement == null) {
            head = removable.nextElement;
        } else {
            removable.prevElement.nextElement = removable.nextElement;
        }
        if (removable.nextElement == null) {
            tail = removable.prevElement;
        } else {
            removable.nextElement.prevElement = removable.prevElement;
        }
        removable.prevElement = null;
        removable.nextElement = null;
        size--;
        return removable.element;
    }

    @Override
    public boolean remove(T t) {
        Node<T> atIndexNode = head;
        for (int i = 0; i < size; i++) {
            if (t == atIndexNode.element || t != null && t.equals(atIndexNode.element)) {
                remove(i);
                return true;
            }
            atIndexNode = atIndexNode.nextElement;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void indexExtends(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
    }

    private Node<T> getNode(int index) {
        indexExtends(index);
        Node<T> atIndexNode = head;
        for (int i = 0; i < index; i++) {
            atIndexNode = atIndexNode.nextElement;
        }
        return atIndexNode;
    }

    private static class Node<E> {
        public E element;
        public Node<E> prevElement;
        public Node<E> nextElement;

        public Node(Node<E> prevElement, E element, Node<E> nextElement) {
            this.element = element;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("dfsdf1");
        linkedList.add("dfsdf2");
        linkedList.add("dfsdf3");
        linkedList.add("dfsdf4");
        linkedList.add("dfsdf0",2);
        linkedList.remove(0);
        linkedList.get(0);
    }
}
