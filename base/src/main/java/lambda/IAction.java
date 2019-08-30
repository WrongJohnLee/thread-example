package lambda;

/**
 * 对象处理函数式接口
 * @param <In>
 * @param <Out>
 */
@FunctionalInterface
public interface IAction<In,Out>{
    Out accept(In in);
}