package output;

import container.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable<T extends Comparable<T>> {
    private final BinaryTree<T> tree = new BinaryTree<>();
    private final List<T> items = new ArrayList<>();

    public T getByIndex(int index) {
        return items.get(index);
    }

    public int add(T value) {
        int index = items.indexOf(value);
        if (index < 0) {
            items.add(value);
            tree.getOrPut(value);
            return items.size() - 1;
        }
        return index;
    }

    public List<T> getItems() {
        return this.items;
    }
}
