package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.BookMapper;
import com.atguigu.ssm.mapper.BorrowMapper;
import com.atguigu.ssm.mapper.StudentMapper;
import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.service.BorrowService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public void deleteBorrowMessage(String borrowId) {
        borrowMapper.deleteBorrowMessage(borrowId);
    }

    @Override
    public void deleteBorrowIdFromStudent(String studentId, String bookName) {
        String borrowId;
borrowId=borrowMapper.CheckBorrowId(studentId,bookName);
        System.out.println("学生"+studentId+"的借阅信息"+borrowId+"被删除");
        borrowMapper.deleteBorrowIdFromStudent(studentId,borrowId);
        borrowMapper.deleteBorrowIdFromStudent2(studentId,borrowId);
        borrowMapper.bookBack(bookName);
       deleteBorrowMessage(borrowId);
    }

    @Override
    public void bookBack(String borrowResult) {
borrowMapper.bookBack(borrowResult);
    }

    @Override
    public List<borrowResult> checkItemById(String studentId) {
        List<borrowResult> list1=borrowMapper.checkItemById1(studentId);
        List<borrowResult> list2=borrowMapper.checkItemById2(studentId);
 list1.addAll(list2);
        System.out.println(list1);
return list1;

    }

    @Override
    public List<borrowResult> showBorrowInfoById(String studentId) {
        return borrowMapper.showBorrowInfoById(studentId);
    }

    @Override
    public void studentBorrowBook(String studentId, String bookName, String borrow_time, String deadline) {
        //按照条件查找图书
       List<book> list=bookMapper.checkBookByCondition(null,bookName,null,null);
       Integer bookId=list.get(0).getBookId();
       //图书库存减一
       borrowMapper.bookBorrow(bookName);
       //添加新的借阅信息
       borrowMapper.InsertNewBorrow(studentId,bookId,borrow_time,deadline);
       //查找需要添加借阅信息的学生id
       String borrowId= borrowMapper.CheckIdByCondition(studentId,bookId,borrow_time,deadline);
       //从第一个空位开始扫描，如果为空则添加此次借阅信息
        if(null==studentMapper.checkStudentBorrowId1(studentId)){
            borrowMapper.updateAfterBorrow1(studentId,borrowId);
            return;
        }
        if(null==studentMapper.checkStudentBorrowId2(studentId)){
            borrowMapper.updateAfterBorrow2(studentId,borrowId);
        }
    }
}
