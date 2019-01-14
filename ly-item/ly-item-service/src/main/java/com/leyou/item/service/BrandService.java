package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpecMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.SpecParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description: Brand服务层
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * @param: [id]
     * @return: com.leyou.item.pojo.Brand
     * @author: Caobo
     * @date: 2019/1/11 16:47
     * @Description: 根据id查询品牌
     */
    public Brand queryBrandById(Long id){
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null){
            throw new LyException(ExceptionEnum.Brand_NOT_FOUND);
        }
        return  brand;
    }


    /**
     * @param: [page, rows, sortBy, desc, key]
     * @return: com.leyou.common.vo.PageResult<com.leyou.item.pojo.Brand>
     * @author: Caobo
     * @date: 2019/1/9 18:59
     * @Description: 分页查询品牌并排序
     */
    public PageResult<Brand> queryBrandListByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 使用分页助手进行分页
        PageHelper.startPage(page, rows);
        // 设置查询条件
        Example example = new Example(Brand.class);
        // 关键字模糊查询
        if (StringUtils.isNotBlank(key)){
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(sortBy)){
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);

    }

    /**
     * @param: [brand, cids]
     * @return: void
     * @author: Caobo
     * @date: 2019/1/10 14:06
     * @Description: 保存品牌
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 保存品牌,因为id是自增，所以为了防止出现意外，先置为空
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if ( count != 1){
            throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
        }
        // 保存中间表信息
        for (Long cid : cids) {
            count = brandMapper.insertBrandCategory(cid, brand.getId());
            if ( count != 1){
                throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
            }
        }

    }
}
