package tree.binarytree;

/**
 * 树抽象接口
 * @see <link https://www.cnblogs.com/ysocean/p/8032642.html />
 */
public interface Tree<T>  {

    void insert(T t);

    Node find(T t);

    boolean delete(T t);
}
