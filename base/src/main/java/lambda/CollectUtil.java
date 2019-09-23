package lambda;

import java.util.*;

public class CollectUtil {

    /**
     * 提取集合里面的某个属性
     * @param in 输入集合
     * @param gtter 提取实现接口
     * @param ignoreEmpty 是否忽略空值
     * @param <In> 输入类型
     * @param <Out> 输出类型
     * @return 属性集合
     */
    public static <In, Out> List<Out> createPropertyByGetter(List<In> in, IGtter<In, Out> gtter, boolean ignoreEmpty) {
        if (in == null || in.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        List<Out> result = new ArrayList<>(in.size());
        in.forEach(
                el -> {
                    //如果属性不为空或者为空但不忽略空
                    if (el != null && gtter.get(el) != null || (!ignoreEmpty && gtter.get(el) == null)) {
                        result.add(gtter.get(el));
                    }
                }
        );
        return result;
    }

    /**
     * 提取对象的属性作为key组成Map
     * @param in 输入集合
     * @param gtter 属性提取实现接口
     * @param ignoreEmpty 是否忽略空值
     * @param <V> 值类型
     * @param <K> 键类型
     * @return 输出键值对Map
     */
    public static <V, K> Map<K, V> mapCreate(List<V> in, IGtter<V, K> gtter, boolean ignoreEmpty) {
        if (in == null || in.size() == 0) {
            return new HashMap<>(0);
        }
        Map<K, V> result = new HashMap<>();
        in.forEach(
                el -> {
                    //如果属性不为空或者为空但不忽略空
                    if (el != null && gtter.get(el) != null || (!ignoreEmpty && gtter.get(el) == null)) {
                        result.put(gtter.get(el), el);
                    }
                }
        );
        return result;
    }
}
