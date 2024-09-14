package com.czh.automation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author ChengZhiHao
 * @Description //TODO admin
 * @Date 15:14 2022/5/17
 * @Param
 * @return
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseResult {

    private Integer id;
    private String success;
    private String message;
    private String responseData;
    private LocalDateTime creationTime;
    private String remark;
}
