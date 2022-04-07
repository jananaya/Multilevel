import interfaces.IBinaryTree;

public class BinaryTree<E> implements IBinaryTree<E> {
    private IBinaryTree left;
    private IBinaryTree right;
    private E data;

    public BinaryTree(E dato) {
        this.data = dato;
    }
    
    @Override
    public IBinaryTree<E> left() {
        return this.left;
    }

    @Override
    public IBinaryTree<E> right() {
        return this.right;
    }

    @Override
    public void linkLeft(IBinaryTree<E> x) {
        this.left = x;
    }

    @Override
    public void linkRight(IBinaryTree x) {
        this.right = x;
    }

    @Override
    public E getData() {
        return this.data;
    }

    @Override
    public void shift(E x) {
        this.data = x;
    }
}
