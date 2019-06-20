package tree.binarytree;

/**
 * 树抽象接口
 * @see <link https://www.cnblogs.com/ysocean/p/8032642.html />
 */
public interface Tree<T>  {

    boolean insert(T t);

    Node<T> find(T t);

    Node<T> findMax();

    Node<T> findMin();

    boolean delete(T t);
}
