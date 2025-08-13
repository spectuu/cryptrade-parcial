package parcial.model;

import java.util.Stack;

// se usa una bag para almacenar los elementos de la billetera, ya que no se requiere un orden específico de los elementos
public class Wallet<T> {

    private final Stack<T> items; // se usa Stack para que el último elemento agregado sea el primero en salir (LIFO)

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
