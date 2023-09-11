package pers.blog.service;

import org.springframework.web.multipart.MultipartFile;
import pers.blog.domain.ResponseResult;

/**
 * @author: zyx
 * @create: 2023/8/31
 */
public interface UploadService {
    // 头像上传
    ResponseResult uploadImage(MultipartFile img);
}
