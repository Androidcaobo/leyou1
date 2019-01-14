package com.leyou.common.vo;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 异常结果类
 */
@Data
public class ExceptionResult {

    private int statue;// 错误状态码
    private String message;// 错误提示信息
    private Long timeStamp;// 时间戳

    /**
     * @param: [em]
     * @return:
     * @author:
     * @date:
     * @Description: 构造，初始化错误信息，使得返回信息较为友好
     */
    public ExceptionResult(ExceptionEnum em) {
        this.statue = em.getCode();
        this.message = em.getMsg();
        this.timeStamp = System.currentTimeMillis();
    }
}
