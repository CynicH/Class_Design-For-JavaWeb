package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.pojo.student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
//插入新学生

//查找学生
List<borrowResult> searchStudentByNumber(@Param("studentId") String studentId,
                                         @Param("studentName") String studentName,
                                         @Param("borrowedBooks") String borrowedBooks
);

    //查找借阅了某书的学生
    List<borrowResult> searchStudentByName(@Param("studentId")String studentId,@Param("studentName") String studentName,@Param("borrowedBooks") String borrowedBooks);
    //显示所有学生
    List<student> getAllStudent();
    //添加新学生
    void addNew(@Param("studentId") String studentId, @Param("studentName")  String studentName);
    //根据Id删除学生
    void deleteStudentById(@Param("id") String id);
    //查询学生的全部借阅id
    String checkStudentBorrowId1(@Param("studentId") String studentId);
    String checkStudentBorrowId2(@Param("studentId") String studentId);
    //查询学生的借阅书籍
    List<String> CheckStudentBorrowBooks(@Param("studentId") String studentId);
    //根据学生的名字查询id
    List<String> CheckStudentIdByName(@Param("studentName")  String studentName);
//没传借阅信息时候的查询学生
    List<borrowResult> searchStudentByNoBorrowMessage(@Param("studentId") String studentId,@Param("studentName")  String studentName);
    //连携设置学生的id
    void addNewUserByStudent(@Param("studentId") String studentId,@Param("studentId1") String studentId1);
}
