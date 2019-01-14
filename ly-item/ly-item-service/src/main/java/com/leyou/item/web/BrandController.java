package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description: Brand控制层
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @param: [page, rows, sortBy, desc, key]
     * @return: org.springframework.http.ResponseEntity<com.leyou.common.vo.PageResult<com.leyou.item.pojo.Brand>>
     * @author: Caobo
     * @date: 2019/1/9 18:58
     * @Description: 根据前台分页数据查询品牌数据
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandListbyPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key
        ){

        // 调用Service
        PageResult<Brand> result = brandService.queryBrandListByPageAndSort(page,rows,sortBy,desc,key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<PageResult<Brand>>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    /**
     * @param: [brand, cids]
     * @return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @author: Caobo
     * @date: 2019/1/10 14:05
     * @Description: 新增品牌
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        brandService.saveBrand(brand,cids);
        // 没有请求体的话，直接用build而不用body
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
