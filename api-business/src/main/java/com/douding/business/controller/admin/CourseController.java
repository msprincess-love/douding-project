package com.douding.business.controller.admin;




import com.douding.server.dto.*;
import com.douding.server.service.CourseCategoryService;
import com.douding.server.service.CourseService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="课程";

    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value="courseId")String courseId){

        return null;
    }

    @RequestMapping("/list")
    public ResponseDto list(CoursePageDto pageDto){

        return null;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){


        return null;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){


        return null;
    }

    //课程内容相关查找
    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id) {

        return null;
    }

    //课程内容保存
    @PostMapping("/save-content")
    public ResponseDto saveConent(@RequestBody CourseContentDto contentDto) {

        return null;
    }

    //课程排序
    @PostMapping("/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto){

        return null;
    }

}//end class