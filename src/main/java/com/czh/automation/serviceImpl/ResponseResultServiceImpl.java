package com.czh.automation.serviceImpl;

import com.czh.automation.entity.ResponseResult;
import com.czh.automation.mapper.responseresult.ResponseResultMapper;
import com.czh.automation.service.responseresult.ResponseResultService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Service
public class ResponseResultServiceImpl implements ResponseResultService {
    @Resource
    private ResponseResultMapper responseResultMapper;

    @Override
    public List<ResponseResult> queryResponseResult(String remark) {
        return responseResultMapper.queryResponseResult(remark);
    }

}
