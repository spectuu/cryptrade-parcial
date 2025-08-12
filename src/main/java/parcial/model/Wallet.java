package parcial.model;

import java.util.Stack;

public class Wallet<T> {

    private final Stack<T> items;

    public Wallet() {

        this.items = new Stack<>();

    }

    public Stack<T> getItems() {
        return items;
    }

    public void addItem(T item) {
        items.push(item);
    }

    public T removeItem() {
        if (!items.isEmpty()) {
            return items.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

}
