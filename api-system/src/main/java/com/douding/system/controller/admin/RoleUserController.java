package com.douding.system.controller.admin;




import com.douding.server.dto.RoleUserDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.RoleUserService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/roleUser")
public class RoleUserController {

    private static final Logger LOG = LoggerFactory.getLogger(RoleUserController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="角色用户关联";

    @Resource
    private RoleUserService roleUserService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        roleUserService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody RoleUserDto roleUserDto){
        //field.length?c 为freemarker语法 c表示把数字转成字符串
                    ValidatorUtil.require(roleUserDto.getRoleId(), "角色");
                    ValidatorUtil.require(roleUserDto.getUserId(), "用户");
        ResponseDto<RoleUserDto> responseDto = new ResponseDto<>();
        roleUserService.save(roleUserDto);
        responseDto.setContent(roleUserDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<RoleUserDto> responseDto = new ResponseDto<>();
        roleUserService.delete(id);
        return responseDto;
    }

}//end class