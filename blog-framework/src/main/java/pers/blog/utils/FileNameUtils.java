package pers.blog.utils;

import java.util.UUID;

/**
 * @author: zyx
 * @create: 2023/8/31
 * @description: 头像名称生成
 */
public class FileNameUtils {
    public static String generateFilePath(String fileName){
        // uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 后缀和文件后缀一致
        int index = fileName.lastIndexOf(".");
        // test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return new StringBuilder().append(uuid).append(fileType).toString();
    }
}
