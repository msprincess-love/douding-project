package com.douding.business.controller.admin;





import com.douding.server.dto.PageDto;
import com.douding.server.dto.ResponseDto;
import com.douding.server.service.MemberService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/member")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    //给了日志用的
    public  static final String BUSINESS_NAME ="会员";

    @Resource
    private MemberService memberService;

    @RequestMapping("/list")
    public ResponseDto list(PageDto pageDto){

        ResponseDto<PageDto> responseDto = new ResponseDto<>();
        memberService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }


}//end class