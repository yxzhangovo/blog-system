package pers.blog.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pers.blog.domain.ResponseResult;
import pers.blog.enums.AppHttpCodeEnum;
import pers.blog.exception.SystemException;
import pers.blog.service.UploadService;
import pers.blog.utils.FileNameUtils;

import java.io.*;

/**
 * @author: zyx
 * @create: 2023/8/31
 */
@Data
@Service
public class UploadServiceImpl implements UploadService {
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 上传头像
     * @param img
     * @return
     */
    @Override
    public ResponseResult uploadImage(MultipartFile img) {
        // 判断文件类型
        if (!img.getOriginalFilename().endsWith(".png")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }

        // 设置文件名称
        String fileName = FileNameUtils.generateFilePath(img.getOriginalFilename());

        String url = uploadOss(img, fileName);
        return ResponseResult.okResult(url);
    }

    /**
     * 上传文件
     */
    private String uploadOss(MultipartFile img, String fileName) {
        // 创建OssClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件
        try (InputStream inputStream = img.getInputStream()) {
            ossClient.putObject("ahci", fileName, inputStream);
            // 返回文件链接
            return "https://ahci.oss-cn-beijing.aliyuncs.com/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }
    }
}
