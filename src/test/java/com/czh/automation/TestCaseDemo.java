package com.czh.automation;


import com.alibaba.fastjson.JSONObject;
import com.czh.automation.entity.ResponseResult;
import com.czh.automation.service.responseresult.ResponseResultService;
import com.czh.automation.util.BaseCase;
import com.czh.automation.util.ReadDataBaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PackageName: com.anheng.daslink
 * @ClassName: TestCaseDemo
 * @Description: TestCaseDemo/description:
 * @Author: ChengZhiHao
 * @Date: 2022/8/24 13:53
 * @Version: v1.0
 */
@Slf4j
public class TestCaseDemo extends BaseCase {


    @Resource
    private ResponseResultService responseResultService;

    @Test(dataProvider = "zz")
    public void test(String aa, String bb, String cc) {
        log.info(aa + bb + cc);
    }


    @Test
    public void test1() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        System.out.println(data);
        String parameter = JSONObject.toJSONString(data);
        System.out.println(parameter);
    }


    @DataProvider(name = "zz")
    public Object[][] zz() {
        List<ResponseResult> data = responseResultService.queryResponseResult("测试");
        String parameter = JSONObject.toJSONString(data);
        return ReadDataBaseUtil.readDataBase(parameter);
//        return new Object[][]{
//                {"33","11","22"}
//        };
    }


}
