package com.atguigu.ssm.controller;
import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.pojo.borrowResult;
import com.atguigu.ssm.pojo.student;
import com.atguigu.ssm.service.BookService;
import com.atguigu.ssm.service.BorrowService;
import com.atguigu.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//关键代码的实现

@Controller
public class StudentController {
    @Autowired
private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String showAllStudent(Model model, HttpSession session, @PathVariable("id") String id) {
//查询所有信息
        List<book> list = bookService.showAllBooks();
        System.out.println(id);
        List<borrowResult> borrowedBooks=studentService.searchStudentByNumber(id,null,null);
        System.out.println("该学生借阅了"+borrowedBooks);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        System.out.println("The Login User Is:"+name);
        model.addAttribute("loginUser", name);
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "student_list";
    }
    @RequestMapping(value ="/student/book/search/", method =RequestMethod.POST)
    public String studentItem(HttpServletRequest request, Model model, HttpSession session) {
        String bookName=request.getParameter("bookName");
        String status=request.getParameter("status");
        List<book> list=bookService.checkBookByCondition(null,bookName,null,status);
        System.out.println(list);
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        model.addAttribute("loginUser", name);
        System.out.println("bookName:"+bookName);
        System.out.println("status:"+status);
        return "search_result_for_student";

    }

    @RequestMapping(value ="/student/info/{id}", method ={RequestMethod.GET,RequestMethod.POST})
    public String searchBook(Model model, HttpSession session,@PathVariable("id") String id) throws IOException {
        List<borrowResult> list=borrowService.showBorrowInfoById(id);
        System.out.println(list);
        if(list.isEmpty()){
            model.addAttribute("None", "您还没有借阅任何书籍！");
        }
        model.addAttribute("list", list);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        model.addAttribute("loginUser", name);

        return "student_info";

    }
    @RequestMapping(value = "student/book/back/{bookName}/{id}",method = RequestMethod.GET)
    public String addNewStudent(@PathVariable("bookName") String bookName,@PathVariable("id") String id) {


        System.out.println("正在执行对学生"+id+"的图书"+bookName+"的归还操作");
        borrowService.deleteBorrowIdFromStudent(id,bookName);
        return "redirect://student/info/"+id;
    }
    @RequestMapping( "/student/book/borrow/{bookName}/{id}")
    public String borrowBook(@PathVariable("bookName") String bookName,@PathVariable("id") String id,Model model, HttpSession session) {
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        model.addAttribute("loginUser", name);
        List<String> borrowedBooks=studentService.CheckStudentBorrowBooks(id);
        //查看学生借阅书籍，如过已借阅则报错
        if(borrowedBooks.contains(bookName)){
            model.addAttribute("id",id);
            return "HaveBeenBorrowedError";
        }
        //查看书籍借阅状态，如果为0则报错
        Integer status=bookService.CheckBookStatus(bookName);
        if(status==0){
            model.addAttribute("id",id);
            return "NoBookError";
        }
        //查看是否借阅的书籍数量已达到上限
String emptyBorrowId=studentService.CheckStudentBorrowAvailable(id);
        System.out.println("空闲的借阅位置是"+emptyBorrowId);
        if(null==emptyBorrowId) {
            model.addAttribute("id", id);
            return "NoEmptyError";
        }
List<book> list=bookService.checkBookByCondition(null,bookName,null,null);
   book book=list.get(0);
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = tempDate.format(new java.util.Date());
        System.out.println("现在是"+datetime);
        model.addAttribute("time",datetime);
model.addAttribute("book",book);
model.addAttribute("id",id);
        return "student_borrow";
    }

    @RequestMapping(value = "/student/book/borrowRequest",method = RequestMethod.POST)
    public String addNewStudent(HttpServletRequest request, Model model, HttpSession session) {
String bookName=request.getParameter("book.bookName");
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");
        String time = tempDate.format(new java.util.Date());
String year=request.getParameter("year");
String day=request.getParameter("day");
String name = (String) session.getAttribute("username");
        String month=request.getParameter("month");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, Integer.parseInt(year));
        calendar.add(Calendar.MONTH, Integer.parseInt(month));
        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        Integer year1 = calendar.get(Calendar.YEAR);
        Integer month1 = calendar.get(Calendar.MONTH) + 1;  // 月份从0开始，因此需要加1
        Integer day1 = calendar.get(Calendar.DAY_OF_MONTH);
        String month2;String day2;
        if(month1<10){
            month2="0"+month1;
        }else {
            month2=month1.toString();
        }
        if(day1<10){
            day2="0"+day1;
        }else {
            day2=day1.toString();
        }
        String deadline;

           deadline=year1+"-"+month2+"-"+day2;

        System.out.println("正在执行对学生"+name+"的图书"+bookName+"的借阅操作"+time+"       "+"year:"+year+"time:"+month+"day:"+day);
        System.out.println("借阅后的时间为"+deadline);

        borrowService.studentBorrowBook(name,bookName,time,deadline);
        return "redirect:/student/"+name;
    }
}
