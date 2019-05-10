package com.zeus.zeusblog.controller;

import com.zeus.zeusblog.pojo.User;
import com.zeus.zeusblog.service.UserSeriviceImpl;
import com.zeus.zeusblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zeus
 * @Description 用户登陆相关
 * @Date 10:46 2019/4/15
 **/
@Controller
@RequestMapping("/logincontro")
public class LoginController {

    @Autowired
    UserService userService = new UserSeriviceImpl();

    /**
     * @Author zeus
     * @Description 账户密码登陆
     * @Date 10:46 2019/4/15
     * @Param [request, loginname, password]
     * @return java.lang.String
     **/
    @ResponseBody
    @RequestMapping("/jug")
    public User login(HttpServletRequest request
                        ,@RequestParam("loginname") String loginname
                        ,@RequestParam("password") String password) {
        User user = new User();
        try {
            //调用用户服务获取登陆用户
            user = userService.getUserByLogin(loginname,password);
        }catch (EmptyResultDataAccessException e){
            //捕获返回对象为空异常
            System.out.println("该用户不存在!");
            return user;
        }catch (Exception e){
            e.printStackTrace();
        }

        //获取当前用户时间并格式化后存入当前用户数据库
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式,此处无需时分秒
        String dataNow = df.format(date);
        try {
            userService.updateLatestTime(user, dataNow);
        }catch (Exception e){
            e.printStackTrace();
        }

        //用户积分统计
        try {
            userService.updateLevelAndScore(user);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //将登陆用户信息存入session中,重定向至主页
        request.getSession().setAttribute("userInfo",user);
        return user;
    }

    /**
     * @Author zeus
     * @Description 用户登出
     * @Date 16:35 2019/4/19
     * @Param [request]
     * @return java.lang.String
     **/
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        User user = null;
        //从session中除去userinfo
        user = (User) request.getSession().getAttribute("userInfo");

        if (user != null){
            //从当前会话中除去当前登陆用户信息
            request.getSession().removeAttribute("userInfo");
        }

        return "redirect:/zeusblog/index";
    }




}
