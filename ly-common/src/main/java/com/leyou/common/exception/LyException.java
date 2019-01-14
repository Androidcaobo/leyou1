package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 自定义异常
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;  //使用定义好的枚举
}
