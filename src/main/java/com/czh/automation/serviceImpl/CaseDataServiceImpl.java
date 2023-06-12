package com.czh.automation.serviceImpl;

import com.czh.automation.entity.CaseData;
import com.czh.automation.mapper.casemanagement.CaseDataMapper;
import com.czh.automation.service.casemanagement.CaseDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @PackageName: com.anheng.daslink.service.impl
 * @ClassName: CaseDataServiceImpl
 * @Description: CaseDataServiceImpl/description:
 * @Author: ChengZhiHao
 * @Date: 2022/8/23 13:08
 * @Version: v1.0
 */
@Service
public class CaseDataServiceImpl implements CaseDataService {

    @Resource
    private CaseDataMapper caseDataMapper;

    @Override
    public List<CaseData> queryCaseData() {
        return null;
    }
}
