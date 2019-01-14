package com.leyou.upload.web;

import com.leyou.common.utils.IdWorker;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.UUID;

/**
 * @Author: Caobo
 * @Date: 2019/1/10
 * @Description: 文件上传控制层
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * @param: [file]
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author: Caobo
     * @date: 2019/1/10 15:16
     * @Description: 上传图片
     */
    @PostMapping("image")
    public ResponseEntity<String> uploadImage( @RequestParam("file")MultipartFile file){

        String url = uploadService.uploadImage(file);
        if (StringUtils.isBlank(url)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(url);
    }
}
