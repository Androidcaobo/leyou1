package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: Caobo
 * @Date: 2019/1/10
 * @Description:
 */
@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    // 注入FastDFS客户端
    @Autowired
    private FastFileStorageClient storageClient;



    // 支持的文件类型
    // private static final List<String> imageType = Arrays.asList("image/png", "image/jpeg");
    // 注入属性类
    @Autowired
    private UploadProperties prop;


    /**
     * @param: [file]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author: Caobo
     * @date: 2019/1/10 15:18
     * @Description: 图片上传
     */
    public String uploadImage(MultipartFile file) {
        System.out.println(prop.getBaseUrl());

        try {
            // 校验图片类型
            String contentType = file.getContentType();
            if (!prop.getAllowTypes().contains(contentType)){
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 校验文件内容,如果是图片则返回不为null
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null){
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            // 保存图片
            // 截取后缀名
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            // 1:输入流 2.文件大小 3.扩展名 4 null
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);

            // 返回路径
            return prop.getBaseUrl() +  storePath.getFullPath();
        } catch (IOException e) {
            log.error("文件上传失败",e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}
