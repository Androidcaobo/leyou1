package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Spu;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Caobo
 * @Date: 2019/1/11
 * @Description: 商品Controller
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * @param: [page, rows, saleable, key]
     * @return: org.springframework.http.ResponseEntity<com.leyou.common.vo.PageResult<com.leyou.item.pojo.Spu>>
     * @author: Caobo
     * @date: 2019/1/11 16:02
     * @Description: 分页查询spu信息
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key
    ){

        return ResponseEntity.ok(goodsService.querySpuByPage(page,rows,saleable,key));
    }
}
