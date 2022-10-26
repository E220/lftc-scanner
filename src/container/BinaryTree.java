package container;

import java.util.Objects;

public class BinaryTree<T extends Comparable<T>> {
    private T value;

    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public T getOrPut(T value) {
        if (Objects.isNull(this.value)) {
            return this.overWrite(value);
        }
        int comparisonResult = this.value.compareTo(value);
        if (comparisonResult == 0) {
            return this.overWrite(value);
        }
        return comparisonResult > 0 ? this.propagateLeft(value) : this.propagateRight(value);
    }

    private T overWrite(T value) {
        this.value = value;
        return this.value;
    }

    private T propagateLeft(T value) {
        if (Objects.isNull(this.left)) {
            this.left = new BinaryTree<>();
        }
        return this.left.getOrPut(value);
    }

    private T propagateRight(T value) {
        if (Objects.isNull(this.right)) {
            this.right = new BinaryTree<>();
        }
        return this.right.getOrPut(value);
    }
}
