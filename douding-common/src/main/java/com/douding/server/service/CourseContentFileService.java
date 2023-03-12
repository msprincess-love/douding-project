package com.douding.server.service;

import com.douding.server.domain.CourseContentFile;
import com.douding.server.domain.CourseContentFileExample;
import com.douding.server.dto.CourseContentFileDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.CourseContentFileMapper;
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


@Service
public class CourseContentFileService {

    @Resource
    private CourseContentFileMapper courseContentFileMapper;


    /**
     * 列表查询
     */
    public List<CourseContentFileDto> list(String courseId) {

        CourseContentFileExample example = new CourseContentFileExample();
        CourseContentFileExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        List<CourseContentFile> fileList = courseContentFileMapper.selectByExample(example);
        return CopyUtil.copyList(fileList,CourseContentFileDto.class);
    }

    public void save(CourseContentFileDto courseContentFileDto) {

        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto, CourseContentFile.class);

        //判断是新增 还是修改
        if (StringUtils.isEmpty(courseContentFileDto.getId())) {
            this.insert(courseContentFile);
        } else {
            this.update(courseContentFile);
        }

    }

    //新增数据
    private void insert(CourseContentFile courseContentFile) {


        courseContentFile.setId(UuidUtil.getShortUuid());
        courseContentFileMapper.insert(courseContentFile);
    }

    //更新数据
    private void update(CourseContentFile courseContentFile) {
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }

    public void delete(String id) {
        courseContentFileMapper.deleteByPrimaryKey(id);
    }
}//end class
