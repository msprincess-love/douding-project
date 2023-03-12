package com.douding.business.controller.admin;




import com.douding.server.dto.TeacherDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="讲师";

    @Resource
    private TeacherService teacherService;

    @RequestMapping("/list")
    public ResponseDto<?> list(PageDto pageDto){
        PageDto<TeacherDto> pages = teacherService.list(pageDto);
        return ResponseDto.success(pages);
    }

    @PostMapping("/save")
    public ResponseDto<?> save(@RequestBody TeacherDto teacherDto){
        return ResponseDto.success(teacherService.save(teacherDto));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> delete(@PathVariable String id){
        teacherService.delete(id);
        return ResponseDto.success(id);
    }

    @RequestMapping("/all")
    public ResponseDto<?> all(){
        return ResponseDto.success(teacherService.all());
    }

}//end class