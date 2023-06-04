package com.atguigu.ssm.service;
import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.pojo.student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface StudentService {
    //插入新学生

//查找学生
    List<borrowResult> searchStudentByNumber(@Param("studentId") String studentId,
                                             @Param("studentName") String studentName,
                                             @Param("borrowedBooks") String borrowedBooks
    );
//显示所有学生
    List<student> getAllStudent();
//添加新学生
    void addNew(@Param("studentId") String studentId, @Param("studentName")  String studentName);

    //根据Id删除学生
    void deleteStudentById(@Param("id") String id);
    //查询学生的借阅信息
    String CheckStudentBorrowAvailable(@Param("studentId") String studentId);
    //查询学生的借阅书籍
    List<String> CheckStudentBorrowBooks(@Param("studentId") String studentId);
    //根据学生的名字查询id
    List<String> CheckStudentIdByName(@Param("studentName")  String studentName);
//连携添加User
    void addNewUser(@Param("studentId") String studentId,@Param("studentId1") String studentId1);
}
