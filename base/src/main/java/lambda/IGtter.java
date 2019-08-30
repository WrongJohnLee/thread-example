package lambda;

/**
 * 属性提取函数式接口
 * @param <In> 输入类型
 * @param <Out> 输出类型
 */
@FunctionalInterface
public interface IGtter<In, Out> {
    /**
     * 根据一个对象提取结果
     * @param in 输入
     * @return 输出
     */
    Out get(In in);
}
