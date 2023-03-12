package com.douding.business.controller.admin;

import com.douding.server.dto.ChapterDto;
import com.douding.server.dto.ChapterPageDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.ChapterService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/chapter")
//对应包路径,访问变为 /business/admin/chapter
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="大章";

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public ResponseDto list(ChapterPageDto chapterPageDto){


        return null;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chatperDto){


        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){


        return null;
    }

}
