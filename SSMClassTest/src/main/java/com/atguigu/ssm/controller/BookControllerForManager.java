package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/teacher/")
public class BookControllerForManager {
    @Autowired
    private BookService bookService;
    @RequestMapping(value = "book")
    public String getAllBooks(Model model, HttpSession session) {
        //查询所有信息
        List<book> list = bookService.showAllBooks();
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        //将集合中的课程名称属性抽离形成一个新的集合
        List<String> books = CollectionUtils.transform(list, s -> ((book) s).getBookName());
        model.addAttribute("books", books);
        System.out.println(list);
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        System.out.println("The Login User Is:"+name);
        model.addAttribute("loginUser", name);
        //跳转到course_list.html
        return "manager_list";
    }
    @RequestMapping("book/delete/{book:.*}")
    public String deleteBook(HttpServletRequest request, Model model, @PathVariable String book) {
        System.out.println("正在删除" + book);
        bookService.deleteBookByName(book);
        return "redirect:/teacher/book";
    }
    @RequestMapping("/book/add")
    public String test(HttpServletRequest request, Model model, MultipartFile photo, HttpSession session) throws IOException {
        String bookName = request.getParameter("bookName");
        String status = request.getParameter("status");
        //上传图片-------------------------------------
        //photo.getOriginalFilename(),获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //如果没有上传图片
        if(null==fileName|| StringUtils.isEmpty(fileName)) {
            //////插入
            //没有传文件则默认为1.png
            fileName = "1.png";
            //Insert
            bookService.InsertNewBook(null,bookName,fileName,status);
        }
else{
            //获取上传的文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //获取uuid
            String uuid = UUID.randomUUID().toString();
            //获取一个永远不重复的文件名
            fileName=uuid+suffixName;
            System.out.println(fileName);
            //获取ServletContext对象
            ServletContext servletContext=session.getServletContext();
            //获取当前工程下photo目录的真实路径
            String photoPath = servletContext.getRealPath("photo");
            //创建photoPath所对应的File对象
            File file=new File(photoPath);
            //判断file所对应目录是否存在
            if(!file.exists()){
                file.mkdir();
            }
            String finalPath=photoPath+File.separator+fileName;
            //上传文件
            photo.transferTo(new File(finalPath));
            //Insert
            bookService.InsertNewBook(null,bookName,fileName,status);
        }
        return "redirect:/teacher/book";
    }
    @RequestMapping(value ="book/search/", method =RequestMethod.POST)
    public String searchBook(HttpServletRequest request, Model model, MultipartFile photo, HttpSession session) throws IOException {
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
        return "search_result";

    }
}
