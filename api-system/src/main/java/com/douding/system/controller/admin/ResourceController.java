package com.douding.system.controller.admin;




import com.douding.server.dto.ResourceDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.ResourceService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/resource")
public class ResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="资源";

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        resourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody String jsonStr){
        //field.length?c 为freemarker语法 c表示把数字转成字符串
        ValidatorUtil.require(jsonStr, "资源树json");

        ResponseDto<ResourceDto> responseDto = new ResponseDto<>();
        resourceService.saveJson(jsonStr);

        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<ResourceDto> responseDto = new ResponseDto<>();
        resourceService.delete(id);
        return responseDto;
    }

    @GetMapping("/load-tree")
    public ResponseDto loadTree(){

        ResponseDto responseDto = new ResponseDto();
        List<ResourceDto> resourceDtoList = resourceService.loadTree();
        responseDto.setContent(resourceDtoList);
        return responseDto;
    }

}//end class