package com.atguigu.ssm.service.impl;
import com.alibaba.druid.util.StringUtils;
import com.atguigu.ssm.mapper.BookMapper;
import com.atguigu.ssm.mapper.StudentMapper;
import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.pojo.student;
import com.atguigu.ssm.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl  implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public void deleteStudentById(String id) {
        //根据Id删除学生
       studentMapper.deleteStudentById(id);
    }

    @Override
    public String CheckStudentBorrowAvailable(String studentId) {
        System.out.println("borrowid："+studentMapper.checkStudentBorrowId1(studentId)+' '+studentMapper.checkStudentBorrowId2(studentId));;
if(null==studentMapper.checkStudentBorrowId1(studentId)){
    return "borrow1_id";
}
        if(null==studentMapper.checkStudentBorrowId2(studentId)){
            return "borrow2_id";
        }
        return null;
    }

    @Override
    public List<String> CheckStudentBorrowBooks(String studentId) {
        return studentMapper.CheckStudentBorrowBooks(studentId);

    }

    @Override
    public List<String> CheckStudentIdByName(String studentName) {
         return  studentMapper.CheckStudentIdByName(studentName);
    }

    @Override
    public void addNewUser(String studentId, String studentId1) {
        studentMapper.addNewUserByStudent(studentId,studentId1);
    }


    @Override
    public List<borrowResult> searchStudentByNumber(String studentId, String studentName, String borrowedBooks) {
java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
if(!(null==borrowedBooks|| StringUtils.isEmpty(borrowedBooks))){
    if(pattern.matcher(borrowedBooks).matches()){
        return studentMapper.searchStudentByNumber(studentId,studentName,borrowedBooks);
    }
    else return studentMapper.searchStudentByName(studentId,studentName,borrowedBooks);
}else {
    return studentMapper.searchStudentByNoBorrowMessage(studentId,studentName);
}
    }

    @Override
    public List<student> getAllStudent() {
        return  studentMapper.getAllStudent();
    }

    @Override
    public void addNew(String studentId, String studentName) {
        String id=studentId;
        String name=studentName;
        studentMapper.addNew(id,name);
//        studentMapper.addNewUserByStudent(id,id);
    }
}
