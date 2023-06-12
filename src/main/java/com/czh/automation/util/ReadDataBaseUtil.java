package com.czh.automation.util;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * @PackageName: com.example.demo.util
 * @ClassName: ReadDataBaseUtil
 * @Description: ReadDataBaseUtil/description:TODO 读取数据库数据 转为二维数据方便TestNG进行参数化
 * @Author: ChengZhiHao
 * @Date: 2022/6/14 13:42
 * @Version: v1.0
 */
@Slf4j
public class ReadDataBaseUtil {

    public static Object[][] readDataBase(String data) {
        //查出数据库的数据，将数据库数据转通过JSONObject转换一下
        //List<ResponseResult> data = responseResultMapper.queryResponseResult("测试");
        //String parameter = JSONObject.toJSONString(data);

        //确定二维数据的行和列
        Object[][] dataS = new Object[JSONArray.parseArray(data).size()][JSONArray.parseArray(data).getJSONObject(0).size()];
        for (int i = 0; i < JSONArray.parseArray(data).size(); i++) {
            int j = 0;
            Set<Map.Entry<String, Object>> entries = JSONArray.parseArray(data).getJSONObject(i).entrySet();
            log.info("入参数据："+entries);
            //结果展示数据根据ASCII表中 对应的字母或者数字符号 倒叙排列，此处根据字段名称首字母 倒叙排列
            for (Map.Entry<String, Object> entry : entries) {
                dataS[i][j++] = entry.getValue();
            }
        }
        return dataS;
    }
}
