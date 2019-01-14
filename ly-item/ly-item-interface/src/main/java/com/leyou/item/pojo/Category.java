package com.leyou.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: Caobo
 * @date: 2019/1/9 14:55
 * @Description: 商品分类实体类
 */
@Table(name="tb_category")
@Data
public class Category {
     @Id
     @KeySql(useGeneratedKeys = true)
     private Long id;
     private String name;
     private Long parentId;
     private Boolean isParent;
     private Integer sort;
     // getter和setter略
     // 注意isParent的get和set方法
}