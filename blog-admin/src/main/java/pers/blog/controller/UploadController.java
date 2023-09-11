package pers.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pers.blog.domain.ResponseResult;
import pers.blog.service.UploadService;

/**
 * @author: zyx
 * @create: 2023/9/6
 */
@Api(tags = "图片上传", description = "图片上传相关接口")
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @ApiOperation(value = "图片上传")
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img")MultipartFile multipartFile) {
        try {
            return uploadService.uploadImage(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传失败");
        }
    }
}
