package com.caobo.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.common.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Caobo
 * @Date: 2019/1/8
 * @Description: 测试Json工具类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {




    @Test
    public void fun1(){
        // toString
       /* User user = new User();
        user.setName("caobo");
        user.setAge(21);
        String s = JsonUtils.toString(user);
        System.out.println(s);*/

        // 反序列化
        /*User user1 = JsonUtils.toBean(s, User.class);
        System.out.println(user1);*/

        // 数组
       /* String list = "[1,2,3,4]";
        List<Integer> integers = JsonUtils.toList(list, Integer.class);
        System.out.println(integers);*/

        // Map
        /*String map = "{\"name\":\"caobo\",\"age\":\"22\"}";
        Map<String, String> toMap = JsonUtils.toMap(map, String.class, String.class);
        System.out.println(toMap);*/

        //复杂类型
        String json = "[{\"name\":\"caobo\",\"age\":\"22\"},{\"name\":\"caobo\",\"age\":\"22\"}]";
        List<Map<String, String>> maps = JsonUtils.nativeRead(json, new TypeReference<List<Map<String, String>>>() {
        });
        System.out.println(maps);
    }
}
