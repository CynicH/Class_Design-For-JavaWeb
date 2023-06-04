package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
//添加新图书
    void InsertNewBook(@Param("book_id")String book_id,@Param("book_name")String book_name,@Param("picture")String picture,@Param("status")String status);
//显示所有图书
    List<book> showAllBooks();
    //按照条件查找图书
    List<book> checkBookByCondition(@Param("book_id")String book_id,@Param("book_name")String book_name,@Param("picture")String picture,@Param("status")String status);
    //按照名字删除图书
    void deleteBookByName(@Param("book_name")String book_name);
    //按照名字查找图书状态
    Integer CheckBookStatus(@Param("book_name")String book_name);
}
