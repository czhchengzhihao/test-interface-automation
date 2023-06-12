package com.czh.automation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @PackageName: com.anheng.daslink.entity
 * @ClassName: CaseData
 * @Description: CaseData/description:TODO 测试数据实体类
 * @Author: ChengZhiHao
 * @Date: 2022/6/21 17:11
 * @Version: v1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CaseData {
    /***/
    private Integer id;
    /**
     * 项目名称
     */
    private String project;
    /**
     * 模块
     */
    private String module;
    /**
     * 用例名称
     */
    private String caseName;
    /**
     * 接口地址
     */
    private String apiUrl;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 入参
     */
    private String parameter;
    /**
     * 预期结果
     */
    private String expectedResults;
    /**
     * 创建时间
     */
    private LocalDateTime creationTime;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 备注
     */
    private String remark;
}
