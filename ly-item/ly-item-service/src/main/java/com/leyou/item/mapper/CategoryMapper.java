package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description: Category接口类,通用Mapper
 *                  这里是接口的多继承
 */
public interface CategoryMapper extends Mapper<Category> ,IdListMapper<Category,Long> {
}
