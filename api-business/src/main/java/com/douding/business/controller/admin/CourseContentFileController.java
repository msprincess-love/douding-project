package com.douding.business.controller.admin;




import com.douding.server.dto.CourseContentFileDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.CourseContentFileService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/course-content-file")
public class CourseContentFileController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseContentFileController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="课程内容文件";

    @Resource
    private CourseContentFileService courseContentFileService;

    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId){


        return null;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseContentFileDto courseContentFileDto){

        return null;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){


        return null;
    }

}//end class