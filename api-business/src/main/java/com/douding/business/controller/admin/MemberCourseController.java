package com.douding.business.controller.admin;




import com.douding.server.dto.MemberCourseDto;
import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.MemberCourseService;
import com.douding.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/memberCourse")
public class MemberCourseController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberCourseController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="会员课程报名";

    @Resource
    private MemberCourseService memberCourseService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        memberCourseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberCourseDto memberCourseDto){
        //field.length?c 为freemarker语法 c表示把数字转成字符串
                    ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
                    ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");
                    ValidatorUtil.require(memberCourseDto.getAt(), "报名时间");
        ResponseDto<MemberCourseDto> responseDto = new ResponseDto<>();
        memberCourseService.save(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){

        ResponseDto<MemberCourseDto> responseDto = new ResponseDto<>();
        memberCourseService.delete(id);
        return responseDto;
    }

}//end class