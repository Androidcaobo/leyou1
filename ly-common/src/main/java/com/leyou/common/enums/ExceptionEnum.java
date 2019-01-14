package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

/**
 * @author: Caobo
 * @date: 2019/1/8 18:25
 * @Description: 自定义状态码   测试更新2
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    PRICE_CANNOT_BE_NULL(400,"价格不能为空!"),  //简写，底层相当于private static final ExceptionEnum = new ExceptionEnum();
    CATEGORY_NOT_FOUND(404,"商品分类未找到"),
    GOODS_NOT_FOUND(404,"商品不存在"),
    Brand_NOT_FOUND(404,"商品不存在"),
    SPEC_GROUP_NOT_FOUND(404,"规格组不存在"),
    SPEC_GROUP_PARAM_NOT_FOUND(404,"规格组参数不存在"),
    BRAND_SAVE_ERROR(500,"新增品牌失败"),
    UPLOAD_FILE_ERROR(500,"文件上传失败"),
    INVALID_FILE_TYPE(400,"无效文件类型"),
    GOODS_SAVE_ERROR(500,"新增商品失败"),
    GOODS_DETAIL_NOT_FOND(500,"新增商品失败"),
    GOODS_SKU_NOT_FOND(404,"商品Sku不存在"),
    GOODS_STOCK_NOT_FOND(404,"商品Sku不存在")
    ;
    private int code;
    private String msg;
}
