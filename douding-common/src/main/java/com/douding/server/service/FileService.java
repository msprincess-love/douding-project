package com.douding.server.service;

import com.douding.server.domain.File;
import com.douding.server.domain.FileExample;
import com.douding.server.dto.FileDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.FileMapper;
import com.douding.server.util.CopyUtil;
import com.douding.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import java.util.Date;

@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        FileExample fileExample = new FileExample();
        List<File> fileList = fileMapper.selectByExample(fileExample);
        PageInfo<File> pageInfo = new PageInfo<>(fileList);
        pageDto.setTotal(pageInfo.getTotal());
        List<FileDto> fileDtoList = CopyUtil.copyList(fileList, FileDto.class);
        pageDto.setList(fileDtoList);
    }

    public void save(FileDto fileDto) {

        File file = CopyUtil.copy(fileDto, File.class);
        //根据key值去数据库查询File
        File fileDb = selectByKey(fileDto.getKey());
        //判断是新增 还是修改
        if (fileDb == null) {
            this.insert(file);
        } else {
            //如果是更新的话 先更改文件的ShardIndex属性 在update
            fileDb.setShardIndex(fileDto.getShardIndex());
            this.update(fileDb);
        }

    }

    //新增数据
    private void insert(File file) {

        Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);

        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

    //更新数据
    private void update(File file) {
        file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    public void delete(String id) {

        fileMapper.deleteByPrimaryKey(id);
    }

    //根据前端生成的MD5标识查询文件
    public File selectByKey(String key) {
        FileExample example = new FileExample();
        example.createCriteria().andKeyEqualTo(key);
        List<File> fileList = fileMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(fileList)) {
            return null;
        } else {
            return fileList.get(0);
        }
    }
    /**
     * 根据文件标识查询数据库记录
     */
    public FileDto findByKey(String key) {
        return CopyUtil.copy(selectByKey(key), FileDto.class);
    }

}//end class
