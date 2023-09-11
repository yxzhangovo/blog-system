package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "图片上传", description = "图片上传相关接口")
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传头像
     * @param img
     * @return
     */
    @ApiOperation(value = "上传头像")
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile img) {
        if (img.isEmpty()) {
            throw new SystemException(AppHttpCodeEnum.FILE_IS_NULL);
        }
        return uploadService.uploadImage(img);
    }
}
