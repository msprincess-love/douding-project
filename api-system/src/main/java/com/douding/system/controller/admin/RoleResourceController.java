package com.douding.system.controller.admin;




import com.douding.server.dto.RoleResourceDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.RoleResourceService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/roleResource")
public class RoleResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleResourceController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="角色资源关联";

    @Resource
    private RoleResourceService roleResourceService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        roleResourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleResourceDto roleResourceDto){
        //field.length?c 为freemarker语法 c表示把数字转成字符串
                    ValidatorUtil.require(roleResourceDto.getRoleId(), "角色");
                    ValidatorUtil.require(roleResourceDto.getResourceId(), "资源");
        ResponseDto<RoleResourceDto> responseDto = new ResponseDto<>();
        roleResourceService.save(roleResourceDto);
        responseDto.setContent(roleResourceDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<RoleResourceDto> responseDto = new ResponseDto<>();
        roleResourceService.delete(id);
        return responseDto;
    }

}//end class