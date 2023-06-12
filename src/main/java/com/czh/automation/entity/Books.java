package com.czh.automation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Books {
    private int bookId;
    private String bookName;
    private int bookCounts;
    private String detail;
}
