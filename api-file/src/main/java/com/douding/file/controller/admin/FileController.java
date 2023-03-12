package com.douding.file.controller.admin;




import com.douding.server.dto.FileDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.FileService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="文件";

    @Resource
    private FileService fileService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }




}//end class