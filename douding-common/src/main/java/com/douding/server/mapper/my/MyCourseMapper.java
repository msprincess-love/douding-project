package com.douding.server.mapper.my;

import com.douding.server.dto.CourseDto;
import com.douding.server.dto.CoursePageDto;
import com.douding.server.dto.SortDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCourseMapper {

    //web 页面 分类查询数据
    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);

    int updateTime(@Param("courseId")String courseId);

    //排序相关的三个方法
    int updateSort(SortDto sortDto);
    int moveSortForward(SortDto sortDto);
    int moveSortBackward(SortDto sortDto);
}
