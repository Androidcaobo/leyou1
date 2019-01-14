package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.SpecService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Caobo
 * @Date: 2019/1/11
 * @Description: 规格控制层
 */
@RestController
@RequestMapping("spec")
public class SpecController {

    @Autowired
    private SpecService specService;

    /**
     * @param: [cid]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
     * @author: Caobo
     * @date: 2019/1/11 10:25
     * @Description: 根据cid查询规格组信息
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid")Long cid){
        return ResponseEntity.ok(specService.queryGroupByCid(cid));
    }

    /**
     * @param: [gid,cid,searching]
     * @return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecParam>>
     * @author: Caobo
     * @date: 2019/1/11 13:27
     * @Description: 根据参数查询组内参数
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryGroupParam(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "searching",required = false)Boolean searching
    ){
        return ResponseEntity.ok(specService.queryGroupParam(gid,cid,searching));
    }
}
