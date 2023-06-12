package com.czh.automation.serviceImpl;


import com.czh.automation.entity.Books;
import com.czh.automation.mapper.querybooks.QueryBooksMapper;
import com.czh.automation.service.querybooks.QueryBooksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Service
public class QueryBooksServiceImpl implements QueryBooksService {

    @Resource
    private QueryBooksMapper queryBooksMapper;

    @Override
    public List<Books> queryBooks() {
        return null;
    }
}
