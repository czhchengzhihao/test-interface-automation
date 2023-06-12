package com.czh.automation.service.responseresult;

import com.czh.automation.entity.ResponseResult;

import java.util.List;

/**
 * @author admin
 */

public interface ResponseResultService {
    /**
     * @return java.util.List<com.anheng.daslink.entity.ResponseResult>
     * @Author ChengZhiHao
     * @Description //TODO admin
     * @Date 15:30 2022/6/21
     * @Param [remark]
     **/
    List<ResponseResult> queryResponseResult(String remark);

}
