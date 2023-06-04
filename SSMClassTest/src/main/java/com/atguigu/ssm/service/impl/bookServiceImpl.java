package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.BookMapper;
import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.service.BookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class bookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void InsertNewBook(String bookId, String bookName, String picture, String status) {
        bookMapper.InsertNewBook(bookId, bookName,picture,status);
    }

    @Override
    public List<book> showAllBooks() {
        return bookMapper.showAllBooks();
    }

    @Override
    public List<book> checkBookByCondition(String bookId, String bookName, String picture, String status) {
        return bookMapper.checkBookByCondition(bookId,bookName,picture,status);
    }

    @Override
    public void deleteBookByName(String book_name) {
       bookMapper.deleteBookByName(book_name);
    }

    @Override
    public Integer CheckBookStatus(String book_name) {
        return bookMapper.CheckBookStatus(book_name);
    }
}
