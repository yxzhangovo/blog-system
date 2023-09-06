package pers.blog.utils;

/**
 * @author: zyx
 * @create: 2023/9/6
 * @description: 在ServiceImpl中的重复操作提取出来整合为一个工具类
 */

public class ImplUtils {
    /**
     * 将String ids转换为Long[] ids
     * @return
     */
    public static Long[] removeByIds(String ids) {
        String[] strings = ids.split(",");
        Long[] longs = new Long[strings.length];
        for (int i = 0; i < strings.length; i++) {
            longs[i] = Long.parseLong(strings[i]);
        }
        return longs;
    }
}
