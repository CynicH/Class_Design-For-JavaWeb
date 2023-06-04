package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.book;
import com.atguigu.ssm.pojo.user;
import com.atguigu.ssm.service.StudentService;
import com.atguigu.ssm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @RequestMapping("/toLoginPage")
    public String toLoginPage()
    {
        //跳转至登录页面
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession session, Model model, HttpServletRequest request,user user)
    {
        String username=user.getUsername();
        String password=user.getPassword();
        String root=user.getRoot();
        System.out.println("用户账号为："+username+"用户密码为："+password+"用户权限为："+root);

        user dbuser=userService.getUserByNameAndRoot(username,root);System.out.println("数据库中的结果为"+dbuser);

        if(null==dbuser){
            model.addAttribute("noUser",username);
            System.out.println("比对错误，返回登录页面");
            return "index";
        }
        System.out.println("数据库内用户账号为："+dbuser.getUsername()+"用户密码为："+dbuser.getPassword()+"用户权限为："+dbuser.getRoot());

        if(password.equals(dbuser.getPassword())){
            request.getSession().setAttribute("user",username);
            request.getSession().setAttribute("root",root);
            model.addAttribute("loginUser",username);
            model.addAttribute("root",root);
            session.setAttribute("root", root);
            session.setAttribute("username", username);
            if (root.equals("学生")){
                System.out.println("学生登录");
                return "redirect:/student/"+username;}
            else {
                System.out.println("老师登录");
                return "redirect:/teacher";
            }
        }
        else
        {
            model.addAttribute("errorMsg", "ERROR");
        }
        return "index";
//        return "redirect:/teacher";
    }
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String getAllCourse(Model model, HttpSession session) {
        String username=(String) session.getAttribute("username");
        model.addAttribute("loginUser",username);
        return "manager_index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginwait()
    {
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request)
    {
        //销毁session对象
        request.getSession().invalidate();
        return "delete_success";
    }
    @RequestMapping(value = "/register" ,method = {RequestMethod.POST})
    public String register(user user,Model model)
    {

        user user1=userService.getUserByNameAndRoot(user.getUsername(), user.getRoot());
        if(null == user1 || StringUtils.isEmpty(user1.getUsername())){
            userService.InsertNewUser(user.getUsername(),user.getPassword(),user.getRoot());
        }
        System.out.println(user);
if(user.getRoot().equals("学生")){
    studentService.addNew(user.getUsername(),user.getUsername());
List<String> list=studentService.CheckStudentIdByName(user.getUsername());
    int lastIndex = list.size() - 1;
    String lastElement = list.get(lastIndex);
    model.addAttribute("exitUser",lastElement);
    return "index";
}model.addAttribute("exitUser",user.getUsername());
        return "index";
    }
    @RequestMapping(value = "/register" ,method = {RequestMethod.GET})
    public void registerget(Model model)
    {List<String> list=userService.getAllUser();
model.addAttribute("list",list);
    }
}
