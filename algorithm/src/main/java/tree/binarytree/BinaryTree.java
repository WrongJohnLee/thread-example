package tree.binarytree;

import java.util.HashSet;

public class BinaryTree<T> implements Tree<T>{
    private Node<T> root;

    /**
     * 插入节点，从根节点开始，判断左右子节点是否存在，进行比较
     * @param t
     * @return
     */
    public boolean insert(T t) {
        Node<T> newNode = new Node<T>();
        newNode.setData(t);
        if (root ==null) {
            root = newNode;
            return true;
        }else {
            int hashCode = t.hashCode();
            Node<T> current = root;
            Node<T> parent;
            while (current != null) {
                parent = current;
                if (current.getData().hashCode() > hashCode) {
                    if (current.getLeftChild() == null) {
                        parent.setLeftChild(newNode);
                        return true;
                    }else {
                        current = current.getLeftChild();
                    }
                }
                else if (current.getData().hashCode() < hashCode) {
                    if (current.getRightChild() == null) {
                        parent.setRightChild(newNode);
                        return true;
                    }else {
                        current = current.getRightChild();
                    }
                }
            }

        }
        return false;
    }

    /**
     * 从跟节点开始往下找，根据左右节点的大小比较
     * @param t
     * @return
     */
    public Node<T> find(T t) {
        int hashCode = t.hashCode();
        Node<T> current = root;
        int size = 0;
        while (current != null) {
            System.out.println(++size);
            int currentCode = current.getData().hashCode();
            if (hashCode > currentCode) {
                current = current.getRightChild();
            } else if (hashCode < currentCode) {
                current = current.getLeftChild();
            }else {
                return current;
            }
        }
        return null;
    }

    public Node<T> findMax() {
        Node<T> current = root;
        Node<T> maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.getRightChild();
        }

        return maxNode;
    }

    public Node<T> findMin() {
        Node<T> current = root;
        Node<T> minNode = current;
        while (current != null) {
            minNode = current;
            current = current.getLeftChild();
        }

        return minNode;
    }

    public boolean delete(T t) {
        return false;
    }

    public static void main(String[] args) {
        Tree<Integer> tree = new BinaryTree<Integer>();
        tree.insert(1);
        tree.insert(4);
        tree.insert(7);
        tree.insert(-1);
        tree.insert(10);
        tree.insert(18);
        tree.insert(-8);
        tree.insert(-18);

        tree.find(-8);
        //System.out.println(tree.findMax());
        //System.out.println(tree.findMin());
    }
}
