package com.czh.automation.mapper.responseresult;

import com.czh.automation.entity.ResponseResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface ResponseResultMapper {
    /**
     * @return java.util.List<com.anheng.daslink.entity.ResponseResult>
     * @Author ChengZhiHao
     * @Description //TODO admin
     * @Date 15:30 2022/6/21
     * @Param [remark]
     **/
    List<ResponseResult> queryResponseResult(String remark);

}
