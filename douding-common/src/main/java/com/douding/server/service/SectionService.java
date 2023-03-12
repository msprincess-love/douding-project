package com.douding.server.service;

import com.douding.server.domain.ChapterExample;
import com.douding.server.domain.Section;
import com.douding.server.domain.SectionExample;
import com.douding.server.dto.SectionDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.SectionPageDto;
import com.douding.server.enums.SectionChargeEnum;
import com.douding.server.mapper.SectionMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.util.List;


import java.util.Date;


@Service
public class SectionService {

    @Resource
    private SectionMapper sectionMapper;

    @Resource
    private CourseService courseService;


    /**
     * 列表查询
     */
    public void list(SectionPageDto sectionPageDto) {

    }


    public void save(SectionDto sectionDto) {

    }

    //新增数据
    private void insert(Section section) {


    }

    //更新数据
    private void update(Section section) {

    }

    public void delete(String id) {

    }

    /**
     * 查询某一课程下的所有节
     */
    public List<SectionDto> listByCourse(String courseId) {

        return null;
    }

}//end class
