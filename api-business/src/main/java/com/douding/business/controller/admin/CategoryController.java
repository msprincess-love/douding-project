package com.douding.business.controller.admin;




import com.douding.server.dto.CategoryDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.CategoryService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="分类";

    @Resource
    private CategoryService categoryService;

    //条件查询
    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        categoryService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    //查询所有数据
    @RequestMapping("/all")
    public ResponseDto all(){

        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto){
        //field.length?c 为freemarker语法 c表示把数字转成字符串
                    ValidatorUtil.require(categoryDto.getParent(), "父id");
                    ValidatorUtil.require(categoryDto.getName(), "名称");
                    ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);
        ResponseDto<CategoryDto> responseDto = new ResponseDto<>();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<CategoryDto> responseDto = new ResponseDto<>();
        categoryService.delete(id);
        return responseDto;
    }

}//end class