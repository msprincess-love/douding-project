package com.douding.server.service;

import com.douding.server.domain.CourseCategory;
import com.douding.server.domain.CourseCategoryExample;
import com.douding.server.dto.CategoryDto;
import com.douding.server.dto.CourseCategoryDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.CourseCategoryMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);
        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseCategoryDto> courseCategoryDtoList = CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
        pageDto.setList(courseCategoryDtoList);
    }

    public void save(CourseCategoryDto courseCategoryDto) {

        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);

        //判断是新增 还是修改
        if (StringUtils.isEmpty(courseCategoryDto.getId())) {
            this.insert(courseCategory);
        } else {
            this.update(courseCategory);
        }

    }

    //新增数据
    private void insert(CourseCategory courseCategory) {


        courseCategory.setId(UuidUtil.getShortUuid());
        courseCategoryMapper.insert(courseCategory);
    }

    //更新数据
    private void update(CourseCategory courseCategory) {
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    //批量保存
    @Transactional
    public void saveBatch(String courseId, List<CategoryDto> categories) {

        //防止重复灌相同的数据 批量增加之前先清空相关数据
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);

        for (int i = 0; i < categories.size(); i++) {
            CategoryDto categoryDto = categories.get(i);
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setId(UuidUtil.getShortUuid());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            insert(courseCategory);
        }
    }//end method

    //查询课程下所有分类
    public List<CourseCategoryDto> listByCourse(String courseId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> categories = courseCategoryMapper.selectByExample(example);
        return CopyUtil.copyList(categories, CourseCategoryDto.class);
    }

}//end class
