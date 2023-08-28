package pers.blog.utils;

import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zyx
 * @create: 2023/8/28
 * @description: 拷贝工具类
 */
public class BeanCopyUtils {

    private BeanCopyUtils() {

    }

    /**
     * Bean拷贝
     * @param source
     * @param clazz
     * @return
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * List拷贝
     * @param source
     * @param clazz
     * @return
     * @param <O>
     * @param <V>
     */
    public static <O,V> List<V> copyList(List<O> source, Class<V> clazz) {
        return source.stream().map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

}
