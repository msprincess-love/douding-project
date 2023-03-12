package com.douding.server.service;

import com.douding.server.domain.Category;
import com.douding.server.domain.CategoryExample;
import com.douding.server.domain.Teacher;
import com.douding.server.domain.TeacherExample;
import com.douding.server.dto.CategoryDto;
import com.douding.server.dto.TeacherDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.TeacherMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;


    /**
     * 列表查询
     */
    public PageDto<TeacherDto> list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        pageDto.setList(teacherDtoList);
        return pageDto;
    }

    public int save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.hasText(teacherDto.getId())) {
            return this.update(teacher);
        } else {
            return this.insert(teacher);
        }
    }

    //新增数据
    private int insert(Teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        return teacherMapper.insert(teacher);
    }

    //更新数据
    private int update(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    public List<TeacherDto> all() {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.setOrderByClause("sort asc");
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        return CopyUtil.copyList(teachers, TeacherDto.class);
    }


    /**
     * 查找
     * @param id
     */
    public TeacherDto findById(String id) {

        return null;
    }
}//end class
