package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: Caobo
 * @Date: 2019/1/11
 * @Description: 规格Service
 */
@Service
public class SpecService {

    @Autowired
    private SpecMapper specMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    /**
     * @param: [gid]
     * @return: java.util.List<com.leyou.item.pojo.SpecParam>
     * @author: Caobo
     * @date: 2019/1/11 13:34
     * @Description: 根据gid查询规格组参数
     */
    public  List<SpecParam> queryGroupParamByGid(Long gid) {
        // 查询条件
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        //查询
        List<SpecParam> list = specParamMapper.select(specParam);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_GROUP_PARAM_NOT_FOUND);
        }
        return list;
    }


    /**
     * @param: [cid]
     * @return: java.util.List<com.leyou.item.pojo.SpecGroup>
     * @author: Caobo
     * @date: 2019/1/11 10:31
     * @Description: 根据id查询规格组
     */
    public List<SpecGroup> queryGroupByCid(Long cid) {
        // 查询条件
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        // 查询
        List<SpecGroup> list = specMapper.select(specGroup);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;

    }
}
