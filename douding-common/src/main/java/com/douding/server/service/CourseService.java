package com.douding.server.service;

import com.douding.server.domain.Course;
import com.douding.server.domain.CourseContent;
import com.douding.server.domain.CourseExample;
import com.douding.server.dto.*;
import com.douding.server.enums.CourseStatusEnum;
import com.douding.server.mapper.CourseContentMapper;
import com.douding.server.mapper.CourseMapper;
import com.douding.server.mapper.my.MyCourseMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import java.util.Date;


@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService myCategoryService;

    @Resource
    private CourseContentMapper courseContentMapper;

    @Resource
    private TeacherService teacherService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private SectionService sectionService;



    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);


    /**
     * 列表查询：关联课程分类表 web接口
     * @param pageDto
     */
    public void list(CoursePageDto pageDto) {

    }


    public void save(CourseDto courseDto) {

    }

    //新增数据
    private void insert(Course course) {


    }

    //更新数据
    private void update(Course course) {

    }

    public void delete(String id) {

    }

    //更新课程时长
    public void updateTime(@Param("courseId")String courseId){

    }

    //课程内容相关的操作 查找 新增,修改
    public CourseContentDto findContent(String id) {

        return null;
    }

    //新增内容 或者修改内容
    public int saveContent(CourseContentDto contentDto) {

        return 0;
    }


    public void sort(SortDto sortDto){

    }
    /**
     * 查找某一课程，供web模块用，只能查已发布的
     * @param id
     * @return
     */
    public CourseDto findCourse(String id) {

        return null;
    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<CourseDto> listNew(PageDto pageDto) {

        return null;
    }



}//end class
