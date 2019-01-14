package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description: Brand接口类,通用Mapper
 */
public interface BrandMapper extends Mapper<Brand> {

    /**
     * @param: [cid,bid]
     * @return: int
     * @author: Caobo
     * @date: 2019/1/10 14:16
     * @Description:  插入中间表
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertBrandCategory(@Param("cid") Long cid ,@Param("bid") Long bid);
}
