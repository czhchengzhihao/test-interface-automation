package com.czh.automation.mapper.querybooks;


import com.czh.automation.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 */
@Mapper
@Repository
public interface QueryBooksMapper {
    List<Books> queryBooks();
}
