package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.pojo.student;
import com.atguigu.ssm.service.BookService;
import com.atguigu.ssm.service.BorrowService;
import com.atguigu.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher/")
public class StudentControllerForManager {
    @Autowired
    private StudentService studentService;
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "student")
    public String getAllStudents(Model model, HttpSession session) {
        //查询所有信息
        List<student> list = studentService.getAllStudent();
        //将集合中的课程名称属性抽离形成一个新的集合
        List<String> studentIds= CollectionUtils.transform(list, s -> ((student) s).getStudentId());
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        model.addAttribute("studentIds", studentIds);
        System.out.println(list);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        System.out.println("The Login User Is:"+name);
        model.addAttribute("loginUser", name);
        //跳转到course_list.html
        return "manager_student_list";
    }
    @RequestMapping(value = "student/search/")
    public String searchStudent(Model model, HttpSession session, HttpServletRequest request) {
        String studentId=request.getParameter("studentId");
        String studentName=request.getParameter("studentName");
        String borrowedBooks=request.getParameter("borrowedBooks");
        //查询所有学生信息
        List<student> list2 = studentService.getAllStudent();
        //将集合中的Id属性抽离形成一个新的集合
        List<String> studentIds= CollectionUtils.transform(list2, s -> ((student) s).getStudentId());
        List<borrowResult> list = studentService.searchStudentByNumber(studentId,studentName,borrowedBooks);
        System.out.println("查询的学生符合的要求为Id："+studentId+"Name:"+studentName+"borrowed books:"+borrowedBooks);
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        model.addAttribute("studentIds", studentIds);
        System.out.println(list);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        System.out.println("The Login User Is:"+name);
        model.addAttribute("loginUser", name);
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
if (null==list) {
            model.addAttribute("NoResult", "没有搜索到符合条件的信息！");
            return "manager_student_list";
        }
if(null==borrowedBooks||StringUtils.isEmpty(borrowedBooks)){
    System.out.println("没有借阅信息");
  return "manager_student_list";
}
else {
    System.out.println("有借阅信息");
    if(pattern.matcher(borrowedBooks).matches()) {
        System.out.println("借阅信息要求为借阅数量");
        return "manager_student_list";
    }
    System.out.println("借阅信息要求为借阅书名");
    return "manager_student_list_searchResult";
}

    }
    @RequestMapping(value = "book/back/{bookName}/{id}",method = RequestMethod.GET)
    public String deleteBook(Model model, HttpSession session, HttpServletRequest request,@PathVariable("bookName") String bookName,@PathVariable("id") String id) {
            System.out.println("正在执行对学生"+id+"的图书"+bookName+"的归还操作");
            borrowService.deleteBorrowIdFromStudent(id,bookName);

//studentService.deleteStudentById(id);
        return "redirect:/teacher/student";
    }
    @RequestMapping("student/add")
    public String addNewStudent(HttpServletRequest request, Model model) {
        String studentId=request.getParameter("studentId");
        String studentName=request.getParameter("studentName");
studentService.addNew(studentId,studentName);
studentService.addNewUser(studentId,studentId);
        return "redirect:/teacher/student";
    }


    @RequestMapping(value = "student/show/{studentId}")
    public String searchStudentDetails(Model model, HttpSession session, HttpServletRequest request, @PathVariable("studentId") String studentId ) {
        System.out.println(studentId);
        //得到该学生的查询信息
        List<borrowResult> list2 = borrowService.checkItemById(studentId);
        //将课程信息在请求域中共享
        model.addAttribute("list", list2);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        System.out.println("The Login User Is:" + name);
        model.addAttribute("loginUser", name);
        return "manager_search_student_list";
    }

    @RequestMapping("student/delete/{id}")
    public String deleteStudent(Model model, HttpSession session, HttpServletRequest request, @PathVariable("id") String id ) {
studentService.deleteStudentById(id);
        return "redirect:/teacher/student";
    }
    }


