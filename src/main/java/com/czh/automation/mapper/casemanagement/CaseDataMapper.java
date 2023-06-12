package com.czh.automation.mapper.casemanagement;

import com.czh.automation.entity.CaseData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PackageName: com.anheng.daslink.mapper.casemanagement
 * @ClassName: CaseDataMapper
 * @Description: CaseDataMapper/description:
 * @Author: ChengZhiHao
 * @Date: 2022/6/21 17:09
 * @Version: v1.0
 */
@Mapper
@Repository
public interface CaseDataMapper {
    List<CaseData> queryCaseData();
}
