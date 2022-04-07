package interfaces;

public interface IBinaryTree<E> {
    IBinaryTree<E> left();
    IBinaryTree<E> right();
    void linkLeft(IBinaryTree<E> x);
    void linkRight(IBinaryTree<E> x);
    
    E getData();
    void shift(E x);
}
