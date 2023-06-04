package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.borrowResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {
    //根据学生Id和书名删除借阅信息
    void deleteBorrowMessage(@Param("borrowId")String borrowId);
    //清除学生状态栏的借阅信息
    void deleteBorrowIdFromStudent(@Param("studentId") String studentId,@Param("borrowId") String borrowId);
    //清除学生状态栏的借阅信息2
    void deleteBorrowIdFromStudent2(@Param("studentId") String studentId,@Param("borrowId") String borrowId);
    //图书存储量加一
    void bookBack(@Param("bookName") String bookName);
    //查询借阅id号
    String CheckBorrowId(@Param("studentId") String studentId, @Param("bookName") String bookName);
    //查询该学号学生的借阅信息1
    List<borrowResult> checkItemById1(@Param("studentId") String studentId);
    //查询该学号学生的借阅信息2
    List<borrowResult> checkItemById2(@Param("studentId") String studentId);
    //显示该学号学生的借阅信息（学生端）
    List<borrowResult> showBorrowInfoById(@Param("studentId") String studentId);
    //图书存储量减一
    void bookBorrow(@Param("bookName") String bookName);
    //新增借阅信息
   void InsertNewBorrow(@Param("studentId") String studentId,
                        @Param("bookId") Integer bookId,
                        @Param("borrowTime")String borrowTime,
                        @Param("deadline")String deadline);
   //查询新插入的借阅信息id号
    String CheckIdByCondition(@Param("studentId") String studentId,
                              @Param("bookId") Integer bookId,
                              @Param("borrowTime")String borrowTime,
                              @Param("deadline")String deadline);
//借阅后更新学生信息
    void updateAfterBorrow1(@Param("studentId") String studentId,@Param("borrowId") String borrowId);
    //借阅后更新学生信息
    void updateAfterBorrow2(@Param("studentId") String studentId,@Param("borrowId") String borrowId);

}
