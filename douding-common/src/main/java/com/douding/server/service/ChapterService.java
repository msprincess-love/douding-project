package com.douding.server.service;

import com.douding.server.domain.Chapter;
import com.douding.server.domain.ChapterExample;
import com.douding.server.dto.ChapterDto;
import com.douding.server.dto.ChapterPageDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.ChapterMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public void list(ChapterPageDto chapterPageDto){



    }// method


    public void save(ChapterDto chapterDto) {



    }
    //新增数据
    private void insert(Chapter chapter) {

    }
    //更新数据
    private void update(Chapter chapter) {

    }

    public void delete(String id) {

    }


    /**
     * 查询某一课程下的所有章
     */
    public List<ChapterDto> listByCourse(String courseId) {

        return null;
    }
}//end class
