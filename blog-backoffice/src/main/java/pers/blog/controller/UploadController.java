package pers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.blog.domain.ResponseResult;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.service.UploadService;

/**
 * @author: zyx
 * @create: 2023/8/31
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传头像
     * @param img
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile img) {
        if (img.isEmpty()) {
            throw new SystemException(AppHttpCodeEnum.FILE_IS_NULL);
        }
        return uploadService.uploadImage(img);
    }
}
