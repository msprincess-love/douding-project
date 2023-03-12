package com.douding.server.service;

import com.douding.server.domain.RoleResource;
import com.douding.server.domain.RoleResourceExample;
import com.douding.server.dto.RoleResourceDto;
import com.douding.server.dto.PageDto;
import com.douding.server.mapper.RoleResourceMapper;
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
public class RoleResourceService {

@Resource
private RoleResourceMapper roleResourceMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleResourceDto> roleResourceDtoList = CopyUtil.copyList(roleResourceList, RoleResourceDto.class);
        pageDto.setList(roleResourceDtoList);
    }

            public void save(RoleResourceDto roleResourceDto) {

                RoleResource roleResource = CopyUtil.copy(roleResourceDto, RoleResource.class);

                //判断是新增 还是修改
                if(StringUtils.isEmpty(roleResourceDto.getId())){
                this.insert(roleResource);
                }
                else{
                this.update(roleResource);
                }

            }
            //新增数据
            private void insert(RoleResource roleResource) {


                    roleResource.setId(UuidUtil.getShortUuid());
                    roleResourceMapper.insert(roleResource);
            }
            //更新数据
            private void update(RoleResource roleResource) {
                roleResourceMapper.updateByPrimaryKey(roleResource);
            }

            public void delete(String id) {
                roleResourceMapper.deleteByPrimaryKey(id);
            }
}//end class
