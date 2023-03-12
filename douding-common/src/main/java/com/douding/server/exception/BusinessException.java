package com.douding.server.exception;

public class BusinessException extends RuntimeException{

    //引入枚举类
    private BusinessExceptionCode code;

    public BusinessException (BusinessExceptionCode code) {
        super(code.getDesc());
        this.code = code;
    }

    public BusinessExceptionCode getCode() {
        return code;
    }

    public void setCode(BusinessExceptionCode code) {
        this.code = code;
    }

    /**
     * 不写入堆栈信息，提高性能 抛异常时控制台不打印信息
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}