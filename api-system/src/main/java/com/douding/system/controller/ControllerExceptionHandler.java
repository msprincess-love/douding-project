package com.douding.system.controller;

import com.douding.server.dto.ResponseDto;
import com.douding.server.exception.BusinessException;
import com.douding.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//增强型控制器 全局异常处理
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        LOG.warn(e.getMessage());
        responseDto.setMessage("请求参数异常！");
        return responseDto;
    }


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody //数据以json 形式返回回去
    public ResponseDto businessExceptionHandler(BusinessException e) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSuccess(false);
        //隐藏掉 真实的异常问题 不暴露给前端
        //通过LOG 查看真实异常  这样方式探测攻击
        //打印异常码的中文描述信息
        LOG.error("业务异常:{}",e.getCode().getDesc());
        responseDto.setMessage(e.getCode().getDesc());
        return responseDto;
    }
}
