package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.borrowResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowService {
    //根据学生Id和书名删除借阅信息
 void deleteBorrowMessage(@Param("borrowId)")String borrowId);
 //清除学生状态栏的借阅信息
    void deleteBorrowIdFromStudent(@Param("studentId") String studentId,@Param("bookName") String bookName);
    //图书存储量加一
    void bookBack(@Param("bookName") String borrowResult);
//查询该学号学生的借阅信息
    List<borrowResult> checkItemById(@Param("studentId") String studentId);
    //显示该学号学生的借阅信息（学生端）
    List<borrowResult> showBorrowInfoById(@Param("studentId") String studentId);
    //学生借阅书籍
    void studentBorrowBook(@Param("studentId") String studentId,@Param("bookName") String bookName,@Param("borrow_time") String borrow_time,@Param("deadline") String deadline);

}
