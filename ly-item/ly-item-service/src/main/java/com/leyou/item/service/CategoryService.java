package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description: Category的service层
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @param: [pid]
     * @return: java.util.List<com.leyou.item.pojo.Category>
     * @author: Caobo
     * @date: 2019/1/9 15:05
     * @Description: 根据pid查询所有的父节点
     */
    public List<Category> queryCategoryListByPid(Long pid) {
        // 查询条件
        Category category = new Category();
        category.setParentId(pid);
        // 会根据传入对象的非空字段去查询符合条件的对象
        List<Category> list = categoryMapper.select(category);
        // 集合判空
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

    /**
     * @param: [ids]
     * @return: java.util.List<com.leyou.item.pojo.Category>
     * @author: Caobo
     * @date: 2019/1/11 16:31
     * @Description: 根据多个id查询多个category
     */
    public  List<Category>  queryCategoryByIds(List<Long> ids){
        List<Category> list = categoryMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }
}
