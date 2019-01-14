package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import com.netflix.discovery.converters.Auto;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Caobo
 * @Date: 2019/1/9
 * @Description:
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @param: [pid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.Category>>
     * @author: Caobo
     * @date: 2019/1/9 15:07
     * @Description: 根据pid查询所有的父节点
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid){
        // return ResponseEntity.status(HttpStatus.OK).body(null); 成功的情况下是可以这么写的，但是有更简单的写法
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));
    }
}
