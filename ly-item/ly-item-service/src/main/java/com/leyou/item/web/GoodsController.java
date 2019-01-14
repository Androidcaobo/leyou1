package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
     * @param: [SpuId]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.Sku>>
     * @author: Caobo
     * @date: 2019/1/14 16:43
     * @Description: 根据SpuId查询Sku列表
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuListBySpuId(@RequestParam("id")Long SpuId){
        return ResponseEntity.ok(goodsService.querySkuListBySpuId(SpuId));
    }

    /**
     * @param: [spuId]
     * @return: org.springframework.http.ResponseEntity<com.leyou.item.pojo.SpuDetail>
     * @author: Caobo
     * @date: 2019/1/14 16:35
     * @Description: 根据id查询商品细节
     */
    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long spuId) {
        return ResponseEntity.ok(goodsService.queryDetailById(spuId));
    }


    /**
     * @param: [spu]
     * @return: org.springframework.http.ResponseEntity<java.lang.Void>
     * @author: Caobo
     * @date: 2019/1/14 15:28
     * @Description: 新增商品
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody Spu spu) {
        goodsService.saveGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * @param: [page, rows, saleable, key]
     * @return: org.springframework.http.ResponseEntity<com.leyou.common.vo.PageResult                                                                                                                               <                                                                                                                               com.leyou.item.pojo.Spu>>
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
    ) {

        return ResponseEntity.ok(goodsService.querySpuByPage(page, rows, saleable, key));
    }
}
