package queue;

import java.util.Stack;

/**
 * @ClassName QueueByStack
 * @Description 采用两个stack实现队列
 * @Author qzli
 * @Date 2019/7/30 22:07
 * @Version 1.0
 **/
public class QueueByStack<T> {
    private Stack<T> stackA = new Stack<>();
    private Stack<T> stackB = new Stack<>();
    
    public void enQueue(T t) {
        stackA.push(t);
    }
    
    public T dequeue() {
        if (stackB.isEmpty()) {
            if (stackA.isEmpty()) {
                return null;
            }
            transfer();
        }
        return stackB.pop();
    }
    
    private void transfer() {
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }
    }
    
    public static void main(String[] args) {
        QueueByStack<Integer> queue = new QueueByStack<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enQueue(4);
        System.out.println(queue.dequeue());
    }
}
