package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Spu;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Caobo
 * @Date: 2019/1/11
 * @Description: 商品服务Service
 */
@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    /**
     * @param: [page, rows, saleable, key]
     * @return: com.leyou.common.vo.PageResult<com.leyou.item.pojo.Spu>
     * @author: Caobo
     * @date: 2019/1/11 16:23
     * @Description: 商品列表分页查询
     */
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
         // 开始分页
        PageHelper.startPage(page,rows);

        // 关键字查询
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 模糊查询
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }

        // 是否下架
        if (saleable != null){
            criteria.orEqualTo("saleable",saleable);
        }

        // 默认排序
        example.setOrderByClause("last_update_time DESC");

        // 查询
        List<Spu> spus = spuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnum.GOODS_NOT_FOUND);
        }

        // 解析分类和品牌名称
        loadCategoryAndBrandName(spus);

        // 封装查询结果
        PageInfo<Spu> spuPageInfo = new PageInfo<>(spus);

        return new PageResult<Spu>(spuPageInfo.getTotal(),spus);

    }


    private void loadCategoryAndBrandName(List<Spu> spus) {
        for (Spu spu : spus) {
            // 1.处理分类名称
            // 利用lambd表达式处理，
            // 先用Stream转换成category流，然后在转换成Map<String>,然后在转成集合，不采用字符串拼接的方式，防止内存占用过多
            List<String> names = categoryService.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2()
                    , spu.getCid3())).stream().map(Category::getName).collect(Collectors.toList());
            // 会把一个集合拼成字符串
            spu.setCname(StringUtils.join(names,"/"));

            // 2.处理品牌名称
            Brand brand = brandService.queryBrandById(spu.getBrandId());
            spu.setBname(brand.getName());
        }
    }
}
