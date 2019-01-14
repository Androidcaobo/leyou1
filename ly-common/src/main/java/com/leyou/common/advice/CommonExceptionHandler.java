package com.leyou.common.advice;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 通用异常处理器
 */
@ControllerAdvice  //实际上是一个通知，默认情况下拦截那些加了Controller注解的类
public class CommonExceptionHandler {

    /**
     * @param: [e] 抓到的那个异常
     * @return: org.springframework.http.ResponseEntity<java.lang.String>
     * @author: Caobo
     * @date: 2019/1/8 18:00
     * @Description: 特定异常拦截类，这种方法可以有多个
     */
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> hanldeException(LyException e){
        ExceptionEnum exceptionEnum = e.getExceptionEnum();// 拿到枚举
        // 封装错误信息
        return ResponseEntity.status(exceptionEnum.getCode())
                .body(new ExceptionResult(exceptionEnum));
    }
}
