package com.douding.file.controller.admin;


import com.douding.server.dto.FileDto;
import com.douding.server.dto.ResponseDto;

import com.douding.server.service.FileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class OssController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);


    public static final String BUSINESS_NAME = "文件上传";

    @Resource
    private FileService fileService;

    @PostMapping("/oss-append")
    public ResponseDto fileUpload(@RequestBody FileDto fileDto) throws Exception {

        return null;
    }




    @PostMapping("/oss-simple")
    public ResponseDto fileUpload(@RequestParam MultipartFile file, String use) throws Exception {

        return null;
    }

    /*
        http://127.0.0.1:9000/file/admin/oss-check/267GleNQaeI6uaYKs22YW
        文件指纹加密值 267GleNQaeI6uaYKs22YW 验证数据库中是否已经保存过该文件
        查找文件分片位置
        无论断点传,还是全新传,都需要调用该方法进行验证
     */
    @GetMapping("/oss-check/{key}")
    public ResponseDto check(@PathVariable String key) throws Exception {
       return null;
    }


}
